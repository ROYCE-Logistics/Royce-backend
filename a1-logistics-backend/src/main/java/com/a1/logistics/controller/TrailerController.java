package com.a1.logistics.controller;

import com.a1.logistics.dto.trailer.TrailerDto;
import com.a1.logistics.dto.trailer.TrailerRequest;
import com.a1.logistics.service.TrailerService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trailers")
public class TrailerController {

    private final TrailerService trailerService;

    public TrailerController(TrailerService trailerService) {
        this.trailerService = trailerService;
    }

    @GetMapping
    public Page<TrailerDto> list(Pageable pageable) {
        return trailerService.list(pageable);
    }

    @GetMapping("/{id}")
    public TrailerDto get(@PathVariable Long id) {
        return trailerService.get(id);
    }

    @PostMapping
    public TrailerDto create(@Valid @RequestBody TrailerRequest request) {
        return trailerService.create(request);
    }

    @PutMapping("/{id}")
    public TrailerDto update(@PathVariable Long id, @Valid @RequestBody TrailerRequest request) {
        return trailerService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        trailerService.delete(id);
    }
}
