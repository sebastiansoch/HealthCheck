package com.gmail.ssoch.healthcheck.utils;

import com.gmail.ssoch.healthcheck.dao.data.BloodPressureData;
import com.gmail.ssoch.healthcheck.dao.data.BloodPressureNorm;
import com.gmail.ssoch.healthcheck.dao.data.PulseNorm;

import java.util.List;

public class BloodPressureValidator {

    private final BloodPressureData bloodPressure;
    private String systolicDesc;
    private String diastolicDesc;
    private String pulseDesc;

    public BloodPressureValidator(BloodPressureData bloodPressure) {

        this.bloodPressure = bloodPressure;
    }

    public void checkBloodPressure(List<BloodPressureNorm> bloodPressureNorms) throws NumberFormatException {
        for (BloodPressureNorm norm : bloodPressureNorms) {
            if (systolicDesc == null && norm.getSystolic().contains(Integer.parseInt(bloodPressure.getSystolic()))) {
                systolicDesc = norm.getDescription();
            }

            if (diastolicDesc == null && norm.getDiastolic().contains(Integer.parseInt(bloodPressure.getDiastolic()))) {
                diastolicDesc = norm.getDescription();
            }
        }
    }

    public void checkPulse(List<PulseNorm> pulseNorms) throws NumberFormatException {
        for (PulseNorm norm : pulseNorms) {
            if (norm.getPulse().contains(Integer.parseInt(bloodPressure.getPulse()))) {
                pulseDesc = norm.getDescription();
                break;
            }
        }
    }

    public CharSequence getResultDescription() {
        return "Your systolic blood pressure is " + systolicDesc + "; your diastolic blood pressure is " + diastolicDesc + ". Your pulse is " + pulseDesc;
    }

}
