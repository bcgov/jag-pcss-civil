package ca.bc.gov.open.pcss.Exceptions;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(
        faultCode = FaultCode.CLIENT,
        faultStringOrReason =
                "Unparsable date must be of form dd-MMM-yy hh.mm.ss.SSSSSS a or dd-MMM-yy")
public class BadDateException extends Exception {
    public BadDateException() {
        super();
    }
}
