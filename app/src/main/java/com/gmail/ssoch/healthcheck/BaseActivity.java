package com.gmail.ssoch.healthcheck;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.gmail.ssoch.healthcheck.repo.HealthCheckRepo;
import com.gmail.ssoch.healthcheck.repo.fake.BloodPressureFake;
import com.gmail.ssoch.healthcheck.repo.fake.HealthCheckRepoFake;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected HealthCheckRepo getRepository(ActivityType type) {
        if (type == ActivityType.BLOOD_PRESSURE) {
            BloodPressureFake fake = new BloodPressureFake();
            fake.init(getApplicationContext());
            return fake;
        }
        return new HealthCheckRepoFake();
    }
}
