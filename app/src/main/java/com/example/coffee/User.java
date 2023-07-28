package com.example.coffee;

public class User {
    public String fullname, phonenum, email, address;

    // Default constructor (no arguments)
    public User() {
        // Empty constructor
    }

    // Constructor with all properties
    public User(String fullname, String phonenum, String email, String address){
        this.fullname = fullname;
        this.phonenum = phonenum;
        this.email = email;
        this.address = address;
    }
}
