package com.example.coffee;

public class User {
    private String fullname, phonenum, email, address;
    private int point;

    public User(String fullname, String phonenum, String email, String address) {
        this.fullname = fullname;
        this.phonenum = phonenum;
        this.email = email;
        this.address = address;
        this.point = 0;
    }

    public User(String fullname, String phonenum, String email, String address, int point) {
        this.fullname = fullname;
        this.phonenum = phonenum;
        this.email = email;
        this.address = address;
        this.point = point;
    }

    public String getFullname() {
        return fullname;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public int getPoint() {
        return point;
    }
}