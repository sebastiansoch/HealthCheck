package com.gmail.ssoch.healthcheck.dao.dborm.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName = "pulse")
public class PulseEntity {
    @DatabaseField(generatedId = true)
    private long id;
    @DatabaseField
    private int pulse;
    @DatabaseField
    private Date measurementDate;

    public PulseEntity() {
    }

    public PulseEntity(int pulse, Date measurementDate) {
        this.pulse = pulse;
        this.measurementDate = measurementDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPulse() {
        return pulse;
    }

    public void setPulse(int pulse) {
        this.pulse = pulse;
    }

    public Date getMeasurementDate() {
        return measurementDate;
    }

    public void setMeasurementDate(Date measurementDate) {
        this.measurementDate = measurementDate;
    }
}
