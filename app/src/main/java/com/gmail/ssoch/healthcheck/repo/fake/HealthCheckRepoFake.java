package com.gmail.ssoch.healthcheck.repo.fake;

import android.content.Context;

import com.gmail.ssoch.healthcheck.repo.HealthCheckRepo;

public class HealthCheckRepoFake implements HealthCheckRepo {

    Context applicationContext;

    @Override
    public void init(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public <T> boolean save(T data) {
        return false;
    }

    @Override
    public <T, V> T findByCriteria(V criteria) {
        return null;
    }
}
