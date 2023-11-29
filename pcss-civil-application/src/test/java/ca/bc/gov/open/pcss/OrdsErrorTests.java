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
import java.time.Instant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

@WebMvcTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrdsErrorTests {

    @Autowired private MockMvc mockMvc;

    @Mock private ObjectMapper objectMapper;
    @Mock private RestTemplate restTemplate;

    @Mock private HealthController healthController;
    @Mock private SyncController syncController;
    @Mock private AppearanceController appearanceController;
    @Mock private SecureEndpointController secureController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        healthController = Mockito.spy(new HealthController(restTemplate, objectMapper));
        syncController = Mockito.spy(new SyncController(restTemplate, objectMapper));
        secureController = Mockito.spy(new SecureEndpointController(restTemplate, objectMapper));
        appearanceController = Mockito.spy(new AppearanceController(restTemplate, objectMapper));
    }

    @Test
    public void testHealthPingOrdsFail() {
        MockitoAnnotations.openMocks(this);
        healthController = Mockito.spy(new HealthController(restTemplate, objectMapper));
        appearanceController = Mockito.spy(new AppearanceController(restTemplate, objectMapper));
        syncController = Mockito.spy(new SyncController(restTemplate, objectMapper));
        secureController = Mockito.spy(new SecureEndpointController(restTemplate, objectMapper));

    }

    @Test
    public void testHealthHealthOrdsFail() {
        Assertions.assertThrows(
                ORDSException.class, () -> healthController.getHealth(new GetHealth()));
    }

    @Test
    public void getSyncCivilAppearanceTest() {
        Assertions.assertThrows(
                ORDSException.class,
                () -> syncController.getSyncCivilAppearance(new GetSyncCivilAppearance()));
    }

    @Test
    public void getSyncFileDetailTest() {
        Assertions.assertThrows(
                ORDSException.class,
                () -> syncController.getFileDetailCivil(new GetFileDetailCivil()));
    }

    @Test
    public void getSyncSyncCivilHearingRestrictionTest() {
        Assertions.assertThrows(
                ORDSException.class,
                () ->
                        syncController.getSyncCivilHearingRestriction(
                                new GetSyncCivilHearingRestriction()));
    }

    @Test
    public void setSyncSyncCivilHearingRestrictionTest() {
        Assertions.assertThrows(
                ORDSException.class,
                () ->
                        syncController.setSyncCivilHearingRestriction(
                                new SetHearingRestrictionCivil()));
    }

    @Test
    public void getAppearanceCivilApprMethodSecureRequestTest() {
        Assertions.assertThrows(
                ORDSException.class,
                () ->
                        secureController.getAppearanceCivilApprMethodSecureRequest(
                                new GetAppearanceCivilApprMethodSecure()));
    }

    @Test
    public void getAppearanceCivilPartySecureTest() {
        Assertions.assertThrows(
                ORDSException.class,
                () ->
                        secureController.getAppearanceCivilPartySecure(
                                new GetAppearanceCivilPartySecure()));
    }

    @Test
    public void getAppearanceCivilSecureTest() {
           Assertions.assertThrows(
                ORDSException.class,
                () -> secureController.getAppearanceCivilSecure(new GetAppearanceCivilSecure()));
    }

    @Test
    public void getFileDetailCivilSecureTest() {
        Assertions.assertThrows(
                ORDSException.class,
                () -> secureController.getFileDetailCivilSecure(new GetFileDetailCivilSecure()));
    }

    @Test
    public void getAppearanceCivilTest() {
        var one = new GetAppearanceCivil();
        var two = new GetAppearanceCivilRequest();
        one.setGetAppearanceCivilRequest(two);
        var three = new ca.bc.gov.open.pcss.one.GetAppearanceCivilRequest();
        two.setGetAppearanceCivilRequest(three);
        three.setRequestDtm(Instant.now());

        Assertions.assertThrows(
                ORDSException.class, () -> appearanceController.getAppearanceCivil(one));
    }

    @Test
    public void getAppearanceCivilNullRequestTest() {
        Assertions.assertThrows(
                BadDateException.class,
                () -> appearanceController.getAppearanceCivil(new GetAppearanceCivil()));
    }

    @Test
    public void setAppearanceCivilTest() {
        Assertions.assertThrows(
                ORDSException.class,
                () -> appearanceController.setAppearanceCivil(new SetAppearanceCivil()));
    }

    @Test
    public void getAppearanceCivilApprMethodTest() {
        var one = new GetAppearanceCivilApprMethod();
        var two = new GetAppearanceCivilApprMethodRequest();
        var three = new ca.bc.gov.open.pcss.one.GetAppearanceCivilApprMethodRequest();
        three.setRequestDtm(Instant.now());
        two.setGetAppearanceCivilApprMethodRequest(three);
        one.setGetAppearanceCivilApprMethodRequest(two);

        Assertions.assertThrows(
                ORDSException.class, () -> appearanceController.getAppearanceCivilApprMethod(one));
    }

    @Test
    public void getAppearanceCivilApprMethodBadDateTest() {
        var one = new GetAppearanceCivilApprMethod();
        Assertions.assertThrows(
                BadDateException.class, () -> appearanceController.getAppearanceCivilApprMethod(one));
    }

    @Test
    public void setAppearanceMethodCivilTestBadDate() {
        Assertions.assertThrows(
                BadDateException.class,
                () -> appearanceController.setAppearanceMethodCivil(new SetAppearanceMethodCivil()));
    }

    @Test
    public void setAppearanceMethodCivilTest() {
        var one = new SetAppearanceMethodCivil();
        var two = new SetAppearanceMethodCivilRequest();
        var three = new ca.bc.gov.open.pcss.one.SetAppearanceMethodCivilRequest();
        one.setSetAppearanceMethodCivilRequest(two);
        two.setSetAppearanceMethodCivilRequest(three);
        three.setRequestDtm(Instant.now());

        Assertions.assertThrows(
                ORDSException.class, () -> appearanceController.setAppearanceMethodCivil(one));
    }

    @Test
    public void getAppearanceCivilDocumentTest() {
        Assertions.assertThrows(
                ORDSException.class,
                () ->
                        appearanceController.getAppearanceCivilDocument(
                                new GetAppearanceCivilDocument()));
    }

    @Test
    public void getAppearanceCivilPartyTest() {
        Assertions.assertThrows(
                ORDSException.class,
                () -> appearanceController.getAppearanceCivilParty(new GetAppearanceCivilParty()));
    }

    @Test
    public void getAppearanceCivilResourceTest() {
        Assertions.assertThrows(
                ORDSException.class,
                () ->
                        appearanceController.getAppearanceCivilResource(
                                new GetAppearanceCivilResource()));
    }

    @Test
    public void setCounselDetailCivilTest() {
        Assertions.assertThrows(
                ORDSException.class,
                () -> appearanceController.setCounselDetailCivil(new SetCounselDetailCivil()));
    }

    @Test
    public void badDateTests() {
        var one = new GetAppearanceCivil();
        var two = new GetAppearanceCivilRequest();
        one.setGetAppearanceCivilRequest(two);
        var three = new ca.bc.gov.open.pcss.one.GetAppearanceCivilRequest();
        two.setGetAppearanceCivilRequest(three);

        Assertions.assertThrows(
                BadDateException.class, () -> appearanceController.getAppearanceCivil(one));
    }

    @Test
    public void securityTestFail_Then401() throws Exception {
        mockMvc.perform(post("/ws").contentType(MediaType.TEXT_XML))
                .andExpect(status().is4xxClientError())
                .andReturn();
    }
}
