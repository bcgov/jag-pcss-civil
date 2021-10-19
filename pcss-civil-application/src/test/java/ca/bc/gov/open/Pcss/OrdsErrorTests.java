package ca.bc.gov.open.Pcss;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import ca.bc.gov.open.Pcss.Controllers.AppearanceController;
import ca.bc.gov.open.Pcss.Controllers.HealthController;
import ca.bc.gov.open.Pcss.Controllers.SecureEndpointController;
import ca.bc.gov.open.Pcss.Controllers.SyncController;
import ca.bc.gov.open.Pcss.Exceptions.BadDateException;
import ca.bc.gov.open.Pcss.Exceptions.ORDSException;
import ca.bc.gov.open.pcss.secure.two.*;
import ca.bc.gov.open.pcss.three.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Instant;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class OrdsErrorTests {

    @Mock private RestTemplate restTemplate;

    @Autowired private ObjectMapper objectMapper;

    @Autowired private MockMvc mockMvc;

    @Test
    public void testHealthPingOrdsFail() throws JsonProcessingException {
        HealthController healthController = new HealthController(restTemplate, objectMapper);

        // Set up to mock ords response
        setUpRestTemplate();

        try {
            healthController.getPing(new GetPing());
        } catch (ORDSException ex) {
            // Exception caught as expected
            assert true;
            return;
        }
        // Test should never reach here
        assert false;
    }

    @Test
    public void testHealthHealthOrdsFail() throws JsonProcessingException {
        HealthController healthController = new HealthController(restTemplate, objectMapper);

        // Set up to mock ords response
        setUpRestTemplate();

        try {
            healthController.getHealth(new GetHealth());
        } catch (ORDSException ex) {
            // Exception caught as expected
            assert true;
            return;
        }
        // Test should never reach here
        assert false;
    }

    @Test
    public void getSyncCivilAppearanceTest() throws JsonProcessingException {
        SyncController syncController = new SyncController(restTemplate, objectMapper);

        setUpRestTemplate();

        try {
            syncController.getSyncCivilAppearance(new GetSyncCivilAppearance());
        } catch (ORDSException ex) {
            // Exception caught as expected
            assert true;
            return;
        }
        // Test should never reach here
        assert false;
    }

    @Test
    public void getSyncFileDetailTest() throws JsonProcessingException {
        SyncController syncController = new SyncController(restTemplate, objectMapper);

        setUpRestTemplate();

        try {
            syncController.getFileDetailCivil(new GetFileDetailCivil());
        } catch (ORDSException ex) {
            // Exception caught as expected
            assert true;
            return;
        }
        // Test should never reach here
        assert false;
    }

    @Test
    public void getSyncSyncCivilHearingRestrictionTest() throws JsonProcessingException {
        SyncController syncController = new SyncController(restTemplate, objectMapper);

        setUpRestTemplate();

        try {
            syncController.getSyncCivilHearingRestriction(new GetSyncCivilHearingRestriction());
        } catch (ORDSException ex) {
            // Exception caught as expected
            assert true;
            return;
        }
        // Test should never reach here
        assert false;
    }

    @Test
    public void setSyncSyncCivilHearingRestrictionTest() throws JsonProcessingException {
        SyncController syncController = new SyncController(restTemplate, objectMapper);

        setUpRestTemplate();

        try {
            syncController.setSyncCivilHearingRestriction(new SetHearingRestrictionCivil());
        } catch (ORDSException ex) {
            // Exception caught as expected
            assert true;
            return;
        }
        // Test should never reach here
        assert false;
    }

    @Test
    public void getAppearanceCivilApprMethodSecureRequestTest() throws JsonProcessingException {
        SecureEndpointController secureController =
                new SecureEndpointController(restTemplate, objectMapper);

        setUpRestTemplate();

        try {
            secureController.getAppearanceCivilApprMethodSecureRequest(
                    new GetAppearanceCivilApprMethodSecure());
        } catch (ORDSException ex) {
            // Exception caught as expected
            assert true;
            return;
        }
        // Test should never reach here
        assert false;
    }

    @Test
    public void getAppearanceCivilPartySecureTest() throws JsonProcessingException {
        SecureEndpointController secureController =
                new SecureEndpointController(restTemplate, objectMapper);

        setUpRestTemplate();

        try {
            secureController.getAppearanceCivilPartySecure(new GetAppearanceCivilPartySecure());
        } catch (ORDSException ex) {
            // Exception caught as expected
            assert true;
            return;
        }
        // Test should never reach here
        assert false;
    }

    @Test
    public void getAppearanceCivilSecureTest() throws JsonProcessingException {
        SecureEndpointController secureController =
                new SecureEndpointController(restTemplate, objectMapper);

        setUpRestTemplate();

        try {
            secureController.getAppearanceCivilSecure(new GetAppearanceCivilSecure());
        } catch (ORDSException ex) {
            // Exception caught as expected
            assert true;
            return;
        }
        // Test should never reach here
        assert false;
    }

    @Test
    public void getFileDetailCivilSecureTest() throws JsonProcessingException {
        SecureEndpointController secureController =
                new SecureEndpointController(restTemplate, objectMapper);

        setUpRestTemplate();

        try {
            secureController.getFileDetailCivilSecure(new GetFileDetailCivilSecure());
        } catch (ORDSException ex) {
            // Exception caught as expected
            assert true;
            return;
        }
        // Test should never reach here
        assert false;
    }

    @Test
    public void getAppearanceCivilTest() throws BadDateException, JsonProcessingException {
        AppearanceController secureController =
                new AppearanceController(restTemplate, objectMapper);

        setUpRestTemplate();

        try {
            var one = new GetAppearanceCivil();
            var two = new GetAppearanceCivilRequest();
            one.setGetAppearanceCivilRequest(two);
            var three = new ca.bc.gov.open.pcss.one.GetAppearanceCivilRequest();
            two.setGetAppearanceCivilRequest(three);
            three.setRequestDtm(Instant.now());
            secureController.getAppearanceCivil(one);
        } catch (ORDSException ex) {
            // Exception caught as expected
            assert true;
            return;
        }
        // Test should never reach here
        assert false;
    }

    @Test
    public void getAppearanceCivilNullRequestTest()
            throws BadDateException, JsonProcessingException {
        AppearanceController secureController =
                new AppearanceController(restTemplate, objectMapper);

        setUpRestTemplate();

        try {
            secureController.getAppearanceCivil(new GetAppearanceCivil());
        } catch (BadDateException ex) {
            // Exception caught as expected
            assert true;
            return;
        }
        // Test should never reach here
        assert false;
    }

    @Test
    public void setAppearanceCivilTest() throws JsonProcessingException {
        AppearanceController secureController =
                new AppearanceController(restTemplate, objectMapper);

        setUpRestTemplate();

        try {
            secureController.setAppearanceCivil(new SetAppearanceCivil());
        } catch (ORDSException ex) {
            // Exception caught as expected
            assert true;
            return;
        }
        // Test should never reach here
        assert false;
    }

    @Test
    public void getAppearanceCivilApprMethodTest()
            throws JsonProcessingException, BadDateException {
        AppearanceController secureController =
                new AppearanceController(restTemplate, objectMapper);

        setUpRestTemplate();

        try {
            var one = new GetAppearanceCivilApprMethod();
            var two = new GetAppearanceCivilApprMethodRequest();
            var three = new ca.bc.gov.open.pcss.one.GetAppearanceCivilApprMethodRequest();
            three.setRequestDtm(Instant.now());
            two.setGetAppearanceCivilApprMethodRequest(three);
            one.setGetAppearanceCivilApprMethodRequest(two);
            secureController.getAppearanceCivilApprMethod(one);
        } catch (ORDSException ex) {
            // Exception caught as expected
            assert true;
            return;
        }
        // Test should never reach here
        assert false;
    }

    @Test
    public void getAppearanceCivilApprMethodBadDateTest()
            throws JsonProcessingException, BadDateException {
        AppearanceController secureController =
                new AppearanceController(restTemplate, objectMapper);

        setUpRestTemplate();

        try {
            var one = new GetAppearanceCivilApprMethod();
            secureController.getAppearanceCivilApprMethod(one);
        } catch (BadDateException ex) {
            // Exception caught as expected
            assert true;
            return;
        }
        // Test should never reach here
        assert false;
    }

    @Test
    public void setAppearanceMethodCivilTestBadDate() throws JsonProcessingException {
        AppearanceController secureController =
                new AppearanceController(restTemplate, objectMapper);

        setUpRestTemplate();

        try {
            secureController.setAppearanceMethodCivil(new SetAppearanceMethodCivil());
        } catch (BadDateException ex) {
            // Exception caught as expected
            assert true;
            return;
        }
        // Test should never reach here
        assert false;
    }

    @Test
    public void setAppearanceMethodCivilTest() throws JsonProcessingException, BadDateException {
        AppearanceController secureController =
                new AppearanceController(restTemplate, objectMapper);

        setUpRestTemplate();

        try {
            var one = new SetAppearanceMethodCivil();
            var two = new SetAppearanceMethodCivilRequest();
            var three = new ca.bc.gov.open.pcss.one.SetAppearanceMethodCivilRequest();
            one.setSetAppearanceMethodCivilRequest(two);
            two.setSetAppearanceMethodCivilRequest(three);
            three.setRequestDtm(Instant.now());
            secureController.setAppearanceMethodCivil(one);
        } catch (ORDSException ex) {
            // Exception caught as expected
            assert true;
            return;
        }
        // Test should never reach here
        assert false;
    }

    @Test
    public void getAppearanceCivilDocumentTest() throws JsonProcessingException {
        AppearanceController secureController =
                new AppearanceController(restTemplate, objectMapper);

        setUpRestTemplate();

        try {
            secureController.getAppearanceCivilDocument(new GetAppearanceCivilDocument());
        } catch (ORDSException ex) {
            // Exception caught as expected
            assert true;
            return;
        }
        // Test should never reach here
        assert false;
    }

    @Test
    public void getAppearanceCivilPartyTest() throws JsonProcessingException {
        AppearanceController secureController =
                new AppearanceController(restTemplate, objectMapper);

        setUpRestTemplate();

        try {
            secureController.getAppearanceCivilParty(new GetAppearanceCivilParty());
        } catch (ORDSException ex) {
            // Exception caught as expected
            assert true;
            return;
        }
        // Test should never reach here
        assert false;
    }

    @Test
    public void getAppearanceCivilResourceTest() throws JsonProcessingException {
        AppearanceController secureController =
                new AppearanceController(restTemplate, objectMapper);

        setUpRestTemplate();

        try {
            secureController.getAppearanceCivilResource(new GetAppearanceCivilResource());
        } catch (ORDSException ex) {
            // Exception caught as expected
            assert true;
            return;
        }
        // Test should never reach here
        assert false;
    }

    @Test
    public void setCounselDetailCivilTest() throws JsonProcessingException {
        AppearanceController secureController =
                new AppearanceController(restTemplate, objectMapper);

        setUpRestTemplate();

        try {
            secureController.setCounselDetailCivil(new SetCounselDetailCivil());
        } catch (ORDSException ex) {
            // Exception caught as expected
            assert true;
            return;
        }
        // Test should never reach here
        assert false;
    }

    private void setUpRestTemplate() {
        when(restTemplate.exchange(
                        Mockito.any(String.class),
                        Mockito.eq(HttpMethod.GET),
                        Mockito.<HttpEntity<String>>any(),
                        Mockito.<Class<Object>>any()))
                .thenThrow(new RestClientException("BAD"));

        when(restTemplate.exchange(
                        Mockito.any(String.class),
                        Mockito.eq(HttpMethod.POST),
                        Mockito.<HttpEntity<String>>any(),
                        Mockito.<Class<Object>>any()))
                .thenThrow(new RestClientException("BAD"));

        when(restTemplate.exchange(
                        Mockito.any(String.class),
                        Mockito.eq(HttpMethod.PUT),
                        Mockito.<HttpEntity<String>>any(),
                        Mockito.<Class<Object>>any()))
                .thenThrow(new RestClientException("BAD"));

        when(restTemplate.exchange(
                        Mockito.any(String.class),
                        Mockito.eq(HttpMethod.DELETE),
                        Mockito.<HttpEntity<String>>any(),
                        Mockito.<Class<Object>>any()))
                .thenThrow(new RestClientException("BAD"));
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

        try {
            secureController.getAppearanceCivil(one);
        } catch (BadDateException | JsonProcessingException ex) {
            assert true;
            return;
        }
        assert false;
    }

    @Test
    public void securityTestFail_Then403() throws Exception {
        var response =
                mockMvc.perform(post("/ws").contentType(MediaType.TEXT_XML))
                        .andExpect(status().is4xxClientError())
                        .andReturn();
        assert response.getResponse().getStatus() == 401;
    }
}
