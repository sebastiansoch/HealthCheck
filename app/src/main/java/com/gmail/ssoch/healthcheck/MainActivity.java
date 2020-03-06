package com.gmail.ssoch.healthcheck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private ImageButton bloodPreasureBtn;
    private ImageButton weightBtn;
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

        weightBtn = findViewById(R.id.main_weight_Btn);
        glucoseLevelBtn = findViewById(R.id.main_glucose_level_Btn);
        statisticBtn = findViewById(R.id.main_statistic_Btn);
    }
}
