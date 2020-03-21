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
import com.gmail.ssoch.healthcheck.dao.file.HealthCheckDataDaoFile;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BodyWeight extends AppCompatActivity {

    private HealthCheckDataDao healthCheckDataDao;

    private EditText bodyWeightET;

    private Button saveBtn;
    private Button.OnClickListener saveBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                parseAndSaveData();

                Intent intent = new Intent(BodyWeight.this, MainActivity.class);
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
            Intent intent = new Intent(BodyWeight.this, MainActivity.class);
            startActivity(intent);
        }
    };

    private void parseAndSaveData() throws Exception {
        String currentDate = getCurrentData();
        String weight = bodyWeightET.getText().toString();

        DataParser.parseBodyWeightData(weight);
        saveData(weight, currentDate);
    }

    private String getCurrentData() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return format.format(date);
    }

    private void saveData(String weight, String currentDate) throws Exception {
        BodyWeightData bodyWeight = new BodyWeightData(weight, currentDate);
        healthCheckDataDao.saveBodyWeight(bodyWeight);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_weight);

        healthCheckDataDao = new HealthCheckDataDaoFile(getApplicationContext());

        bodyWeightET = findViewById(R.id.body_weight_ET);

        saveBtn = findViewById(R.id.body_weight_save_Btn);
        saveBtn.setOnClickListener(saveBtnListener);

        cancelBtn = findViewById(R.id.body_weight_cancel_Btn);
        cancelBtn.setOnClickListener(cancelBtnListener);
    }
}
