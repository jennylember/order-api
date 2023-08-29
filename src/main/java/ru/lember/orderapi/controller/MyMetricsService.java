package ru.lember.orderapi.controller;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public record MyMetricsService(MeterRegistry meterRegistry) {

    public void incrementMyMetric() {
        meterRegistry.counter("my_own_metric").increment();
    }

}

