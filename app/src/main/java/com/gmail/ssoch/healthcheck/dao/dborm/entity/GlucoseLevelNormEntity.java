package com.gmail.ssoch.healthcheck.dao.dborm.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "glucose_level_norm")
public class GlucoseLevelNormEntity {
    @DatabaseField(generatedId = true)
    private long id;
    @DatabaseField
    private double glucoseLevelLower;
    @DatabaseField
    private double glucoseLevelUpper;
    @DatabaseField
    private String description;

    public GlucoseLevelNormEntity() {
    }

    public GlucoseLevelNormEntity(double glucoseLevelLower, double glucoseLevelUpper, String description) {
        this.glucoseLevelLower = glucoseLevelLower;
        this.glucoseLevelUpper = glucoseLevelUpper;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getGlucoseLevelLower() {
        return glucoseLevelLower;
    }

    public void setGlucoseLevelLower(double glucoseLevelLower) {
        this.glucoseLevelLower = glucoseLevelLower;
    }

    public double getGlucoseLevelUpper() {
        return glucoseLevelUpper;
    }

    public void setGlucoseLevelUpper(double glucoseLevelUpper) {
        this.glucoseLevelUpper = glucoseLevelUpper;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
