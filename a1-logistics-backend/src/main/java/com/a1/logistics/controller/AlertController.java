package com.a1.logistics.controller;

import com.a1.logistics.dto.alert.AlertDto;
import com.a1.logistics.dto.alert.AlertRequest;
import com.a1.logistics.service.AlertService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alerts")
public class AlertController {

    private final AlertService alertService;

    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    @GetMapping
    public Page<AlertDto> list(Pageable pageable) {
        return alertService.list(pageable);
    }

    @PostMapping
    public AlertDto create(@Valid @RequestBody AlertRequest request) {
        return alertService.create(request);
    }

    @PostMapping("/{id}/read")
    public AlertDto markRead(@PathVariable Long id) {
        return alertService.markRead(id);
    }
}
