package com.a1.logistics.service;

import com.a1.logistics.dto.job.JobDto;

public interface ExportService {
    JobDto exportLoads(String format);
}
