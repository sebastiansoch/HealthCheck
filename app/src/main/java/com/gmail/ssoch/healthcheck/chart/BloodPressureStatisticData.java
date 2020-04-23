package com.gmail.ssoch.healthcheck.chart;

import android.util.Range;

import com.gmail.ssoch.healthcheck.dao.HealthCheckDataDao;
import com.gmail.ssoch.healthcheck.dao.data.BloodPressureData;
import com.gmail.ssoch.healthcheck.dao.data.BloodPressureNorm;

import java.io.IOException;
import java.util.List;

public class BloodPressureStatisticData implements StatisticData <BloodPressureData, BloodPressureNorm> {
    private HealthCheckDataDao healthCheckDataDao;
    private Range statisticRange;

    public BloodPressureStatisticData(HealthCheckDataDao healthCheckDataDao, Range statisticRange) {
        this.healthCheckDataDao = healthCheckDataDao;
        this.statisticRange = statisticRange;
    }

    @Override
    public List<BloodPressureData> getDataToShow() throws Exception {
        return healthCheckDataDao.getBloodPressureInRange(statisticRange);
    }

    @Override
    public List<BloodPressureNorm> getNorms() throws Exception {
        return healthCheckDataDao.getBloodPressureNorms();
    }
}

