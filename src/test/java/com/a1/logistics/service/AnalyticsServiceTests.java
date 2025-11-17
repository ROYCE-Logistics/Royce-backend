package com.a1.logistics.service;

import com.a1.logistics.service.impl.AnalyticsServiceImpl;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AnalyticsServiceTests {

    private final AnalyticsServiceImpl analyticsService = new AnalyticsServiceImpl();

    @Test
    void dashboardReturnsMetrics() {
        var response = analyticsService.dashboard();
        assertThat(response.metrics()).containsKeys("activeDrivers", "fleetUtilization");
    }
}
