package com.cs993.cs993bookingapp;

import java.io.File;

public class Staff extends User{

    public Staff(String email, String password, String uName, String accessLevel) {
        super(email, password, uName, accessLevel);
    }

    public Staff(File file) {
        super(file);
    }
}
