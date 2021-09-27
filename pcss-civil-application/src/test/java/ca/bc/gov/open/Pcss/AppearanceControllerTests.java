package ca.bc.gov.open.Pcss;

import static org.mockito.Mockito.when;

import ca.bc.gov.open.Pcss.Controllers.AppearanceController;
import com.example.demp.wsdl.pcss.one.*;
import com.example.demp.wsdl.pcss.three.*;
import com.example.demp.wsdl.pcss.three.GetAppearanceCivilApprMethodRequest;
import com.example.demp.wsdl.pcss.three.GetAppearanceCivilDocumentRequest;
import com.example.demp.wsdl.pcss.three.GetAppearanceCivilPartyRequest;
import com.example.demp.wsdl.pcss.three.GetAppearanceCivilRequest;
import com.example.demp.wsdl.pcss.three.GetAppearanceCivilResourceRequest;
import com.example.demp.wsdl.pcss.three.SetAppearanceCivilRequest;
import com.example.demp.wsdl.pcss.three.SetAppearanceMethodCivilRequest;
import com.example.demp.wsdl.pcss.three.SetCounselDetailCivilRequest;
import com.example.demp.wsdl.pcss.two.*;
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

    @Mock private RestTemplate restTemplate = new RestTemplate();

    @Test
    public void getAppearanceCivilTest() {
        appearanceController = new AppearanceController(restTemplate);

        GetAppearanceCivil ac = new GetAppearanceCivil();
        GetAppearanceCivilRequest one = new GetAppearanceCivilRequest();
        var two = new com.example.demp.wsdl.pcss.one.GetAppearanceCivilRequest();

        two.setRequestAgencyIdentifierId("A");
        two.setRequestPartId("A");
        two.setRequestDtm("A");
        two.setPhysicalFileId("A");
        two.setFutureYN(YesNoType.Y);
        two.setHistoryYN(YesNoType.Y);

        one.setGetAppearanceCivilRequest(two);

        ac.setGetAppearanceCivilRequest(one);

        var out = new com.example.demp.wsdl.pcss.one.GetAppearanceCivilResponse();
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
        ResponseEntity<com.example.demp.wsdl.pcss.one.GetAppearanceCivilResponse> responseEntity =
                new ResponseEntity<>(out, HttpStatus.OK);

        //     Set up to mock ords response
        when(restTemplate.exchange(
                        Mockito.any(String.class),
                        Mockito.eq(HttpMethod.GET),
                        Mockito.<HttpEntity<String>>any(),
                        Mockito
                                .<Class<com.example.demp.wsdl.pcss.one.GetAppearanceCivilResponse>>
                                        any()))
                .thenReturn(responseEntity);

        var res = appearanceController.getAppearanceCivil(ac);

        assert res != null;
    }

    @Test
    public void getAppearanceCivilApprMethod() {
        appearanceController = new AppearanceController(restTemplate);

        var cam = new GetAppearanceCivilApprMethod();
        var one = new GetAppearanceCivilApprMethodRequest();
        var two = new com.example.demp.wsdl.pcss.one.GetAppearanceCivilApprMethodRequest();
        two.setRequestAgencyIdentifierId("A");
        two.setRequestPartId("A");
        two.setRequestDtm("A");
        two.setAppearanceId("A");
        one.setGetAppearanceCivilApprMethodRequest(two);
        cam.setGetAppearanceCivilApprMethodRequest(one);

        var out = new com.example.demp.wsdl.pcss.one.GetAppearanceCivilApprMethodResponse();
        out.setResponseCd("A");
        out.setResponseMessageTxt("A");
        AppearanceMethod am = new AppearanceMethod();
        am.setApprMethodCcn("A");
        am.setAppearanceMethodCd("A");
        am.setRoleTypeCd("A");

        out.setAppearanceMethod(Collections.singletonList(am));

        ResponseEntity<com.example.demp.wsdl.pcss.one.GetAppearanceCivilApprMethodResponse>
                responseEntity = new ResponseEntity<>(out, HttpStatus.OK);

        //     Set up to mock ords response
        when(restTemplate.exchange(
                        Mockito.any(String.class),
                        Mockito.eq(HttpMethod.GET),
                        Mockito.<HttpEntity<String>>any(),
                        Mockito
                                .<Class<
                                                com.example.demp.wsdl.pcss.one
                                                        .GetAppearanceCivilApprMethodResponse>>
                                        any()))
                .thenReturn(responseEntity);

        var resp = appearanceController.getAppearanceCivilApprMethod(cam);

        assert resp != null;
    }

    @Test
    public void setAppearanceMethodCivilTest() {
        appearanceController = new AppearanceController(restTemplate);

        var sam = new SetAppearanceMethodCivil();
        var one = new SetAppearanceMethodCivilRequest();
        var two = new com.example.demp.wsdl.pcss.one.SetAppearanceMethodCivilRequest();
        two.setRequestAgencyIdentifierId("A");
        two.setRequestPartId("A");
        two.setRequestDtm("A");
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

        var out = new com.example.demp.wsdl.pcss.one.SetAppearanceMethodCivilResponse();
        out.setResponseCd("A");
        out.setResponseMessageTxt("A");

        ResponseEntity<com.example.demp.wsdl.pcss.one.SetAppearanceMethodCivilResponse>
                responseEntity = new ResponseEntity<>(out, HttpStatus.OK);

        //     Set up to mock ords response
        when(restTemplate.exchange(
                        Mockito.any(String.class),
                        Mockito.eq(HttpMethod.POST),
                        Mockito.<HttpEntity<String>>any(),
                        Mockito
                                .<Class<
                                                com.example.demp.wsdl.pcss.one
                                                        .SetAppearanceMethodCivilResponse>>
                                        any()))
                .thenReturn(responseEntity);

        var resp = appearanceController.setAppearanceMethodCivil(sam);

        assert resp != null;
    }

    @Test
    public void setAppearanceCivilTest() {
        appearanceController = new AppearanceController(restTemplate);
        var sca = new SetAppearanceCivil();
        var one = new SetAppearanceCivilRequest();
        var two = new com.example.demp.wsdl.pcss.one.SetAppearanceCivilRequest();

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
        two.setRequestDtm("A");
        two.setRequestPartId("A");
        two.setRequestAgencyIdentifierId("A");

        two.setDetail(Collections.singletonList(dt));
        one.setSetAppearanceCivilRequest(two);
        sca.setSetAppearanceCivilRequest(one);

        var out = new com.example.demp.wsdl.pcss.one.SetAppearanceCivilResponse();
        var dt2 = new Detail2();
        dt2.setAppearanceCcn("A");
        dt2.setAppearanceCcn("A");
        dt2.setPcssAppearanceId("A");
        dt2.setAppearanceId("A");

        out.setResponseCd("A");
        out.setResponseMessageTxt("A");
        out.setDetail(Collections.singletonList(dt2));

        ResponseEntity<com.example.demp.wsdl.pcss.one.SetAppearanceCivilResponse> responseEntity =
                new ResponseEntity<>(out, HttpStatus.OK);

        //     Set up to mock ords response
        when(restTemplate.exchange(
                        Mockito.any(String.class),
                        Mockito.eq(HttpMethod.POST),
                        Mockito.<HttpEntity<String>>any(),
                        Mockito
                                .<Class<com.example.demp.wsdl.pcss.one.SetAppearanceCivilResponse>>
                                        any()))
                .thenReturn(responseEntity);

        var resp = appearanceController.setAppearanceCivil(sca);

        assert resp != null;
    }

    @Test
    public void getAppearanceCivilDocumentTest() {
        appearanceController = new AppearanceController(restTemplate);
        var cd = new GetAppearanceCivilDocument();
        var one = new GetAppearanceCivilDocumentRequest();
        var two = new com.example.demp.wsdl.pcss.one.GetAppearanceCivilDocumentRequest();
        two.setRequestAgencyIdentifierId("A");
        two.setAppearanceId("A");
        two.setRequestPartId("A");
        two.setRequestDtm("A");
        one.setGetAppearanceCivilDocumentRequest(two);
        cd.setGetAppearanceCivilDocumentRequest(one);

        var out = new com.example.demp.wsdl.pcss.one.GetAppearanceCivilDocumentResponse();
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

        ResponseEntity<com.example.demp.wsdl.pcss.one.GetAppearanceCivilDocumentResponse>
                responseEntity = new ResponseEntity<>(out, HttpStatus.OK);

        //     Set up to mock ords response
        when(restTemplate.exchange(
                        Mockito.any(String.class),
                        Mockito.eq(HttpMethod.GET),
                        Mockito.<HttpEntity<String>>any(),
                        Mockito
                                .<Class<
                                                com.example.demp.wsdl.pcss.one
                                                        .GetAppearanceCivilDocumentResponse>>
                                        any()))
                .thenReturn(responseEntity);

        var resp = appearanceController.getAppearanceCivilDocument(cd);

        assert resp != null;
    }

    @Test
    public void getAppearanceCivilPartyTest() {
        appearanceController = new AppearanceController(restTemplate);

        var acp = new GetAppearanceCivilParty();
        var one = new GetAppearanceCivilPartyRequest();
        var two = new com.example.demp.wsdl.pcss.one.GetAppearanceCivilPartyRequest();
        two.setRequestAgencyIdentifierId("A");
        two.setAppearanceId("A");
        two.setRequestPartId("A");
        two.setRequestDtm("A");
        one.setGetAppearanceCivilPartyRequest(two);
        acp.setGetAppearanceCivilPartyRequest(one);

        var out = new com.example.demp.wsdl.pcss.one.GetAppearanceCivilPartyResponse();

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

        ResponseEntity<com.example.demp.wsdl.pcss.one.GetAppearanceCivilPartyResponse>
                responseEntity = new ResponseEntity<>(out, HttpStatus.OK);

        //     Set up to mock ords response
        when(restTemplate.exchange(
                        Mockito.any(String.class),
                        Mockito.eq(HttpMethod.GET),
                        Mockito.<HttpEntity<String>>any(),
                        Mockito
                                .<Class<
                                                com.example.demp.wsdl.pcss.one
                                                        .GetAppearanceCivilPartyResponse>>
                                        any()))
                .thenReturn(responseEntity);

        var resp = appearanceController.getAppearanceCivilParty(acp);
        assert resp != null;
    }

    @Test
    public void getAppearanceCivilResourceTest() {
        appearanceController = new AppearanceController(restTemplate);
        var cr = new GetAppearanceCivilResource();
        var one = new GetAppearanceCivilResourceRequest();
        var two = new com.example.demp.wsdl.pcss.one.GetAppearanceCivilResourceRequest();
        two.setRequestAgencyIdentifierId("A");
        two.setAppearanceId("A");
        two.setRequestPartId("A");
        two.setRequestDtm("A");

        one.setGetAppearanceCivilResourceRequest(two);
        cr.setGetAppearanceCivilResourceRequest(one);

        var out = new com.example.demp.wsdl.pcss.one.GetAppearanceCivilResourceResponse();
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

        ResponseEntity<com.example.demp.wsdl.pcss.one.GetAppearanceCivilResourceResponse>
                responseEntity = new ResponseEntity<>(out, HttpStatus.OK);

        //     Set up to mock ords response
        when(restTemplate.exchange(
                        Mockito.any(String.class),
                        Mockito.eq(HttpMethod.GET),
                        Mockito.<HttpEntity<String>>any(),
                        Mockito
                                .<Class<
                                                com.example.demp.wsdl.pcss.one
                                                        .GetAppearanceCivilResourceResponse>>
                                        any()))
                .thenReturn(responseEntity);

        var resp = appearanceController.getAppearanceCivilResource(cr);

        assert resp != null;
    }

    @Test
    public void setCounselDetailCivilTest() {
        appearanceController = new AppearanceController(restTemplate);
        var cd = new SetCounselDetailCivil();
        var one = new SetCounselDetailCivilRequest();
        var two = new com.example.demp.wsdl.pcss.one.SetCounselDetailCivilRequest();
        two.setRequestAgencyIdentifierId("A");
        two.setRequestPartId("A");
        two.setRequestDtm("A");
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

        var out = new com.example.demp.wsdl.pcss.one.SetCounselDetailCivilResponse();
        out.setResponseCd("A");
        out.setResponseMessageTxt("A");

        ResponseEntity<com.example.demp.wsdl.pcss.one.SetCounselDetailCivilResponse>
                responseEntity = new ResponseEntity<>(out, HttpStatus.OK);

        //     Set up to mock ords response
        when(restTemplate.exchange(
                        Mockito.any(String.class),
                        Mockito.eq(HttpMethod.POST),
                        Mockito.<HttpEntity<String>>any(),
                        Mockito
                                .<Class<
                                                com.example.demp.wsdl.pcss.one
                                                        .SetCounselDetailCivilResponse>>
                                        any()))
                .thenReturn(responseEntity);

        var resp = appearanceController.setCounselDetailCivil(cd);

        assert resp != null;
    }
}
