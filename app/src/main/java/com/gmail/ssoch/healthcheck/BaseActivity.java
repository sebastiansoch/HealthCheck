package com.gmail.ssoch.healthcheck;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.gmail.ssoch.healthcheck.dao.HealthCheckDataDao;
import com.gmail.ssoch.healthcheck.dao.dborm.DBHelper;
import com.gmail.ssoch.healthcheck.dao.dborm.HealthCheckDataDaoDB;
import com.gmail.ssoch.healthcheck.dao.file.HealthCheckDataDaoFile;

public class BaseActivity extends AppCompatActivity {

    HealthCheckDataDao healthCheckDataDao = null;
    DBHelper dbHelper = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        healthCheckDataDao = new HealthCheckDataDaoFile(this);

        if (dbHelper == null) {
            dbHelper = new DBHelper(this);
        }

        if (healthCheckDataDao == null) {
            healthCheckDataDao = new HealthCheckDataDaoDB(this, dbHelper);
        }

    }
}
