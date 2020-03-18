package com.gmail.ssoch.healthcheck;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gmail.ssoch.healthcheck.data.BloodPressureData;
import com.gmail.ssoch.healthcheck.repo.HealthCheckRepo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BloodPressure extends BaseActivity {

    private Button saveBtn;
    private EditText systolicET;
    private EditText diastolicET;
    private EditText pulseET;
    private HealthCheckRepo healthCheckRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_pressure);

        healthCheckRepo = super.getRepository(ActivityType.BLOOD_PRESSURE);

        prepareFields();
    }

    private void prepareFields() {
        systolicET = findViewById(R.id.pressure_systolic_ET);
        diastolicET = findViewById(R.id.pressure_diastolic_ET);
        pulseET = findViewById(R.id.pressure_pulse_ET);

        saveBtn = findViewById(R.id.pressure_save_Btn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BloodPressureData bloodPressureData = prepareBloodPressureData();
                if (bloodPressureData != null) {
                    saveBloodPressure(bloodPressureData);
                } else {
                    Toast.makeText(BloodPressure.this, "Incorrect blood pressure data", Toast.LENGTH_SHORT);
                }
            }
        });
    }

    private BloodPressureData prepareBloodPressureData() {
        String currentDate = getCurrentDate();
        String systolic = systolicET.getText().toString();
        String diastolic = diastolicET.getText().toString();
        String pulse = pulseET.getText().toString();

        if (validateBloodPressureData(systolic, diastolic, pulse)) {
            return new BloodPressureData(systolic, diastolic, pulse, currentDate);
        }

        return null;
    }

    private boolean validateBloodPressureData(String systolic, String diastolic, String pulse) {

        if (systolic == null || systolic.isEmpty()) {
            return false;
        }

        if (diastolic == null || diastolic.isEmpty()) {
            return false;
        }

        if (pulse == null || pulse.isEmpty()) {
            return false;
        }

        return true;
    }

    private String getCurrentDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-ss");
        Date now = new Date();
        return simpleDateFormat.format(now);
    }

    private void saveBloodPressure(BloodPressureData bloodPressureData) {
          healthCheckRepo.save(bloodPressureData);
    }


}
