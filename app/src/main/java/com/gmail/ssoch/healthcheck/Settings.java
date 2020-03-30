package com.gmail.ssoch.healthcheck;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Settings extends AppCompatActivity {

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
    }
}
