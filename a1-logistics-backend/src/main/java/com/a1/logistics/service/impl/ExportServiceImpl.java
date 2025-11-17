package com.a1.logistics.service.impl;

import com.a1.logistics.dto.job.JobDto;
import com.a1.logistics.entity.ExportJob;
import com.a1.logistics.entity.JobStatus;
import com.a1.logistics.repository.ExportJobRepository;
import com.a1.logistics.service.ExportService;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;

@Service
public class ExportServiceImpl implements ExportService {

    private final ExportJobRepository exportJobRepository;

    public ExportServiceImpl(ExportJobRepository exportJobRepository) {
        this.exportJobRepository = exportJobRepository;
    }

    @Override
    public JobDto exportLoads(String format) {
        ExportJob job = new ExportJob();
        job.setType("LOADS");
        job.setFormat(format);
        job.setStatus(JobStatus.RUNNING);
        job.setCreatedAt(Instant.now());
        job = exportJobRepository.save(job);
        try {
            Path exports = Paths.get("exports");
            Files.createDirectories(exports);
            Path file = exports.resolve("loads-" + job.getId() + "." + format);
            Files.writeString(file, "Sample export payload");
            job.setDownloadLink(file.toString());
            job.setStatus(JobStatus.COMPLETED);
        } catch (Exception ex) {
            job.setStatus(JobStatus.FAILED);
            job.setErrorMessage(ex.getMessage());
        }
        job.setFinishedAt(Instant.now());
        ExportJob saved = exportJobRepository.save(job);
        return new JobDto(saved.getId(), saved.getType(), saved.getFormat(), saved.getStatus(), saved.getCreatedAt(), saved.getFinishedAt(), saved.getDownloadLink());
    }
}
