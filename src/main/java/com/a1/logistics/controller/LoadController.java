package com.a1.logistics.controller;

import com.a1.logistics.dto.load.LoadAssignmentRequest;
import com.a1.logistics.dto.load.LoadDto;
import com.a1.logistics.dto.load.LoadRequest;
import com.a1.logistics.dto.load.LoadStatusUpdateRequest;
import com.a1.logistics.service.LoadService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loads")
public class LoadController {

    private final LoadService loadService;

    public LoadController(LoadService loadService) {
        this.loadService = loadService;
    }

    @GetMapping
    public Page<LoadDto> list(Pageable pageable) {
        return loadService.list(pageable);
    }

    @GetMapping(params = "status")
    public Page<LoadDto> listByStatus(@RequestParam String status, Pageable pageable) {
        return loadService.listByStatus(status, pageable);
    }

    @GetMapping("/{id}")
    public LoadDto get(@PathVariable Long id) {
        return loadService.get(id);
    }

    @PostMapping
    public LoadDto create(@Valid @RequestBody LoadRequest request) {
        return loadService.create(request);
    }

    @PutMapping("/{id}")
    public LoadDto update(@PathVariable Long id, @Valid @RequestBody LoadRequest request) {
        return loadService.update(id, request);
    }

    @PostMapping("/{id}/assign")
    public LoadDto assign(@PathVariable Long id, @RequestBody LoadAssignmentRequest request) {
        return loadService.assign(id, request);
    }

    @PostMapping("/{id}/status")
    public LoadDto updateStatus(@PathVariable Long id,
                                @Valid @RequestBody LoadStatusUpdateRequest request,
                                @AuthenticationPrincipal UserDetails userDetails) {
        return loadService.updateStatus(id, request, userDetails != null ? userDetails.getUsername() : null);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        loadService.delete(id);
    }

    @GetMapping("/{id}/history")
    public java.util.List<com.a1.logistics.dto.load.LoadStatusHistoryDto> history(@PathVariable Long id) {
        return loadService.history(id);
    }
}
