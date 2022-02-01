package ca.bc.gov.open.pcss.civil.comparison.services;

import ca.bc.gov.open.pcss.civil.comparison.config.DualProtocolSaajSoapMessageFactory;
import ca.bc.gov.open.pcss.civil.comparison.config.WebServiceSenderWithAuth;
import javax.xml.soap.*;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.SoapVersion;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

@Service
public class TestService {
    private WebServiceTemplate webServiceTemplate = new WebServiceTemplate();

    private WebServiceSenderWithAuth webServiceSenderWithAuth;

    @Autowired
    public TestService(WebServiceSenderWithAuth webServiceSenderWithAuth) {
        this.webServiceSenderWithAuth = webServiceSenderWithAuth;
    }

    @Value("${host.api_host}")
    private String apiHost;

    @Value("${host.wm_host}")
    private String wmHost;

    @Value("${host.username}")
    private String username;

    @Value("${host.password}")
    private String password;

    public <T, G> boolean compare(T response, G request) throws SOAPException {

        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();

        MessageFactory messageFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);

        DualProtocolSaajSoapMessageFactory saajSoapMessageFactory =
                new DualProtocolSaajSoapMessageFactory();
        saajSoapMessageFactory.setSoapVersion(SoapVersion.SOAP_12);
        saajSoapMessageFactory.afterPropertiesSet();

        HttpComponentsMessageSender httpComponentsMessageSender = new HttpComponentsMessageSender();
        httpComponentsMessageSender.setCredentials(
                new UsernamePasswordCredentials(username, password));

        webServiceTemplate.setMessageSender(webServiceSenderWithAuth);
        webServiceTemplate.setMessageFactory(saajSoapMessageFactory);
        jaxb2Marshaller.setContextPaths("ca.bc.gov.open.pcss.one", "ca.bc.gov.open.pcss.three");
        webServiceTemplate.setMarshaller(jaxb2Marshaller);
        webServiceTemplate.setUnmarshaller(jaxb2Marshaller);
        webServiceTemplate.afterPropertiesSet();

        T resultObjectWM = (T) webServiceTemplate.marshalSendAndReceive(wmHost, request);
        T resultObjectAPI = (T) webServiceTemplate.marshalSendAndReceive(apiHost, request);

        Javers javers = JaversBuilder.javers().build();

        Diff diff = javers.compare(resultObjectAPI, resultObjectWM);
        diff.getChanges().prettyPrint();
        return false;
    }
}
