package com.gmail.ssoch.healthcheck.chart;

import android.util.Range;

import com.gmail.ssoch.healthcheck.dao.HealthCheckDataDao;
import com.gmail.ssoch.healthcheck.dao.data.GlucoseLevelData;
import com.gmail.ssoch.healthcheck.dao.data.GlucoseLevelNorm;

import java.io.IOException;
import java.util.List;

public class GlucoseLevelStatisticData implements StatisticData<GlucoseLevelData, GlucoseLevelNorm> {
    private final HealthCheckDataDao healthCheckDataDao;
    private final Range<String> statisticRange;

    public GlucoseLevelStatisticData(HealthCheckDataDao healthCheckDataDao, Range<String> statisticRange) {
        this.healthCheckDataDao = healthCheckDataDao;
        this.statisticRange = statisticRange;
    }

    @Override
    public List<GlucoseLevelData> getDataToShow() throws Exception {
        return healthCheckDataDao.getGlucoseLevelInRange(statisticRange);
    }

    @Override
    public List<GlucoseLevelNorm> getNorms() throws Exception {
        return healthCheckDataDao.getGlucoseLevelNorms();
    }
}
