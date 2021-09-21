package ca.bc.gov.open.Pcss;

import ca.bc.gov.open.Pcss.Controllers.AppearanceController;
import com.example.demp.wsdl.pcss.three.GetAppearanceCivil;
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

import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class FakeTest {

    @Mock
    private RestTemplate restTemplate = new RestTemplate();

    @Test
    public void test() {
        AppearanceController controller = new AppearanceController(restTemplate);
        ResponseEntity<com.example.demp.wsdl.pcss.one.GetAppearanceCivilResponse> responseEntity =
                new ResponseEntity<>(new com.example.demp.wsdl.pcss.one.GetAppearanceCivilResponse(), HttpStatus.OK);

        when(restTemplate.exchange(
                Mockito.any(String.class),
                Mockito.eq(HttpMethod.GET),
                Mockito.<HttpEntity<String>>any(),
                Mockito.<Class<com.example.demp.wsdl.pcss.one.GetAppearanceCivilResponse>>any()))
                .thenReturn(responseEntity);

        var out = controller.getAppearanceCivil(new GetAppearanceCivil());

        assert out != null;
    }
}
