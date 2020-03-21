package com.gmail.ssoch.healthcheck.dao.data;

public class BodyWeightData {
    private final String bodyWeight;
    private final String currentDate;

    public BodyWeightData(String bodyWeight, String currentDate) {
        this.bodyWeight = bodyWeight;
        this.currentDate = currentDate;
    }

    public String getBodyWeight() {
        return bodyWeight;
    }

    public String getCurrentDate() {
        return currentDate;
    }
}
