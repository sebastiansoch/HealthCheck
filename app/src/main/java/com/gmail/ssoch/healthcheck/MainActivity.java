package com.gmail.ssoch.healthcheck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private ImageButton bloodPreasureBtn;
    private ImageButton bodyWeightBtn;
    private ImageButton glucoseLevelBtn;
    private ImageButton statisticBtn;
    private ImageButton settingsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prepareButtons();
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

        bloodPreasureBtn = findViewById(R.id.main_blood_pressure_Btn);
        bloodPreasureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BloodPressure.class);
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
