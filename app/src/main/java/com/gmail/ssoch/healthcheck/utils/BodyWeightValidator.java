package com.gmail.ssoch.healthcheck.utils;

import com.gmail.ssoch.healthcheck.dao.data.BodyWeightData;
import com.gmail.ssoch.healthcheck.dao.data.BodyWeightNorm;

import java.util.List;

public class BodyWeightValidator {
    private final BodyWeightData bodyWeight;
    private String bodyWeightDesc;

    public BodyWeightValidator(BodyWeightData bodyWeight) {
        this.bodyWeight = bodyWeight;
    }

    public void checkBodyWeight(List<BodyWeightNorm> bodyWeightNorms) throws NumberFormatException {
        for (BodyWeightNorm norm : bodyWeightNorms) {
            if (norm.getBodyWeight().contains(Double.parseDouble(bodyWeight.getBodyWeight()))) {
                bodyWeightDesc = norm.getDescription();
                break;
            }
        }
    }

    public CharSequence getResultDescription() {
        return "Your body weight is " + bodyWeightDesc;
    }

}
