package com.a1.logistics.controller;

import com.a1.logistics.dto.driver.DriverDto;
import com.a1.logistics.dto.driver.DriverRequest;
import com.a1.logistics.service.DriverService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/drivers")
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping
    public Page<DriverDto> list(Pageable pageable) {
        return driverService.list(pageable);
    }

    @GetMapping("/active")
    public Page<DriverDto> listActive(Pageable pageable) {
        return driverService.listActive(pageable);
    }

    @GetMapping("/{id}")
    public DriverDto get(@PathVariable Long id) {
        return driverService.get(id);
    }

    @PostMapping
    public DriverDto create(@Valid @RequestBody DriverRequest request) {
        return driverService.create(request);
    }

    @PutMapping("/{id}")
    public DriverDto update(@PathVariable Long id, @Valid @RequestBody DriverRequest request) {
        return driverService.update(id, request);
    }

    @PostMapping("/{id}/online")
    public void setOnline(@PathVariable Long id, @RequestParam boolean online) {
        driverService.setOnlineStatus(id, online);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        driverService.delete(id);
    }
}
