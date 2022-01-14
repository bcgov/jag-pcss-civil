package ca.bc.gov.open.pcss.exceptions;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(
        faultCode = FaultCode.CLIENT,
        faultStringOrReason = "Unparsable date must be of form yyyy-MM-dd hh:mm:ss.S")
public class BadDateException extends Exception {
    public BadDateException() {
        super();
    }
}
