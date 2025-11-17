package com.a1.logistics.service;

import com.a1.logistics.dto.document.DocumentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface DocumentService {
    Page<DocumentDto> list(Pageable pageable);
    DocumentDto upload(Long loadId, Long driverId, String type, MultipartFile file, String username);
}
