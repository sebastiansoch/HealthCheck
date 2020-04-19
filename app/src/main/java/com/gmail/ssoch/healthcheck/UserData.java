package com.gmail.ssoch.healthcheck;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.gmail.ssoch.healthcheck.dao.data.UserPersonalData;
import com.gmail.ssoch.healthcheck.utils.DataParser;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserData extends BaseActivity {

    private ConstraintLayout layout;
    private ConstraintLayout.OnTouchListener layoutOnTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            return true;
        }
    };


    private Spinner genderSpinner;
    private AdapterView.OnItemSelectedListener genderItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            parent.getSelectedItem();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private EditText heightET;

    private TextView dateOfBirthTV;
    private ImageButton dateOfBirthBtn;
    private DatePickerDialog.OnDateSetListener dateOfBirthPickerDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = dateFormat.parse(year + "-" + (month + 1) + "-" + dayOfMonth);
                if (date.after(new Date())) {
                    Toast.makeText(UserData.this, "Date of birth is not seated correctly", Toast.LENGTH_SHORT).show();
                    return;
                }

                String sBeginDate = dateFormat.format(date);
                dateOfBirthTV.setText(sBeginDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    };
    private ImageButton.OnClickListener dateOfBirthBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String[] split = dateOfBirthTV.getText().toString().split("-");
            int year = Integer.parseInt(split[0]);
            int month = Integer.parseInt(split[1]) - 1;
            int day = Integer.parseInt(split[2]);

            DatePickerDialog datePickerDialog = new DatePickerDialog(UserData.this, android.R.style.Theme_Material_Dialog_MinWidth,
                    dateOfBirthPickerDateSetListener, year, month, day);
            datePickerDialog.show();
        }
    };

    private UserPersonalData userPersonalData;

    private Button saveBtn;
    private Button.OnClickListener saveBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                parseAndSaveData();
                saveAsStartData();
            } catch (Exception ex) {
                Toast.makeText(v.getContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    };

    private Button cancelBtn;
    private Button.OnClickListener cancelBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(UserData.this, MainActivity.class);
            startActivity(intent);
        }
    };


    private void parseAndSaveData() throws ParseException, IOException {
        int genderID = genderSpinner.getSelectedItemPosition();
        String height = heightET.getText().toString();
        String dateOfBirth = dateOfBirthTV.getText().toString();

        DataParser.parseUserData(genderID, height, dateOfBirth);

        String gender;
        if (genderID == 0) {
            gender = "male";
        } else {
            gender = "female";
        }

        saveData(gender, height, dateOfBirth);
    }

    private void saveData(String gender, String height, String dateOfBirth) throws IOException {
        userPersonalData = new UserPersonalData(gender, height, dateOfBirth);
        healthCheckDataDao.saveUserPersonalData(userPersonalData);
    }

    private void saveAsStartData() {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("Height", Integer.parseInt(heightET.getText().toString()));
        editor.putString("Date Of Birth", dateOfBirthTV.getText().toString());
        editor.putInt("Gender", genderSpinner.getSelectedItemPosition());
        editor.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);

        layout = findViewById(R.id.user_data_layout);
        layout.setOnTouchListener(layoutOnTouchListener);

        heightET = findViewById(R.id.user_data_height_ET);

        genderSpinner = findViewById(R.id.user_data_gender_spinner);
        ArrayAdapter<CharSequence> genderArrayAdapter = ArrayAdapter.createFromResource(this, R.array.gender_array, android.R.layout.simple_expandable_list_item_1);
        genderArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(genderArrayAdapter);
        genderSpinner.setOnItemSelectedListener(genderItemSelectedListener);

        dateOfBirthTV = findViewById(R.id.user_data_date_of_birth_TV);
        dateOfBirthBtn = findViewById(R.id.user_data_date_of_birth_Btn);
        dateOfBirthBtn.setOnClickListener(dateOfBirthBtnListener);

        saveBtn = findViewById(R.id.user_data_save_Btn);
        saveBtn.setOnClickListener(saveBtnListener);

        cancelBtn = findViewById(R.id.user_data_cancel_Btn);
        cancelBtn.setOnClickListener(cancelBtnListener);

        fillEditTextWithStartData();
    }

    private void fillEditTextWithStartData() {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        heightET.setText(Integer.toString(preferences.getInt("Height", 0)));
        dateOfBirthTV.setText(preferences.getString("Date Of Birth", "1990-01-01"));
        genderSpinner.setSelection(preferences.getInt("Gender", 0));
    }
}
