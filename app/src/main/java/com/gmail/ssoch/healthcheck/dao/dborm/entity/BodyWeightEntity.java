package com.gmail.ssoch.healthcheck.dao.dborm.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName = "body_weight")
public class BodyWeightEntity {

    @DatabaseField(generatedId = true)
    private long id;
    @DatabaseField
    private double weight;
    @DatabaseField
    private Date measurementDate;

    public BodyWeightEntity() {
    }

    public BodyWeightEntity(double weight, Date measurementDate) {
        this.weight = weight;
        this.measurementDate = measurementDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Date getMeasurementDate() {
        return measurementDate;
    }

    public void setMeasurementDate(Date measurementDate) {
        this.measurementDate = measurementDate;
    }
}
