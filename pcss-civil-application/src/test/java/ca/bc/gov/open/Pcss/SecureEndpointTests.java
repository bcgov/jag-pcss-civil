package ca.bc.gov.open.Pcss;

import static org.mockito.Mockito.when;

import ca.bc.gov.open.Pcss.Controllers.SecureEndpointController;
import com.example.demp.wsdl.pcss.secure.one.*;
import com.example.demp.wsdl.pcss.secure.three.*;
import com.example.demp.wsdl.pcss.secure.two.*;
import com.example.demp.wsdl.pcss.secure.two.GetAppearanceCivilApprMethodSecureRequest;
import com.example.demp.wsdl.pcss.secure.two.GetAppearanceCivilPartySecureRequest;
import com.example.demp.wsdl.pcss.secure.two.GetAppearanceCivilSecureRequest;
import com.example.demp.wsdl.pcss.secure.two.GetFileDetailCivilSecureRequest;
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
public class SecureEndpointTests {

    private SecureEndpointController endpointController;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Mock private RestTemplate restTemplate = new RestTemplate();

    @Test
    public void getGetAppearanceCivilApprMethodSecureRequestTest() throws JsonProcessingException {
        endpointController = new SecureEndpointController(restTemplate, objectMapper);
        var req = new GetAppearanceCivilApprMethodSecureRequest();
        var one =
                new com.example.demp.wsdl.pcss.secure.one
                        .GetAppearanceCivilApprMethodSecureRequest();
        one.setAppearanceId("A");
        one.setRequestDtm(Instant.now());
        one.setApplicationCd("A");
        one.setRequestPartId("A");
        one.setRequestAgencyIdentifierId("A");

        req.setGetAppearanceCivilApprMethodSecureRequest(one);

        var resp = new com.example.demp.wsdl.pcss.secure.one.GetAppearanceCivilApprMethodResponse();

        resp.setResponseCd("A");
        resp.setResponseMessageTxt("A");
        AppearanceMethod am = new AppearanceMethod();
        am.setAppearanceMethodCd("A");
        am.setApprMethodCcn("A");
        am.setRoleTypeCd("A");
        resp.setAppearanceMethod(Collections.singletonList(am));

        ResponseEntity<com.example.demp.wsdl.pcss.secure.one.GetAppearanceCivilApprMethodResponse>
                responseEntity = new ResponseEntity<>(resp, HttpStatus.OK);
        //     Set up to mock ords response
        when(restTemplate.exchange(
                        Mockito.any(String.class),
                        Mockito.eq(HttpMethod.GET),
                        Mockito.<HttpEntity<String>>any(),
                        Mockito
                                .<Class<
                                                com.example.demp.wsdl.pcss.secure.one
                                                        .GetAppearanceCivilApprMethodResponse>>
                                        any()))
                .thenReturn(responseEntity);

        var out = endpointController.getGetAppearanceCivilApprMethodSecureRequest(req);

        assert out != null;
    }

    @Test
    public void getAppearanceCivilPartySecure() throws JsonProcessingException {
        endpointController = new SecureEndpointController(restTemplate, objectMapper);

        var req = new GetAppearanceCivilPartySecureRequest();
        var one = new com.example.demp.wsdl.pcss.secure.one.GetAppearanceCivilPartySecureRequest();

        one.setAppearanceId("A");
        one.setApplicationCd("A");
        one.setRequestDtm(Instant.now());
        one.setRequestAgencyIdentifierId("A");
        one.setRequestPartId("A");

        req.setGetAppearanceCivilPartySecureRequest(one);

        var resp = new com.example.demp.wsdl.pcss.secure.one.GetAppearanceCivilPartyResponse();
        resp.setResponseCd("A");
        resp.setResponseMessageTxt("A");

        Party2 party = new Party2();
        party.setPartyId("A");
        party.setLastNm("A");
        party.setOrgNm("A");
        party.setGivenNm("A");
        party.setCounselNm("A");
        party.setCourtParticipantCcn("A");
        party.setCourtParticipantId("A");
        party.setPartyRoleTypeCd("A");

        resp.setParty(Collections.singletonList(party));

        ResponseEntity<com.example.demp.wsdl.pcss.secure.one.GetAppearanceCivilPartyResponse>
                responseEntity = new ResponseEntity<>(resp, HttpStatus.OK);
        //     Set up to mock ords response
        when(restTemplate.exchange(
                        Mockito.any(String.class),
                        Mockito.eq(HttpMethod.GET),
                        Mockito.<HttpEntity<String>>any(),
                        Mockito
                                .<Class<
                                                com.example.demp.wsdl.pcss.secure.one
                                                        .GetAppearanceCivilPartyResponse>>
                                        any()))
                .thenReturn(responseEntity);

        var out = endpointController.getAppearanceCivilPartySecure(req);

        assert out != null;
    }

    @Test
    public void getAppearanceCivilSecure() throws JsonProcessingException {
        endpointController = new SecureEndpointController(restTemplate, objectMapper);

        var req = new GetAppearanceCivilSecure();
        var one = new GetAppearanceCivilSecureRequest();
        var two = new com.example.demp.wsdl.pcss.secure.one.GetAppearanceCivilSecureRequest();
        two.setApplicationCd("A");
        two.setRequestDtm(Instant.now());
        two.setRequestAgencyIdentifierId("A");
        two.setFutureYN(YesNoType.Y);
        two.setHistoryYN(YesNoType.Y);
        two.setPhysicalFileId("A");
        two.setRequestPartId("A");

        one.setGetAppearanceCivilSecureRequest(two);
        req.setGetAppearanceCivilSecureRequest(one);

        var resp = new com.example.demp.wsdl.pcss.secure.one.GetAppearanceCivilResponse();
        resp.setResponseCd("A");
        resp.setResponseMessageTxt("A");
        resp.setFutureRecCount("A");
        resp.setHistoryRecCount("A");
        ApprDetail ap = new ApprDetail();
        ap.setHistoryYN(YesNoType.Y);
        ap.setAppearanceId("A");
        ap.setAppearanceDt("A");
        ap.setAppearanceTm("A");
        ap.setAppearanceReasonCd("A");
        ap.setCourtAgencyId("A");
        ap.setCourtRoomCd("A");
        ap.setJudgeFullNm("A");
        ap.setJudgeInitials("A");
        ap.setEstimatedTimeHour("A");
        ap.setEstimatedTimeMin("A");
        ap.setPartOfTrialYN(YesNoType.Y);
        ap.setAppearanceStatusCd(AppearanceStatusType.CNCL);
        ap.setAppearanceResultCd("A");
        ap.setAppearanceCcn("A");
        ap.setDocumentTypeCd("A");
        ap.setDocumentRecCount("A");
        ap.setSupplementalEquipmentTxt("A");
        ap.setSecurityRestrictionTxt("A");
        ap.setOutOfTownJudgeTxt("A");

        resp.setApprDetail(Collections.singletonList(ap));

        ResponseEntity<com.example.demp.wsdl.pcss.secure.one.GetAppearanceCivilResponse>
                responseEntity = new ResponseEntity<>(resp, HttpStatus.OK);
        //     Set up to mock ords response
        when(restTemplate.exchange(
                        Mockito.any(String.class),
                        Mockito.eq(HttpMethod.GET),
                        Mockito.<HttpEntity<String>>any(),
                        Mockito
                                .<Class<
                                                com.example.demp.wsdl.pcss.secure.one
                                                        .GetAppearanceCivilResponse>>
                                        any()))
                .thenReturn(responseEntity);

        var out = endpointController.getAppearanceCivilSecure(req);

        assert out != null;
    }

    @Test
    public void getFileDetailCivilSecure() throws JsonProcessingException {
        endpointController = new SecureEndpointController(restTemplate, objectMapper);

        var req = new GetFileDetailCivilSecureRequest();
        var one = new com.example.demp.wsdl.pcss.secure.one.GetFileDetailCivilSecureRequest();

        one.setApplicationCd("A");
        one.setPhysicalFileId("A");
        one.setRequestAgencyIdentifierId("A");
        one.setRequestPartId("A");
        one.setRequestDtm(Instant.now());

        req.setGetFileDetailCivilSecureRequest(one);

        var resp = new com.example.demp.wsdl.pcss.secure.one.GetFileDetailCivilResponse();

        resp.setResponseCd("A");
        resp.setResponseMessageTxt("A");
        resp.setPhysicalFileId("A");
        resp.setFileNumberTxt("A");
        resp.setHomeLocationAgenId("A");
        resp.setCourtLevelCd(CourtLevelType.S);
        resp.setCourtClassCd(CourtClassType.S);
        resp.setSocTxt("A");
        resp.setLeftRoleDsc("A");
        resp.setRightRoleDsc("A");
        resp.setTrialRemarkTxt("A");
        resp.setCommentToJudgeTxt("A");

        HearingRestriction hr = new HearingRestriction();
        hr.setAdjFullNm("A");
        hr.setPhysicalFileId("A");
        hr.setAdjInitialsTxt("A");
        hr.setCivilDocumentId("A");
        hr.setAdjPartId("A");
        hr.setApplyToNm("A");
        hr.setHearingRestrictionCcn("A");
        hr.setHearingRestrictionId("A");
        hr.setHearingRestrictionTypeCd(HearingRestrictionType.S);

        resp.setHearingRestriction(Collections.singletonList(hr));

        Party pt = new Party();
        pt.setGivenNm("A");
        pt.setLastNm("A");
        pt.setOrgNm("A");
        pt.setLeftRightCd(LeftRightType.L);
        pt.setRoleTypeCd("A");
        pt.setSelfRepresentedYN("A");
        Counsel counsel = new Counsel();
        counsel.setCounselId("A");
        counsel.setPhoneNumberTxt("A");
        counsel.setFullNm("A");
        pt.setCounsel(Collections.singletonList(counsel));

        resp.setParty(Collections.singletonList(pt));

        Document doc = new Document();
        doc.setCivilDocumentId("A");
        doc.setDocumentTypeCd("A");
        doc.setCommentTxt("A");
        doc.setFileSeqNo("A");
        doc.setConcludedYn(YesNoType.Y);
        doc.setFiledDt("A");
        doc.setLastAppearanceDt("A");
        doc.setLastAppearanceTm("A");
        doc.setLastAppearanceId("A");

        DocumentSupport ds = new DocumentSupport();
        ds.setActCd("A");
        ds.setActDsc("A");

        doc.setDocumentSupport(Collections.singletonList(ds));

        Issue is = new Issue();
        is.setConcludedYn(YesNoType.Y);
        is.setIssueDsc("A");
        is.setIssueNumber("A");
        is.setIssueResultDsc("A");
        is.setIssueResultCd("A");
        is.setIssueTypeCd("A");

        doc.setIssue(Collections.singletonList(is));

        ResponseEntity<com.example.demp.wsdl.pcss.secure.one.GetFileDetailCivilResponse>
                responseEntity = new ResponseEntity<>(resp, HttpStatus.OK);
        //     Set up to mock ords response
        when(restTemplate.exchange(
                        Mockito.any(String.class),
                        Mockito.eq(HttpMethod.GET),
                        Mockito.<HttpEntity<String>>any(),
                        Mockito
                                .<Class<
                                                com.example.demp.wsdl.pcss.secure.one
                                                        .GetFileDetailCivilResponse>>
                                        any()))
                .thenReturn(responseEntity);

        var out = endpointController.getFileDetailCivilSecure(req);

        assert out != null;
    }
}
