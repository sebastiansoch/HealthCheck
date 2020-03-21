package com.gmail.ssoch.healthcheck;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gmail.ssoch.healthcheck.dao.HealthCheckDataDao;
import com.gmail.ssoch.healthcheck.dao.data.BodyWeightData;
import com.gmail.ssoch.healthcheck.dao.data.GlucoseLevelData;
import com.gmail.ssoch.healthcheck.dao.file.HealthCheckDataDaoFile;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GlucoseLevel extends AppCompatActivity {

    private HealthCheckDataDao healthCheckDataDao;

    private EditText glucoseLevelMgET;
    private EditText glucoseLevelMmolET;

    private Button saveBtn;
    private Button.OnClickListener saveBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                parseAndSaveData();

                Intent intent = new Intent(GlucoseLevel.this, MainActivity.class);
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
            Intent intent = new Intent(GlucoseLevel.this, MainActivity.class);
            startActivity(intent);
        }
    };

    private void parseAndSaveData() throws Exception {
        String currentDate = getCurrentData();
        String glucoseLevelMmol = glucoseLevelMmolET.getText().toString();
        String glucoseLevelMg = glucoseLevelMgET.getText().toString();

        DataParser.parseGlucoseLevelData(glucoseLevelMmol, glucoseLevelMg);
        saveData(glucoseLevelMmol, glucoseLevelMg, currentDate);
    }

    private String getCurrentData() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return format.format(date);
    }

    private void saveData(String glucoseLevelMmol, String glucoseLevelMg, String currentDate) throws Exception {
        GlucoseLevelData glucoseLevel = new GlucoseLevelData(glucoseLevelMmol, glucoseLevelMg, currentDate);
        healthCheckDataDao.saveGlucoseLevel(glucoseLevel);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glucose_level);

        healthCheckDataDao = new HealthCheckDataDaoFile(getApplicationContext());

        glucoseLevelMmolET = findViewById(R.id.glucose_level_mmol_ET);
        glucoseLevelMgET = findViewById(R.id.glucose_level_mg_ET);

        saveBtn = findViewById(R.id.glucose_level_save_Btn);
        saveBtn.setOnClickListener(saveBtnListener);

        cancelBtn = findViewById(R.id.glucose_level_cancel_Btn);
        cancelBtn.setOnClickListener(cancelBtnListener);
    }
}
