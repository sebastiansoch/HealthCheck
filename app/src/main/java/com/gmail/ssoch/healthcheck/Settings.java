package com.gmail.ssoch.healthcheck;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

import static android.widget.Toast.LENGTH_SHORT;

public class Settings extends AppCompatActivity {
    private Locale myLocale;
    private String currentLanguage = "en", currentLang;
    private AlertDialogItem[] alertDialogItems = new AlertDialogItem[2];
    private ListAdapter alertDialogListAdapter;
    private ImageButton languageBtn;
    private ImageButton.OnClickListener languageBtnListener;
    private ImageButton syncBtn;
    private Button cancelBtn;
    private Button.OnClickListener cancelBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Settings.this, MainActivity.class);
            startActivity(intent);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        alertDialogItems[0] = new AlertDialogItem("English", getResources().getDrawable(R.drawable.ic_flag_uk, null));
        alertDialogItems[1] = new AlertDialogItem("Polski", getResources().getDrawable(R.drawable.ic_flag_pl, null));

        alertDialogListAdapter = new ArrayAdapter<AlertDialogItem>(
                this,
                android.R.layout.select_dialog_singlechoice,
                android.R.id.text1,
                alertDialogItems) {

            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView textView = view.findViewById(android.R.id.text1);
                textView.setText(alertDialogItems[position].language);
                textView.setCompoundDrawablesWithIntrinsicBounds(alertDialogItems[position].icon, null, null, null);

                int marginIconAndText = (int) (5 * getResources().getDisplayMetrics().density + 0.5f);

                textView.setCompoundDrawablePadding(marginIconAndText);

                return view;
            }
        };

        languageBtnListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] language = {"English", "Polski"};
                final String[] chosenLanguage = {"en"};

                AlertDialog.Builder builder = new AlertDialog.Builder(Settings.this);
                builder.setTitle("Choose language ...");

                builder.setAdapter(alertDialogListAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
//                                chosenLanguage[0] = "en";
                                setLocale("en");
                                break;
                            case 1:
//                                chosenLanguage[0] = "pl";
                                setLocale("pl");
                                break;
                        }
                    }
                });

//            builder.setSingleChoiceItems(language, -1, new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    switch (which) {
//                        case 0:
//                            chosenLanguage[0] = "en";
//                            break;
//                        case 1:
//                            chosenLanguage[0] = "pl";
//                            break;
//                    }
//                }
//            });

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

        currentLanguage = getIntent().getStringExtra(currentLang);

        languageBtn = findViewById(R.id.settings_language_Btn);
        languageBtn.setOnClickListener(languageBtnListener);

        syncBtn = findViewById(R.id.settings_sync_Btn);

        cancelBtn = findViewById(R.id.settings_cancel_Btn);
        cancelBtn.setOnClickListener(cancelBtnListener);


    }

    private class AlertDialogItem {
        final String language;
        final Drawable icon;

        AlertDialogItem(String language, Drawable icon) {
            this.language = language;
            this.icon = icon;
        }
    }

}
