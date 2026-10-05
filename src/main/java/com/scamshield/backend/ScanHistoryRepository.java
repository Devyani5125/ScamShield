package com.scamshield.backend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScanHistoryRepository
        extends JpaRepository<ScanHistory, Long> {

    long countByRiskLevel(String riskLevel);

    long countByScanType(String scanType);
}