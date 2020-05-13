package ca.bc.gov.open.pcss.ords.pcss.client.civil;

import ca.bc.gov.open.pcss.ords.pcss.client.api.handler.ApiException;

import java.math.BigDecimal;

public class TestHelpers {

    public static final String CASE_1 = "CASE_1";
    public static final String CASE_2 = "CASE_2";
    public static final String CASE_3 = "CASE_3";
    public static final String CASE_4 = "CASE_4";
    public static final String CASE_5 = "CASE_5";

    public static final BigDecimal SUCCESS_RESPONSE_CD =  BigDecimal.valueOf(0);
    public static final BigDecimal DEFAULT_ERROR_RESPONSE_CD =  BigDecimal.valueOf(-1);
    public static final String SUCCESS_RESPONSE_MSG = "SUCCESS";


    public static final String RESPONSE_BODY = "exception";
    public static final ApiException DEFAULT_EXCEPTION = new ApiException(400, null, null, RESPONSE_BODY);


}
