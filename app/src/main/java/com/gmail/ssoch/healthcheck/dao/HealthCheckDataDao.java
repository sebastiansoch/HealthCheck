package com.gmail.ssoch.healthcheck.dao;

import com.gmail.ssoch.healthcheck.dao.data.BloodPressureData;
import com.gmail.ssoch.healthcheck.dao.data.BodyWeightData;
import com.gmail.ssoch.healthcheck.dao.data.GlucoseLevelData;

public interface HealthCheckDataDao {
    void saveBloodPressure(BloodPressureData bloodPressure) throws Exception;

    void saveBodyWeight(BodyWeightData bodyWeight) throws Exception;

    void saveGlucoseLevel(GlucoseLevelData glucoseLevel) throws Exception;
}
