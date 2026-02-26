package com.slusarz.sandbox.custom.counter;


import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CounterService {
    private final Counter counter;
    public CounterService(@Autowired MeterRegistry registry) {
        counter = Counter.builder("sandbox.counter")
                .description("Sample counter metric")
                .register(registry);
    }

    public void count() {
        counter.increment();
    }

}
