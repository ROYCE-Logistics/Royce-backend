package com.a1.logistics.controller;

import com.a1.logistics.dto.truck.TruckDto;
import com.a1.logistics.dto.truck.TruckRequest;
import com.a1.logistics.service.TruckService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trucks")
public class TruckController {

    private final TruckService truckService;

    public TruckController(TruckService truckService) {
        this.truckService = truckService;
    }

    @GetMapping
    public Page<TruckDto> list(Pageable pageable) {
        return truckService.list(pageable);
    }

    @GetMapping("/{id}")
    public TruckDto get(@PathVariable Long id) {
        return truckService.get(id);
    }

    @PostMapping
    public TruckDto create(@Valid @RequestBody TruckRequest request) {
        return truckService.create(request);
    }

    @PutMapping("/{id}")
    public TruckDto update(@PathVariable Long id, @Valid @RequestBody TruckRequest request) {
        return truckService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        truckService.delete(id);
    }
}
