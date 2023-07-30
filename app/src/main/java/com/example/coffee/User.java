package com.example.coffee;

public class User {
    private String fullname, phonenum, email, address;

    public User(String fullname, String phonenum, String email, String address) {
        this.fullname = fullname;
        this.phonenum = phonenum;
        this.email = email;
        this.address = address;
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
}