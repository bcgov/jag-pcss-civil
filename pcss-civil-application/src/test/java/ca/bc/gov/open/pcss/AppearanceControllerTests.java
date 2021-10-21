package ca.bc.gov.open.pcss;

import static org.mockito.Mockito.when;

import ca.bc.gov.open.pcss.controllers.AppearanceController;
import ca.bc.gov.open.pcss.exceptions.BadDateException;
import ca.bc.gov.open.pcss.one.*;
import ca.bc.gov.open.pcss.three.*;
import ca.bc.gov.open.pcss.three.GetAppearanceCivilApprMethodRequest;
import ca.bc.gov.open.pcss.three.GetAppearanceCivilDocumentRequest;
import ca.bc.gov.open.pcss.three.GetAppearanceCivilPartyRequest;
import ca.bc.gov.open.pcss.three.GetAppearanceCivilRequest;
import ca.bc.gov.open.pcss.three.GetAppearanceCivilResourceRequest;
import ca.bc.gov.open.pcss.three.SetAppearanceCivilRequest;
import ca.bc.gov.open.pcss.three.SetAppearanceMethodCivilRequest;
import ca.bc.gov.open.pcss.three.SetCounselDetailCivilRequest;
import ca.bc.gov.open.pcss.two.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Instant;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AppearanceControllerTests {

    private AppearanceController appearanceController;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Mock private RestTemplate restTemplate = new RestTemplate();

    @Test
    public void getAppearanceCivilTest() throws JsonProcessingException, BadDateException {
        appearanceController = new AppearanceController(restTemplate, objectMapper);

        GetAppearanceCivil ac = new GetAppearanceCivil();
        GetAppearanceCivilRequest one = new GetAppearanceCivilRequest();
        var two = new ca.bc.gov.open.pcss.one.GetAppearanceCivilRequest();

        two.setRequestAgencyIdentifierId("A");
        two.setRequestPartId("A");
        two.setRequestDtm(Instant.now());
        two.setPhysicalFileId("A");
        two.setFutureYN(YesNoType.Y);
        two.setHistoryYN(YesNoType.Y);

        one.setGetAppearanceCivilRequest(two);

        ac.setGetAppearanceCivilRequest(one);

        var out = new ca.bc.gov.open.pcss.one.GetAppearanceCivilResponse();
        out.setResponseCd("A");
        out.setResponseMessageTxt("A");
        out.setFutureRecCount("A");
        out.setHistoryRecCount("A");
        ApprDetail dt = new ApprDetail();
        dt.setHistoryYN(YesNoType.Y);
        dt.setAppearanceId("A");
        dt.setAppearanceDt("A");
        dt.setAppearanceTm("A");
        dt.setAppearanceReasonCd("A");
        dt.setCourtAgencyId("A");
        dt.setCourtRoomCd("A");
        dt.setJudgeFullNm("A");
        dt.setJudgeInitials("A");
        dt.setEstimatedTimeHour("A");
        dt.setEstimatedTimeMin("A");
        dt.setPartOfTrialYN(YesNoType.Y);
        dt.setAppearanceStatusCd(AppearanceStatusType.CNCL);
        dt.setAppearanceResultCd("A");
        dt.setAppearanceCcn("A");
        dt.setDocumentTypeCd("A");
        dt.setDocumentRecCount("A");
        dt.setSupplementalEquipmentTxt("A");
        dt.setOutOfTownJudgeTxt("A");
        out.setApprDetail(Collections.singletonList(dt));
        ResponseEntity<ca.bc.gov.open.pcss.one.GetAppearanceCivilResponse> responseEntity =
                new ResponseEntity<>(out, HttpStatus.OK);

        //     Set up to mock ords response
        when(restTemplate.exchange(
                        Mockito.any(String.class),
                        Mockito.eq(HttpMethod.GET),
                        Mockito.<HttpEntity<String>>any(),
                        Mockito.<Class<ca.bc.gov.open.pcss.one.GetAppearanceCivilResponse>>any()))
                .thenReturn(responseEntity);

        var res = appearanceController.getAppearanceCivil(ac);

        assert res != null;
    }

    @Test
    public void getAppearanceCivilApprMethod() throws JsonProcessingException, BadDateException {
        appearanceController = new AppearanceController(restTemplate, objectMapper);

        var cam = new GetAppearanceCivilApprMethod();
        var one = new GetAppearanceCivilApprMethodRequest();
        var two = new ca.bc.gov.open.pcss.one.GetAppearanceCivilApprMethodRequest();
        two.setRequestAgencyIdentifierId("A");
        two.setRequestPartId("A");
        two.setRequestDtm(Instant.now());
        two.setAppearanceId("A");
        one.setGetAppearanceCivilApprMethodRequest(two);
        cam.setGetAppearanceCivilApprMethodRequest(one);

        var out = new ca.bc.gov.open.pcss.one.GetAppearanceCivilApprMethodResponse();
        out.setResponseCd("A");
        out.setResponseMessageTxt("A");
        AppearanceMethod am = new AppearanceMethod();
        am.setApprMethodCcn("A");
        am.setAppearanceMethodCd("A");
        am.setRoleTypeCd("A");

        out.setAppearanceMethod(Collections.singletonList(am));

        ResponseEntity<ca.bc.gov.open.pcss.one.GetAppearanceCivilApprMethodResponse>
                responseEntity = new ResponseEntity<>(out, HttpStatus.OK);

        //     Set up to mock ords response
        when(restTemplate.exchange(
                        Mockito.any(String.class),
                        Mockito.eq(HttpMethod.GET),
                        Mockito.<HttpEntity<String>>any(),
                        Mockito
                                .<Class<
                                                ca.bc.gov.open.pcss.one
                                                        .GetAppearanceCivilApprMethodResponse>>
                                        any()))
                .thenReturn(responseEntity);

        var resp = appearanceController.getAppearanceCivilApprMethod(cam);

        assert resp != null;
    }

    @Test
    public void setAppearanceMethodCivilTest() throws JsonProcessingException, BadDateException {
        appearanceController = new AppearanceController(restTemplate, objectMapper);

        var sam = new SetAppearanceMethodCivil();
        var one = new SetAppearanceMethodCivilRequest();
        var two = new ca.bc.gov.open.pcss.one.SetAppearanceMethodCivilRequest();
        two.setRequestAgencyIdentifierId("A");
        two.setRequestPartId("A");
        two.setRequestDtm(Instant.now());
        Detail3 dt = new Detail3();
        dt.setOperationModeCd(OperationModeType.ADD);
        dt.setAppearanceId("A");
        dt.setOldRoleTypeCd("A");
        dt.setOldAppearanceMethodCd("A");
        dt.setApprMethodCcn("A");
        dt.setNewRoleTypeCd("A");
        dt.setNewAppearanceMethodCd("A");
        one.setSetAppearanceMethodCivilRequest(two);
        sam.setSetAppearanceMethodCivilRequest(one);

        var out = new ca.bc.gov.open.pcss.one.SetAppearanceMethodCivilResponse();
        out.setResponseCd("A");
        out.setResponseMessageTxt("A");

        ResponseEntity<ca.bc.gov.open.pcss.one.SetAppearanceMethodCivilResponse> responseEntity =
                new ResponseEntity<>(out, HttpStatus.OK);

        //     Set up to mock ords response
        when(restTemplate.exchange(
                        Mockito.any(String.class),
                        Mockito.eq(HttpMethod.POST),
                        Mockito.<HttpEntity<String>>any(),
                        Mockito
                                .<Class<ca.bc.gov.open.pcss.one.SetAppearanceMethodCivilResponse>>
                                        any()))
                .thenReturn(responseEntity);

        var resp = appearanceController.setAppearanceMethodCivil(sam);

        assert resp != null;
    }

    @Test
    public void setAppearanceCivilTest() throws JsonProcessingException {
        appearanceController = new AppearanceController(restTemplate, objectMapper);
        var sca = new SetAppearanceCivil();
        var one = new SetAppearanceCivilRequest();
        var two = new ca.bc.gov.open.pcss.one.SetAppearanceCivilRequest();

        Detail dt = new Detail();
        dt.setOperationMode(OperationModeType.ADD);
        dt.setPcssAppearanceId("A");
        dt.setPhysicalFileId("A");
        dt.setAppearanceDt("A");
        dt.setAppearanceTm("A");
        dt.setCourtAgencyId("A");
        dt.setCourtRoomCd("A");
        dt.setEstimatedTimeHour("A");
        dt.setEstimatedTimeMin("A");
        dt.setSupplementalEquipmentTxt("A");
        dt.setSecurityRestrictionTxt("A");
        dt.setOutOfTownJudgeTxt("A");
        dt.setAppearanceId("A");
        dt.setAppearanceCcn("A");

        Document2 doc = new Document2();
        doc.setOperationMode(OperationModeType.ADD);
        doc.setCivilDocumentId("A");
        doc.setAppearanceReasonCd("A");
        doc.setEstimatedTimeHour("A");
        doc.setEstimatedTimeMin("A");

        var party = new Party2();
        party.setPartyId("A");
        party.setOperationMode(OperationModeType.ADD);

        dt.setParty(Collections.singletonList(party));
        dt.setDocument(Collections.singletonList(doc));

        two.setDetail(Collections.singletonList(dt));
        two.setRequestDtm(Instant.now());
        two.setRequestPartId("A");
        two.setRequestAgencyIdentifierId("A");

        two.setDetail(Collections.singletonList(dt));
        one.setSetAppearanceCivilRequest(two);
        sca.setSetAppearanceCivilRequest(one);

        var out = new ca.bc.gov.open.pcss.one.SetAppearanceCivilResponse();
        var dt2 = new Detail2();
        dt2.setAppearanceCcn("A");
        dt2.setAppearanceCcn("A");
        dt2.setPcssAppearanceId("A");
        dt2.setAppearanceId("A");

        out.setResponseCd("A");
        out.setResponseMessageTxt("A");
        out.setDetail(Collections.singletonList(dt2));

        ResponseEntity<ca.bc.gov.open.pcss.one.SetAppearanceCivilResponse> responseEntity =
                new ResponseEntity<>(out, HttpStatus.OK);

        //     Set up to mock ords response
        when(restTemplate.exchange(
                        Mockito.any(String.class),
                        Mockito.eq(HttpMethod.POST),
                        Mockito.<HttpEntity<String>>any(),
                        Mockito.<Class<ca.bc.gov.open.pcss.one.SetAppearanceCivilResponse>>any()))
                .thenReturn(responseEntity);

        var resp = appearanceController.setAppearanceCivil(sca);

        assert resp != null;
    }

    @Test
    public void getAppearanceCivilDocumentTest() throws JsonProcessingException {
        appearanceController = new AppearanceController(restTemplate, objectMapper);
        var cd = new GetAppearanceCivilDocument();
        var one = new GetAppearanceCivilDocumentRequest();
        var two = new ca.bc.gov.open.pcss.one.GetAppearanceCivilDocumentRequest();
        two.setRequestAgencyIdentifierId("A");
        two.setAppearanceId("A");
        two.setRequestPartId("A");
        two.setRequestDtm(Instant.now());
        one.setGetAppearanceCivilDocumentRequest(two);
        cd.setGetAppearanceCivilDocumentRequest(one);

        var out = new ca.bc.gov.open.pcss.one.GetAppearanceCivilDocumentResponse();
        out.setResponseCd("A");
        out.setResponseCd("A");
        Document doc = new Document();
        doc.setDocumentId("A");
        doc.setFileSeqNo("A");
        doc.setDocumentTypeCd("A");
        doc.setAppearanceReasonCd("A");
        doc.setAppearanceResultCd("A");
        doc.setFiledDt("A");
        doc.setDocumentHearingCcn("A");
        Issue is = new Issue();
        is.setIssueDsc("A");
        is.setIssueNumber("A");
        is.setIssueResultCd("A");
        is.setIssueResultCd("A");

        doc.setIssue(Collections.singletonList(is));
        out.setDocument(Collections.singletonList(doc));

        ResponseEntity<ca.bc.gov.open.pcss.one.GetAppearanceCivilDocumentResponse> responseEntity =
                new ResponseEntity<>(out, HttpStatus.OK);

        //     Set up to mock ords response
        when(restTemplate.exchange(
                        Mockito.any(String.class),
                        Mockito.eq(HttpMethod.GET),
                        Mockito.<HttpEntity<String>>any(),
                        Mockito
                                .<Class<ca.bc.gov.open.pcss.one.GetAppearanceCivilDocumentResponse>>
                                        any()))
                .thenReturn(responseEntity);

        var resp = appearanceController.getAppearanceCivilDocument(cd);

        assert resp != null;
    }

    @Test
    public void getAppearanceCivilPartyTest() throws JsonProcessingException {
        appearanceController = new AppearanceController(restTemplate, objectMapper);

        var acp = new GetAppearanceCivilParty();
        var one = new GetAppearanceCivilPartyRequest();
        var two = new ca.bc.gov.open.pcss.one.GetAppearanceCivilPartyRequest();
        two.setRequestAgencyIdentifierId("A");
        two.setAppearanceId("A");
        two.setRequestPartId("A");
        two.setRequestDtm(Instant.now());
        one.setGetAppearanceCivilPartyRequest(two);
        acp.setGetAppearanceCivilPartyRequest(one);

        var out = new ca.bc.gov.open.pcss.one.GetAppearanceCivilPartyResponse();

        out.setResponseMessageTxt("A");
        out.setResponseCd("A");
        Party party = new Party();
        party.setPartyId("A");
        party.setCounselNm("A");
        party.setCourtParticipantCcn("A");
        party.setPartyRoleTypeCd("A");
        party.setLastNm("A");
        party.setOrgNm("A");
        party.setCourtParticipantId("A");
        party.setGivenNm("A");

        out.setParty(Collections.singletonList(party));

        ResponseEntity<ca.bc.gov.open.pcss.one.GetAppearanceCivilPartyResponse> responseEntity =
                new ResponseEntity<>(out, HttpStatus.OK);

        //     Set up to mock ords response
        when(restTemplate.exchange(
                        Mockito.any(String.class),
                        Mockito.eq(HttpMethod.GET),
                        Mockito.<HttpEntity<String>>any(),
                        Mockito
                                .<Class<ca.bc.gov.open.pcss.one.GetAppearanceCivilPartyResponse>>
                                        any()))
                .thenReturn(responseEntity);

        var resp = appearanceController.getAppearanceCivilParty(acp);
        assert resp != null;
    }

    @Test
    public void getAppearanceCivilResourceTest() throws JsonProcessingException {
        appearanceController = new AppearanceController(restTemplate, objectMapper);
        var cr = new GetAppearanceCivilResource();
        var one = new GetAppearanceCivilResourceRequest();
        var two = new ca.bc.gov.open.pcss.one.GetAppearanceCivilResourceRequest();
        two.setRequestAgencyIdentifierId("A");
        two.setAppearanceId("A");
        two.setRequestPartId("A");
        two.setRequestDtm(Instant.now());

        one.setGetAppearanceCivilResourceRequest(two);
        cr.setGetAppearanceCivilResourceRequest(one);

        var out = new ca.bc.gov.open.pcss.one.GetAppearanceCivilResourceResponse();
        out.setResponseCd("A");
        out.setResponseMessageTxt("A");
        var res = new Resource();

        res.setBookingId("A");
        res.setBookingSeqNo("A");
        res.setAssetTypeCd("A");
        res.setAssetUsageRuleCd(AssetUsageRuleType.FIX);
        res.setBookedForRoleCd("A");
        res.setCourtAgencyId("A");
        res.setCourtRoomCd("A");
        res.setResourceNm("A");
        res.setResourceId("A");
        res.setBookedDt("A");
        res.setBookedFromTm("A");
        res.setBookedByNm("A");
        res.setBookingCommentTxt("A");
        res.setBookingCcn("A");
        out.setResource(Collections.singletonList(res));

        ResponseEntity<ca.bc.gov.open.pcss.one.GetAppearanceCivilResourceResponse> responseEntity =
                new ResponseEntity<>(out, HttpStatus.OK);

        //     Set up to mock ords response
        when(restTemplate.exchange(
                        Mockito.any(String.class),
                        Mockito.eq(HttpMethod.GET),
                        Mockito.<HttpEntity<String>>any(),
                        Mockito
                                .<Class<ca.bc.gov.open.pcss.one.GetAppearanceCivilResourceResponse>>
                                        any()))
                .thenReturn(responseEntity);

        var resp = appearanceController.getAppearanceCivilResource(cr);

        assert resp != null;
    }

    @Test
    public void setCounselDetailCivilTest() throws JsonProcessingException {
        appearanceController = new AppearanceController(restTemplate, objectMapper);
        var cd = new SetCounselDetailCivil();
        var one = new SetCounselDetailCivilRequest();
        var two = new ca.bc.gov.open.pcss.one.SetCounselDetailCivilRequest();
        two.setRequestAgencyIdentifierId("A");
        two.setRequestPartId("A");
        two.setRequestDtm(Instant.now());
        two.setCivilPartyId("A");
        two.setSelfRepresentedYn(YesNoType.Y);
        Detail4 dt = new Detail4();
        dt.setOperationModeCd(OperationMode2Type.ADD);
        dt.setCounselLastNm("A");
        dt.setCounselFirstNm("A");
        dt.setOfficePhoneNoTxt("A");
        two.setDetail(Collections.singletonList(dt));

        one.setSetCounselDetailCivilRequest(two);
        cd.setSetCounselDetailCivilRequest(one);

        var out = new ca.bc.gov.open.pcss.one.SetCounselDetailCivilResponse();
        out.setResponseCd("A");
        out.setResponseMessageTxt("A");

        ResponseEntity<ca.bc.gov.open.pcss.one.SetCounselDetailCivilResponse> responseEntity =
                new ResponseEntity<>(out, HttpStatus.OK);

        //     Set up to mock ords response
        when(restTemplate.exchange(
                        Mockito.any(String.class),
                        Mockito.eq(HttpMethod.POST),
                        Mockito.<HttpEntity<String>>any(),
                        Mockito
                                .<Class<ca.bc.gov.open.pcss.one.SetCounselDetailCivilResponse>>
                                        any()))
                .thenReturn(responseEntity);

        var resp = appearanceController.setCounselDetailCivil(cd);

        assert resp != null;
    }
}
