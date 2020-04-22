package com.gmail.ssoch.healthcheck.dao.dborm;

import android.content.Context;
import android.util.Range;

import com.gmail.ssoch.healthcheck.BaseActivity;
import com.gmail.ssoch.healthcheck.dao.HealthCheckDataDao;
import com.gmail.ssoch.healthcheck.dao.data.BloodPressureData;
import com.gmail.ssoch.healthcheck.dao.data.BloodPressureNorm;
import com.gmail.ssoch.healthcheck.dao.data.BodyWeightData;
import com.gmail.ssoch.healthcheck.dao.data.BodyWeightNorm;
import com.gmail.ssoch.healthcheck.dao.data.GlucoseLevelData;
import com.gmail.ssoch.healthcheck.dao.data.GlucoseLevelNorm;
import com.gmail.ssoch.healthcheck.dao.data.PulseData;
import com.gmail.ssoch.healthcheck.dao.data.PulseNorm;
import com.gmail.ssoch.healthcheck.dao.data.UserPersonalData;
import com.gmail.ssoch.healthcheck.dao.dborm.entity.BloodPressureEntity;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class HealthCheckDataDaoDB implements HealthCheckDataDao {

    private final Context context;
    private final DBHelper dbHelper;

    public HealthCheckDataDaoDB(Context context, DBHelper dbHelper) {
        this.context = context;
        this.dbHelper = dbHelper;
    }

    @Override
    public void saveBloodPressure(BloodPressureData bloodPressure) throws Exception {
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//            BloodPressureEntity bloodPressureEntity = new BloodPressureEntity(
//                    Integer.parseInt(bloodPressure.getSystolic()),
//                    Integer.parseInt(bloodPressure.getDiastolic()),
//                    format.parse(bloodPressure.getMeasurementDate()));
//            dbHelper.create(bloodPressureEntity);

            dbHelper.deleteById(new BloodPressureEntity(), 4);
    }

    @Override
    public void savePulse(PulseData pulseData) throws Exception {

    }

    @Override
    public void saveBodyWeight(BodyWeightData bodyWeight) throws Exception {

    }

    @Override
    public void saveGlucoseLevel(GlucoseLevelData glucoseLevel) throws Exception {

    }

    @Override
    public void saveUserPersonalData(UserPersonalData userPersonalData) throws IOException {

    }

    @Override
    public List<BloodPressureNorm> getBloodPressureNorms() throws Exception {
        return null;
    }

    @Override
    public List<PulseNorm> getPulseNorms() throws Exception {
        return null;
    }

    @Override
    public List<BodyWeightNorm> getBodyWeightNorms() throws IOException {
        return null;
    }

    @Override
    public List<GlucoseLevelNorm> getGlucoseLevelNorms() throws IOException {
        return null;
    }

    @Override
    public List<BloodPressureData> getBloodPressureInRange(Range<String> stringRange) throws IOException {
        return null;
    }

    @Override
    public List<PulseData> getPulseInRange(Range<String> dataRange) throws IOException {
        return null;
    }

    @Override
    public List<BodyWeightData> getBodyWeighInRange(Range<String> stringRange) throws IOException {
        return null;
    }

    @Override
    public List<GlucoseLevelData> getGlucoseLevelInRange(Range<String> stringRange) throws IOException {
        return null;
    }

    @Override
    public UserPersonalData getUserPersonalData() throws IOException {
        return null;
    }
}
