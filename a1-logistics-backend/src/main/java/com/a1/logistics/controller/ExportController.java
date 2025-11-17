package com.a1.logistics.controller;

import com.a1.logistics.dto.job.JobDto;
import com.a1.logistics.service.ExportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/export")
public class ExportController {

    private final ExportService exportService;

    public ExportController(ExportService exportService) {
        this.exportService = exportService;
    }

    @GetMapping("/loads")
    public JobDto exportLoads(@RequestParam(defaultValue = "csv") String format) {
        return exportService.exportLoads(format);
    }
}
