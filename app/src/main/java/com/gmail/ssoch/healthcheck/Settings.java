package com.gmail.ssoch.healthcheck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Settings extends AppCompatActivity {

    private Button cancelBtn;
    Button.OnClickListener cancelBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Settings.this, MainActivity.class);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        final ImageButton languageBtn = findViewById(R.id.settings_language_Btn);
        languageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LanguagePicker languagePicker = new LanguagePicker();
                languagePicker.show(getSupportFragmentManager(), "Language Picker");
            }
        });


        cancelBtn = findViewById(R.id.settings_cancel_Btn);
        cancelBtn.setOnClickListener(cancelBtnListener);
    }
}
