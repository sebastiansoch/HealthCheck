package com.gmail.ssoch.healthcheck;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import static android.widget.Toast.LENGTH_SHORT;

public class LanguagePicker extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.language_picker, container, false);
        view.setMinimumWidth(getScreenWidthPixels() / 2);

        final RadioButton polish = view.findViewById(R.id.language_polish_RBtn);
        final RadioButton english = view.findViewById(R.id.language_english_RBtn);

        RadioButton.OnClickListener languagePikerListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.language_english_RBtn:
                        if (english.isChecked()) {
                            Toast.makeText(view.getContext(), "English", LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.language_polish_RBtn:
                        if (polish.isChecked()) {
                            Toast.makeText(view.getContext(), "Polish", LENGTH_SHORT).show();
                        }
                        break;
                }
            }
        };

        polish.setOnClickListener(languagePikerListener);
        english.setOnClickListener(languagePikerListener);


        return view;
    }

    private int getScreenWidthPixels() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }
}
