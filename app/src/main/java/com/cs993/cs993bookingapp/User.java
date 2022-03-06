package com.cs993.cs993bookingapp;

public class User {

    private String userName;
    private String userEmail;
    private String userPassword;

    public User() {
        this.userName = "";
        this.userEmail = "";
        this.userPassword = "";
    }


    // Getters and Setters

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
