package com.gmail.ssoch.healthcheck;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gmail.ssoch.healthcheck.dao.HealthCheckDataDao;
import com.gmail.ssoch.healthcheck.dao.data.BloodPressureData;
import com.gmail.ssoch.healthcheck.dao.file.HealthCheckDataDaoFile;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BloodPressure extends AppCompatActivity {

    private HealthCheckDataDao healthCheckDataDao;

    private EditText systolicET;
    private EditText diastolicET;
    private EditText pulseET;

    private Button saveBtn;
    private Button.OnClickListener saveBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                parseAndSaveData();

                Intent intent = new Intent(BloodPressure.this, MainActivity.class);
                startActivity(intent);
            } catch (Exception ex) {
                Toast.makeText(v.getContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    };
    private Button cancelBtn;
    private Button.OnClickListener cancelBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(BloodPressure.this, MainActivity.class);
            startActivity(intent);
        }
    };

    private void parseAndSaveData() throws Exception {
        String currentDate = getCurrentData();
        String systolic = systolicET.getText().toString();
        String diastolic = diastolicET.getText().toString();
        String pulse = pulseET.getText().toString();

        DataParser.parseBloodPressureData(systolic, diastolic, pulse);
        saveData(systolic, diastolic, pulse, currentDate);
    }

    private String getCurrentData() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return format.format(date);
    }

    private void saveData(String systolic, String diastolic, String pulse, String currentDate) throws Exception {
        BloodPressureData bloodPressure = new BloodPressureData(systolic, diastolic, pulse, currentDate);
        healthCheckDataDao.saveBloodPressure(bloodPressure);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_pressure);

        healthCheckDataDao = new HealthCheckDataDaoFile(this);

        systolicET = findViewById(R.id.pressure_systolic_ET);
        diastolicET = findViewById(R.id.pressure_diastolic_ET);
        pulseET = findViewById(R.id.pressure_pulse_ET);

        saveBtn = findViewById(R.id.pressure_save_Btn);
        saveBtn.setOnClickListener(saveBtnListener);

        cancelBtn = findViewById(R.id.pressure_cancel_Btn);
        cancelBtn.setOnClickListener(cancelBtnListener);
    }
}
