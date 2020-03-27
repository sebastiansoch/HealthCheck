package com.gmail.ssoch.healthcheck.dao;

import android.util.Range;

import com.gmail.ssoch.healthcheck.dao.data.BloodPressureData;
import com.gmail.ssoch.healthcheck.dao.data.BloodPressureNorm;
import com.gmail.ssoch.healthcheck.dao.data.BodyWeightData;
import com.gmail.ssoch.healthcheck.dao.data.BodyWeightNorm;
import com.gmail.ssoch.healthcheck.dao.data.GlucoseLevelData;
import com.gmail.ssoch.healthcheck.dao.data.GlucoseLevelNorm;
import com.gmail.ssoch.healthcheck.dao.data.PulseData;
import com.gmail.ssoch.healthcheck.dao.data.PulseNorm;

import java.io.IOException;
import java.util.List;

public interface HealthCheckDataDao {
    void saveBloodPressure(BloodPressureData bloodPressure) throws Exception;

    void savePulse(PulseData pulseData) throws Exception;

    void saveBodyWeight(BodyWeightData bodyWeight) throws Exception;

    void saveGlucoseLevel(GlucoseLevelData glucoseLevel) throws Exception;

    List<BloodPressureNorm> getBloodPressureNorms() throws Exception;

    List<PulseNorm> getPulseNorms() throws Exception;

    List<BodyWeightNorm> getBodyWeightNorms() throws IOException;

    List<GlucoseLevelNorm> getGlucoseLevelNorms() throws IOException;

    List<BloodPressureData> getBloodPressureInRange(Range<String> stringRange) throws IOException;

    List<PulseData> getPulseInRange(Range<String> dataRange) throws IOException;

    List<BodyWeightData> getBodyWeighInRange(Range<String> stringRange) throws IOException;

    List<GlucoseLevelData> getGlucoseLevelInRange(Range<String> stringRange) throws IOException;

}
