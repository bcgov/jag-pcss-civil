package ca.bc.gov.open.pcss.pcsswebservice.civil;

import ca.bc.gov.courts.xml.ns.pcss.civil.v1.GetAppearanceCivilDocumentRequest;
import ca.bc.gov.courts.xml.ns.pcss.civil.v1.GetAppearanceCivilDocumentResponse2;
import ca.bc.gov.open.pcss.ords.pcss.client.api.PcssCivilApi;
import ca.bc.gov.open.pcss.ords.pcss.client.api.model.SearchFileAppearanceIssueData;
import ca.bc.gov.open.pcss.ords.pcss.client.civil.CivilService;
import ca.bc.gov.open.pcss.ords.pcss.client.civil.models.AppearanceDocumentData;
import ca.bc.gov.open.pcss.ords.pcss.client.civil.models.AppearanceDocumentResponse;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EndpointGetAppearanceCivilDocumentTest {


    public static final String APPEARANCEREASONCD = "Appearancereasoncd";
    public static final String DOCUMENT_ID = "DocumentId";
    public static final String DOCUMENTHEARINGCCN = "Documenthearingccn";
    public static final String APPEARANCERUSLTCD = "Appearancerusltcd";
    public static final String FILEDDT = "fileddt";
    public static final String FILESGNO = "Filesgno";
    public static final String DOCUMENTTYPECD = "Documenttypecd";
    public static final String ISSUERESULTCD = "Issueresultcd";
    public static final String ISSUENUMBER = "Issuenumber";
    public static final String ISSUE_DSC = "issueDsc";
    public PcssCivilEndpoint sut;

    @Mock
    public PcssCivilApi pcssCivilApiMock;

    @Mock
    public CivilService civilServiceMock;

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.initMocks(this);

        Mockito
                .when(civilServiceMock.getCivilSearchFileAppearanceDocument(Mockito.eq(TestHelpers.CASE_1)))
                .thenReturn(getFakeAppearanceDocumentResponse());

        Mockito
                .when(civilServiceMock.getCivilSearchFileAppearanceDocument(Mockito.eq(TestHelpers.CASE_2)))
                .thenReturn(getFakeErrorAppearanceDocumentResponse());

        sut = new PcssCivilEndpoint(pcssCivilApiMock, civilServiceMock);

    }

    @DisplayName(TestHelpers.CASE_1 + ": When the PCSS api returns a single result.")
    @Test
    public void withResultShouldReturnResult() {

        GetAppearanceCivilDocumentRequest request = new GetAppearanceCivilDocumentRequest();

        ca.bc.gov.open.pcss.civil.GetAppearanceCivilDocumentRequest request2 = new ca.bc.gov.open.pcss.civil.GetAppearanceCivilDocumentRequest();

        request2.setAppearanceId(TestHelpers.REQUEST_AGENCY_IDENTIFIER_ID);
        request2.setRequestAgencyIdentifierId(TestHelpers.REQUEST_AGENCY_IDENTIFIER_ID);

        request2.setAppearanceId(TestHelpers.CASE_1);

        request.setGetAppearanceCivilDocumentRequest(request2);

        GetAppearanceCivilDocumentResponse2 actual =  sut.getAppearanceCivilDocument(request);

        Assertions.assertEquals(TestHelpers.SUCCESS_RESPONSE_CD, actual.getGetAppearanceCivilDocumentResponse().getResponseCd());
        Assertions.assertEquals(TestHelpers.SUCCESS_RESPONSE_MSG, actual.getGetAppearanceCivilDocumentResponse().getResponseMessageTxt());
        Assertions.assertEquals(1, actual.getGetAppearanceCivilDocumentResponse().getDocument().size());

        actual.getGetAppearanceCivilDocumentResponse().getDocument().stream().forEach(document -> {
            Assertions.assertEquals(APPEARANCEREASONCD, document.getAppearanceReasonCd());
            Assertions.assertEquals(APPEARANCERUSLTCD, document.getAppearanceResultCd());
            Assertions.assertEquals(DOCUMENTTYPECD, document.getDocumentTypeCd());
            Assertions.assertEquals(DOCUMENTHEARINGCCN, document.getDocumentHearingCcn());
            Assertions.assertEquals(DOCUMENT_ID, document.getDocumentId());
            Assertions.assertEquals(FILEDDT, document.getFiledDt());
            Assertions.assertEquals(FILESGNO, document.getFileSeqNo());

            Assertions.assertEquals(1, document.getIssue().size());

            document.getIssue().stream().forEach(issue -> {
                Assertions.assertEquals(ISSUERESULTCD, issue.getIssueResultCd());
                Assertions.assertEquals(ISSUENUMBER, issue.getIssueNumber());
                Assertions.assertEquals(ISSUE_DSC, issue.getIssueDsc());
            });

        });

    }

    @DisplayName(TestHelpers.CASE_2 + ": When the PCSS api returns an error")
    @Test
    public void withErrorShouldReturnResult() {

        GetAppearanceCivilDocumentRequest request = new GetAppearanceCivilDocumentRequest();

        ca.bc.gov.open.pcss.civil.GetAppearanceCivilDocumentRequest request2 = new ca.bc.gov.open.pcss.civil.GetAppearanceCivilDocumentRequest();

        request2.setAppearanceId(TestHelpers.REQUEST_AGENCY_IDENTIFIER_ID);
        request2.setRequestAgencyIdentifierId(TestHelpers.REQUEST_AGENCY_IDENTIFIER_ID);

        request2.setAppearanceId(TestHelpers.CASE_2);

        request.setGetAppearanceCivilDocumentRequest(request2);

        GetAppearanceCivilDocumentResponse2 actual =  sut.getAppearanceCivilDocument(request);

        Assertions.assertEquals(TestHelpers.ERROR_RESPONSE_CD, actual.getGetAppearanceCivilDocumentResponse().getResponseCd());
        Assertions.assertEquals(TestHelpers.ERROR_RESPONSE_MSG, actual.getGetAppearanceCivilDocumentResponse().getResponseMessageTxt());
        Assertions.assertEquals(0, actual.getGetAppearanceCivilDocumentResponse().getDocument().size());


    }


    private AppearanceDocumentResponse getFakeAppearanceDocumentResponse() {

        AppearanceDocumentResponse response = new AppearanceDocumentResponse();

        response.setResponseCd(BigDecimal.valueOf(Integer.valueOf(TestHelpers.SUCCESS_RESPONSE_CD)));
        response.setResponseMsg(TestHelpers.SUCCESS_RESPONSE_MSG);

        AppearanceDocumentData data = new AppearanceDocumentData();

        data.setAppearancereasoncd(APPEARANCEREASONCD);
        data.setAppearancerusltcd(APPEARANCERUSLTCD);
        data.setDocumenthearingccn(DOCUMENTHEARINGCCN);
        data.setDocumenttypecd(DOCUMENTTYPECD);
        data.setDocumentid(DOCUMENT_ID);
        data.setFileddt(FILEDDT);
        data.setFilesegno(FILESGNO);

        SearchFileAppearanceIssueData item = new SearchFileAppearanceIssueData();

        item.setIssueresultcd(ISSUERESULTCD);
        item.setIssuenumber(ISSUENUMBER);
        item.setIssuedsc(ISSUE_DSC);

        data.getSearchFileAppearanceIssueDataList().add(item);

        response.getData().add(data);


        return response;

    }

    private AppearanceDocumentResponse getFakeErrorAppearanceDocumentResponse() {

        AppearanceDocumentResponse response = new AppearanceDocumentResponse();

        response.setResponseCd(BigDecimal.valueOf(Integer.valueOf(TestHelpers.ERROR_RESPONSE_CD)));
        response.setResponseMsg(TestHelpers.ERROR_RESPONSE_MSG);

        return response;

    }



}
