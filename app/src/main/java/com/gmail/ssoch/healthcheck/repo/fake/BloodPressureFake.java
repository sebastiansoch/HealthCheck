package com.gmail.ssoch.healthcheck.repo.fake;


import com.gmail.ssoch.healthcheck.data.BloodPressureData;

import static android.content.Context.MODE_APPEND;

public class BloodPressureFake extends HealthCheckRepoFake {

    private String FILE_NAME = "hc_blood_pressure.txt";

    @Override
    public <T> boolean save(T data) {
        BloodPressureData bloodPressureData = (BloodPressureData) data;

        String dataToSave = prepareDatatoSave(bloodPressureData);

        AppendDataToFile appendDataToFile = new AppendDataToFile(applicationContext, FILE_NAME, MODE_APPEND);
        if (appendDataToFile.append(dataToSave)) {
            return true;
        }

        return false;
    }

    private String prepareDatatoSave(BloodPressureData bloodPressureData) {
        StringBuilder builder = new StringBuilder(bloodPressureData.getCurrentDate());
        builder.append("|");
        builder.append(bloodPressureData.getSystolic());
        builder.append("|");
        builder.append(bloodPressureData.getDiastolic());
        builder.append("|");
        builder.append(bloodPressureData.getPulse());
        builder.append("\n");
        return builder.toString();
    }

}
