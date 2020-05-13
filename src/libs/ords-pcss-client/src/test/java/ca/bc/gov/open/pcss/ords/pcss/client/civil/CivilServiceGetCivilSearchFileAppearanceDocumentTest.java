package ca.bc.gov.open.pcss.ords.pcss.client.civil;

import ca.bc.gov.open.pcss.ords.pcss.client.api.PcssCivilApi;
import ca.bc.gov.open.pcss.ords.pcss.client.api.handler.ApiException;
import ca.bc.gov.open.pcss.ords.pcss.client.api.model.SearchFileAppearanceDocumentData;
import ca.bc.gov.open.pcss.ords.pcss.client.api.model.SearchFileAppearanceDocumentResponse;
import ca.bc.gov.open.pcss.ords.pcss.client.api.model.SearchFileAppearanceIssueData;
import ca.bc.gov.open.pcss.ords.pcss.client.api.model.SearchFileAppearanceIssueResponse;
import ca.bc.gov.open.pcss.ords.pcss.client.civil.models.AppearanceDocumentResponse;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

@DisplayName("CivilService: gvilSearchFileAppearanceDocument test suite")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CivilServiceGetCivilSearchFileAppearanceDocumentTest {

    private static final String SUCCESS_RESPONSE_MSG = "success";
    private static final BigDecimal SUCCESS_RESPONSE_CD = BigDecimal.valueOf(0);
    private static final String ERROR_RESPONSE_MSG = "error";
    public static final BigDecimal ERROR_RESPONSE_CD = BigDecimal.valueOf(-1);

    private static final String APPEARANCEREASONCD = "Appearancereasoncd";
    private static final String APPEARANCERUSLTCD = "Appearancerusltcd";
    private static final String DOCUMENTHEARINGCCN = "Documenthearingccn";
    private static final String DOCUMENTID = "Documentid";
    private static final String DOCUMENTTYPECD = "Documenttypecd";
    private static final String FILEDDT = "Fileddt";
    private static final String FILESEGNO = "Filesegno";
    private static final String ISSUEDSC = "Issuedsc";
    private static final String ISSUENUMBER = "Issuenumber";
    private static final String ISSUERESULTCD = "Issueresultcd";

    private CivilServiceImpl sut;

    @Mock
    private PcssCivilApi pcssCivilApiMock;

    @BeforeEach
    public void setUp() throws ApiException {

        MockitoAnnotations.initMocks(this);

        Mockito.when(pcssCivilApiMock
                .civilSearchFileAppearanceDocumentGet(Mockito.eq(TestHelpers.CASE_4)))
                .thenThrow(TestHelpers.DEFAULT_EXCEPTION);

        Mockito.when(pcssCivilApiMock
                .civilSearchFileAppearanceDocumentGet(Mockito.eq(TestHelpers.CASE_5)))
                .thenReturn(getFakeSearchFileAppearanceDocumentResponse(1));

        Mockito.when(pcssCivilApiMock
                .civilSearchFileAppearanceIssueGet(Mockito.eq(TestHelpers.CASE_5), Mockito.anyString()))
                .thenThrow(TestHelpers.DEFAULT_EXCEPTION);

        sut = new CivilServiceImpl(pcssCivilApiMock);

    }

    @DisplayName(TestHelpers.CASE_1 + ": When api return 1 document")
    @Test
    public void withOneDocumentShouldReturnResult() throws ApiException {
        testSuccess(TestHelpers.CASE_1, 1, 1, true);
    }

    @DisplayName(TestHelpers.CASE_2 + ": When api return 100 document")
    @Test
    public void withMultipleDocumentShouldReturnResult() throws ApiException {
        testSuccess(TestHelpers.CASE_2, 100, 100, true);
    }

    @DisplayName(TestHelpers.CASE_3 + ": When api return 1 document, and error on issue")
    @Test
    public void withErrorIssueShouldReturnResult() throws ApiException {
        testSuccess(TestHelpers.CASE_2, 100, 100, false);
    }

    @DisplayName(TestHelpers.CASE_4 + ": When api error")
    @Test
    public void withApiExceptionShouldReturnResult() throws ApiException {

        AppearanceDocumentResponse actual = sut.getCivilSearchFileAppearanceDocument(TestHelpers.CASE_4);

        Assertions.assertEquals(ERROR_RESPONSE_CD, actual.getResponseCd());
        Assertions.assertEquals(TestHelpers.RESPONSE_BODY, actual.getResponseMsg());
    }

    @DisplayName(TestHelpers.CASE_5 + ": When api return 1 document, and error on issue")
    @Test
    public void withApiExceptionShouldReturnResultWithIssue() throws ApiException {

        AppearanceDocumentResponse actual = sut.getCivilSearchFileAppearanceDocument(TestHelpers.CASE_5);

        Assertions.assertEquals(SUCCESS_RESPONSE_CD, actual.getResponseCd());
        Assertions.assertEquals(SUCCESS_RESPONSE_MSG, actual.getResponseMsg());

        Assertions.assertEquals(1, actual.getData().size());

        actual.getData().stream().forEach(item -> {

            Assertions.assertEquals(APPEARANCEREASONCD, item.getAppearancereasoncd());
            Assertions.assertEquals(APPEARANCERUSLTCD, item.getAppearancerusltcd());
            Assertions.assertEquals(DOCUMENTHEARINGCCN, item.getDocumenthearingccn());
            Assertions.assertEquals(DOCUMENTID, item.getDocumentid());
            Assertions.assertEquals(DOCUMENTTYPECD, item.getDocumenttypecd());
            Assertions.assertEquals(FILEDDT, item.getFileddt());
            Assertions.assertEquals(FILESEGNO, item.getFilesegno());
            Assertions.assertEquals(0, item.getSearchFileAppearanceIssueDataList().size());

        });

    }


    private void testSuccess(String testCase, int documentCount, int issueCount, boolean isIssueSuccess) throws ApiException {


        Mockito.when(pcssCivilApiMock
                .civilSearchFileAppearanceDocumentGet(Mockito.eq(testCase)))
                .thenReturn(getFakeSearchFileAppearanceDocumentResponse(documentCount));

        Mockito.when(pcssCivilApiMock.civilSearchFileAppearanceIssueGet(Mockito.eq(testCase), Mockito.anyString()))
                .thenReturn(getFakeSearchFileAppearanceIssueResponse(issueCount, isIssueSuccess));

        AppearanceDocumentResponse actual = sut.getCivilSearchFileAppearanceDocument(testCase);

        Assertions.assertEquals(SUCCESS_RESPONSE_CD, actual.getResponseCd());
        Assertions.assertEquals(SUCCESS_RESPONSE_MSG, actual.getResponseMsg());

        Assertions.assertEquals(documentCount, actual.getData().size());

        actual.getData().stream().forEach(item -> {

            Assertions.assertEquals(APPEARANCEREASONCD, item.getAppearancereasoncd());
            Assertions.assertEquals(APPEARANCERUSLTCD, item.getAppearancerusltcd());
            Assertions.assertEquals(DOCUMENTHEARINGCCN, item.getDocumenthearingccn());
            Assertions.assertEquals(DOCUMENTID, item.getDocumentid());
            Assertions.assertEquals(DOCUMENTTYPECD, item.getDocumenttypecd());
            Assertions.assertEquals(FILEDDT, item.getFileddt());
            Assertions.assertEquals(FILESEGNO, item.getFilesegno());

            if(isIssueSuccess) {

                Assertions.assertEquals(issueCount, item.getSearchFileAppearanceIssueDataList().size());

                item.getSearchFileAppearanceIssueDataList().stream().forEach(issueItem -> {

                    Assertions.assertEquals(ISSUEDSC, issueItem.getIssuedsc());
                    Assertions.assertEquals(ISSUENUMBER, issueItem.getIssuenumber());
                    Assertions.assertEquals(ISSUERESULTCD, issueItem.getIssueresultcd());

                });
            } else {
                Assertions.assertEquals(0, item.getSearchFileAppearanceIssueDataList().size());
            }

        });
    }

    private SearchFileAppearanceIssueResponse getFakeSearchFileAppearanceIssueResponse(int amount,
                                                                                       boolean isIssueSuccess) {
        SearchFileAppearanceIssueResponse result = new SearchFileAppearanceIssueResponse();

        result.setResponseCd(isIssueSuccess ? SUCCESS_RESPONSE_CD : ERROR_RESPONSE_CD);

        result.setResponseMsg(isIssueSuccess ? SUCCESS_RESPONSE_MSG : ERROR_RESPONSE_MSG);

        for(int i =0; i <  amount; i++) {


            SearchFileAppearanceIssueData item = new SearchFileAppearanceIssueData();

            item.setIssuedsc(ISSUEDSC);
            item.setIssuenumber(ISSUENUMBER);
            item.setIssueresultcd(ISSUERESULTCD);
            result.addDataItem(item);

        }

        return result;

    }

    private SearchFileAppearanceDocumentResponse getFakeSearchFileAppearanceDocumentResponse(int documentAmount) {

        SearchFileAppearanceDocumentResponse result = new SearchFileAppearanceDocumentResponse();

        result.setResponseCd(SUCCESS_RESPONSE_CD);
        result.setResponseMsg(SUCCESS_RESPONSE_MSG);

        for(int i =0; i < documentAmount; i++) {
            SearchFileAppearanceDocumentData item = new SearchFileAppearanceDocumentData();
            item.setAppearancereasoncd(APPEARANCEREASONCD);
            item.setAppearancerusltcd(APPEARANCERUSLTCD);
            item.setDocumenthearingccn(DOCUMENTHEARINGCCN);
            item.setDocumentid(DOCUMENTID);
            item.setDocumenttypecd(DOCUMENTTYPECD);
            item.setFileddt(FILEDDT);
            item.setFilesegno(FILESEGNO);
            result.addDataItem(item);
        }

        return result;
    }


}
