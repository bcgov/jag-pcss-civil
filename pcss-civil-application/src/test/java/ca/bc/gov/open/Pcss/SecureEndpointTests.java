package ca.bc.gov.open.Pcss;

import static org.mockito.Mockito.when;

import ca.bc.gov.open.Pcss.Controllers.SecureEndpointController;
import com.example.demp.wsdl.pcss.secure.two.GetAppearanceCivilApprMethodSecureRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        var resp = new com.example.demp.wsdl.pcss.secure.one.GetAppearanceCivilApprMethodResponse();

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
}
