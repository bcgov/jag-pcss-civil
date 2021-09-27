package ca.bc.gov.open.Pcss;

import static org.mockito.Mockito.when;

import ca.bc.gov.open.Pcss.Controllers.HealthController;
import com.example.demp.wsdl.pcss.three.*;
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
public class HealthControllerTests {

    @Mock private RestTemplate restTemplate = new RestTemplate();

    private HealthController healthController;

    @Test
    public void getPingTest() {
        healthController = new HealthController(restTemplate);

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

        assert resp != null;
    }

    @Test
    public void getHealthTest() {
        healthController = new HealthController(restTemplate);

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

        assert resp != null;
    }
}
