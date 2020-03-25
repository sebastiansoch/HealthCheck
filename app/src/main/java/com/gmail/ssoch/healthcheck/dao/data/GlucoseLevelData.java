package com.gmail.ssoch.healthcheck.dao.data;

public class GlucoseLevelData {
    private final String glucoseLevelMmol;
    private final String glucoseLevelMg;
    private final String measurementDate;

    public GlucoseLevelData(String glucoseLevelMmol, String glucoseLevelMg, String currentDate) {
        this.glucoseLevelMmol = glucoseLevelMmol;
        this.glucoseLevelMg = glucoseLevelMg;
        this.measurementDate = currentDate;
    }

    public String getGlucoseLevelMmol() {
        return glucoseLevelMmol;
    }

    public String getGlucoseLevelMg() {
        return glucoseLevelMg;
    }

    public String getMeasurementDate() {
        return measurementDate;
    }
}
