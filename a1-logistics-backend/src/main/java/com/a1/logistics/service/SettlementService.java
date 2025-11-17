package com.a1.logistics.service;

import com.a1.logistics.dto.settlement.SettlementDto;
import com.a1.logistics.dto.settlement.SettlementRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SettlementService {
    Page<SettlementDto> list(Pageable pageable);
    SettlementDto generate(SettlementRequest request);
    SettlementDto markPaid(Long id);
}
