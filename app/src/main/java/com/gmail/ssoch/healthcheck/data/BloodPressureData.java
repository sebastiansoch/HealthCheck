package com.gmail.ssoch.healthcheck.data;

public class BloodPressureData {
    private String systolic;
    private String diastolic;
    private String pulse;
    private String currentDate;

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
