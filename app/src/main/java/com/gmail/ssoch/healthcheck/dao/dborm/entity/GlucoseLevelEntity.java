package com.gmail.ssoch.healthcheck.dao.dborm.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName = "glucose_level")
public class GlucoseLevelEntity {
    @DatabaseField(generatedId = true)
    private long id;
    @DatabaseField
    private double glucoseLevelMmol;
    @DatabaseField
    private double glucoseLevelMg;
    @DatabaseField
    private Date measurementDate;

    public GlucoseLevelEntity() {
    }

    public GlucoseLevelEntity(double glucoseLevelMmol, double glucoseLevelMg, Date measurementDate) {
        this.glucoseLevelMmol = glucoseLevelMmol;
        this.glucoseLevelMg = glucoseLevelMg;
        this.measurementDate = measurementDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getGlucoseLevelMmol() {
        return glucoseLevelMmol;
    }

    public void setGlucoseLevelMmol(double glucoseLevelMmol) {
        this.glucoseLevelMmol = glucoseLevelMmol;
    }

    public double getGlucoseLevelMg() {
        return glucoseLevelMg;
    }

    public void setGlucoseLevelMg(double glucoseLevelMg) {
        this.glucoseLevelMg = glucoseLevelMg;
    }

    public Date getMeasurementDate() {
        return measurementDate;
    }

    public void setMeasurementDate(Date measurementDate) {
        this.measurementDate = measurementDate;
    }
}
