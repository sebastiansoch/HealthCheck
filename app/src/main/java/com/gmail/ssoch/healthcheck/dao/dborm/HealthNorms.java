package com.gmail.ssoch.healthcheck.dao.dborm;

import com.gmail.ssoch.healthcheck.dao.dborm.entity.BloodPressureNormEntity;
import com.gmail.ssoch.healthcheck.dao.dborm.entity.BodyWeightNormEntity;
import com.gmail.ssoch.healthcheck.dao.dborm.entity.GlucoseLevelNormEntity;
import com.gmail.ssoch.healthcheck.dao.dborm.entity.PulseNormEntity;

import java.util.ArrayList;
import java.util.List;

class HealthNorms {

    private final DBHelper dbHelper;

    public HealthNorms(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public void fillBloodPressureNormTable() {

    }

    public void fillPulseNormTable() {

    }

    public void fillBodyWeightNormTable() {

    }

    public void fillGlucoseLevelNormTable() {

    }

    private List<BloodPressureNormEntity> bloodPressureNorm() {
        return new ArrayList<BloodPressureNormEntity>() {
            {
                add(new BloodPressureNormEntity(0, 90, 0, 60, "low_blood_pressure"));
                add(new BloodPressureNormEntity(90, 120, 60, 80, "normal_blood_pressure"));
                add(new BloodPressureNormEntity(120, 139, 80, 89, "elevated_blood_pressure"));
                add(new BloodPressureNormEntity(140, 159, 90, 99, "high_blood_pressure_stg1"));
                add(new BloodPressureNormEntity(160, 180, 100, 110, "high_blood_pressure_stg2"));
                add(new BloodPressureNormEntity(180, 1000, 110, 1000, "hypertensive_crisis_blood_pressure"));
            }
        };
    }

    private List<PulseNormEntity> fillPulseNorm() {
        return new ArrayList<PulseNormEntity>() {
            {
                add(new PulseNormEntity(0, 60, "low_pulse"));
                add(new PulseNormEntity(60, 90, "normal_pulse"));
                add(new PulseNormEntity(90, 1000, "high_pulse"));
            }
        };
    }

    private List<BodyWeightNormEntity> fillBodyWeightNorm() {
        return new ArrayList<BodyWeightNormEntity>() {
            {
                add(new BodyWeightNormEntity(0, 50, "low_body_weight"));
                add(new BodyWeightNormEntity(50, 70, "normal_body_weight"));
                add(new BodyWeightNormEntity(70, 1000, "high_body_weight"));
            }
        };

    }

    private List<GlucoseLevelNormEntity> fillGlucoseLevelNorm() {
        return new ArrayList<GlucoseLevelNormEntity>() {
            {
                add(new GlucoseLevelNormEntity(0, 3.9, "low_glucose_level"));
                add(new GlucoseLevelNormEntity(3.9, 5.5, "normal_glucose_level"));
                add(new GlucoseLevelNormEntity(5.6, 6.9, "prediabetes_glucose_level"));
                add(new GlucoseLevelNormEntity(7.0, 1000, "diabetes_glucose_level"));
            }
        };
    }
}
