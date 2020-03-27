package com.gmail.ssoch.healthcheck.dao.data;

public class BloodPressureData {
    private final String systolic;
    private final String diastolic;
    private final String measurementDate;

    public BloodPressureData(String systolic, String diastolic, String currentDate) {
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.measurementDate = currentDate;
    }

    public String getSystolic() {
        return systolic;
    }

    public String getDiastolic() {
        return diastolic;
    }

    public String getMeasurementDate() {
        return measurementDate;
    }
}
