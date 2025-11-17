package com.a1.logistics.controller;

import com.a1.logistics.dto.job.JobDto;
import com.a1.logistics.service.ImportService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/import")
public class ImportController {

    private final ImportService importService;

    public ImportController(ImportService importService) {
        this.importService = importService;
    }

    @PostMapping("/loads")
    public JobDto importLoads(@RequestParam MultipartFile file) {
        return importService.importLoads(file);
    }

    @PostMapping("/drivers")
    public JobDto importDrivers(@RequestParam MultipartFile file) {
        return importService.importDrivers(file);
    }
}
