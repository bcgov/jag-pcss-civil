package ca.bc.gov.open.pcss.pcsswebservice.civil;

import ca.bc.gov.courts.xml.ns.pcss.civil.v1.GetAppearanceCivilApprMethodRequest;
import ca.bc.gov.courts.xml.ns.pcss.civil.v1.GetAppearanceCivilApprMethodResponse2;
import ca.bc.gov.open.pcss.ords.pcss.client.api.PcssCivilApi;
import ca.bc.gov.open.pcss.ords.pcss.client.api.handler.ApiException;
import ca.bc.gov.open.pcss.ords.pcss.client.api.model.SearchFileAppearanceMethodData;
import ca.bc.gov.open.pcss.ords.pcss.client.api.model.SearchFileAppearanceMethodResponse;
import ca.bc.gov.open.pcss.ords.pcss.client.civil.CivilService;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("GetAppearanceCivil soap operation test suite")
public class EndpointGetAppearanceCivilApprMethodTest {


    private static final String APPEARANCEMETHODCCN = "Appearancemethodccn";
    private static final String APPEARANCEMETHODCD = "Appearancemethodcd";
    private static final String ROLE_TYPE_CD = "RoleTypeCd";


    private PcssCivilEndpoint sut;

    @Mock
    public PcssCivilApi pcssCivilApiMock;

    @Mock
    public CivilService civilServiceMock;

    @BeforeAll
    public void setUp() throws ApiException {

        MockitoAnnotations.initMocks(this);

        SearchFileAppearanceMethodResponse fakeResponse = getFakeResponse(1);
        Mockito.when(pcssCivilApiMock.civilSearchFileAppearanceMethodGet(Mockito.eq(TestHelpers.CASE_1))).thenReturn(fakeResponse);

        SearchFileAppearanceMethodResponse fakeMultiResponse = getFakeResponse(10);
        Mockito.when(pcssCivilApiMock.civilSearchFileAppearanceMethodGet(Mockito.eq(TestHelpers.CASE_2))).thenReturn(fakeMultiResponse);

        Mockito.when(pcssCivilApiMock.civilSearchFileAppearanceMethodGet(Mockito.eq(TestHelpers.CASE_3))).thenThrow(TestHelpers.DEFAULT_EXCEPTION);

        sut = new PcssCivilEndpoint(pcssCivilApiMock, civilServiceMock);

    }

    @DisplayName(TestHelpers.CASE_1 + ": When the PCSS api returns a single result.")
    @Test
    public void withValidParamsShouldReturnListOfResults() throws ApiException {
        TestSuccess(TestHelpers.CASE_1 , 1);
    }

    @DisplayName(TestHelpers.CASE_2 + ": When the PCSS api returns multiple result.")
    @Test
    public void withValidParamsShouldReturnMultipleOfResults() throws ApiException {
        TestSuccess(TestHelpers.CASE_2 , 10);
    }

    private void TestSuccess(String testCase, int expectedAmount) {
        GetAppearanceCivilApprMethodRequest request = buildRequest(testCase);

        GetAppearanceCivilApprMethodResponse2 actual = sut.getAppearanceCivilApprMethod(request);

        TestHelpers.validateSuccessResponse(
                actual.getGetAppearanceCivilApprMethodResponse().getResponseCd(),
                actual.getGetAppearanceCivilApprMethodResponse().getResponseMessageTxt());

        assertList(actual, expectedAmount);
    }


    @DisplayName(TestHelpers.CASE_3 + ": When the PCSS api throws an exception.")
    @Test
    public void withApiExceptionShouldErrorCode() throws ApiException {

        GetAppearanceCivilApprMethodRequest request = buildRequest(TestHelpers.CASE_3);

        GetAppearanceCivilApprMethodResponse2 actual = sut.getAppearanceCivilApprMethod(request);

        TestHelpers.validateErrorResponse(
                actual.getGetAppearanceCivilApprMethodResponse().getResponseCd(),
                actual.getGetAppearanceCivilApprMethodResponse().getResponseMessageTxt());
    }

    private GetAppearanceCivilApprMethodRequest buildRequest(String testCase) {
        GetAppearanceCivilApprMethodRequest request = new GetAppearanceCivilApprMethodRequest();

        ca.bc.gov.open.pcss.civil.GetAppearanceCivilApprMethodRequest request2 =
                new ca.bc.gov.open.pcss.civil.GetAppearanceCivilApprMethodRequest();

        request2.setAppearanceId(testCase);
        request2.setRequestAgencyIdentifierId(TestHelpers.REQUEST_AGENCY_IDENTIFIER_ID);
        request2.setRequestPartId(TestHelpers.REQUEST_PART_ID);
        request2.setRequestDtm(TestHelpers.REQUEST_DTM);

        request.setGetAppearanceCivilApprMethodRequest(request2);
        return request;
    }

    private void assertList(GetAppearanceCivilApprMethodResponse2 actual, int amount) {

        Assertions.assertEquals(amount, actual.getGetAppearanceCivilApprMethodResponse().getAppearanceMethod().size());

        actual.getGetAppearanceCivilApprMethodResponse().getAppearanceMethod().stream().forEach(actualItem -> {

            Assertions.assertEquals(APPEARANCEMETHODCCN, actualItem.getApprMethodCcn());
            Assertions.assertEquals(APPEARANCEMETHODCD, actualItem.getAppearanceMethodCd());
            Assertions.assertEquals(ROLE_TYPE_CD, actualItem.getRoleTypeCd());

        });

    }

    private SearchFileAppearanceMethodResponse getFakeResponse(int amount) {

        SearchFileAppearanceMethodResponse result = new SearchFileAppearanceMethodResponse();

        result.setResponseCd(BigDecimal.valueOf(Integer.valueOf(TestHelpers.SUCCESS_RESPONSE_CD)));
        result.setResponseMsg(TestHelpers.SUCCESS_RESPONSE_MSG);

        for (int i = 1; i <= amount; i++) {

            SearchFileAppearanceMethodData item = new SearchFileAppearanceMethodData();

            item.setAppearancemethodccn(APPEARANCEMETHODCCN);
            item.setAppearancemethodcd(APPEARANCEMETHODCD);
            item.setRoletypecd(ROLE_TYPE_CD);
            result.addDataItem(item);

        }

        return result;
    }

}
