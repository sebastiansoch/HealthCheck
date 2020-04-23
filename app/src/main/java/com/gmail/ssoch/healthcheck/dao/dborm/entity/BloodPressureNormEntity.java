package com.gmail.ssoch.healthcheck.dao.dborm.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "blood_pressure_norm")
public class BloodPressureNormEntity {
    @DatabaseField(generatedId = true)
    private long id;
    @DatabaseField
    private int systolicLower;
    @DatabaseField
    private int systolicUpper;
    @DatabaseField
    private int diastolicLower;
    @DatabaseField
    private int diastolicUpper;
    @DatabaseField
    private String description;

    public BloodPressureNormEntity() {
    }

    public BloodPressureNormEntity(int systolicLower, int systolicUpper, int diastolicLower, int diastolicUpper, String description) {
        this.systolicLower = systolicLower;
        this.systolicUpper = systolicUpper;
        this.diastolicLower = diastolicLower;
        this.diastolicUpper = diastolicUpper;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSystolicLower() {
        return systolicLower;
    }

    public void setSystolicLower(int systolicLower) {
        this.systolicLower = systolicLower;
    }

    public int getSystolicUpper() {
        return systolicUpper;
    }

    public void setSystolicUpper(int systolicUpper) {
        this.systolicUpper = systolicUpper;
    }

    public int getDiastolicLower() {
        return diastolicLower;
    }

    public void setDiastolicLower(int diastolicLower) {
        this.diastolicLower = diastolicLower;
    }

    public int getDiastolicUpper() {
        return diastolicUpper;
    }

    public void setDiastolicUpper(int diastolicUpper) {
        this.diastolicUpper = diastolicUpper;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
