package com.gmail.ssoch.healthcheck.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataParser {
    public static void parseBloodPressureData(String systolic, String diastolic) throws ParseException {

        if (systolic == null || systolic.isEmpty()) {
            throw new ParseException("Systolic pressure is not set or set incorrectly", 0);
        }

        if (diastolic == null || diastolic.isEmpty()) {
            throw new ParseException("Diastolic pressure is not set or set incorrectly", 0);
        }
    }

    public static void parsePulseData(String pulse) throws ParseException {
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

    public static void parseUserData(int genderID, String height, String dateOfBirth) throws ParseException {
        if (genderID < 0) {
            throw new ParseException("Gender is set incorrectly", 0);
        }

        if (height == null || height.isEmpty()) {
            throw new ParseException("Height is not set or set incorrectly", 0);
        }

        if (dateOfBirth == null || dateOfBirth.isEmpty()) {
            throw new ParseException("Date of birth is not set or set incorrectly", 0);
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        Date dob = format.parse(dateOfBirth);
        if (dob.after(now)) {
            throw new ParseException("Date of birth is set incorrectly", 0);
        }

        Date oldestDate = format.parse("1900-01-01");
        if (dob.before(oldestDate)) {
            throw new ParseException("Date of birth is set incorrectly", 0);
        }
    }
}
