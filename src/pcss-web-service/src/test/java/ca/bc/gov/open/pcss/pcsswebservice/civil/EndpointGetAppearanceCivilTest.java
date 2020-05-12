package ca.bc.gov.open.pcss.pcsswebservice.civil;

import ca.bc.gov.courts.xml.ns.pcss.civil.v1.GetAppearanceCivilRequest;
import ca.bc.gov.courts.xml.ns.pcss.civil.v1.GetAppearanceCivilResponse2;
import ca.bc.gov.courts.xmlschema.pcss.common._1_0.AppearanceStatusType;
import ca.bc.gov.courts.xmlschema.pcss.common._1_0.YesNoType;
import ca.bc.gov.open.pcss.civil.ApprDetail;
import ca.bc.gov.open.pcss.ords.pcss.client.api.PcssApi;
import ca.bc.gov.open.pcss.ords.pcss.client.api.PcssCivilApi;
import ca.bc.gov.open.pcss.ords.pcss.client.api.handler.ApiException;
import ca.bc.gov.open.pcss.ords.pcss.client.api.model.SearchFileAppearanceData;
import ca.bc.gov.open.pcss.ords.pcss.client.api.model.SearchFileAppearanceResponse;
import ca.bc.gov.open.pcss.pcsswebservice.Keys;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("GetAppearanceCivil soap operation test suite")
public class EndpointGetAppearanceCivilTest {

    public static final String CASE_1 = "CASE_1";
    public static final String CASE_2 = "CASE_2";
    public static final String CASE_3 = "CASE_3";
    public static final String IDENTIFIER = "identifier";
    public static final String PARTY_ID = "PARTY_ID";
    public static final String APPEARANCECCN = "Appearanceccn";
    public static final String APPEARANCEDT = "Appearancedt";
    public static final String APPEARANCEREASONCD = "Appearancereasoncd";
    public static final String APPEARANCEID = String.valueOf(1);
    public static final String APPEARANCERESULTCD = "Appearanceresultcd";
    public static final AppearanceStatusType APPEARANCESTATUSCD = AppearanceStatusType.CNCL;
    public static final String APPEARANCETM = "Appearancetm";
    public static final String COURROOMCD = "Courroomcd";
    public static final String COURTAGENCYID = String.valueOf(2);
    public static final String DOCUMENTRECCOUNT = String.valueOf(3);
    public static final String DOCUMENTTYPECD = "Documenttypecd";
    public static final String ESTIMATEDTIMEHOUR = String.valueOf(4);
    public static final String ESTIMATEDTIMEMIN = String.valueOf(5);
    public static final YesNoType HISTORYYN = YesNoType.Y;
    public static final String JUDGEFULLNM = "Judgefullnm";
    public static final String JUDGEINITIALS = "Judgeinitials";
    public static final String OUTOFTOWNJUDGETXT = "Outoftownjudgetxt";
    public static final YesNoType PARTOFTRIALYN = YesNoType.N;
    public static final String SECURITYRESTRICTIONTXT = "Securityrestrictiontxt";
    public static final String SUPPLEMENTALEQUIPMENTTXT = "Supplementalequipmenttxt";
    public static final String RESPONSE_BODY = "body_content";
    public static final String FUTURE_RECORD_CNT = String.valueOf(5);
    public static final String HISTORY_RECORD_CNT = String.valueOf(6);
    public static final String RESPONSE_CD = String.valueOf(0);
    public static final String RESPONSE_MSG = "success";
    private PcssCivilEndpoint sut;

    @Mock
    public PcssCivilApi pcssCivilApiMock;

    @BeforeAll
    public void setUp() throws ApiException {

        MockitoAnnotations.initMocks(this);

        SearchFileAppearanceResponse fakeResponse = getFakeSearchFileAppearanceResponse(1);
        Mockito.when(pcssCivilApiMock.civilSearchFileAppearanceGet(Mockito.eq("Y"), Mockito.eq("N"), Mockito.eq(CASE_1))).thenReturn(fakeResponse);

        SearchFileAppearanceResponse fakeMultiResponse = getFakeSearchFileAppearanceResponse(10);
        Mockito.when(pcssCivilApiMock.civilSearchFileAppearanceGet(Mockito.eq("Y"), Mockito.eq("N"), Mockito.eq(CASE_2))).thenReturn(fakeMultiResponse);

        Mockito.when(pcssCivilApiMock.civilSearchFileAppearanceGet(Mockito.eq("Y"), Mockito.eq("N"), Mockito.eq(CASE_3))).thenThrow(new ApiException(400, null, null, RESPONSE_BODY));

        sut = new PcssCivilEndpoint(pcssCivilApiMock);

    }

    @DisplayName(CASE_1 + ": When the PCSS api returns a single result.")
    @Test
    public void withValidParamsShouldReturnListOfResults() throws ApiException {


        GetAppearanceCivilRequest request = new GetAppearanceCivilRequest();

        ca.bc.gov.open.pcss.civil.GetAppearanceCivilRequest request2 =
                new ca.bc.gov.open.pcss.civil.GetAppearanceCivilRequest();

        request2.setFutureYN(YesNoType.Y);
        request2.setHistoryYN(YesNoType.N);
        request2.setPhysicalFileId(CASE_1);
        request2.setRequestAgencyIdentifierId(IDENTIFIER);
        request2.setRequestPartId(PARTY_ID);

        request.setGetAppearanceCivilRequest(request2);

        GetAppearanceCivilResponse2 actual = sut.getAppearanceCivil(request);

        Assertions.assertEquals(FUTURE_RECORD_CNT, actual.getGetAppearanceCivilResponse().getFutureRecCount());
        Assertions.assertEquals(HISTORY_RECORD_CNT, actual.getGetAppearanceCivilResponse().getHistoryRecCount());
        Assertions.assertEquals(RESPONSE_CD, actual.getGetAppearanceCivilResponse().getResponseCd());
        Assertions.assertEquals(RESPONSE_MSG, actual.getGetAppearanceCivilResponse().getResponseMessageTxt());


        Assertions.assertEquals(1, actual.getGetAppearanceCivilResponse().getApprDetail().size());

        Optional<ApprDetail> actualApprDetail =
                actual.getGetAppearanceCivilResponse().getApprDetail().stream().findFirst();

        actualApprDetail.ifPresent(actualItem -> {
            Assertions.assertEquals(APPEARANCECCN + 1, actualItem.getAppearanceCcn());
            AssertApprDetail(actualItem);
        });
    }

    private void AssertApprDetail(ApprDetail actualItem) {
        Assertions.assertEquals(APPEARANCEDT, actualItem.getAppearanceDt());
        Assertions.assertEquals(APPEARANCEID, actualItem.getAppearanceId());
        Assertions.assertEquals(APPEARANCEREASONCD, actualItem.getAppearanceReasonCd());
        Assertions.assertEquals(APPEARANCERESULTCD, actualItem.getAppearanceResultCd());
        Assertions.assertEquals(APPEARANCESTATUSCD, actualItem.getAppearanceStatusCd());
        Assertions.assertEquals(APPEARANCETM, actualItem.getAppearanceTm());
        Assertions.assertEquals(COURROOMCD, actualItem.getCourtRoomCd());
        Assertions.assertEquals(COURTAGENCYID, actualItem.getCourtAgencyId());
        Assertions.assertEquals(DOCUMENTRECCOUNT, actualItem.getDocumentRecCount());
        Assertions.assertEquals(DOCUMENTTYPECD, actualItem.getDocumentTypeCd());
        Assertions.assertEquals(ESTIMATEDTIMEHOUR, actualItem.getEstimatedTimeHour());
        Assertions.assertEquals(ESTIMATEDTIMEMIN, actualItem.getEstimatedTimeMin());
        Assertions.assertEquals(HISTORYYN, actualItem.getHistoryYN());
        Assertions.assertEquals(JUDGEFULLNM, actualItem.getJudgeFullNm());
        Assertions.assertEquals(JUDGEINITIALS, actualItem.getJudgeInitials());
        Assertions.assertEquals(OUTOFTOWNJUDGETXT, actualItem.getOutOfTownJudgeTxt());
        Assertions.assertEquals(PARTOFTRIALYN, actualItem.getPartOfTrialYN());
        Assertions.assertEquals(SECURITYRESTRICTIONTXT, actualItem.getSecurityRestrictionTxt());
        Assertions.assertEquals(SUPPLEMENTALEQUIPMENTTXT, actualItem.getSupplementalEquipmentTxt());
    }

    @DisplayName(CASE_2 + ": When the PCSS api returns multiple result.")
    @Test
    public void withValidParamsShouldReturnMultipleOfResults() throws ApiException {


        GetAppearanceCivilRequest request = new GetAppearanceCivilRequest();

        ca.bc.gov.open.pcss.civil.GetAppearanceCivilRequest request2 =
                new ca.bc.gov.open.pcss.civil.GetAppearanceCivilRequest();

        request2.setFutureYN(YesNoType.Y);
        request2.setHistoryYN(YesNoType.N);
        request2.setPhysicalFileId(CASE_2);
        request2.setRequestAgencyIdentifierId(IDENTIFIER);
        request2.setRequestPartId(PARTY_ID);

        request.setGetAppearanceCivilRequest(request2);

        GetAppearanceCivilResponse2 actual = sut.getAppearanceCivil(request);

        Assertions.assertEquals(FUTURE_RECORD_CNT, actual.getGetAppearanceCivilResponse().getFutureRecCount());
        Assertions.assertEquals(HISTORY_RECORD_CNT, actual.getGetAppearanceCivilResponse().getHistoryRecCount());
        Assertions.assertEquals(RESPONSE_CD, actual.getGetAppearanceCivilResponse().getResponseCd());
        Assertions.assertEquals(RESPONSE_MSG, actual.getGetAppearanceCivilResponse().getResponseMessageTxt());


        Assertions.assertEquals(10, actual.getGetAppearanceCivilResponse().getApprDetail().size());

        Optional<ApprDetail> actualApprDetail =
                actual.getGetAppearanceCivilResponse().getApprDetail().stream().findFirst();

        actual.getGetAppearanceCivilResponse().getApprDetail().stream().forEach(actualItem -> AssertApprDetail(actualItem));
    }


    @DisplayName(CASE_3 + ": When the PCSS api throws an exception.")
    @Test
    public void withApiExceptionShouldErrorCode() throws ApiException {

        GetAppearanceCivilRequest request = new GetAppearanceCivilRequest();

        ca.bc.gov.open.pcss.civil.GetAppearanceCivilRequest request2 =
                new ca.bc.gov.open.pcss.civil.GetAppearanceCivilRequest();

        request2.setFutureYN(YesNoType.Y);
        request2.setHistoryYN(YesNoType.N);
        request2.setPhysicalFileId(CASE_3);
        request2.setRequestAgencyIdentifierId(IDENTIFIER);
        request2.setRequestPartId(PARTY_ID);

        request.setGetAppearanceCivilRequest(request2);

        GetAppearanceCivilResponse2 actual = sut.getAppearanceCivil(request);

        Assertions.assertEquals(Keys.DEFAULT_ERROR_RESPONSE_CD, actual.getGetAppearanceCivilResponse().getResponseCd());
        Assertions.assertEquals(RESPONSE_BODY, actual.getGetAppearanceCivilResponse().getResponseMessageTxt());


    }


    private SearchFileAppearanceResponse getFakeSearchFileAppearanceResponse(int amount) {

        if (amount <= 0) amount = 1;

        SearchFileAppearanceResponse result = new SearchFileAppearanceResponse();

        result.setFutureRecordCnt(BigDecimal.valueOf(Integer.valueOf(FUTURE_RECORD_CNT)));
        result.setHistoryRecordCnt(BigDecimal.valueOf(Integer.valueOf(HISTORY_RECORD_CNT)));
        result.setResponseCd(BigDecimal.valueOf(Integer.valueOf(RESPONSE_CD)));
        result.setResponseMsg(RESPONSE_MSG);

        List<SearchFileAppearanceData> data = new ArrayList<>();

        for (int i = 1; i <= amount; i++) {

            SearchFileAppearanceData item = new SearchFileAppearanceData();
            item.setAppearanceccn(APPEARANCECCN + i);
            item.setAppearancedt(APPEARANCEDT);
            item.setAppearanceid(BigDecimal.valueOf(Integer.valueOf(APPEARANCEID)));
            item.setAppearancereasoncd(APPEARANCEREASONCD);
            item.setAppearanceresultcd(APPEARANCERESULTCD);
            item.setAppearancestatuscd(APPEARANCESTATUSCD.value());
            item.setAppearancetm(APPEARANCETM);
            item.setCourroomcd(COURROOMCD);
            item.setCourtagencyid(BigDecimal.valueOf(Integer.valueOf(COURTAGENCYID)));
            item.setDocumentreccount(BigDecimal.valueOf(Integer.valueOf(DOCUMENTRECCOUNT)));
            item.setDocumenttypecd(DOCUMENTTYPECD);
            item.setEstimatedtimehour(BigDecimal.valueOf(Integer.valueOf(ESTIMATEDTIMEHOUR)));
            item.setEstimatedtimemin(BigDecimal.valueOf(Integer.valueOf(ESTIMATEDTIMEMIN)));
            item.setHistoryyn(HISTORYYN.value());
            item.setJudgefullnm(JUDGEFULLNM);
            item.setJudgeinitials(JUDGEINITIALS);
            item.setOutoftownjudgetxt(OUTOFTOWNJUDGETXT);
            item.setPartoftrialyn(PARTOFTRIALYN.value());
            item.setSecurityrestrictiontxt(SECURITYRESTRICTIONTXT);
            item.setSupplementalequipmenttxt(SUPPLEMENTALEQUIPMENTTXT);

            data.add(item);

            result.setData(data);

        }

        return result;
    }

}
