package com.gmail.ssoch.healthcheck;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

import static android.widget.Toast.LENGTH_SHORT;

public class Settings extends AppCompatActivity {
    private Locale myLocale;
    private String currentLanguage = "en", currentLang;


    private ImageButton languageBtn;
    private ImageButton.OnClickListener languageBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final CharSequence[] language = {"English", "Polski"};
            final String[] chosenLanguage = {"en"};

            AlertDialog.Builder builder = new AlertDialog.Builder(Settings.this);
            builder.setTitle("Choose language ...");

            builder.setSingleChoiceItems(language, -1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case 0:
                            chosenLanguage[0] = "en";
                            break;
                        case 1:
                            chosenLanguage[0] = "pl";
                            break;
                    }
                }
            });

            builder.setPositiveButton(R.string.alter_dialog_ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    setLocale(chosenLanguage[0]);
                }
            });

            builder.setNegativeButton(R.string.alter_dialog_cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }
    };

    public void setLocale(String localeName) {
        if (!localeName.equals(currentLanguage)) {
            myLocale = new Locale(localeName);

            Configuration configuration = getResources().getConfiguration();
            configuration.setLocale(myLocale);
            getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());

            Intent refresh = new Intent(this, Settings.class);
            refresh.putExtra(currentLang, localeName);
            startActivity(refresh);
        } else {
            Toast.makeText(Settings.this, "Language already selected!", LENGTH_SHORT).show();
        }
    }


    private ImageButton syncBtn;
    private Button cancelBtn;
    private Button.OnClickListener cancelBtnListener = new View.OnClickListener() {
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

        currentLanguage = getIntent().getStringExtra(currentLang);

        languageBtn = findViewById(R.id.settings_language_Btn);
        languageBtn.setOnClickListener(languageBtnListener);

        syncBtn = findViewById(R.id.settings_sync_Btn);

        cancelBtn = findViewById(R.id.settings_cancel_Btn);
        cancelBtn.setOnClickListener(cancelBtnListener);
    }

}
