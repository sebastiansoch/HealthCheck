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
import com.gmail.ssoch.healthcheck.dao.data.UserPersonalData;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface HealthCheckDataDao {
    void saveBloodPressure(BloodPressureData bloodPressure) throws Exception;

    void savePulse(PulseData pulseData) throws Exception;

    void saveBodyWeight(BodyWeightData bodyWeight) throws Exception;

    void saveGlucoseLevel(GlucoseLevelData glucoseLevel) throws Exception;

    void saveUserPersonalData(UserPersonalData userPersonalData) throws Exception;

    List<BloodPressureNorm> getBloodPressureNorms() throws Exception;

    List<PulseNorm> getPulseNorms() throws Exception;

    List<BodyWeightNorm> getBodyWeightNorms() throws Exception;

    List<GlucoseLevelNorm> getGlucoseLevelNorms() throws Exception;

    List<BloodPressureData> getBloodPressureInRange(Range<String> dateRange) throws Exception;

    List<PulseData> getPulseInRange(Range<String> dateRange) throws Exception;

    List<BodyWeightData> getBodyWeighInRange(Range<String> dateRange) throws Exception;

    List<GlucoseLevelData> getGlucoseLevelInRange(Range<String> dateRange) throws Exception;

    UserPersonalData getUserPersonalData() throws Exception;
}
