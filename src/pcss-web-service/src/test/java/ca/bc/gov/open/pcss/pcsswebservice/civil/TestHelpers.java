package ca.bc.gov.open.pcss.pcsswebservice.civil;

import ca.bc.gov.open.pcss.ords.pcss.client.api.handler.ApiException;
import ca.bc.gov.open.pcss.pcsswebservice.Keys;
import org.junit.jupiter.api.Assertions;

public class TestHelpers {




    private TestHelpers() {}

    public static final String CASE_1 = "CASE_1";
    public static final String CASE_2 = "CASE_2";
    public static final String CASE_3 = "CASE_3";
    public static final String SUCCESS_RESPONSE_CD = String.valueOf(0);
    public static final String ERROR_RESPONSE_CD = String.valueOf(-1);
    public static final String SUCCESS_RESPONSE_MSG = "success";
    public static final String ERROR_RESPONSE_MSG = "error";
    public static final String RESPONSE_BODY = "body_content";
    public static final String REQUEST_AGENCY_IDENTIFIER_ID = "RequestAgencyIdentifierId";
    public static final String REQUEST_PART_ID = "RequestPartId";
    public static final String REQUEST_DTM = "RequestDtm";

    public static ApiException DEFAULT_EXCEPTION = new ApiException(400, null, null, TestHelpers.RESPONSE_BODY);


    public static void validateSuccessResponse(String responseCode, String responseMsg) {
        Assertions.assertEquals(SUCCESS_RESPONSE_CD, responseCode);
        Assertions.assertEquals(SUCCESS_RESPONSE_MSG, responseMsg);
    }

    public static void validateErrorResponse(String responseCode, String responseMsg) {
        Assertions.assertEquals(Keys.DEFAULT_ERROR_RESPONSE_CD, responseCode);
        Assertions.assertEquals(TestHelpers.RESPONSE_BODY, responseMsg);
    }

}
