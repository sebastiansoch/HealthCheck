package com.gmail.ssoch.healthcheck.dao.data;

public class BodyWeightData {
    private final String bodyWeight;
    private final String measurementDate;

    public BodyWeightData(String bodyWeight, String currentDate) {
        this.bodyWeight = bodyWeight;
        this.measurementDate = currentDate;
    }

    public String getBodyWeight() {
        return bodyWeight;
    }

    public String getMeasurementDate() {
        return measurementDate;
    }
}
