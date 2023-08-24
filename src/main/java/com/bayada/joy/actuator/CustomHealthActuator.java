package com.bayada.joy.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthActuator implements HealthIndicator {
    // Implement the health() method from the HealthIndicator interface
    @Override
    public Health health() {
        // Return a Health object representing an "up" status
        return Health.up().build();
    }
}
