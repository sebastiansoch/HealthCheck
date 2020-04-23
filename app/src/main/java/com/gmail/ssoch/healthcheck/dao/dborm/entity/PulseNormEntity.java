package com.gmail.ssoch.healthcheck.dao.dborm.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "pulse_norm")
public class PulseNormEntity {

    @DatabaseField(generatedId = true)
    private long id;
    @DatabaseField
    private int pulseLower;
    @DatabaseField
    private int pulseUpper;
    @DatabaseField
    private String description;

    public PulseNormEntity() {
    }

    public PulseNormEntity(int pulseLower, int pulseUpper, String description) {
        this.pulseLower = pulseLower;
        this.pulseUpper = pulseUpper;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPulseLower() {
        return pulseLower;
    }

    public void setPulseLower(int pulseLower) {
        this.pulseLower = pulseLower;
    }

    public int getPulseUpper() {
        return pulseUpper;
    }

    public void setPulseUpper(int pulseUpper) {
        this.pulseUpper = pulseUpper;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
