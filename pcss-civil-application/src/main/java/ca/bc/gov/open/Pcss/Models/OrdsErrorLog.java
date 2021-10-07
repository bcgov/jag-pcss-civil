package ca.bc.gov.open.Pcss.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrdsErrorLog {

    private String message;
    private String method;
    private Object request;
}
