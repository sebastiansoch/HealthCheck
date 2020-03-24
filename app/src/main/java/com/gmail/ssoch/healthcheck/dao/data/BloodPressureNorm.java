package com.gmail.ssoch.healthcheck.dao.data;

import android.util.Range;


public class BloodPressureNorm {

    private final Range<Integer> systolic;
    private final Range<Integer> diastolic;
    private final String description;

    public BloodPressureNorm(Range<Integer> systolic, Range<Integer> diastolic, String description) {
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.description = description;
    }

    public Range<Integer> getSystolic() {
        return systolic;
    }

    public Range<Integer> getDiastolic() {
        return diastolic;
    }

    public String getDescription() {
        return description;
    }
}
