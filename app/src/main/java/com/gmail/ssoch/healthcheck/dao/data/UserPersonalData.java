package com.gmail.ssoch.healthcheck.dao.data;

public class UserPersonalData {

    private final String gender;
    private final String height;
    private final String dateOfBirth;

    public UserPersonalData(String gender, String height, String dateOfBirth) {
        this.gender = gender;
        this.height = height;
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public String getHeight() {
        return height;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }
}
