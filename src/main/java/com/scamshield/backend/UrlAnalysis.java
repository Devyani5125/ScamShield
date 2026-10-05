package com.scamshield.backend;

import java.util.ArrayList;
import java.util.List;

public class UrlAnalysis {

    private int riskScore;
    private String riskLevel;
    private List<String> indicators;
    private String recommendedAction;

    public UrlAnalysis() {
        indicators = new ArrayList<>();
    }

    public int getRiskScore() {
        return riskScore;
    }

    public void setRiskScore(int riskScore) {
        this.riskScore = riskScore;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public List<String> getIndicators() {
        return indicators;
    }

    public String getRecommendedAction() {
        return recommendedAction;
    }

    public void setRecommendedAction(String recommendedAction) {
        this.recommendedAction = recommendedAction;
    }
}