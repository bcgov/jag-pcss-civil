package ca.bc.gov.open.pcss.observability;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import jakarta.xml.soap.SOAPException;
import java.lang.reflect.Method;
import java.util.Locale;
import org.springframework.stereotype.Component;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.server.endpoint.MethodEndpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.ws.soap.saaj.SaajSoapMessage;
import org.w3c.dom.NodeList;

@Component
public class SoapMetricsInterceptor implements EndpointInterceptor {
    static final String METRIC_NAME = "pcss.soap.server.requests";
    static final String UNSPECIFIED = "UNSPECIFIED";
    private static final String TIMER_STATE = SoapMetricsInterceptor.class.getName() + ".timerState";

    private final MeterRegistry meterRegistry;

    public SoapMetricsInterceptor(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @Override
    public boolean handleRequest(MessageContext messageContext, Object endpoint) {
        try {
            messageContext.setProperty(
                    TIMER_STATE,
                    new TimerState(
                            Timer.start(meterRegistry),
                            operation(endpoint),
                            callerApplication(messageContext)));
        } catch (RuntimeException ignored) {
            // Observability must never affect request processing.
        }
        return true;
    }

    @Override
    public boolean handleResponse(MessageContext messageContext, Object endpoint) {
        return true;
    }

    @Override
    public boolean handleFault(MessageContext messageContext, Object endpoint) {
        return true;
    }

    @Override
    public void afterCompletion(MessageContext messageContext, Object endpoint, Exception exception) {
        Object value = messageContext.getProperty(TIMER_STATE);
        if (!(value instanceof TimerState state)) {
            return;
        }
        try {
            String outcome = exception != null || hasSoapFault(messageContext) ? "ERROR" : "SUCCESS";
            state.sample()
                    .stop(
                            Timer.builder(METRIC_NAME)
                                    .description("SOAP server request duration")
                                    .tag("operation", state.operation())
                                    .tag("caller_application", state.callerApplication())
                                    .tag("outcome", outcome)
                                    .register(meterRegistry));
        } catch (RuntimeException ignored) {
            // Observability must never affect request processing.
        }
    }

    private String operation(Object endpoint) {
        if (!(endpoint instanceof MethodEndpoint methodEndpoint)) {
            return "unknown";
        }
        Method method = methodEndpoint.getMethod();
        PayloadRoot payloadRoot = method.getAnnotation(PayloadRoot.class);
        return payloadRoot == null ? method.getName() : payloadRoot.localPart();
    }

    private String callerApplication(MessageContext messageContext) {
        try {
            if (!(messageContext.getRequest() instanceof SaajSoapMessage request)) {
                return UNSPECIFIED;
            }
            NodeList values =
                    request.getSaajMessage()
                            .getSOAPBody()
                            .getElementsByTagNameNS("*", "ApplicationCd");
            String caller = null;
            String sourceValue = null;
            for (int index = 0; index < values.getLength(); index++) {
                String currentSource = canonicalSource(values.item(index).getTextContent());
                String current = normalize(currentSource);
                if (caller == null) {
                    caller = current;
                    sourceValue = currentSource;
                } else if (!sourceValue.equals(currentSource)) {
                    return UNSPECIFIED;
                }
            }
            return caller == null ? UNSPECIFIED : caller;
        } catch (RuntimeException | SOAPException ignored) {
            return UNSPECIFIED;
        }
    }

    private String normalize(String value) {
        if (value == null || value.isBlank()) {
            return UNSPECIFIED;
        }
        return switch (value.trim().toUpperCase(Locale.ROOT)) {
            case "PCSS" -> "PCSS";
            case "CCSS" -> "CCSS";
            default -> "OTHER";
        };
    }

    private String canonicalSource(String value) {
        return value == null ? "" : value.trim().toUpperCase(Locale.ROOT);
    }

    private boolean hasSoapFault(MessageContext messageContext) {
        return messageContext.hasResponse()
                && messageContext.getResponse() instanceof SoapMessage response
                && response.getSoapBody().hasFault();
    }

    private record TimerState(Timer.Sample sample, String operation, String callerApplication) {}
}
