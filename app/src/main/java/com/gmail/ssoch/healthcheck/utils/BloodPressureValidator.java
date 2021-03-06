package com.gmail.ssoch.healthcheck.utils;

import com.gmail.ssoch.healthcheck.dao.data.BloodPressureData;
import com.gmail.ssoch.healthcheck.dao.data.BloodPressureNorm;

import java.util.List;

public class BloodPressureValidator {

    private final BloodPressureData bloodPressure;
    private String systolicDesc;
    private String diastolicDesc;

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

    public CharSequence getResultDescription() {
        return "Your systolic blood pressure is " + systolicDesc + "; your diastolic blood pressure is " + diastolicDesc;
    }

}
