package com.slusarz.sandbox.custom.gauge;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class GaugeService {
    private final AtomicInteger requests;

    public GaugeService(@Autowired MeterRegistry registry) {
        requests = new AtomicInteger(0);
        Gauge.builder("sandbox.gauge", () -> requests)
                .description("Sample gauge metric")
                .register(registry);
    }

    public void gaugeAdd() {
        requests.incrementAndGet();
    }

    public void gaugeDelete() {
        requests.decrementAndGet();
    }
}
