package com.gmail.ssoch.healthcheck;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.gmail.ssoch.healthcheck.dao.data.BloodPressureData;
import com.gmail.ssoch.healthcheck.dao.data.PulseData;
import com.gmail.ssoch.healthcheck.utils.BloodPressureValidator;
import com.gmail.ssoch.healthcheck.utils.DataParser;
import com.gmail.ssoch.healthcheck.utils.PulseValidator;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BloodPressureAndPulse extends BaseActivity {

    private ConstraintLayout layout;
    private ConstraintLayout.OnTouchListener layoutOnTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            return true;
        }
    };

    private EditText systolicET;
    private EditText diastolicET;
    private EditText pulseET;

    private BloodPressureData bloodPressureData;
    private PulseData pulseData;

    private Button saveBtn;
    private Button.OnClickListener saveBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                parseAndSaveData();
                compareResultsWithNorm();
                saveAsStartData();
            } catch (Exception ex) {
                Toast.makeText(v.getContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    };

    private Button cancelBtn;
    private Button.OnClickListener cancelBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(BloodPressureAndPulse.this, MainActivity.class);
            startActivity(intent);
        }
    };

    private void parseAndSaveData() throws Exception {
        String currentDate = getCurrentData();
        String systolic = systolicET.getText().toString();
        String diastolic = diastolicET.getText().toString();
        String pulse = pulseET.getText().toString();

        DataParser.parseBloodPressureData(systolic, diastolic);
        DataParser.parsePulseData(pulse);
        saveData(systolic, diastolic, pulse, currentDate);
    }

    private String getCurrentData() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return format.format(date);
    }

    private void saveData(String systolic, String diastolic, String pulse, String currentDate) throws Exception {
        bloodPressureData = new BloodPressureData(systolic, diastolic, currentDate);
        pulseData = new PulseData(pulse, currentDate);
        healthCheckDataDao.saveBloodPressure(bloodPressureData);
        healthCheckDataDao.savePulse(pulseData);
    }

    private void compareResultsWithNorm() throws Exception {
        BloodPressureValidator validatorBP = new BloodPressureValidator(bloodPressureData);
        validatorBP.checkBloodPressure(healthCheckDataDao.getBloodPressureNorms());
        Toast.makeText(this, validatorBP.getResultDescription().toString(), Toast.LENGTH_SHORT).show();

        PulseValidator validatorP = new PulseValidator(pulseData);
        validatorP.checkPulse(healthCheckDataDao.getPulseNorms());
        Toast.makeText(this, validatorP.getResultDescription().toString(), Toast.LENGTH_SHORT).show();
    }

    private void saveAsStartData() {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("Systolic", Integer.parseInt(systolicET.getText().toString()));
        editor.putInt("Diastolic", Integer.parseInt(diastolicET.getText().toString()));
        editor.putInt("Pulse", Integer.parseInt(pulseET.getText().toString()));
        editor.commit();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_pressure_and_pulse);

        layout = findViewById(R.id.blood_pressure_layout);
        layout.setOnTouchListener(layoutOnTouchListener);

        systolicET = findViewById(R.id.blood_pressure_systolic_ET);
        diastolicET = findViewById(R.id.blood_pressure_diastolic_ET);
        pulseET = findViewById(R.id.blood_pressure_pulse_ET);

        saveBtn = findViewById(R.id.blood_pressure_save_Btn);
        saveBtn.setOnClickListener(saveBtnListener);

        cancelBtn = findViewById(R.id.blood_pressure_cancel_Btn);
        cancelBtn.setOnClickListener(cancelBtnListener);

        fillEditTextWithStartData();
    }

    private void fillEditTextWithStartData() {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        systolicET.setText(Integer.toString(preferences.getInt("Systolic", 0)));
        diastolicET.setText(Integer.toString(preferences.getInt("Diastolic", 0)));
        pulseET.setText(Integer.toString(preferences.getInt("Pulse", 0)));
    }
}
