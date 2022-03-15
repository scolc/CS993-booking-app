package com.cs993.cs993bookingapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Booking implements Parcelable {

    private String email, uName, date, time, guestNum, status;

    public Booking(String email, String uName, String date, String time, String guestNum, String status) {

        this.email = email;
        this.uName = uName;
        this.date = date;
        this.time = time;
        this.guestNum = guestNum;
        this.status = status;
    }

    // Parcelable Methods

    protected Booking(Parcel in) {
        email = in.readString();
        uName = in.readString();
        date = in.readString();
        time = in.readString();
        guestNum = in.readString();
        status = in.readString();
    }

    public static final Creator<Booking> CREATOR = new Creator<Booking>() {
        @Override
        public Booking createFromParcel(Parcel in) {
            return new Booking(in);
        }

        @Override
        public Booking[] newArray(int size) {
            return new Booking[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(email);
        parcel.writeString(uName);
        parcel.writeString(date);
        parcel.writeString(time);
        parcel.writeString(guestNum);
        parcel.writeString(status);
    }

    // Getters and Setters

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUName() {
        return uName;
    }

    public void setUName(String uName) {
        this.uName = uName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getGuestNum() {
        return guestNum;
    }

    public void setGuestNum(String guestNum) {
        this.guestNum = guestNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Methods

    /**
     * Overrides the toString to format a string for display on screen
     * @return The formatted String
     */
    @Override
    public String toString(){
        String result = "";
        result += "Date: " + date + "\n";
        result += "Time: " + time + "\n";
        result += "Status: " + status;
        return result;
    }

    /**
     * Overrides the equals reference equality with object equality
     * @param aBooking The object to compare to this Booking object
     * @return True if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object aBooking) {

        Booking booking = (Booking) aBooking;
        return email.equals(booking.getEmail()) && date.equals(booking.getDate()) && time.equals(booking.getTime());
    }
}
