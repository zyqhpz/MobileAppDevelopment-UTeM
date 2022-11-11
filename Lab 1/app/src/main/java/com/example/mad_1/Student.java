package com.example.mad_1;

public class Student {
    private String fullname;
    private String studNo;
    private String email;
    private String birthdate;
    private String gender;
    private String state;

    public Student (String fullname, String studNo, String email, String birthdate, String gender, String state) {
        this.fullname = fullname;
        this.studNo= studNo;
        this.email = email;
        this.birthdate = birthdate;
        this.gender = gender;
        this.state = state;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStudNo() {
        return studNo;
    }

    public void setStudNo(String studNo) {
        this.studNo = studNo;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
