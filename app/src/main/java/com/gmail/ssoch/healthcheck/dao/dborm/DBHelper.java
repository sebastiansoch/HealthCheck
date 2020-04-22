package com.gmail.ssoch.healthcheck.dao.dborm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.gmail.ssoch.healthcheck.dao.dborm.entity.BloodPressureEntity;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

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
            Log.i(DBHelper.class.getName(), "onCreate");
            TableUtils.createTable(connectionSource, BloodPressureEntity.class);
        } catch (SQLException ex) {
            Log.e(DBHelper.class.getName(), "Can't create database", ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            Log.i(DBHelper.class.toString(), "onUpgrade");
            TableUtils.dropTable(connectionSource, BloodPressureEntity.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException ex) {
            Log.e(DBHelper.class.toString(), "Can't drop database", ex);
            throw new RuntimeException(ex);
        }
    }

    public <T> int create(T obj) throws SQLException {
        Dao<T, ?> dao = (Dao<T, ?>) getDao(obj.getClass());
        return dao.create(obj);
    }

    public <T> int deleteById(T obj, Object id) throws SQLException {
        Dao<T, Object> dao = (Dao<T, Object>) getDao(obj.getClass());
        return dao.deleteById(id);
    }

}
