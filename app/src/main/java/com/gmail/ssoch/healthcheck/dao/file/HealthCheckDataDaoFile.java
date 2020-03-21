package com.gmail.ssoch.healthcheck.dao.file;

import android.content.Context;

import com.gmail.ssoch.healthcheck.dao.HealthCheckDataDao;
import com.gmail.ssoch.healthcheck.dao.data.BloodPressureData;
import com.gmail.ssoch.healthcheck.dao.data.BodyWeightData;
import com.gmail.ssoch.healthcheck.dao.data.GlucoseLevelData;

import java.io.IOException;

public class HealthCheckDataDaoFile implements HealthCheckDataDao {
    private final Context appContext;

    public HealthCheckDataDaoFile(Context appContext) {

        this.appContext = appContext;
    }

    @Override
    public void saveBloodPressure(BloodPressureData bloodPressure) throws IOException {
        try {
            AppendDataToFile appendDataToFile = new AppendDataToFile(appContext, "hc_blood_pressure.txt");
            String data = getParsedBloodPressureData(bloodPressure);
            appendDataToFile.append(data);
        } catch (IOException ex) {
            throw new IOException("Something went wrong during data saving");
        }
    }

    private String getParsedBloodPressureData(BloodPressureData bloodPressure) {
        StringBuilder builder = new StringBuilder();
        builder.append(bloodPressure.getCurrentDate());
        builder.append("|");
        builder.append(bloodPressure.getSystolic());
        builder.append("|");
        builder.append(bloodPressure.getDiastolic());
        builder.append("|");
        builder.append(bloodPressure.getPulse());
        builder.append("\n");
        return builder.toString();
    }

    @Override
    public void saveBodyWeight(BodyWeightData bodyWeight) throws Exception {
        try {
            AppendDataToFile appendDataToFile = new AppendDataToFile(appContext, "hc_body_weight.txt");
            String data = getParsedBodyWeightData(bodyWeight);
            appendDataToFile.append(data);
        } catch (IOException ex) {
            throw new IOException("Something went wrong during data saving");
        }
    }

    private String getParsedBodyWeightData(BodyWeightData BodyWeight) {
        StringBuilder builder = new StringBuilder();
        builder.append(BodyWeight.getCurrentDate());
        builder.append("|");
        builder.append(BodyWeight.getBodyWeight());
        builder.append("\n");
        return builder.toString();
    }

    @Override
    public void saveGlucoseLevel(GlucoseLevelData glucoseLevel) throws Exception {
        try {
            AppendDataToFile appendDataToFile = new AppendDataToFile(appContext, "hc_glucose_level.txt");
            String data = getParsedGlucoseLevelData(glucoseLevel);
            appendDataToFile.append(data);
        } catch (IOException ex) {
            throw new IOException("Something went wrong during data saving");
        }
    }

    private String getParsedGlucoseLevelData(GlucoseLevelData glucoseLevel) {
        StringBuilder builder = new StringBuilder();
        builder.append(glucoseLevel.getCurrentDate());
        builder.append("|");
        builder.append(glucoseLevel.getGlucoseLevelMmol());
        builder.append("|");
        builder.append(glucoseLevel.getGlucoseLevelMg());
        builder.append("\n");
        return builder.toString();
    }

}
