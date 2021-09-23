package ca.bc.gov.open.Pcss;

import static org.mockito.Mockito.when;

import ca.bc.gov.open.Pcss.Controllers.AppearanceController;
import com.example.demp.wsdl.pcss.one.ApprDetail;
import com.example.demp.wsdl.pcss.three.*;
import com.example.demp.wsdl.pcss.two.AppearanceStatusType;
import com.example.demp.wsdl.pcss.two.YesNoType;
import org.junit.Test;
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
}
