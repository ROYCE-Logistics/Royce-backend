package com.a1.logistics.service.impl;

import com.a1.logistics.dto.document.DocumentDto;
import com.a1.logistics.entity.Document;
import com.a1.logistics.entity.DocumentType;
import com.a1.logistics.repository.DocumentRepository;
import com.a1.logistics.repository.DriverRepository;
import com.a1.logistics.repository.LoadRepository;
import com.a1.logistics.repository.UserRepository;
import com.a1.logistics.service.DocumentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;

@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final LoadRepository loadRepository;
    private final DriverRepository driverRepository;
    private final UserRepository userRepository;

    public DocumentServiceImpl(DocumentRepository documentRepository,
                               LoadRepository loadRepository,
                               DriverRepository driverRepository,
                               UserRepository userRepository) {
        this.documentRepository = documentRepository;
        this.loadRepository = loadRepository;
        this.driverRepository = driverRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Page<DocumentDto> list(Pageable pageable) {
        return documentRepository.findAll(pageable).map(this::toDto);
    }

    @Override
    public DocumentDto upload(Long loadId, Long driverId, String type, MultipartFile file, String username) {
        try {
            Path storage = Paths.get("storage");
            Files.createDirectories(storage);
            Path destination = storage.resolve(file.getOriginalFilename());
            file.transferTo(destination);
            Document document = new Document();
            document.setRelatedLoad(loadId != null ? loadRepository.findById(loadId).orElse(null) : null);
            document.setRelatedDriver(driverId != null ? driverRepository.findById(driverId).orElse(null) : null);
            document.setType(DocumentType.valueOf(type.toUpperCase()));
            document.setFileName(file.getOriginalFilename());
            document.setFilePath(destination.toString());
            document.setUploadedAt(Instant.now());
            document.setUploadedBy(username != null ? userRepository.findByEmail(username).orElse(null) : null);
            return toDto(documentRepository.save(document));
        } catch (IOException e) {
            throw new IllegalArgumentException("Failed to store document", e);
        }
    }

    private DocumentDto toDto(Document document) {
        return new DocumentDto(document.getId(), document.getType(), document.getFileName(), document.getFilePath(),
                document.getRelatedLoad() != null ? document.getRelatedLoad().getId() : null,
                document.getRelatedDriver() != null ? document.getRelatedDriver().getId() : null,
                document.getUploadedAt(),
                document.getUploadedBy() != null ? document.getUploadedBy().getId() : null);
    }
}
