package com.cs993.cs993bookingapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class User {

    private String email, password, uName, accessLevel;

    public User(String email, String password, String uName, String accessLevel) {

        this.email = email;
        this.password = password;
        this.uName = uName;
        this.accessLevel = accessLevel;
    }

    // Getters and Setters

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUName() {
        return uName;
    }

    public void setUName(String name) {
        this.uName = name;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }

    // Methods

    public void saveUserToFile(File file) {

        String content = "";
        FileOutputStream fos = null;

        content += email + "/";
        content += password + "/";
        content += uName + "/";
        content += accessLevel;

        try {
            fos = new FileOutputStream(file);
            fos.write(content.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
