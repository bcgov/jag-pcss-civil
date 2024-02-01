package ca.bc.gov.open.pcss;

import static org.mockito.Mockito.when;

import ca.bc.gov.open.pcss.controllers.HealthController;
import ca.bc.gov.open.pcss.three.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HealthControllerTests {

    @Mock private ObjectMapper objectMapper;
    @Mock private RestTemplate restTemplate;
    @Mock private HealthController healthController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        healthController = Mockito.spy(new HealthController(restTemplate, objectMapper));
    }

    @Test
    public void getPingTest() throws JsonProcessingException {

        var get = new GetPing();

        var out = new GetPingResponse();
        out.setStatus("A");

        ResponseEntity<GetPingResponse> responseEntity = new ResponseEntity<>(out, HttpStatus.OK);

        when(restTemplate.exchange(
                        Mockito.any(String.class),
                        Mockito.eq(HttpMethod.GET),
                        Mockito.<HttpEntity<String>>any(),
                        Mockito.<Class<GetPingResponse>>any()))
                .thenReturn(responseEntity);

        var resp = healthController.getPing(get);

        Assertions.assertNotNull(resp);
    }

    @Test
    public void getHealthTest() throws JsonProcessingException {
        var get = new GetHealth();

        var out = new GetHealthResponse();
        out.setAppid("A");
        out.setCompatibility("A");
        out.setHost("A");
        out.setStatus("A");
        out.setInstance("A");
        out.setVersion("A");
        out.setMethod("A");

        ResponseEntity<GetHealthResponse> responseEntity = new ResponseEntity<>(out, HttpStatus.OK);

        when(restTemplate.exchange(
                        Mockito.any(String.class),
                        Mockito.eq(HttpMethod.GET),
                        Mockito.<HttpEntity<String>>any(),
                        Mockito.<Class<GetHealthResponse>>any()))
                .thenReturn(responseEntity);

        var resp = healthController.getHealth(get);

        Assertions.assertNotNull(resp);
    }
}
