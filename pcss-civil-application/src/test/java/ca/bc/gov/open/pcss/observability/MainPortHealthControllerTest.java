package ca.bc.gov.open.pcss.observability;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.boot.health.actuate.endpoint.HealthDescriptor;
import org.springframework.boot.health.actuate.endpoint.HealthEndpoint;
import org.springframework.boot.health.actuate.endpoint.IndicatedHealthDescriptor;
import org.springframework.boot.health.contributor.Status;
import org.springframework.http.HttpStatus;

class MainPortHealthControllerTest {
    private static HealthDescriptor healthDescriptor(Status status) {
        // HealthDescriptor is sealed/abstract, so mock its concrete (final) permitted subclass
        IndicatedHealthDescriptor descriptor = mock(IndicatedHealthDescriptor.class);
        when(descriptor.getStatus()).thenReturn(status);
        return descriptor;
    }

    @Test
    void returnsServiceUnavailableWhenHealthEndpointIsUnavailable() {
        var response = new MainPortHealthController(Optional.empty()).health();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SERVICE_UNAVAILABLE);
        assertThat(response.getBody()).containsEntry("status", "UNKNOWN");
    }

    @Test
    void returnsOkForUpHealth() {
        HealthEndpoint healthEndpoint = mock(HealthEndpoint.class);
        HealthDescriptor up = healthDescriptor(Status.UP);
        when(healthEndpoint.health()).thenReturn(up);
        var response = new MainPortHealthController(Optional.of(healthEndpoint)).health();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).containsEntry("status", "UP");
    }

    @Test
    void returnsServiceUnavailableForNonUpHealth() {
        HealthEndpoint healthEndpoint = mock(HealthEndpoint.class);
        HealthDescriptor down = healthDescriptor(Status.DOWN);
        when(healthEndpoint.health()).thenReturn(down);
        var response = new MainPortHealthController(Optional.of(healthEndpoint)).health();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SERVICE_UNAVAILABLE);
        assertThat(response.getBody()).containsEntry("status", "DOWN");
    }
}
