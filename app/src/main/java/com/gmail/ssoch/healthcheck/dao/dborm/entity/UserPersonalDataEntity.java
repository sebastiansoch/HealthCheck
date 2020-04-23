package com.gmail.ssoch.healthcheck.dao.dborm.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName = "personal_data")
public class UserPersonalDataEntity {
    @DatabaseField(generatedId = true)
    private long id;
    @DatabaseField
    private String gender;
    @DatabaseField
    private double height;
    @DatabaseField
    private Date dateOfBirth;

    public UserPersonalDataEntity() {
    }

    public UserPersonalDataEntity(String gender, double height, Date dateOfBirth) {
        this.gender = gender;
        this.height = height;
        this.dateOfBirth = dateOfBirth;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

}
