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
import com.gmail.ssoch.healthcheck.dao.data.PulseData;
import com.gmail.ssoch.healthcheck.dao.data.PulseNorm;
import com.gmail.ssoch.healthcheck.dao.data.UserPersonalData;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class HealthCheckDataDaoFile implements HealthCheckDataDao {
    private final Context appContext;

    private final String FILE_BLOOD_PRESSURE = "hc_blood_pressure.txt";
    private final String FILE_PULSE = "hc_pulse.txt";
    private final String FILE_BODY_WEIGHT = "hc_body_weight.txt";
    private final String FILE_GLUCOSE_LEVEL = "hc_glucose_level.txt";
    private final String FILE_USER_PERSONAL_DATA = "hc_user_personal_data.txt";

    public HealthCheckDataDaoFile(Context appContext) {
        this.appContext = appContext;
    }

    @Override
    public void saveBloodPressure(BloodPressureData bloodPressure) throws IOException {
        try {
            AppendDataToFile appendDataToFile = new AppendDataToFile(appContext, FILE_BLOOD_PRESSURE);
            String data = getParsedBloodPressureData(bloodPressure);
            appendDataToFile.append(data);
        } catch (IOException ex) {
            throw new IOException("Something went wrong during data saving");
        }
    }

    private String getParsedBloodPressureData(BloodPressureData bloodPressure) {
        StringBuilder builder = new StringBuilder();
        builder.append(bloodPressure.getMeasurementDate());
        builder.append("|");
        builder.append(bloodPressure.getSystolic());
        builder.append("|");
        builder.append(bloodPressure.getDiastolic());
        builder.append("\n");
        return builder.toString();
    }

    @Override
    public void savePulse(PulseData pulse) throws IOException {
        try {
            AppendDataToFile appendDataToFile = new AppendDataToFile(appContext, FILE_PULSE);
            String data = getParsedPulseData(pulse);
            appendDataToFile.append(data);
        } catch (IOException ex) {
            throw new IOException("Something went wrong during data saving");
        }
    }

    private String getParsedPulseData(PulseData pulse) {
        StringBuilder builder = new StringBuilder();
        builder.append(pulse.getMeasurementDate());
        builder.append("|");
        builder.append(pulse.getPulse());
        builder.append("\n");
        return builder.toString();
    }

    @Override
    public void saveBodyWeight(BodyWeightData bodyWeight) throws IOException {
        try {
            AppendDataToFile appendDataToFile = new AppendDataToFile(appContext, FILE_BODY_WEIGHT);
            String data = getParsedBodyWeightData(bodyWeight);
            appendDataToFile.append(data);
        } catch (IOException ex) {
            throw new IOException("Something went wrong during data saving");
        }
    }

    private String getParsedBodyWeightData(BodyWeightData BodyWeight) {
        StringBuilder builder = new StringBuilder();
        builder.append(BodyWeight.getMeasurementDate());
        builder.append("|");
        builder.append(BodyWeight.getBodyWeight());
        builder.append("\n");
        return builder.toString();
    }

    @Override
    public void saveGlucoseLevel(GlucoseLevelData glucoseLevel) throws IOException {
        try {
            AppendDataToFile appendDataToFile = new AppendDataToFile(appContext, FILE_GLUCOSE_LEVEL);
            String data = getParsedGlucoseLevelData(glucoseLevel);
            appendDataToFile.append(data);
        } catch (IOException ex) {
            throw new IOException("Something went wrong during data saving");
        }
    }

    private String getParsedGlucoseLevelData(GlucoseLevelData glucoseLevel) {
        StringBuilder builder = new StringBuilder();
        builder.append(glucoseLevel.getMeasurementDate());
        builder.append("|");
        builder.append(glucoseLevel.getGlucoseLevelMmol());
        builder.append("|");
        builder.append(glucoseLevel.getGlucoseLevelMg());
        builder.append("\n");
        return builder.toString();
    }

    @Override
    public void saveUserPersonalData(UserPersonalData userPersonalData) throws IOException {
        AppendDataToFile appendDataToFile = new AppendDataToFile(appContext, FILE_USER_PERSONAL_DATA);
        String data = getParsedUserPersonalData(userPersonalData);
        appendDataToFile.save(data);
    }

    private String getParsedUserPersonalData(UserPersonalData userPersonalData) {
        StringBuilder builder = new StringBuilder();
        builder.append(userPersonalData.getGender());
        builder.append("|");
        builder.append(userPersonalData.getDateOfBirth());
        builder.append("|");
        builder.append(userPersonalData.getHeight());
        builder.append("\n");
        return builder.toString();
    }

    @Override
    public List<BloodPressureNorm> getBloodPressureNorms() throws IOException {
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
    public List<PulseNorm> getPulseNorms() throws IOException {
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

    @Override
    public List<BloodPressureData> getBloodPressureInRange(Range<String> dateRange) throws IOException {
        FileInputStream fileInputStream = appContext.openFileInput(FILE_BLOOD_PRESSURE);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

        String readLine = null;
        List<BloodPressureData> bloodPressureData = new ArrayList<>();

        while ((readLine = bufferedReader.readLine()) != null) {
            String[] splitLine = readLine.split("\\|");
            if (dateRange.contains(splitLine[0])) {
                bloodPressureData.add(new BloodPressureData(splitLine[1], splitLine[2], splitLine[0]));
            }
        }

        return bloodPressureData;
    }

    @Override
    public List<PulseData> getPulseInRange(Range<String> dateRange) throws IOException {
        FileInputStream fileInputStream = appContext.openFileInput(FILE_PULSE);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

        String readLine = null;
        List<PulseData> pulseData = new ArrayList<>();

        while ((readLine = bufferedReader.readLine()) != null) {
            String[] splitLine = readLine.split("\\|");
            if (dateRange.contains(splitLine[0])) {
                pulseData.add(new PulseData(splitLine[1], splitLine[0]));
            }
        }

        return pulseData;
    }

    @Override
    public List<BodyWeightData> getBodyWeighInRange(Range<String> dateRange) throws IOException {
        FileInputStream fileInputStream = appContext.openFileInput(FILE_BODY_WEIGHT);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

        String readLine = null;
        List<BodyWeightData> bodyWeightData = new ArrayList<>();

        while ((readLine = bufferedReader.readLine()) != null) {
            String[] splitLine = readLine.split("\\|");
            if (dateRange.contains(splitLine[0])) {
                bodyWeightData.add(new BodyWeightData(splitLine[1], splitLine[0]));
            }
        }

        return bodyWeightData;
    }

    @Override
    public List<GlucoseLevelData> getGlucoseLevelInRange(Range<String> dateRange) throws IOException {
        FileInputStream fileInputStream = appContext.openFileInput(FILE_GLUCOSE_LEVEL);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

        String readLine = null;
        List<GlucoseLevelData> glucoseLevelData = new ArrayList<>();

        while ((readLine = bufferedReader.readLine()) != null) {
            String[] splitLine = readLine.split("\\|");
            if (dateRange.contains(splitLine[0])) {
                glucoseLevelData.add(new GlucoseLevelData(splitLine[1], splitLine[2], splitLine[0]));
            }
        }

        return glucoseLevelData;
    }

    @Override
    public UserPersonalData getUserPersonalData() throws IOException {
        FileInputStream fileInputStream = appContext.openFileInput(FILE_USER_PERSONAL_DATA);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

        UserPersonalData userPersonalData = new UserPersonalData("", "", "");
        String readLine = null;
        if ((readLine = bufferedReader.readLine()) != null) {
            String[] split = readLine.split("\\|");
            userPersonalData = new UserPersonalData(split[0], split[2], split[1]);
        }

        return userPersonalData;
    }


}
