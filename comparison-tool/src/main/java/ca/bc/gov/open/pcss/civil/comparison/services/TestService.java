package ca.bc.gov.open.pcss.civil.comparison.services;

import ca.bc.gov.open.pcss.civil.comparison.config.WebServiceSenderWithAuth;
import ca.bc.gov.open.pcss.three.GetFileDetailCivil;
import com.sun.xml.messaging.saaj.util.ByteOutputStream;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.SoapHeader;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.ws.soap.SoapVersion;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.soap.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;


@Service
public class TestService {
    private RestTemplate restTemplate = new RestTemplate();
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

    public <T, G> boolean compare(T response, G request)
            throws JAXBException, SOAPException, IOException {
        GetFileDetailCivil getFileDetailCivil;

        UriComponentsBuilder builder =
                UriComponentsBuilder.fromHttpUrl(apiHost);

        JAXBContext jaxbContext = JAXBContext.newInstance(request.getClass());
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        StringWriter stringWriter = new StringWriter();

        MessageFactory messageFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();
        SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
        SOAPBody soapBody = soapEnvelope.getBody();
        SaajSoapMessageFactory saajSoapMessageFactory = new SaajSoapMessageFactory(messageFactory);
        saajSoapMessageFactory.afterPropertiesSet();

        // jaxb2Marshaller.marshal(request, soapBody);
        ByteOutputStream byteOutputStream = new ByteOutputStream();
        soapMessage.writeTo(byteOutputStream);

//        HttpHeaders headers = new HttpHeaders();
//        String auth = username + ":" + password;
//        headers.setBasicAuth(username, password);
//
//        headers.setContentType(new MediaType("application", "soap+xml"));
//        HttpEntity<String> payload = new HttpEntity<>(
//                new String(byteOutputStream.getBytes()), headers);

//        ResponseEntity<String> responseApi = restTemplate.postForEntity(
//                                                            builder.toUriString(),
//                                                            payload,
//                                                            String.class
//                                                            );

        StreamSource streamSource = new StreamSource(new StringReader(new String(byteOutputStream.toString())));
        StreamResult streamResult = new StreamResult(stringWriter);

        HttpComponentsMessageSender httpComponentsMessageSender = new HttpComponentsMessageSender();
        httpComponentsMessageSender.setCredentials(new UsernamePasswordCredentials(username, password));

        webServiceTemplate.setMessageSender(webServiceSenderWithAuth);
//        webServiceTemplate.sendSourceAndReceiveToResult(builder.toUriString(),
//                                                      streamSource,
//                                                      streamResult
//                                                      );
        jaxb2Marshaller.setContextPaths("ca.bc.gov.open.pcss.one", "ca.bc.gov.open.pcss.three");
        webServiceTemplate.setMarshaller(jaxb2Marshaller);
        webServiceTemplate.afterPropertiesSet();
        Object resultObject = webServiceTemplate.marshalSendAndReceive(builder.toUriString(), request);

        return false;
    }
}
