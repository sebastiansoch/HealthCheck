package com.gmail.ssoch.healthcheck;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.Locale;

public class MainActivity extends BaseActivity {

    private ImageButton bloodPressureBtn;
    private ImageButton bodyWeightBtn;
    private ImageButton glucoseLevelBtn;
    private ImageButton statisticBtn;
    private ImageButton settingsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setApplicationLanguage();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prepareButtons();
    }

    private void setApplicationLanguage() {
        SharedPreferences preferences = getSharedPreferences("AppLanguage", MODE_PRIVATE);
        String language = preferences.getString("Chosen Language", "en");

        Locale myLocale = new Locale(language);

        Configuration configuration = getResources().getConfiguration();
        configuration.setLocale(myLocale);
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
    }

    private void prepareButtons() {
        settingsBtn = findViewById(R.id.main_settings_Btn);
        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Settings.class);
                startActivity(intent);
            }
        });

        bloodPressureBtn = findViewById(R.id.main_blood_pressure_Btn);
        bloodPressureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BloodPressureAndPulse.class);
                startActivity(intent);
            }
        });

        bodyWeightBtn = findViewById(R.id.main_body_weight_Btn);
        bodyWeightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BodyWeight.class);
                startActivity(intent);
            }
        });

        glucoseLevelBtn = findViewById(R.id.main_glucose_level_Btn);
        glucoseLevelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GlucoseLevel.class);
                startActivity(intent);
            }
        });

        statisticBtn = findViewById(R.id.main_statistic_Btn);
        statisticBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Statistic.class);
                startActivity(intent);
            }
        });
    }
}
