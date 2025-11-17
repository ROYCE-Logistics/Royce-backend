package com.a1.logistics.service;

import com.a1.logistics.dto.load.LoadAssignmentRequest;
import com.a1.logistics.dto.load.LoadDto;
import com.a1.logistics.dto.load.LoadRequest;
import com.a1.logistics.dto.load.LoadStatusUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LoadService {
    Page<LoadDto> list(Pageable pageable);
    Page<LoadDto> listByStatus(String status, Pageable pageable);
    LoadDto get(Long id);
    LoadDto create(LoadRequest request);
    LoadDto update(Long id, LoadRequest request);
    LoadDto assign(Long id, LoadAssignmentRequest request);
    LoadDto updateStatus(Long id, LoadStatusUpdateRequest request, String username);
    void delete(Long id);
    java.util.List<com.a1.logistics.dto.load.LoadStatusHistoryDto> history(Long loadId);
}
