package ca.bc.gov.open.pcss;

import static org.mockito.Mockito.when;

import ca.bc.gov.open.pcss.controllers.SyncController;
import ca.bc.gov.open.pcss.one.*;
import ca.bc.gov.open.pcss.three.*;
import ca.bc.gov.open.pcss.three.GetFileDetailCivilRequest;
import ca.bc.gov.open.pcss.three.GetSyncCivilAppearanceRequest;
import ca.bc.gov.open.pcss.three.GetSyncCivilHearingRestrictionRequest;
import ca.bc.gov.open.pcss.three.SetHearingRestrictionCivilRequest;
import ca.bc.gov.open.pcss.two.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.time.Instant;
import java.util.Collections;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SyncControllerTests {

    @Mock private ObjectMapper objectMapper;
    @Mock private RestTemplate restTemplate;
    @Mock private SyncController syncController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        syncController = Mockito.spy(new SyncController(restTemplate, objectMapper));
    }

    @Test
    public void getSyncCivilAppearanceTest() throws JsonProcessingException {

        var sca = new GetSyncCivilAppearance();
        var one = new GetSyncCivilAppearanceRequest();

        var two = new ca.bc.gov.open.pcss.one.GetSyncCivilAppearanceRequest();
        two.setProcessUpToDtm(Instant.now());
        two.setRequestAgencyIdentifierId("A");
        two.setRequestDtm(Instant.now());
        two.setRequestPartId("A");

        one.setGetSyncCivilAppearanceRequest(two);
        sca.setGetSyncCivilAppearanceRequest(one);

        var out = new ca.bc.gov.open.pcss.one.GetSyncCivilAppearanceResponse();
        Appearance app = new Appearance();
        app.setOperationModeCd(OperationModeType.ADD);
        app.setTransactionDtm(Instant.now());
        app.setAppearanceId("A");
        app.setAppearanceDt(Instant.now());
        app.setAppearanceTm(Instant.now());
        app.setAppearanceReasonCd("A");
        app.setEstimatedDurationHour("A");
        app.setEstimatedDurationMin("A");
        app.setAgenId("A");
        app.setCourtRoomCd("A");
        app.setAdjournedPriorYn(YesNoType.N);
        app.setCancelledYn(YesNoType.Y);
        app.setPartOfTrialYn(YesNoType.Y);
        app.setPhysicalFileId("A");
        app.setPcssCEISDivisionCd("A");
        app.setFileNumberTxt("A");
        app.setCourtLevelCd(CourtLevelType.A);

        out.getAppearance().add(app);
        out.setResponseCd("A");
        out.setResponseCd("A");
        ResponseEntity<ca.bc.gov.open.pcss.one.GetSyncCivilAppearanceResponse> responseEntity =
                new ResponseEntity<>(out, HttpStatus.OK);

        //     Set up to mock ords response
        when(restTemplate.exchange(
                        Mockito.any(URI.class),
                        Mockito.eq(HttpMethod.GET),
                        Mockito.<HttpEntity<String>>any(),
                        Mockito
                                .<Class<ca.bc.gov.open.pcss.one.GetSyncCivilAppearanceResponse>>
                                        any()))
                .thenReturn(responseEntity);

        var resp = syncController.getSyncCivilAppearance(sca);

        Assertions.assertNotNull(resp);
    }

    @Test
    public void getSyncCivilHearingRestrictionTest() throws JsonProcessingException {

        var chr = new GetSyncCivilHearingRestriction();
        var one = new GetSyncCivilHearingRestrictionRequest();

        var two = new ca.bc.gov.open.pcss.one.GetSyncCivilHearingRestrictionRequest();

        two.setRequestAgencyIdentifierId("A");
        two.setRequestPartId("A");
        one.setGetSyncCivilHearingRestrictionRequest(two);
        chr.setGetSyncCivilHearingRestrictionRequest(one);

        var out = new ca.bc.gov.open.pcss.one.GetSyncCivilHearingRestrictionResponse();
        out.setResponseCd("A");
        out.setResponseMessageTxt("A");
        HearingRestriction r = new HearingRestriction();
        r.setOperationModeCd(OperationModeType.F_ADD);
        r.setTransactionDtm(Instant.now());
        r.setHearingRestrictionId("A");
        r.setAdjudicatorPartId("A");
        r.setHearingRestrictionCd(HearingRestrictionType.A);
        r.setPhysicalFileId("A");
        r.setCivilDocumentId("A");
        r.setHomeLocationAgenId("A");
        r.setFileNumberTxt("A");
        r.setSocTxt("A");
        out.getHearingRestriction().add(r);

        ResponseEntity<ca.bc.gov.open.pcss.one.GetSyncCivilHearingRestrictionResponse>
                responseEntity = new ResponseEntity<>(out, HttpStatus.OK);

        //     Set up to mock ords response
        when(restTemplate.exchange(
                        Mockito.any(URI.class),
                        Mockito.eq(HttpMethod.GET),
                        Mockito.<HttpEntity<String>>any(),
                        Mockito
                                .<Class<
                                                ca.bc.gov.open.pcss.one
                                                        .GetSyncCivilHearingRestrictionResponse>>
                                        any()))
                .thenReturn(responseEntity);

        var resp = syncController.getSyncCivilHearingRestriction(chr);

        Assertions.assertNotNull(resp);
    }

    @Test
    public void setSyncCivilHearingRestrictionTest() throws JsonProcessingException {

        var hrc = new SetHearingRestrictionCivil();
        var one = new SetHearingRestrictionCivilRequest();
        var two = new ca.bc.gov.open.pcss.one.SetHearingRestrictionCivilRequest();
        two.setRequestAgencyIdentifierId("A");
        two.setRequestPartId("A");
        two.setRequestDtm(Instant.now());
        two.setOperationModeCd(OperationModeType.I_ADD);
        two.setHearingRestrictionId("A");
        two.setAdjudicatorPartId("A");
        two.setHearingRestrictionCd(HearingRestrictionType.G);
        two.setPhysicalFileId("A");
        two.setCivilDocumentId("A");
        two.setHearingRestrictionCcn("A");

        one.setSetHearingRestrictionCivilRequest(two);
        hrc.setSetHearingRestrictionCivilRequest(one);

        var out = new ca.bc.gov.open.pcss.one.SetHearingRestrictionCivilResponse();
        out.setHearingRestrictionCcn("A");
        out.setResponseCd("A");
        out.setResponseMessageTxt("A");
        out.setHearingRestrictionId("A");

        ResponseEntity<ca.bc.gov.open.pcss.one.SetHearingRestrictionCivilResponse> responseEntity =
                new ResponseEntity<>(out, HttpStatus.OK);

        //     Set up to mock ords response
        when(restTemplate.exchange(
                        Mockito.anyString(),
                        Mockito.eq(HttpMethod.POST),
                        Mockito.<HttpEntity<String>>any(),
                        Mockito
                                .<Class<ca.bc.gov.open.pcss.one.SetHearingRestrictionCivilResponse>>
                                        any()))
                .thenReturn(responseEntity);

        var resp = syncController.setSyncCivilHearingRestriction(hrc);

        Assertions.assertNotNull(resp);
    }

    @Test
    public void getFileDetailCivilTest() throws JsonProcessingException {

        var fsc = new GetFileDetailCivil();
        var one = new GetFileDetailCivilRequest();
        var two = new ca.bc.gov.open.pcss.one.GetFileDetailCivilRequest();
        two.setRequestAgencyIdentifierId("A");
        two.setRequestPartId("A");
        two.setRequestDtm(Instant.now());
        two.setPhysicalFileId("A");

        one.setGetFileDetailCivilRequest(two);
        fsc.setGetFileDetailCivilRequest(one);

        var out = new ca.bc.gov.open.pcss.one.GetFileDetailCivilResponse();

        out.setResponseCd("A");
        out.setResponseMessageTxt("A");
        out.setPhysicalFileId("A");
        out.setFileNumberTxt("A");
        out.setHomeLocationAgenId("A");
        out.setCourtLevelCd(CourtLevelType.S);
        out.setCourtClassCd(CourtClassType.M);
        out.setSocTxt("A");
        out.setLeftRoleDsc("A");
        out.setRightRoleDsc("A");
        out.setTrialRemarkTxt("A");
        out.setCommentToJudgeTxt("A");

        Party3 party = new Party3();
        party.setPartyId("A");
        party.setLastNm("A");
        party.setGivenNm("A");
        party.setOrgNm("A");
        party.setRoleTypeCd("A");
        party.setLeftRightCd(LeftRightType.L);
        party.setSelfRepresentedYN("Y");
        Counsel counsel = new Counsel();
        counsel.setCounselId("A");
        counsel.setFullNm("A");
        counsel.setPhoneNumberTxt("A");
        party.getCounsel().add(counsel);

        Document3 doc = new Document3();
        doc.setCivilDocumentId("A");
        doc.setFileSeqNo("A");
        doc.setDocumentTypeCd("A");
        doc.setFiledDt(Instant.now());
        doc.setCommentTxt("A");
        doc.setConcludedYn(YesNoType.Y);
        doc.setLastAppearanceId("A");
        doc.setLastAppearanceDt(Instant.now());
        doc.setLastAppearanceTm(Instant.now());
        DocumentSupport sup = new DocumentSupport();
        sup.setActCd("A");
        sup.setActDsc("A");
        doc.getDocumentSupport().add(sup);

        HearingRestriction2 hr = new HearingRestriction2();
        hr.setHearingRestrictionId("A");
        hr.setAdjPartId("A");
        hr.setAdjFullNm("A");
        hr.setHearingRestrictionTypeCd(HearingRestrictionType.S);
        hr.setApplyToNm("A");
        hr.setCivilDocumentId("A");
        hr.setPhysicalFileId("A");
        hr.setAdjInitialsTxt("A");
        hr.setHearingRestrictionCcn("A");

        out.getHearingRestriction().add(hr);
        out.getDocument().add(doc);
        out.getParty().add(party);

        ResponseEntity<ca.bc.gov.open.pcss.one.GetFileDetailCivilResponse> responseEntity =
                new ResponseEntity<>(out, HttpStatus.OK);

        //     Set up to mock ords response
        when(restTemplate.exchange(
                        Mockito.any(String.class),
                        Mockito.eq(HttpMethod.GET),
                        Mockito.<HttpEntity<String>>any(),
                        Mockito.<Class<ca.bc.gov.open.pcss.one.GetFileDetailCivilResponse>>any()))
                .thenReturn(responseEntity);

        var resp = syncController.getFileDetailCivil(fsc);

        Assertions.assertNotNull(resp);
    }
}
