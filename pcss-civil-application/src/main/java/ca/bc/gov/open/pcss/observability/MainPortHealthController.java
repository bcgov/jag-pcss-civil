package ca.bc.gov.open.pcss.observability;

import java.util.Map;
import java.util.Optional;
import org.springframework.boot.actuate.health.HealthComponent;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.health.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainPortHealthController {
    private final Optional<HealthEndpoint> healthEndpoint;

    public MainPortHealthController(Optional<HealthEndpoint> healthEndpoint) {
        this.healthEndpoint = healthEndpoint;
    }

    @GetMapping("/actuator/health")
    public ResponseEntity<Map<String, String>> health() {
        if (healthEndpoint.isEmpty()) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body(Map.of("status", Status.UNKNOWN.getCode()));
        }
        HealthComponent health = healthEndpoint.get().health();
        Status status = health.getStatus();
        HttpStatus httpStatus =
                Status.UP.equals(status) ? HttpStatus.OK : HttpStatus.SERVICE_UNAVAILABLE;
        return ResponseEntity.status(httpStatus).body(Map.of("status", status.getCode()));
    }
}
