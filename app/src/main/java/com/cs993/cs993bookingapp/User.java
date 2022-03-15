package com.cs993.cs993bookingapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class User {

    private String email, password, uName, accessLevel;

    public User(String email, String password, String uName, String accessLevel) {

        this.email = email;
        this.password = password;
        this.uName = uName;
        this.accessLevel = accessLevel;
    }

    public User(File file){
        getCurrentUserFromFile(file);
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

    /**
     * Creates a file using the current logged in users information
     * @param file The file to save to
     */
    public void saveCurrentUserToFile(File file) {

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

    /**
     * Reads the current customer information from file
     * @param file The file to read from
     */
    public void getCurrentUserFromFile(File file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

            String line = reader.readLine();
            String[] tokens = line.split("/");
            email = tokens[0];
            password = tokens[1];
            uName = tokens[2];
            accessLevel = tokens[3];

            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
