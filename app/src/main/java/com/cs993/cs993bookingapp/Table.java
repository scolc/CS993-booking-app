package com.cs993.cs993bookingapp;

import java.io.File;

public class Table {

    private String tableNum;
    private BookingList tableBookingList;

    public Table(String tableNum, File file) {

        this.tableNum = tableNum;
        tableBookingList = new BookingList(file);
    }

    // Getters and Setters

    public String getTableNum() {
        return tableNum;
    }

    public void setTableNum(String tableNum) {
        this.tableNum = tableNum;
    }

    public BookingList getTableBookingList() {
        return tableBookingList;
    }
}
