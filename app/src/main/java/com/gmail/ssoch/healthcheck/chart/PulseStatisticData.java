package com.gmail.ssoch.healthcheck.chart;

import android.util.Range;

import com.gmail.ssoch.healthcheck.dao.HealthCheckDataDao;
import com.gmail.ssoch.healthcheck.dao.data.PulseData;
import com.gmail.ssoch.healthcheck.dao.data.PulseNorm;

import java.io.IOException;
import java.util.List;

public class PulseStatisticData implements StatisticData<PulseData, PulseNorm> {

    private final HealthCheckDataDao healthCheckDataDao;
    private final Range<String> statisticRange;

    public PulseStatisticData(HealthCheckDataDao healthCheckDataDao, Range<String> statisticRange) {
        this.healthCheckDataDao = healthCheckDataDao;
        this.statisticRange = statisticRange;
    }

    @Override
    public List<PulseData> getDataToShow() throws Exception {
        return healthCheckDataDao.getPulseInRange(statisticRange);
    }

    @Override
    public List<PulseNorm> getNorms() throws Exception {
        return healthCheckDataDao.getPulseNorms();
    }
}

