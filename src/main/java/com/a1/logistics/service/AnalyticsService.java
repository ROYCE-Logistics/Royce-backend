package com.a1.logistics.service;

import com.a1.logistics.dto.analytics.AnalyticsResponse;

public interface AnalyticsService {
    AnalyticsResponse dashboard();
    AnalyticsResponse revenueTrend();
    AnalyticsResponse aiRevenueForecast();
    AnalyticsResponse aiHighRiskDrivers();
    AnalyticsResponse aiFleetRebalancing();
}
