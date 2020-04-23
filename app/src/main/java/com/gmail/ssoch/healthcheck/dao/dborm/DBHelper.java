package com.gmail.ssoch.healthcheck.dao.dborm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Range;

import com.gmail.ssoch.healthcheck.GlucoseLevel;
import com.gmail.ssoch.healthcheck.dao.dborm.entity.BloodPressureEntity;
import com.gmail.ssoch.healthcheck.dao.dborm.entity.BloodPressureNormEntity;
import com.gmail.ssoch.healthcheck.dao.dborm.entity.BodyWeightEntity;
import com.gmail.ssoch.healthcheck.dao.dborm.entity.BodyWeightNormEntity;
import com.gmail.ssoch.healthcheck.dao.dborm.entity.GlucoseLevelNormEntity;
import com.gmail.ssoch.healthcheck.dao.dborm.entity.PulseEntity;
import com.gmail.ssoch.healthcheck.dao.dborm.entity.PulseNormEntity;
import com.gmail.ssoch.healthcheck.dao.dborm.entity.UserPersonalDataEntity;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class DBHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "health_check.db";
    private static final int DATABASE_VERSION = 2;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            createTables();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void createTables() throws SQLException {
        createTablesWithNorms();

        TableUtils.createTable(connectionSource, BloodPressureEntity.class);
        TableUtils.createTable(connectionSource, PulseEntity.class);
        TableUtils.createTable(connectionSource, BodyWeightEntity.class);
        TableUtils.createTable(connectionSource, GlucoseLevel.class);
        TableUtils.createTable(connectionSource, UserPersonalDataEntity.class);
    }

    private void createTablesWithNorms() throws SQLException {
        HealthNorms norms = new HealthNorms(this);

        TableUtils.createTable(connectionSource, BloodPressureNormEntity.class);
        norms.fillBloodPressureNormTable();

        TableUtils.createTable(connectionSource, PulseNormEntity.class);
        norms.fillPulseNormTable();

        TableUtils.createTable(connectionSource, BodyWeightNormEntity.class);
        norms.fillBodyWeightNormTable();

        TableUtils.createTable(connectionSource, GlucoseLevelNormEntity.class);
        norms.fillGlucoseLevelNormTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, BloodPressureEntity.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    <T> int create(T obj) throws SQLException {
        Dao<T, ?> dao = (Dao<T, ?>) getDao(obj.getClass());
        return dao.create(obj);
    }

    <T> List<T> findInRange(T obj, Range<Date> range) throws SQLException {
        Dao<T, ?> dao = (Dao<T, ?>) getDao(obj.getClass());
        QueryBuilder<T, ?> builder = dao.queryBuilder();
        Where<T, ?> where = builder.where();
        where.ge("measurementDate", range.getLower()).and().le("measurementDate", range.getUpper());
        return builder.query();
    }

    <T> List<T> findAll(T obj) throws SQLException {
        Dao<T, ?> dao = (Dao<T, ?> ) getDao(obj.getClass());
        return dao.queryForAll();
    }

    <T> int deleteById(T obj, Object id) throws SQLException {
        Dao<T, Object> dao = (Dao<T, Object>) getDao(obj.getClass());
        return dao.deleteById(id);
    }

}
