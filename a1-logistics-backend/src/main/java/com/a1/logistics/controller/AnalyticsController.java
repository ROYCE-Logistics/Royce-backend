package com.a1.logistics.controller;

import com.a1.logistics.dto.analytics.AnalyticsResponse;
import com.a1.logistics.service.AnalyticsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("/dashboard")
    public AnalyticsResponse dashboard() {
        return analyticsService.dashboard();
    }

    @GetMapping("/revenue")
    public AnalyticsResponse revenueTrend() {
        return analyticsService.revenueTrend();
    }

    @GetMapping("/ai/revenue-forecast")
    public AnalyticsResponse aiRevenue() {
        return analyticsService.aiRevenueForecast();
    }

    @GetMapping("/ai/high-risk-drivers")
    public AnalyticsResponse aiDrivers() {
        return analyticsService.aiHighRiskDrivers();
    }

    @GetMapping("/ai/fleet-rebalancing")
    public AnalyticsResponse aiFleet() {
        return analyticsService.aiFleetRebalancing();
    }
}
