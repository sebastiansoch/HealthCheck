package com.gmail.ssoch.healthcheck.dao.dborm.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName = "blood_pressure")
public class BloodPressureEntity {

    @DatabaseField (generatedId = true)
    long id;
    @DatabaseField
    int systolic;
    @DatabaseField
    int diastolic;
    @DatabaseField
    Date measurementDate;

    public BloodPressureEntity() {
    }

    public BloodPressureEntity(int systolic, int diastolic, Date measurementDate) {
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.measurementDate = measurementDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSystolic() {
        return systolic;
    }

    public void setSystolic(int systolic) {
        this.systolic = systolic;
    }

    public int getDiastolic() {
        return diastolic;
    }

    public void setDiastolic(int diastolic) {
        this.diastolic = diastolic;
    }

    public Date getMeasurementDate() {
        return measurementDate;
    }

    public void setMeasurementDate(Date measurementDate) {
        this.measurementDate = measurementDate;
    }
}
