package ca.bc.gov.open.Pcss;

import static org.mockito.Mockito.when;

import ca.bc.gov.open.Pcss.Controllers.SyncController;
import com.example.demp.wsdl.pcss.one.*;
import com.example.demp.wsdl.pcss.three.*;
import com.example.demp.wsdl.pcss.three.GetFileDetailCivilRequest;
import com.example.demp.wsdl.pcss.three.GetSyncCivilAppearanceRequest;
import com.example.demp.wsdl.pcss.three.GetSyncCivilHearingRestrictionRequest;
import com.example.demp.wsdl.pcss.three.SetHearingRestrictionCivilRequest;
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
public class SyncControllerTests {

    private SyncController syncController;

    @Mock private RestTemplate restTemplate = new RestTemplate();

    @Test
    public void getSyncCivilAppearanceTest() {
        syncController = new SyncController(restTemplate);

        var sca = new GetSyncCivilAppearance();
        var one = new GetSyncCivilAppearanceRequest();
        var two = new com.example.demp.wsdl.pcss.one.GetSyncCivilAppearanceRequest();
        two.setProcessUpToDtm("A");
        two.setRequestAgencyIdentifierId("A");
        two.setRequestDtm("A");
        two.setRequestPartId("A");

        one.setGetSyncCivilAppearanceRequest(two);
        sca.setGetSyncCivilAppearanceRequest(one);

        var out = new com.example.demp.wsdl.pcss.one.GetSyncCivilAppearanceResponse();
        Appearance app = new Appearance();
        app.setOperationModeCd(OperationModeType.ADD);
        app.setTransactionDtm("A");
        app.setAppearanceId("A");
        app.setAppearanceDt("A");
        app.setAppearanceTm("A");
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

        out.setAppearance(Collections.singletonList(app));
        out.setResponseCd("A");
        out.setResponseCd("A");
        ResponseEntity<com.example.demp.wsdl.pcss.one.GetSyncCivilAppearanceResponse>
                responseEntity = new ResponseEntity<>(out, HttpStatus.OK);

        //     Set up to mock ords response
        when(restTemplate.exchange(
                        Mockito.any(String.class),
                        Mockito.eq(HttpMethod.GET),
                        Mockito.<HttpEntity<String>>any(),
                        Mockito
                                .<Class<
                                                com.example.demp.wsdl.pcss.one
                                                        .GetSyncCivilAppearanceResponse>>
                                        any()))
                .thenReturn(responseEntity);

        var resp = syncController.getSyncCivilAppearance(sca);

        assert resp != null;
    }

    @Test
    public void getSyncCivilHearingRestrictionTest() {
        syncController = new SyncController(restTemplate);

        var chr = new GetSyncCivilHearingRestriction();
        var one = new GetSyncCivilHearingRestrictionRequest();
        var two = new com.example.demp.wsdl.pcss.one.GetSyncCivilHearingRestrictionRequest();
        two.setProcessUpToDtm("A");
        two.setRequestDtm("A");
        two.setRequestAgencyIdentifierId("A");
        two.setRequestPartId("A");
        one.setGetSyncCivilHearingRestrictionRequest(two);
        chr.setGetSyncCivilHearingRestrictionRequest(one);

        var out = new com.example.demp.wsdl.pcss.one.GetSyncCivilHearingRestrictionResponse();
        out.setResponseCd("A");
        out.setResponseMessageTxt("A");
        HearingRestriction r = new HearingRestriction();
        r.setOperationModeCd(OperationModeType.F_ADD);
        r.setTransactionDtm("A");
        r.setHearingRestrictionId("A");
        r.setAdjudicatorPartId("A");
        r.setHearingRestrictionCd(HearingRestrictionType.A);
        r.setPhysicalFileId("A");
        r.setCivilDocumentId("A");
        r.setHomeLocationAgenId("A");
        r.setFileNumberTxt("A");
        r.setSocTxt("A");
        out.setHearingRestriction(Collections.singletonList(r));

        ResponseEntity<com.example.demp.wsdl.pcss.one.GetSyncCivilHearingRestrictionResponse>
                responseEntity = new ResponseEntity<>(out, HttpStatus.OK);

        //     Set up to mock ords response
        when(restTemplate.exchange(
                        Mockito.any(String.class),
                        Mockito.eq(HttpMethod.GET),
                        Mockito.<HttpEntity<String>>any(),
                        Mockito
                                .<Class<
                                                com.example.demp.wsdl.pcss.one
                                                        .GetSyncCivilHearingRestrictionResponse>>
                                        any()))
                .thenReturn(responseEntity);

        var resp = syncController.getSyncCivilHearingRestriction(chr);

        assert resp != null;
    }

    @Test
    public void setSyncCivilHearingRestrictionTest() {
        syncController = new SyncController(restTemplate);

        var hrc = new SetHearingRestrictionCivil();
        var one = new SetHearingRestrictionCivilRequest();
        var two = new com.example.demp.wsdl.pcss.one.SetHearingRestrictionCivilRequest();
        two.setRequestAgencyIdentifierId("A");
        two.setRequestPartId("A");
        two.setRequestDtm("A");
        two.setOperationModeCd(OperationModeType.I_ADD);
        two.setHearingRestrictionId("A");
        two.setAdjudicatorPartId("A");
        two.setHearingRestrictionCd(HearingRestrictionType.G);
        two.setPhysicalFileId("A");
        two.setCivilDocumentId("A");
        two.setHearingRestrictionCcn("A");

        one.setSetHearingRestrictionCivilRequest(two);
        hrc.setSetHearingRestrictionCivilRequest(one);

        var out = new com.example.demp.wsdl.pcss.one.SetHearingRestrictionCivilResponse();
        out.setHearingRestrictionCcn("A");
        out.setResponseCd("A");
        out.setResponseMessageTxt("A");
        out.setHearingRestrictionId("A");

        ResponseEntity<com.example.demp.wsdl.pcss.one.SetHearingRestrictionCivilResponse>
                responseEntity = new ResponseEntity<>(out, HttpStatus.OK);

        //     Set up to mock ords response
        when(restTemplate.exchange(
                        Mockito.anyString(),
                        Mockito.eq(HttpMethod.POST),
                        Mockito.<HttpEntity<String>>any(),
                        Mockito
                                .<Class<
                                                com.example.demp.wsdl.pcss.one
                                                        .SetHearingRestrictionCivilResponse>>
                                        any()))
                .thenReturn(responseEntity);

        var resp = syncController.setSyncCivilHearingRestriction(hrc);

        assert resp != null;
    }

    @Test
    public void getFileDetailCivilTest() {
        syncController = new SyncController(restTemplate);

        var fsc = new GetFileDetailCivil();
        var one = new GetFileDetailCivilRequest();
        var two = new com.example.demp.wsdl.pcss.one.GetFileDetailCivilRequest();
        two.setRequestAgencyIdentifierId("A");
        two.setRequestPartId("A");
        two.setRequestDtm("A");
        two.setPhysicalFileId("A");

        one.setGetFileDetailCivilRequest(two);
        fsc.setGetFileDetailCivilRequest(one);

        var out = new com.example.demp.wsdl.pcss.one.GetFileDetailCivilResponse();

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
        party.setCounsel(Collections.singletonList(counsel));

        Document3 doc = new Document3();
        doc.setCivilDocumentId("A");
        doc.setFileSeqNo("A");
        doc.setDocumentTypeCd("A");
        doc.setFiledDt("A");
        doc.setCommentTxt("A");
        doc.setConcludedYn(YesNoType.Y);
        doc.setLastAppearanceId("A");
        doc.setLastAppearanceDt("A");
        doc.setLastAppearanceTm("A");
        DocumentSupport sup = new DocumentSupport();
        sup.setActCd("A");
        sup.setActDsc("A");
        doc.setDocumentSupport(Collections.singletonList(sup));

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

        out.setHearingRestriction(Collections.singletonList(hr));
        out.setDocument(Collections.singletonList(doc));
        out.setParty(Collections.singletonList(party));

        ResponseEntity<com.example.demp.wsdl.pcss.one.GetFileDetailCivilResponse> responseEntity =
                new ResponseEntity<>(out, HttpStatus.OK);

        //     Set up to mock ords response
        when(restTemplate.exchange(
                        Mockito.any(String.class),
                        Mockito.eq(HttpMethod.GET),
                        Mockito.<HttpEntity<String>>any(),
                        Mockito
                                .<Class<com.example.demp.wsdl.pcss.one.GetFileDetailCivilResponse>>
                                        any()))
                .thenReturn(responseEntity);

        var resp = syncController.getFileDetailCivil(fsc);

        assert resp != null;
    }
}
