package com.scamshield.backend;
import java.util.ArrayList;
import java.util.List;

public class ScamAnalysis {

    private int riskScore;
    private String riskLevel;
    private String scamType;
    private List<String> indicators;
    private List<String> tactics;
    private String recommendedAction;

    public ScamAnalysis() {
        indicators = new ArrayList<>();
        tactics = new ArrayList<>();
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

    public String getScamType() {
        return scamType;
    }

    public void setScamType(String scamType) {
        this.scamType = scamType;
    }

    public List<String> getIndicators() {
        return indicators;
    }

    public List<String> getTactics() {
        return tactics;
    }

    public String getRecommendedAction() {
        return recommendedAction;
    }

    public void setRecommendedAction(String recommendedAction) {
        this.recommendedAction = recommendedAction;
    }
}