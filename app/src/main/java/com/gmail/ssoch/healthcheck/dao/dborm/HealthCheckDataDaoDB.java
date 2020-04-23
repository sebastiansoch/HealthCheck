package com.gmail.ssoch.healthcheck.dao.dborm;

import android.content.Context;
import android.util.Range;

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
import com.gmail.ssoch.healthcheck.dao.dborm.entity.BodyWeightEntity;
import com.gmail.ssoch.healthcheck.dao.dborm.entity.GlucoseLevelEntity;
import com.gmail.ssoch.healthcheck.dao.dborm.entity.PulseEntity;
import com.gmail.ssoch.healthcheck.dao.dborm.entity.UserPersonalDataEntity;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HealthCheckDataDaoDB implements HealthCheckDataDao {

    private final Context context;
    private final DBHelper dbHelper;
    private final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public HealthCheckDataDaoDB(Context context, DBHelper dbHelper) {
        this.context = context;
        this.dbHelper = dbHelper;
    }

    @Override
    public void saveBloodPressure(BloodPressureData bloodPressure) throws ParseException, SQLException {
        BloodPressureEntity bloodPressureEntity = new BloodPressureEntity(
                Integer.parseInt(bloodPressure.getSystolic()),
                Integer.parseInt(bloodPressure.getDiastolic()),
                format.parse(bloodPressure.getMeasurementDate()));
        dbHelper.create(bloodPressureEntity);
    }

    @Override
    public void savePulse(PulseData pulseData) throws ParseException, SQLException {
        PulseEntity pulseEntity = new PulseEntity(
                Integer.parseInt(pulseData.getPulse()),
                format.parse(pulseData.getMeasurementDate()));
        dbHelper.create(pulseEntity);
    }

    @Override
    public void saveBodyWeight(BodyWeightData bodyWeight) throws ParseException, SQLException {
        BodyWeightEntity bodyWeightEntity = new BodyWeightEntity(
                Double.parseDouble(bodyWeight.getBodyWeight()),
                format.parse(bodyWeight.getMeasurementDate())
        );
        dbHelper.create(bodyWeightEntity);
    }

    @Override
    public void saveGlucoseLevel(GlucoseLevelData glucoseLevel) throws ParseException, SQLException {
        GlucoseLevelEntity glucoseLevelEntity = new GlucoseLevelEntity(
                Double.parseDouble(glucoseLevel.getGlucoseLevelMmol()),
                Double.parseDouble(glucoseLevel.getGlucoseLevelMg()),
                format.parse(glucoseLevel.getMeasurementDate())
        );
        dbHelper.create(glucoseLevelEntity);
    }

    @Override
    public void saveUserPersonalData(UserPersonalData userPersonalData) throws ParseException, SQLException {
        UserPersonalDataEntity userPersonalDataEntity = new UserPersonalDataEntity(
                userPersonalData.getGender(),
                Double.parseDouble(userPersonalData.getHeight()),
                format.parse(userPersonalData.getDateOfBirth())
        );
        dbHelper.create(userPersonalDataEntity);
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
    public List<BloodPressureData> getBloodPressureInRange(Range<String> dateRange) throws ParseException, SQLException {
        Range<Date> range = new Range<>(format.parse(dateRange.getLower()), format.parse(dateRange.getUpper()));
        List<BloodPressureEntity> entities = dbHelper.findInRange(new BloodPressureEntity(), range);

        final List<BloodPressureData> data = new ArrayList<>();
        for (BloodPressureEntity entity : entities) {
            data.add(new BloodPressureData(String.valueOf(entity.getSystolic()), String.valueOf(entity.getDiastolic()),
                    format.format(entity.getMeasurementDate()))
            );
        }

        return data;
    }

    @Override
    public List<PulseData> getPulseInRange(Range<String> dateRange) throws ParseException, SQLException {
        Range<Date> range = new Range<>(format.parse(dateRange.getLower()), format.parse(dateRange.getUpper()));
        List<PulseEntity> entities = dbHelper.findInRange(new PulseEntity(), range);

        final List<PulseData> data = new ArrayList<>();
        for (PulseEntity entity : entities) {
            data.add(new PulseData(String.valueOf(entity.getPulse()),
                    format.format(entity.getMeasurementDate()))
            );
        }

        return data;
    }

    @Override
    public List<BodyWeightData> getBodyWeighInRange(Range<String> dateRange) throws ParseException, SQLException {
        Range<Date> range = new Range<>(format.parse(dateRange.getLower()), format.parse(dateRange.getUpper()));
        List<BodyWeightEntity> entities = dbHelper.findInRange(new BodyWeightEntity(), range);

        final List<BodyWeightData> data = new ArrayList<>();
        for (BodyWeightEntity entity : entities) {
            data.add(new BodyWeightData(String.valueOf(entity.getWeight()),
                    format.format(entity.getMeasurementDate()))
            );
        }

        return data;
    }

    @Override
    public List<GlucoseLevelData> getGlucoseLevelInRange(Range<String> dateRange) throws ParseException, SQLException {
        Range<Date> range = new Range<>(format.parse(dateRange.getLower()), format.parse(dateRange.getUpper()));
        List<GlucoseLevelEntity> entities = dbHelper.findInRange(new GlucoseLevelEntity(), range);

        final List<GlucoseLevelData> data = new ArrayList<>();
        for (GlucoseLevelEntity entity : entities) {
            data.add(new GlucoseLevelData(String.valueOf(entity.getGlucoseLevelMmol()), String.valueOf(entity.getGlucoseLevelMg()),
                    format.format(entity.getMeasurementDate()))
            );
        }

        return data;
    }

    @Override
    public UserPersonalData getUserPersonalData() throws SQLException {
        UserPersonalDataEntity entities = dbHelper.findAll(new UserPersonalDataEntity()).get(0);
        return new UserPersonalData(entities.getGender(), String.valueOf(entities.getHeight()), format.format(entities.getDateOfBirth()));
    }
}
//            dbHelper.deleteById(new BloodPressureEntity(), 4);