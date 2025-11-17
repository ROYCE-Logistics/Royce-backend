package com.a1.logistics.controller;

import com.a1.logistics.dto.document.DocumentDto;
import com.a1.logistics.service.DocumentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/documents")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping
    public Page<DocumentDto> list(Pageable pageable) {
        return documentService.list(pageable);
    }

    @PostMapping
    public DocumentDto upload(@RequestParam(required = false) Long loadId,
                              @RequestParam(required = false) Long driverId,
                              @RequestParam String type,
                              @RequestParam MultipartFile file,
                              @AuthenticationPrincipal UserDetails userDetails) {
        return documentService.upload(loadId, driverId, type, file, userDetails != null ? userDetails.getUsername() : null);
    }
}
