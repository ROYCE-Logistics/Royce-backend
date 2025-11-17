package com.a1.logistics.service.impl;

import com.a1.logistics.dto.job.JobDto;
import com.a1.logistics.entity.ImportJob;
import com.a1.logistics.entity.JobStatus;
import com.a1.logistics.repository.ImportJobRepository;
import com.a1.logistics.service.ImportService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;

@Service
public class ImportServiceImpl implements ImportService {

    private final ImportJobRepository importJobRepository;

    public ImportServiceImpl(ImportJobRepository importJobRepository) {
        this.importJobRepository = importJobRepository;
    }

    @Override
    public JobDto importLoads(MultipartFile file) {
        return runImport("LOADS", file);
    }

    @Override
    public JobDto importDrivers(MultipartFile file) {
        return runImport("DRIVERS", file);
    }

    private JobDto runImport(String type, MultipartFile file) {
        ImportJob job = new ImportJob();
        job.setType(type);
        job.setStatus(JobStatus.RUNNING);
        job.setCreatedAt(Instant.now());
        job = importJobRepository.save(job);
        try {
            Path storage = Paths.get("imports");
            Files.createDirectories(storage);
            Path target = storage.resolve(job.getId() + "-" + file.getOriginalFilename());
            file.transferTo(target);
            // Parsing logic placeholder
            job.setSourceFile(target.toString());
            job.setStatus(JobStatus.COMPLETED);
        } catch (IOException ex) {
            job.setStatus(JobStatus.FAILED);
            job.setErrorMessage(ex.getMessage());
        }
        job.setFinishedAt(Instant.now());
        ImportJob saved = importJobRepository.save(job);
        return new JobDto(saved.getId(), saved.getType(), saved.getSourceFile(), saved.getStatus(), saved.getCreatedAt(), saved.getFinishedAt(), null);
    }
}
