package com.example.mad_1;

import java.io.Serializable;

public class User implements Serializable {

    private String fullname;
    private String pwd;
    private String email;
    private String birthdate;
    private String gender;
    private String address;

    public User(String fullname, String pwd, String email, String birthdate, String gender, String address) {
        this.fullname = fullname;
        this.pwd = pwd;
        this.email = email;
        this.birthdate = birthdate;
        this.gender = gender;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
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
}
