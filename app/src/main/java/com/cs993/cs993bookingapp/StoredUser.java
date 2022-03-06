package com.cs993.cs993bookingapp;

public class StoredUser {

    private String email, password, name, accessLevel;

    public StoredUser(String email, String password, String name, String accessLevel) {

        this.email = email;
        this.password = password;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }
}
