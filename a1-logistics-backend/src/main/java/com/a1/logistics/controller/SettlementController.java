package com.a1.logistics.controller;

import com.a1.logistics.dto.settlement.SettlementDto;
import com.a1.logistics.dto.settlement.SettlementRequest;
import com.a1.logistics.service.SettlementService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/settlements")
public class SettlementController {

    private final SettlementService settlementService;

    public SettlementController(SettlementService settlementService) {
        this.settlementService = settlementService;
    }

    @GetMapping
    public Page<SettlementDto> list(Pageable pageable) {
        return settlementService.list(pageable);
    }

    @PostMapping
    public SettlementDto generate(@Valid @RequestBody SettlementRequest request) {
        return settlementService.generate(request);
    }

    @PostMapping("/{id}/paid")
    public SettlementDto markPaid(@PathVariable Long id) {
        return settlementService.markPaid(id);
    }
}
