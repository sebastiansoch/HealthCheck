package com.gmail.ssoch.healthcheck;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Locale;

public class LanguagePicker extends DialogFragment {

    private String currentLanguage;

    private RadioButton polishRBtn;
    private RadioButton englishRBtn;
    private RadioButton.OnClickListener languagePikerListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.language_english_RBtn:
                    currentLanguage = "en";
                    break;
                case R.id.language_polish_RBtn:
                    currentLanguage = "pl";
                    break;
            }
        }
    };

    private Button saveBtn;
    private Button.OnClickListener saveBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setLocale(currentLanguage);
        }
    };

    private Button cancelBtn;
    private Button.OnClickListener cancelBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dismiss();
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.language_picker, container, false);
        view.setMinimumWidth(getScreenWidthPixels() / 2);
        setCancelable(false);

        polishRBtn = view.findViewById(R.id.language_polish_RBtn);
        polishRBtn.setOnClickListener(languagePikerListener);

        englishRBtn = view.findViewById(R.id.language_english_RBtn);
        englishRBtn.setOnClickListener(languagePikerListener);

        setCurrentLanguage();

        saveBtn = view.findViewById(R.id.language_save_Btn);
        saveBtn.setOnClickListener(saveBtnListener);

        cancelBtn = view.findViewById(R.id.language_cancel_Btn);
        cancelBtn.setOnClickListener(cancelBtnListener);

        return view;
    }

    private void setCurrentLanguage() {
        if (getResources().getConfiguration().getLocales().toLanguageTags().equals("pl")) {
            polishRBtn.setChecked(true);
            currentLanguage = "pl";
        } else {
            englishRBtn.setChecked(true);
            currentLanguage = "en";
        }
    }

    private int getScreenWidthPixels() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    private void setLocale(String localeName) {
        Locale myLocale = new Locale(localeName);

        Configuration configuration = getResources().getConfiguration();
        configuration.setLocale(myLocale);
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());

        Intent intent = new Intent(getContext(), Settings.class);
        startActivity(intent);
    }
}
