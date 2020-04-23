package com.gmail.ssoch.healthcheck.dao.dborm.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "body_weight_norm")
public class BodyWeightNormEntity {

    @DatabaseField(generatedId = true)
    private long id;
    @DatabaseField
    private int weightLower;
    @DatabaseField
    private int weightUpper;
    @DatabaseField
    private String description;

    public BodyWeightNormEntity() {
    }

    public BodyWeightNormEntity(int weightLower, int weightUpper, String description) {
        this.weightLower = weightLower;
        this.weightUpper = weightUpper;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getWeightLower() {
        return weightLower;
    }

    public void setWeightLower(int weightLower) {
        this.weightLower = weightLower;
    }

    public int getWeightUpper() {
        return weightUpper;
    }

    public void setWeightUpper(int weightUpper) {
        this.weightUpper = weightUpper;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
