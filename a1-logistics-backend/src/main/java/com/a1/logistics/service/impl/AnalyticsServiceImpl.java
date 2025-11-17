package com.a1.logistics.service.impl;

import com.a1.logistics.dto.analytics.AnalyticsResponse;
import com.a1.logistics.service.AnalyticsService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AnalyticsServiceImpl implements AnalyticsService {

    @Override
    public AnalyticsResponse dashboard() {
        return new AnalyticsResponse(Map.of(
                "activeDrivers", 42,
                "fleetUtilization", 0.82,
                "pendingLoads", 12,
                "amazonMix", Map.of("amazon", 65, "other", 35)
        ));
    }

    @Override
    public AnalyticsResponse revenueTrend() {
        return new AnalyticsResponse(Map.of(
                "months", new String[]{"Jan", "Feb", "Mar", "Apr"},
                "revenue", new double[]{120_000, 140_000, 135_000, 150_000}
        ));
    }

    @Override
    public AnalyticsResponse aiRevenueForecast() {
        return new AnalyticsResponse(Map.of("forecast", "Revenue expected to grow 8% next quarter"));
    }

    @Override
    public AnalyticsResponse aiHighRiskDrivers() {
        return new AnalyticsResponse(Map.of("drivers", new String[]{"John Doe", "Maria Vega"}));
    }

    @Override
    public AnalyticsResponse aiFleetRebalancing() {
        return new AnalyticsResponse(Map.of("suggestion", "Move 2 reefers from ATL to DAL terminal"));
    }
}
