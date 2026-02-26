package com.slusarz.sandbox.custom.timer;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TimerService {

    private final Timer timer;

    public TimerService(@Autowired MeterRegistry registry) {
        timer = Timer.builder("sandbox.time")
                .description("Sample time metrics")
                .register(registry);
    }

    public void timer() {
        timer.record(() -> {
            try {
                Thread.sleep(1000); // Simulating delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }
}
