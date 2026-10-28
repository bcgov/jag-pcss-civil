package ca.bc.gov.open.pcss.observability;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.http.HttpStatus;

class MainPortHealthControllerTest {
    @Test
    void returnsServiceUnavailableWhenHealthEndpointIsUnavailable() {
        var response = new MainPortHealthController(Optional.empty()).health();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SERVICE_UNAVAILABLE);
        assertThat(response.getBody()).containsEntry("status", "UNKNOWN");
    }

    @Test
    void returnsOkForUpHealth() {
        HealthEndpoint healthEndpoint = mock(HealthEndpoint.class);
        when(healthEndpoint.health()).thenReturn(Health.up().build());
        var response = new MainPortHealthController(Optional.of(healthEndpoint)).health();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).containsEntry("status", "UP");
    }

    @Test
    void returnsServiceUnavailableForNonUpHealth() {
        HealthEndpoint healthEndpoint = mock(HealthEndpoint.class);
        when(healthEndpoint.health()).thenReturn(Health.down().build());
        var response = new MainPortHealthController(Optional.of(healthEndpoint)).health();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SERVICE_UNAVAILABLE);
        assertThat(response.getBody()).containsEntry("status", "DOWN");
    }
}
