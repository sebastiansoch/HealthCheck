package com.gmail.ssoch.healthcheck.utils;

import com.gmail.ssoch.healthcheck.dao.data.GlucoseLevelData;
import com.gmail.ssoch.healthcheck.dao.data.GlucoseLevelNorm;

import java.util.List;

public class GlucoseLevelValidator {
    private final GlucoseLevelData glucoseLevel;
    private String glucoseLevelDesc;

    public GlucoseLevelValidator(GlucoseLevelData glucoseLevel) {
        this.glucoseLevel = glucoseLevel;
    }

    public void checkGlucoseLevel(List<GlucoseLevelNorm> glucoseLevelNorms) throws NumberFormatException {
        for (GlucoseLevelNorm norm : glucoseLevelNorms) {
            if (norm.getGlucoseLevel().contains(Double.parseDouble(glucoseLevel.getGlucoseLevelMmol()))) {
                glucoseLevelDesc = norm.getDescription();
                break;
            }
        }
    }

    public CharSequence getResultDescription() {
        return "Your glucose level is " + glucoseLevelDesc;
    }
}
