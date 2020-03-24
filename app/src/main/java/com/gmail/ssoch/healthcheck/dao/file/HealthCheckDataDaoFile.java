package com.gmail.ssoch.healthcheck.dao.file;

import android.content.Context;
import android.util.Range;

import com.gmail.ssoch.healthcheck.R;
import com.gmail.ssoch.healthcheck.dao.HealthCheckDataDao;
import com.gmail.ssoch.healthcheck.dao.data.BloodPressureData;
import com.gmail.ssoch.healthcheck.dao.data.BloodPressureNorm;
import com.gmail.ssoch.healthcheck.dao.data.BodyWeightData;
import com.gmail.ssoch.healthcheck.dao.data.BodyWeightNorm;
import com.gmail.ssoch.healthcheck.dao.data.GlucoseLevelData;
import com.gmail.ssoch.healthcheck.dao.data.GlucoseLevelNorm;
import com.gmail.ssoch.healthcheck.dao.data.PulseNorm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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


    @Override
    public List<BloodPressureNorm> getBloodPressureNorms() throws Exception {
        InputStream inputStream = appContext.getResources().openRawResource(R.raw.blood_pressure);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String readLine = null;
        List<BloodPressureNorm> bloodPressureNorms = new ArrayList<>();

        while ((readLine = bufferedReader.readLine()) != null) {
            String[] splitLine = readLine.split("\\|");
            Range<Integer> systolic = new Range<>(Integer.parseInt(splitLine[0]), Integer.parseInt(splitLine[1]));
            Range<Integer> diastolic = new Range<>(Integer.parseInt(splitLine[2]), Integer.parseInt(splitLine[3]));
            String description = splitLine[4];
            bloodPressureNorms.add(new BloodPressureNorm(systolic, diastolic, description));
        }

        return bloodPressureNorms;
    }

    @Override
    public List<PulseNorm> getPulseNorms() throws Exception {
        InputStream inputStream = appContext.getResources().openRawResource(R.raw.pulse);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String readLine = null;
        List<PulseNorm> pulseNorms = new ArrayList<>();

        while ((readLine = bufferedReader.readLine()) != null) {
            String[] splitLine = readLine.split("\\|");
            Range<Integer> pulse = new Range<>(Integer.parseInt(splitLine[0]), Integer.parseInt(splitLine[1]));
            String description = splitLine[2];
            pulseNorms.add(new PulseNorm(pulse, description));
        }

        return pulseNorms;
    }

    @Override
    public List<BodyWeightNorm> getBodyWeightNorms() throws IOException {
        InputStream inputStream = appContext.getResources().openRawResource(R.raw.body_weight);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String readLine = null;
        List<BodyWeightNorm> bodyWeightNorms = new ArrayList<>();

        while ((readLine = bufferedReader.readLine()) != null) {
            String[] splitLine = readLine.split("\\|");
            Range<Double> bodyWeight = new Range<>(Double.parseDouble(splitLine[0]), Double.parseDouble(splitLine[1]));
            String description = splitLine[2];
            bodyWeightNorms.add(new BodyWeightNorm(bodyWeight, description));
        }

        return bodyWeightNorms;
    }

    @Override
    public List<GlucoseLevelNorm> getGlucoseLevelNorms() throws IOException {
        InputStream inputStream = appContext.getResources().openRawResource(R.raw.glucose_level);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String readLine = null;
        List<GlucoseLevelNorm> glucoseLevelNorm = new ArrayList<>();

        while ((readLine = bufferedReader.readLine()) != null) {
            String[] splitLine = readLine.split("\\|");
            Range<Double> glucoseLevel = new Range<>(Double.parseDouble(splitLine[0]), Double.parseDouble(splitLine[1]));
            String description = splitLine[2];
            glucoseLevelNorm.add(new GlucoseLevelNorm(glucoseLevel, description));
        }

        return glucoseLevelNorm;
    }
}
