package com.gmail.ssoch.healthcheck;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.gmail.ssoch.healthcheck.dao.HealthCheckDataDao;
import com.gmail.ssoch.healthcheck.dao.data.GlucoseLevelData;
import com.gmail.ssoch.healthcheck.dao.file.HealthCheckDataDaoFile;
import com.gmail.ssoch.healthcheck.utils.DataParser;
import com.gmail.ssoch.healthcheck.utils.GlucoseLevelValidator;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GlucoseLevel extends BaseActivity {

    private ConstraintLayout layout;
    private ConstraintLayout.OnTouchListener layoutOnTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            return true;
        }
    };

    private EditText glucoseLevelMmolET;
    private TextWatcher glucoseLevelMmolWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (glucoseLevelMmolET.isFocused()) {
                String glucoseLevel = glucoseLevelMmolET.getText().toString();
                if (glucoseLevel != null && !glucoseLevel.isEmpty()) {
                    double dGlucoseLevel = Double.parseDouble(glucoseLevel);
                    long glucoseLevelMg = Math.round(dGlucoseLevel * 18);
                    glucoseLevelMgET.getText().clear();
                    glucoseLevelMgET.setText(String.valueOf(glucoseLevelMg));
                }
            }
        }
    };

    private EditText glucoseLevelMgET;

    private TextWatcher glucoseLevelMgWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (glucoseLevelMgET.isFocused()) {
                String glucoseLevel = glucoseLevelMgET.getText().toString();
                if (glucoseLevel != null && !glucoseLevel.isEmpty()) {
                    double dGlucoseLevel = Double.parseDouble(glucoseLevel);
                    double glucoseLevelMmmol = dGlucoseLevel / 18;
                    String sGlucoseLevelMmmol = String.valueOf(glucoseLevelMmmol);

                    glucoseLevelMmolET.getText().clear();
                    glucoseLevelMmolET.setText(sGlucoseLevelMmmol);
                }
            }
        }
    };

    GlucoseLevelData glucoseLevelData;
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
        glucoseLevelData = new GlucoseLevelData(glucoseLevelMmol, glucoseLevelMg, currentDate);
        healthCheckDataDao.saveGlucoseLevel(glucoseLevelData);
    }

    private void compareResultsWithNorm() throws Exception {
        GlucoseLevelValidator validator = new GlucoseLevelValidator(glucoseLevelData);
        validator.checkGlucoseLevel(healthCheckDataDao.getGlucoseLevelNorms());
        Toast.makeText(this, validator.getResultDescription().toString(), Toast.LENGTH_SHORT).show();
    }

    private void saveAsStartData() {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putFloat("GlucoseLevel Mmol", Float.parseFloat(glucoseLevelMmolET.getText().toString()));
        editor.putInt("GlucoseLevel Mg", Integer.parseInt(glucoseLevelMgET.getText().toString()));
        editor.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glucose_level);

        layout = findViewById(R.id.glucose_level_layout);
        layout.setOnTouchListener(layoutOnTouchListener);

        glucoseLevelMmolET = findViewById(R.id.glucose_level_mmol_ET);
        glucoseLevelMmolET.addTextChangedListener(glucoseLevelMmolWatcher);

        glucoseLevelMgET = findViewById(R.id.glucose_level_mg_ET);
        glucoseLevelMgET.addTextChangedListener(glucoseLevelMgWatcher);

        saveBtn = findViewById(R.id.glucose_level_save_Btn);
        saveBtn.setOnClickListener(saveBtnListener);

        cancelBtn = findViewById(R.id.glucose_level_cancel_Btn);
        cancelBtn.setOnClickListener(cancelBtnListener);

        fillEditTextWithStartData();
    }

    private void fillEditTextWithStartData() {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        glucoseLevelMmolET.setText(Float.toString(preferences.getFloat("GlucoseLevel Mmol", 0)));
        glucoseLevelMgET.setText(Integer.toString(preferences.getInt("GlucoseLevel Mg", 0)));
        glucoseLevelMmolET.callOnClick();
    }
}
