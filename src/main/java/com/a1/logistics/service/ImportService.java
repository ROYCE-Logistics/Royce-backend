package com.a1.logistics.service;

import com.a1.logistics.dto.job.JobDto;
import org.springframework.web.multipart.MultipartFile;

public interface ImportService {
    JobDto importLoads(MultipartFile file);
    JobDto importDrivers(MultipartFile file);
}
