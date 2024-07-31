package com.example.testDatabase.DTO;

import lombok.Data;

@Data
public class userDto {
    private final String fname;
    private String lname;
    private  String email;
    private String imageurl;

    public userDto(String fname, String lname, String email, String imageurl)
    {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.imageurl = imageurl;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
    }

