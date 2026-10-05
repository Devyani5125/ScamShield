package com.scamshield.backend;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ScamController {

    private final ScamAnalyzer scamAnalyzer;
    private final UrlAnalyzer urlAnalyzer;
    private final OpenAIService openAIService;
    private final DemoAIService demoAIService;
    private final ScanHistoryService scanHistoryService;

    public ScamController(
            ScamAnalyzer scamAnalyzer,
            UrlAnalyzer urlAnalyzer,
            OpenAIService openAIService,
            DemoAIService demoAIService,
            ScanHistoryService scanHistoryService) {

        this.scamAnalyzer = scamAnalyzer;
        this.urlAnalyzer = urlAnalyzer;
        this.openAIService = openAIService;
        this.demoAIService = demoAIService;
        this.scanHistoryService = scanHistoryService;
    }

    // =========================
    // BACKEND TEST
    // =========================

    @GetMapping("/test")
    public String test() {
        return "ScamShield Backend is working!";
    }


    // =========================
    // MESSAGE ANALYSIS
    // =========================

    @PostMapping("/analyze")
    public ScamAnalysis analyze(@RequestBody String message) {

        ScamAnalysis result = scamAnalyzer.analyze(message);

        scanHistoryService.saveScan(
                "MESSAGE",
                message,
                result.getRiskScore(),
                result.getRiskLevel(),
                result.getScamType(),
                result.getRecommendedAction()
        );

        return result;
    }


    // =========================
    // SCREENSHOT ANALYSIS
    // =========================

    @PostMapping("/analyze-screenshot")
    public ScamAnalysis analyzeScreenshot(
            @RequestBody String screenshotText) {

        ScamAnalysis result = scamAnalyzer.analyze(screenshotText);

        scanHistoryService.saveScan(
                "SCREENSHOT",
                screenshotText,
                result.getRiskScore(),
                result.getRiskLevel(),
                result.getScamType(),
                result.getRecommendedAction()
        );

        return result;
    }


    // =========================
    // URL ANALYSIS
    // =========================

    @PostMapping("/analyze-url")
    public UrlAnalysis analyzeUrl(@RequestBody String url) {

        UrlAnalysis result = urlAnalyzer.analyze(url);

        scanHistoryService.saveScan(
                "URL",
                url,
                result.getRiskScore(),
                result.getRiskLevel(),
                "URL Analysis",
                result.getRecommendedAction()
        );

        return result;
    }


    // =========================
    // QR ANALYSIS
    // =========================

    @PostMapping("/analyze-qr")
    public Object analyzeQR(@RequestBody String qrData) {

        String data = qrData.trim();

        // Check whether QR contains a URL
        boolean isUrl =
                data.startsWith("http://") ||
                data.startsWith("https://");

        if (isUrl) {

            // QR contains a URL
            UrlAnalysis result = urlAnalyzer.analyze(data);

            scanHistoryService.saveScan(
                    "QR",
                    data,
                    result.getRiskScore(),
                    result.getRiskLevel(),
                    "QR URL Analysis",
                    result.getRecommendedAction()
            );

            return result;

        } else {

            // QR contains text/message
            ScamAnalysis result = scamAnalyzer.analyze(data);

            scanHistoryService.saveScan(
                    "QR",
                    data,
                    result.getRiskScore(),
                    result.getRiskLevel(),
                    result.getScamType(),
                    result.getRecommendedAction()
            );

            return result;
        }
    }


    // =========================
    // REAL OPENAI AI
    // =========================

    @PostMapping("/ai-test")
    public String aiTest(@RequestBody String message) {
        return openAIService.analyzeWithAI(message);
    }


    // =========================
    // DEMO AI
    // =========================

    @PostMapping("/ai-demo")
    public String aiDemo(@RequestBody String message) {
        return demoAIService.analyzeWithDemoAI(message);
    }


    // =========================
    // SCAN HISTORY
    // =========================

    @GetMapping("/history")
    public List<ScanHistory> getHistory() {
        return scanHistoryService.getAllScans();
    }


    // =========================
    // DASHBOARD STATISTICS
    // =========================

    @GetMapping("/dashboard-stats")
    public Map<String, Long> getDashboardStats() {
        return scanHistoryService.getDashboardStats();
    }
}