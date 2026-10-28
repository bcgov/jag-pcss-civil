package ca.bc.gov.open.pcss.observability;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPElement;
import java.lang.reflect.Method;
import java.util.Locale;
import javax.xml.namespace.QName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ws.context.DefaultMessageContext;
import org.springframework.ws.server.endpoint.MethodEndpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.ws.soap.SoapVersion;
import org.springframework.ws.soap.saaj.SaajSoapMessage;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;

class SoapMetricsInterceptorTest {
    private SimpleMeterRegistry registry;
    private SoapMetricsInterceptor interceptor;
    private SaajSoapMessageFactory messageFactory;
    private MethodEndpoint endpoint;

    @BeforeEach
    void setUp() throws Exception {
        registry = new SimpleMeterRegistry();
        interceptor = new SoapMetricsInterceptor(registry);
        messageFactory = new SaajSoapMessageFactory();
        messageFactory.setSoapVersion(SoapVersion.SOAP_12);
        messageFactory.afterPropertiesSet();
        Method method = TestEndpoint.class.getDeclaredMethod("handle");
        endpoint = new MethodEndpoint(new TestEndpoint(), method);
    }

    @Test
    void recordsAnnotatedOperationCallerAndSuccess() throws Exception {
        DefaultMessageContext context = context(" pcss ");
        SaajSoapMessage response =
                (SaajSoapMessage) messageFactory.createWebServiceMessage();
        response.getSaajMessage()
                .getSOAPBody()
                .addChildElement(new QName("urn:test", "ResponseCd", "t"))
                .addTextNode("BUSINESS_ERROR");
        context.setResponse(response);
        interceptor.handleRequest(context, endpoint);
        interceptor.afterCompletion(context, endpoint, null);
        assertThat(timer("TestOperation", "PCSS", "SUCCESS")).isNotNull();
    }

    @Test
    void recordsSoapFaultAsError() throws Exception {
        DefaultMessageContext context = context("CCSS");
        SoapMessage response = (SoapMessage) messageFactory.createWebServiceMessage();
        response.getSoapBody().addServerOrReceiverFault("failed", Locale.ENGLISH);
        context.setResponse(response);
        interceptor.handleRequest(context, endpoint);
        interceptor.afterCompletion(context, endpoint, null);
        assertThat(timer("TestOperation", "CCSS", "ERROR")).isNotNull();
    }

    @Test
    void recordsCompletionExceptionAsError() throws Exception {
        DefaultMessageContext context = context("PCSS");
        interceptor.handleRequest(context, endpoint);
        interceptor.afterCompletion(context, endpoint, new IllegalStateException("failed"));
        assertThat(timer("TestOperation", "PCSS", "ERROR")).isNotNull();
    }

    @Test
    void usesBoundedCallerLabels() throws Exception {
        assertCaller(null, "UNSPECIFIED");
        assertCaller("   ", "UNSPECIFIED");
        assertCaller("another-system", "OTHER");
    }

    @Test
    void handlesRepeatedAndConflictingCodes() throws Exception {
        DefaultMessageContext repeated = context("PCSS", "pcss");
        repeated.setResponse(messageFactory.createWebServiceMessage());
        interceptor.handleRequest(repeated, endpoint);
        interceptor.afterCompletion(repeated, endpoint, null);
        assertThat(timer("TestOperation", "PCSS", "SUCCESS")).isNotNull();

        DefaultMessageContext conflicting = context("PCSS", "CCSS");
        conflicting.setResponse(messageFactory.createWebServiceMessage());
        interceptor.handleRequest(conflicting, endpoint);
        interceptor.afterCompletion(conflicting, endpoint, null);
        assertThat(timer("TestOperation", "UNSPECIFIED", "SUCCESS")).isNotNull();
    }

    @Test
    void metricsFailureDoesNotEscape() throws Exception {
        MeterRegistry failingRegistry = mock(MeterRegistry.class);
        when(failingRegistry.config()).thenThrow(new IllegalStateException("metrics unavailable"));
        SoapMetricsInterceptor failingInterceptor = new SoapMetricsInterceptor(failingRegistry);
        assertThatCode(() -> failingInterceptor.handleRequest(context("PCSS"), endpoint))
                .doesNotThrowAnyException();
    }

    @Test
    void unexpectedEndpointUsesUnknownOperation() throws Exception {
        DefaultMessageContext context = context("PCSS");
        context.setResponse(messageFactory.createWebServiceMessage());
        interceptor.handleRequest(context, new Object());
        interceptor.afterCompletion(context, new Object(), null);
        assertThat(timer("unknown", "PCSS", "SUCCESS")).isNotNull();
    }

    private void assertCaller(String code, String expected) throws Exception {
        DefaultMessageContext context = code == null ? context() : context(code);
        context.setResponse(messageFactory.createWebServiceMessage());
        interceptor.handleRequest(context, endpoint);
        interceptor.afterCompletion(context, endpoint, null);
        assertThat(timer("TestOperation", expected, "SUCCESS")).isNotNull();
    }

    private DefaultMessageContext context(String... applicationCodes) throws Exception {
        SaajSoapMessage request = (SaajSoapMessage) messageFactory.createWebServiceMessage();
        SOAPBody body = request.getSaajMessage().getSOAPBody();
        SOAPElement operation = body.addChildElement(new QName("urn:test", "request", "t"));
        for (String code : applicationCodes) {
            operation.addChildElement(new QName("urn:test", "ApplicationCd", "t"))
                    .addTextNode(code);
        }
        return new DefaultMessageContext(request, messageFactory);
    }

    private Timer timer(String operation, String caller, String outcome) {
        return registry.find(SoapMetricsInterceptor.METRIC_NAME)
                .tags("operation", operation, "caller_application", caller, "outcome", outcome)
                .timer();
    }

    private static final class TestEndpoint {
        @PayloadRoot(namespace = "urn:test", localPart = "TestOperation")
        public void handle() {}
    }
}
