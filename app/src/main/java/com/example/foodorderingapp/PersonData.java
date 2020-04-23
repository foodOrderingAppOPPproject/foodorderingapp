package com.example.foodorderingapp;

public class PersonData {

    private String PersonName;
    private String PersonNumber;
    private String PersonId;
    private boolean Sub;


    public PersonData(){}

    PersonData(String personName, String personNumber, String personId, boolean Sub) {
        this.PersonName = personName;
        this.PersonNumber = personNumber;
        this.PersonId = personId;
        this.Sub = Sub;
    }

    PersonData(String personName, String personNumber, String personId) {
        this.PersonName = personName;
        this.PersonNumber = personNumber;
        this.PersonId = personId;
       // this.Sub = Sub;
    }
    public String getPersonName() {
        return PersonName;
    }

    public String getPersonNumber() {
        return PersonNumber;
    }

    public String getPersonId() {
        return PersonId;
    }

    public boolean isSub() {
        return Sub;
    }
}
