package com.gmail.ssoch.healthcheck.dao.data;

import android.util.Range;

public class BodyWeightNorm {

    private final Range<Double> bodyWeight;
    private final String description;

    public BodyWeightNorm(Range<Double> bodyWeight, String description) {
        this.bodyWeight = bodyWeight;
        this.description = description;
    }

    public Range<Double> getBodyWeight() {
        return bodyWeight;
    }

    public String getDescription() {
        return description;
    }
}
