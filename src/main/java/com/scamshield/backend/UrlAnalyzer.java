package com.scamshield.backend;

import org.springframework.stereotype.Service;

@Service
public class UrlAnalyzer {

    public UrlAnalysis analyze(String url) {

        UrlAnalysis result = new UrlAnalysis();

        String text = url.toLowerCase();

        int score = 0;

        // =========================================
        // 1. HTTP CHECK
        // =========================================

        if (text.startsWith("http://")) {

            score += 15;

            result.getIndicators().add(
                "Website is using HTTP instead of HTTPS"
            );
        }

        // =========================================
        // 2. SUSPICIOUS KEYWORDS
        // =========================================

        if (text.contains("login") ||
            text.contains("verify") ||
            text.contains("verification") ||
            text.contains("kyc") ||
            text.contains("update") ||
            text.contains("secure") ||
            text.contains("account")) {

            score += 15;

            result.getIndicators().add(
                "URL contains sensitive or verification-related keywords"
            );
        }

        // =========================================
        // 3. FINANCIAL KEYWORDS
        // =========================================

        if (text.contains("bank") ||
            text.contains("payment") ||
            text.contains("wallet") ||
            text.contains("upi") ||
            text.contains("pay") ||
            text.contains("card")) {

            score += 20;

            result.getIndicators().add(
                "URL contains financial-service keywords"
            );
        }

        // =========================================
        // 4. SUSPICIOUS DOMAIN PATTERN
        // =========================================

        if (text.contains("@")) {

            score += 25;

            result.getIndicators().add(
                "URL contains '@' which can hide the actual destination"
            );
        }

        // =========================================
        // 5. IP ADDRESS IN URL
        // =========================================

        if (text.matches(".*https?://[0-9]+\\.[0-9]+\\.[0-9]+\\.[0-9]+.*")) {

            score += 25;

            result.getIndicators().add(
                "URL uses an IP address instead of a normal domain"
            );
        }

        // =========================================
        // 6. TOO MANY SUBDOMAINS
        // =========================================

        String domainPart = text
                .replace("https://", "")
                .replace("http://", "");

        int dotCount = domainPart.length()
                - domainPart.replace(".", "").length();

        if (dotCount >= 4) {

            score += 15;

            result.getIndicators().add(
                "URL contains an unusually large number of subdomains"
            );
        }

        // =========================================
        // 7. LONG URL
        // =========================================

        if (url.length() > 100) {

            score += 10;

            result.getIndicators().add(
                "URL is unusually long"
            );
        }

        // =========================================
        // LIMIT SCORE
        // =========================================

        if (score > 100) {
            score = 100;
        }

        // =========================================
        // RISK LEVEL
        // =========================================

        String riskLevel;

        if (score >= 81) {
            riskLevel = "CRITICAL";
        }
        else if (score >= 61) {
            riskLevel = "HIGH";
        }
        else if (score >= 31) {
            riskLevel = "MEDIUM";
        }
        else {
            riskLevel = "LOW";
        }

        // =========================================
        // RECOMMENDED ACTION
        // =========================================

        String action;

        if (score >= 61) {

            action =
                "Do not open the link or enter personal, login, or financial information. Verify the website through an official source.";

        }
        else if (score >= 31) {

            action =
                "Be cautious with this URL. Verify the website before entering any information.";

        }
        else {

            action =
                "No major suspicious URL indicators detected. Still verify unexpected links before opening them.";
        }

        // =========================================
        // SET RESULT
        // =========================================

        result.setRiskScore(score);
        result.setRiskLevel(riskLevel);
        result.setRecommendedAction(action);

        return result;
    }
}