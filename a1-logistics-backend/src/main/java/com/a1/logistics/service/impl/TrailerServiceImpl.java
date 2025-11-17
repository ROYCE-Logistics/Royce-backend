package com.a1.logistics.service.impl;

import com.a1.logistics.dto.trailer.TrailerDto;
import com.a1.logistics.dto.trailer.TrailerRequest;
import com.a1.logistics.entity.Trailer;
import com.a1.logistics.repository.TrailerRepository;
import com.a1.logistics.service.TrailerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TrailerServiceImpl implements TrailerService {

    private final TrailerRepository trailerRepository;

    public TrailerServiceImpl(TrailerRepository trailerRepository) {
        this.trailerRepository = trailerRepository;
    }

    @Override
    public Page<TrailerDto> list(Pageable pageable) {
        return trailerRepository.findAll(pageable).map(this::toDto);
    }

    @Override
    public TrailerDto get(Long id) {
        return trailerRepository.findById(id).map(this::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Trailer not found"));
    }

    @Override
    public TrailerDto create(TrailerRequest request) {
        Trailer trailer = new Trailer();
        apply(request, trailer);
        return toDto(trailerRepository.save(trailer));
    }

    @Override
    public TrailerDto update(Long id, TrailerRequest request) {
        Trailer trailer = trailerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Trailer not found"));
        apply(request, trailer);
        return toDto(trailerRepository.save(trailer));
    }

    @Override
    public void delete(Long id) {
        trailerRepository.deleteById(id);
    }

    private void apply(TrailerRequest request, Trailer trailer) {
        trailer.setPlateNumber(request.plateNumber());
        trailer.setStatus(request.status());
        trailer.setType(request.type());
    }

    private TrailerDto toDto(Trailer trailer) {
        return new TrailerDto(trailer.getId(), trailer.getPlateNumber(), trailer.getStatus(), trailer.getType());
    }
}
