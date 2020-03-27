package com.gmail.ssoch.healthcheck.dao.data;

public class PulseData {
    private final String pulse;
    private final String measurementDate;

    public PulseData(String pulse, String currentDate) {
        this.pulse = pulse;
        this.measurementDate = currentDate;
    }

    public String getPulse() {
        return pulse;
    }

    public String getMeasurementDate() {
        return measurementDate;
    }
}
