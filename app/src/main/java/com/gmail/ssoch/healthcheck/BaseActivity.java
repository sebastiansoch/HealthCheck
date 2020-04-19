package com.gmail.ssoch.healthcheck;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.gmail.ssoch.healthcheck.dao.HealthCheckDataDao;
import com.gmail.ssoch.healthcheck.dao.file.HealthCheckDataDaoFile;

public class BaseActivity extends AppCompatActivity {

    HealthCheckDataDao healthCheckDataDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        healthCheckDataDao = new HealthCheckDataDaoFile(this);
    }
}
