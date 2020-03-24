package com.gmail.ssoch.healthcheck.dao;

import com.gmail.ssoch.healthcheck.dao.data.BloodPressureData;
import com.gmail.ssoch.healthcheck.dao.data.BloodPressureNorm;
import com.gmail.ssoch.healthcheck.dao.data.BodyWeightData;
import com.gmail.ssoch.healthcheck.dao.data.GlucoseLevelData;
import com.gmail.ssoch.healthcheck.dao.data.PulseNorm;

import java.util.List;

public interface HealthCheckDataDao {
    void saveBloodPressure(BloodPressureData bloodPressure) throws Exception;

    void saveBodyWeight(BodyWeightData bodyWeight) throws Exception;

    void saveGlucoseLevel(GlucoseLevelData glucoseLevel) throws Exception;

    List<BloodPressureNorm> getBloodPressureNorms() throws Exception;

    List<PulseNorm> getPulseNorms() throws Exception;
}
