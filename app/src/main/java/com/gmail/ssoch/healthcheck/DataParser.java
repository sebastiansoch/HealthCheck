package com.gmail.ssoch.healthcheck;

import java.text.ParseException;

class DataParser {
    static void parseBloodPressureData(String systolic, String diastolic, String pulse) throws ParseException {

        if (systolic == null || systolic.isEmpty()) {
            throw new ParseException("Systolic pressure is not set or set incorrectly", 0);
        }

        if (diastolic == null || diastolic.isEmpty()) {
            throw new ParseException("Diastolic pressure is not set or set incorrectly", 0);
        }

        if (pulse == null || pulse.isEmpty()) {
            throw new ParseException("Pulse is not set or set incorrectly", 0);
        }
    }

    public static void parseBodyWeightData(String weight) throws ParseException {

        if (weight == null || weight.isEmpty()) {
            throw new ParseException("Weight is not set or set incorrectly", 0);
        }
    }

    public static void parseGlucoseLevelData(String glucoseLevelMmol, String glucoseLevelMg) throws ParseException {

        if (glucoseLevelMmol == null || glucoseLevelMmol.isEmpty()) {
            throw new ParseException("Glucose level, in units mmol/l, is not set or set incorrectly", 0);
        }

        if (glucoseLevelMg == null || glucoseLevelMg.isEmpty()) {
            throw new ParseException("Glucose level, in units mg/dl, is not set or set incorrectly", 0);
        }
    }
}
