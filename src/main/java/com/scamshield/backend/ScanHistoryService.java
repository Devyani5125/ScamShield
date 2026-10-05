package com.scamshield.backend;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScanHistoryService {

    private final ScanHistoryRepository repository;

    public ScanHistoryService(ScanHistoryRepository repository) {
        this.repository = repository;
    }

    public ScanHistory saveScan(
            String scanType,
            String inputData,
            int riskScore,
            String riskLevel,
            String scamType,
            String recommendedAction) {

        ScanHistory scan = new ScanHistory();

        scan.setScanType(scanType);
        scan.setInputData(inputData);
        scan.setRiskScore(riskScore);
        scan.setRiskLevel(riskLevel);
        scan.setScamType(scamType);
        scan.setRecommendedAction(recommendedAction);
        scan.setScannedAt(LocalDateTime.now());

        return repository.save(scan);
    }

    public List<ScanHistory> getAllScans() {
        return repository.findAll();
    }

    public Map<String, Long> getDashboardStats() {

        Map<String, Long> stats = new HashMap<>();

        stats.put("totalScans", repository.count());

        stats.put("criticalScans",
                repository.countByRiskLevel("CRITICAL"));

        stats.put("highScans",
                repository.countByRiskLevel("HIGH"));

        stats.put("mediumScans",
                repository.countByRiskLevel("MEDIUM"));

        stats.put("lowScans",
                repository.countByRiskLevel("LOW"));

        stats.put("messageScans",
                repository.countByScanType("MESSAGE"));

        stats.put("urlScans",
                repository.countByScanType("URL"));

        stats.put("qrScans",
                repository.countByScanType("QR"));

        stats.put("screenshotScans",
                repository.countByScanType("SCREENSHOT"));

        return stats;
    }
}