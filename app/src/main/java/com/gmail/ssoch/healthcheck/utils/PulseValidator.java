package com.gmail.ssoch.healthcheck.utils;

import com.gmail.ssoch.healthcheck.dao.data.PulseData;
import com.gmail.ssoch.healthcheck.dao.data.PulseNorm;

import java.util.List;

public class PulseValidator {

    private final PulseData pulseData;
    private String pulseDesc;

    public PulseValidator(PulseData pulseData) {

        this.pulseData = pulseData;
    }

    public void checkPulse(List<PulseNorm> pulseNorms) throws NumberFormatException {
        for (PulseNorm norm : pulseNorms) {
            if (norm.getPulse().contains(Integer.parseInt(pulseData.getPulse()))) {
                pulseDesc = norm.getDescription();
                break;
            }
        }
    }

    public CharSequence getResultDescription() {
        return "Your pulse is " + pulseDesc;
    }

}
