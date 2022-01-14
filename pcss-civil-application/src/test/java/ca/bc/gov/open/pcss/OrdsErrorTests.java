package ca.bc.gov.open.pcss;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import ca.bc.gov.open.pcss.controllers.AppearanceController;
import ca.bc.gov.open.pcss.controllers.HealthController;
import ca.bc.gov.open.pcss.controllers.SecureEndpointController;
import ca.bc.gov.open.pcss.controllers.SyncController;
import ca.bc.gov.open.pcss.exceptions.BadDateException;
import ca.bc.gov.open.pcss.exceptions.ORDSException;
import ca.bc.gov.open.pcss.secure.two.*;
import ca.bc.gov.open.pcss.three.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class OrdsErrorTests {

    @Mock private RestTemplate restTemplate;

    @Autowired private ObjectMapper objectMapper;

    @Autowired private MockMvc mockMvc;

    @Test
    public void testHealthPingOrdsFail() {
        HealthController healthController = new HealthController(restTemplate, objectMapper);

        Assertions.assertThrows(ORDSException.class, () -> healthController.getPing(new GetPing()));
    }

    @Test
    public void testHealthHealthOrdsFail() {
        HealthController healthController = new HealthController(restTemplate, objectMapper);

        Assertions.assertThrows(
                ORDSException.class, () -> healthController.getHealth(new GetHealth()));
    }

    @Test
    public void getSyncCivilAppearanceTest() {
        SyncController syncController = new SyncController(restTemplate, objectMapper);

        Assertions.assertThrows(
                ORDSException.class,
                () -> syncController.getSyncCivilAppearance(new GetSyncCivilAppearance()));
    }

    @Test
    public void getSyncFileDetailTest() {
        SyncController syncController = new SyncController(restTemplate, objectMapper);

        Assertions.assertThrows(
                ORDSException.class,
                () -> syncController.getFileDetailCivil(new GetFileDetailCivil()));
    }

    @Test
    public void getSyncSyncCivilHearingRestrictionTest() {
        SyncController syncController = new SyncController(restTemplate, objectMapper);

        Assertions.assertThrows(
                ORDSException.class,
                () ->
                        syncController.getSyncCivilHearingRestriction(
                                new GetSyncCivilHearingRestriction()));
    }

    @Test
    public void setSyncSyncCivilHearingRestrictionTest() {
        SyncController syncController = new SyncController(restTemplate, objectMapper);

        Assertions.assertThrows(
                ORDSException.class,
                () ->
                        syncController.setSyncCivilHearingRestriction(
                                new SetHearingRestrictionCivil()));
    }

    @Test
    public void getAppearanceCivilApprMethodSecureRequestTest() {
        SecureEndpointController secureController =
                new SecureEndpointController(restTemplate, objectMapper);

        Assertions.assertThrows(
                ORDSException.class,
                () ->
                        secureController.getAppearanceCivilApprMethodSecureRequest(
                                new GetAppearanceCivilApprMethodSecure()));
    }

    @Test
    public void getAppearanceCivilPartySecureTest() {
        SecureEndpointController secureController =
                new SecureEndpointController(restTemplate, objectMapper);

        Assertions.assertThrows(
                ORDSException.class,
                () ->
                        secureController.getAppearanceCivilPartySecure(
                                new GetAppearanceCivilPartySecure()));
    }

    @Test
    public void getAppearanceCivilSecureTest() {
        SecureEndpointController secureController =
                new SecureEndpointController(restTemplate, objectMapper);

        Assertions.assertThrows(
                ORDSException.class,
                () -> secureController.getAppearanceCivilSecure(new GetAppearanceCivilSecure()));
    }

    @Test
    public void getFileDetailCivilSecureTest() {
        SecureEndpointController secureController =
                new SecureEndpointController(restTemplate, objectMapper);

        Assertions.assertThrows(
                ORDSException.class,
                () -> secureController.getFileDetailCivilSecure(new GetFileDetailCivilSecure()));
    }

    @Test
    public void getAppearanceCivilTest() {
        AppearanceController secureController =
                new AppearanceController(restTemplate, objectMapper);

        var one = new GetAppearanceCivil();
        var two = new GetAppearanceCivilRequest();
        one.setGetAppearanceCivilRequest(two);
        var three = new ca.bc.gov.open.pcss.one.GetAppearanceCivilRequest();
        two.setGetAppearanceCivilRequest(three);
        three.setRequestDtm("A");

        Assertions.assertThrows(
                ORDSException.class, () -> secureController.getAppearanceCivil(one));
    }

    @Test
    public void getAppearanceCivilNullRequestTest() {
        AppearanceController secureController =
                new AppearanceController(restTemplate, objectMapper);

        Assertions.assertThrows(
                BadDateException.class,
                () -> secureController.getAppearanceCivil(new GetAppearanceCivil()));
    }

    @Test
    public void setAppearanceCivilTest() {
        AppearanceController secureController =
                new AppearanceController(restTemplate, objectMapper);

        Assertions.assertThrows(
                ORDSException.class,
                () -> secureController.setAppearanceCivil(new SetAppearanceCivil()));
    }

    @Test
    public void getAppearanceCivilApprMethodTest() {
        AppearanceController secureController =
                new AppearanceController(restTemplate, objectMapper);

        var one = new GetAppearanceCivilApprMethod();
        var two = new GetAppearanceCivilApprMethodRequest();
        var three = new ca.bc.gov.open.pcss.one.GetAppearanceCivilApprMethodRequest();
        three.setRequestDtm("A");
        two.setGetAppearanceCivilApprMethodRequest(three);
        one.setGetAppearanceCivilApprMethodRequest(two);

        Assertions.assertThrows(
                ORDSException.class, () -> secureController.getAppearanceCivilApprMethod(one));
    }

    @Test
    public void getAppearanceCivilApprMethodBadDateTest() {
        AppearanceController secureController =
                new AppearanceController(restTemplate, objectMapper);
        var one = new GetAppearanceCivilApprMethod();
        Assertions.assertThrows(
                BadDateException.class, () -> secureController.getAppearanceCivilApprMethod(one));
    }

    @Test
    public void setAppearanceMethodCivilTestBadDate() {
        AppearanceController secureController =
                new AppearanceController(restTemplate, objectMapper);

        Assertions.assertThrows(
                BadDateException.class,
                () -> secureController.setAppearanceMethodCivil(new SetAppearanceMethodCivil()));
    }

    @Test
    public void setAppearanceMethodCivilTest() {
        AppearanceController secureController =
                new AppearanceController(restTemplate, objectMapper);

        var one = new SetAppearanceMethodCivil();
        var two = new SetAppearanceMethodCivilRequest();
        var three = new ca.bc.gov.open.pcss.one.SetAppearanceMethodCivilRequest();
        one.setSetAppearanceMethodCivilRequest(two);
        two.setSetAppearanceMethodCivilRequest(three);
        three.setRequestDtm("A");

        Assertions.assertThrows(
                ORDSException.class, () -> secureController.setAppearanceMethodCivil(one));
    }

    @Test
    public void getAppearanceCivilDocumentTest() {
        AppearanceController secureController =
                new AppearanceController(restTemplate, objectMapper);

        Assertions.assertThrows(
                ORDSException.class,
                () ->
                        secureController.getAppearanceCivilDocument(
                                new GetAppearanceCivilDocument()));
    }

    @Test
    public void getAppearanceCivilPartyTest() {
        AppearanceController secureController =
                new AppearanceController(restTemplate, objectMapper);

        Assertions.assertThrows(
                ORDSException.class,
                () -> secureController.getAppearanceCivilParty(new GetAppearanceCivilParty()));
    }

    @Test
    public void getAppearanceCivilResourceTest() {
        AppearanceController secureController =
                new AppearanceController(restTemplate, objectMapper);

        Assertions.assertThrows(
                ORDSException.class,
                () ->
                        secureController.getAppearanceCivilResource(
                                new GetAppearanceCivilResource()));
    }

    @Test
    public void setCounselDetailCivilTest() {
        AppearanceController secureController =
                new AppearanceController(restTemplate, objectMapper);

        Assertions.assertThrows(
                ORDSException.class,
                () -> secureController.setCounselDetailCivil(new SetCounselDetailCivil()));
    }

    @Test
    public void badDateTests() {
        AppearanceController secureController =
                new AppearanceController(restTemplate, objectMapper);

        var one = new GetAppearanceCivil();
        var two = new GetAppearanceCivilRequest();
        one.setGetAppearanceCivilRequest(two);
        var three = new ca.bc.gov.open.pcss.one.GetAppearanceCivilRequest();
        two.setGetAppearanceCivilRequest(three);

        Assertions.assertThrows(
                BadDateException.class, () -> secureController.getAppearanceCivil(one));
    }

    @Test
    public void securityTestFail_Then401() throws Exception {
        var response =
                mockMvc.perform(post("/ws").contentType(MediaType.TEXT_XML))
                        .andExpect(status().is4xxClientError())
                        .andReturn();
        Assertions.assertEquals(
                HttpStatus.UNAUTHORIZED.value(), response.getResponse().getStatus());
    }
}
