package ca.bc.gov.open.pcss.civil.comparison.config;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
import javax.xml.soap.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.ws.InvalidXmlException;
import org.springframework.ws.soap.SoapMessageCreationException;
import org.springframework.ws.soap.SoapMessageFactory;
import org.springframework.ws.soap.SoapVersion;
import org.springframework.ws.soap.saaj.SaajSoapMessage;
import org.springframework.ws.soap.saaj.support.SaajUtils;
import org.springframework.ws.transport.TransportInputStream;
import org.xml.sax.SAXParseException;

public class DualProtocolSaajSoapMessageFactory implements SoapMessageFactory, InitializingBean {
    private MessageFactory messageFactory;
    private String messageFactoryProtocol;
    private boolean langAttributeOnSoap11FaultString = true;
    private Map<String, ?> messageProperties;
    MessageFactory messageFactory11;
    MessageFactory messageFactory12;

    public DualProtocolSaajSoapMessageFactory() {
        super();

        try {
            messageFactory11 = MessageFactory.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL);
            messageFactory12 = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);
        } catch (Exception ex) {
            throw new SoapMessageCreationException(
                    "Could not create SAAJ MessageFactory: " + ex.getMessage(), ex);
        }
    }

    public void setSoapVersion(SoapVersion version) {
        if (SaajUtils.getSaajVersion() >= 2) {
            if (SoapVersion.SOAP_11 == version) {
                this.messageFactoryProtocol = "SOAP 1.1 Protocol";
            } else {
                if (SoapVersion.SOAP_12 != version) {
                    throw new IllegalArgumentException(
                            "Invalid version ["
                                    + version
                                    + "]. Expected the SOAP_11 or SOAP_12 constant");
                }

                this.messageFactoryProtocol = "SOAP 1.2 Protocol";
            }
        } else if (SoapVersion.SOAP_11 != version) {
            throw new IllegalArgumentException("SAAJ 1.1 and 1.2 only support SOAP 1.1");
        }
    }

    public void afterPropertiesSet() {
        if (this.messageFactory == null) {
            try {
                if (SaajUtils.getSaajVersion() >= 2) {
                    if (!StringUtils.hasLength(this.messageFactoryProtocol)) {
                        this.messageFactoryProtocol = "SOAP 1.1 Protocol";
                    }

                    this.messageFactory = MessageFactory.newInstance(this.messageFactoryProtocol);
                } else if (SaajUtils.getSaajVersion() == 1) {

                    this.messageFactory = MessageFactory.newInstance();
                } else {
                    if (SaajUtils.getSaajVersion() != 0) {
                        throw new IllegalStateException(
                                "SaajSoapMessageFactory requires SAAJ 1.1, which was not found on the classpath");
                    }

                    this.messageFactory = MessageFactory.newInstance();
                }
            } catch (NoSuchMethodError var2) {
                throw new SoapMessageCreationException(
                        "Could not create SAAJ MessageFactory. Is the version of the SAAJ specification interfaces ["
                                + SaajUtils.getSaajVersionString()
                                + "] the same as the version supported by the application server?",
                        var2);
            } catch (SOAPException var3) {
                throw new SoapMessageCreationException(
                        "Could not create SAAJ MessageFactory: " + var3.getMessage(), var3);
            }
        }
    }

    public SaajSoapMessage createWebServiceMessage() {
        try {
            SOAPMessage saajMessage = this.messageFactory.createMessage();
            this.postProcess(saajMessage);
            return new SaajSoapMessage(
                    saajMessage, this.langAttributeOnSoap11FaultString, this.messageFactory);
        } catch (SOAPException var2) {
            throw new SoapMessageCreationException(
                    "Could not create empty message: " + var2.getMessage(), var2);
        }
    }
    /*Override default property oenter code heref createWebServiceMessage*/
    public SaajSoapMessage createWebServiceMessage(InputStream inputStream) throws IOException {
        MimeHeaders mimeHeaders = this.parseMimeHeaders(inputStream);
        try {
            inputStream = checkForUtf8ByteOrderMark(inputStream);
            SOAPMessage saajMessage = messageFactory12.createMessage(mimeHeaders, inputStream);
            saajMessage.getSOAPPart().getEnvelope();
            this.postProcess(saajMessage);
            return new SaajSoapMessage(
                    saajMessage, this.langAttributeOnSoap11FaultString, this.messageFactory);
        } catch (SOAPException var7) {
            String contentType =
                    StringUtils.arrayToCommaDelimitedString(mimeHeaders.getHeader("Content-Type"));
            if (contentType.contains("startinfo")) {
                contentType = contentType.replace("startinfo", "start-info");
                mimeHeaders.setHeader("Content-Type", contentType);

                try {
                    SOAPMessage saajMessage =
                            this.messageFactory.createMessage(mimeHeaders, inputStream);
                    this.postProcess(saajMessage);
                    return new SaajSoapMessage(saajMessage, this.langAttributeOnSoap11FaultString);
                } catch (SOAPException var6) {
                }
            }

            SAXParseException parseException = this.getSAXParseException(var7);
            if (parseException != null) {
                throw new InvalidXmlException("Could not parse XML", parseException);
            } else {
                throw new SoapMessageCreationException(
                        "Could not create message from InputStream: " + var7.getMessage(), var7);
            }
        }
    }

    private SAXParseException getSAXParseException(Throwable ex) {
        if (ex instanceof SAXParseException) {
            return (SAXParseException) ex;
        } else {
            return ex.getCause() != null ? this.getSAXParseException(ex.getCause()) : null;
        }
    }

    private MimeHeaders parseMimeHeaders(InputStream inputStream) throws IOException {
        MimeHeaders mimeHeaders = new MimeHeaders();
        if (inputStream instanceof TransportInputStream) {
            TransportInputStream transportInputStream = (TransportInputStream) inputStream;
            Iterator headerNames = transportInputStream.getHeaderNames();

            while (headerNames.hasNext()) {
                String headerName = (String) headerNames.next();
                Iterator headerValues = transportInputStream.getHeaders(headerName);

                while (headerValues.hasNext()) {
                    String headerValue = (String) headerValues.next();
                    StringTokenizer tokenizer = new StringTokenizer(headerValue, ",");

                    while (tokenizer.hasMoreTokens()) {
                        mimeHeaders.addHeader(headerName, tokenizer.nextToken().trim());
                    }
                }
            }
        }

        return mimeHeaders;
    }

    private InputStream checkForUtf8ByteOrderMark(InputStream inputStream) throws IOException {
        PushbackInputStream pushbackInputStream =
                new PushbackInputStream(new BufferedInputStream(inputStream), 3);
        byte[] bytes = new byte[3];

        int bytesRead;
        int n;
        for (bytesRead = 0; bytesRead < bytes.length; bytesRead += n) {
            n = pushbackInputStream.read(bytes, bytesRead, bytes.length - bytesRead);
            if (n <= 0) {
                break;
            }
        }

        if (bytesRead > 0 && !this.isByteOrderMark(bytes)) {
            pushbackInputStream.unread(bytes, 0, bytesRead);
        }

        return pushbackInputStream;
    }

    private boolean isByteOrderMark(byte[] bytes) {
        return bytes.length == 3 && bytes[0] == -17 && bytes[1] == -69 && bytes[2] == -65;
    }

    protected void postProcess(SOAPMessage soapMessage) throws SOAPException {
        if (!CollectionUtils.isEmpty(this.messageProperties)) {
            Iterator var2 = this.messageProperties.entrySet().iterator();

            while (var2.hasNext()) {
                Map.Entry<String, ?> entry = (Map.Entry) var2.next();
                soapMessage.setProperty((String) entry.getKey(), entry.getValue());
            }
        }

        if ("SOAP 1.1 Protocol".equals(this.messageFactoryProtocol)) {
            MimeHeaders headers = soapMessage.getMimeHeaders();
            if (ObjectUtils.isEmpty(headers.getHeader("SOAPAction"))) {
                headers.addHeader("SOAPAction", "\"\"");
            }
        }
    }

    public String toString() {
        StringBuilder builder = new StringBuilder("SaajSoapMessageFactory[");
        builder.append(SaajUtils.getSaajVersionString());
        if (SaajUtils.getSaajVersion() >= 2) {
            builder.append(',');
            builder.append(this.messageFactoryProtocol);
        }

        builder.append(']');
        return builder.toString();
    }
}
