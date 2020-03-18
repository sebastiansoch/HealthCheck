package com.gmail.ssoch.healthcheck.repo;

import android.content.Context;

public interface HealthCheckRepo {

    void init(Context applicationContext);
    <T> boolean save(T data);
    <T, V> T findByCriteria(V criteria);

}
