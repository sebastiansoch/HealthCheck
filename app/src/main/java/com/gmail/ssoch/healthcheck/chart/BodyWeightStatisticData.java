package com.gmail.ssoch.healthcheck.chart;

import android.util.Range;

import com.gmail.ssoch.healthcheck.dao.HealthCheckDataDao;
import com.gmail.ssoch.healthcheck.dao.data.BodyWeightData;
import com.gmail.ssoch.healthcheck.dao.data.BodyWeightNorm;

import java.io.IOException;
import java.util.List;

public class BodyWeightStatisticData implements StatisticData <BodyWeightData, BodyWeightNorm> {

    private HealthCheckDataDao healthCheckDataDao;
    private Range statisticRange;

    public BodyWeightStatisticData(HealthCheckDataDao healthCheckDataDao, Range statisticRange) {
        this.healthCheckDataDao = healthCheckDataDao;
        this.statisticRange = statisticRange;
    }

    @Override
    public List<BodyWeightData> getDataToShow() throws IOException {
        return healthCheckDataDao.getBodyWeighInRange(statisticRange);
    }

    @Override
    public List<BodyWeightNorm> getNorms() throws Exception {
        return healthCheckDataDao.getBodyWeightNorms();
    }
}

