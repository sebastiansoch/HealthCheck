package com.gmail.ssoch.healthcheck.dao.data;

public class GlucoseLevelData {
    private final String glucoseLevelMmol;
    private final String glucoseLevelMg;
    private final String currentDate;

    public GlucoseLevelData(String glucoseLevelMmol, String glucoseLevelMg, String currentDate) {
        this.glucoseLevelMmol = glucoseLevelMmol;
        this.glucoseLevelMg = glucoseLevelMg;
        this.currentDate = currentDate;
    }

    public String getGlucoseLevelMmol() {
        return glucoseLevelMmol;
    }

    public String getGlucoseLevelMg() {
        return glucoseLevelMg;
    }

    public String getCurrentDate() {
        return currentDate;
    }
}
