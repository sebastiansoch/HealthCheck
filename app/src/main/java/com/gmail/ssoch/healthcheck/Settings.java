package com.gmail.ssoch.healthcheck;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Settings extends AppCompatActivity {

    private ImageButton dataUserBtn;
    private ImageButton.OnClickListener dataUserBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Settings.this, UserData.class);
            startActivity(intent);
        }
    };

    private ImageButton languageBtn;
    private ImageButton.OnClickListener languageBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            LanguagePicker languagePicker = new LanguagePicker();
            languagePicker.show(getSupportFragmentManager(), "Language Picker");

        }
    };


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

        dataUserBtn = findViewById(R.id.settings_user_data_Btn);
        dataUserBtn.setOnClickListener(dataUserBtnListener);

        languageBtn = findViewById(R.id.settings_language_Btn);
        languageBtn.setOnClickListener(languageBtnListener);

        cancelBtn = findViewById(R.id.settings_cancel_Btn);
        cancelBtn.setOnClickListener(cancelBtnListener);
    }
}
