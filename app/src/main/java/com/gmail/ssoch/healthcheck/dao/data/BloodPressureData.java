package com.gmail.ssoch.healthcheck.dao.data;

public class BloodPressureData {
    private final String systolic;
    private final String diastolic;
    private final String pulse;
    private final String currentDate;

    public BloodPressureData(String systolic, String diastolic, String pulse, String currentDate) {
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.pulse = pulse;
        this.currentDate = currentDate;
    }

    public String getSystolic() {
        return systolic;
    }

    public String getDiastolic() {
        return diastolic;
    }

    public String getPulse() {
        return pulse;
    }

    public String getCurrentDate() {
        return currentDate;
    }
}
