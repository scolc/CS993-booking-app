package com.cs993.cs993bookingapp;

import java.io.File;

public class Customer extends User{

    public Customer(String email, String password, String uName, String accessLevel) {
        super(email, password, uName, accessLevel);
    }

    public Customer(File file) {
        super(file);
    }
}
