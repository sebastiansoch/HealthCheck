package com.gmail.ssoch.healthcheck.dao.data;

import android.util.Range;

public class GlucoseLevelNorm {
    private final Range<Double> glucoseLevel;
    private final String description;

    public GlucoseLevelNorm(Range<Double> glucoseLevel, String description) {
        this.glucoseLevel = glucoseLevel;
        this.description = description;
    }

    public Range<Double> getGlucoseLevel() {
        return glucoseLevel;
    }

    public String getDescription() {
        return description;
    }
}
