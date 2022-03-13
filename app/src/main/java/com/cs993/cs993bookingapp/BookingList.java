package com.cs993.cs993bookingapp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BookingList {

    private ArrayList<Booking> bookings;

    public BookingList(File file) {
        bookings = new ArrayList<>();
        openStoredBookings(file);
    }

    public void openStoredBookings(File file) {

        try {

            FileInputStream fis = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

            String line;
            while ((line = reader.readLine()) != null) {
                addNewBooking(line);
            }
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addNewBooking(String line){

        String[] tokens = line.split("/");
        String date = tokens[2] + "/" + tokens[3] + "/" + tokens[4];
        Booking booking = new Booking(tokens[0], tokens[1], date, tokens[5], tokens[6], tokens[7]);
        addBooking(booking);
    }

    public void addBooking(Booking booking){
        bookings.add(booking);
    }

    public Boolean removeBooking(Booking booking){
        for (Booking entry : bookings) {
            if (entry.equals(booking)) {
                bookings.remove(entry);
                return true;
            }
        }
        return false;
    }

    public void saveBookings(File file){

        String content = "";
        FileOutputStream fos = null;

        for (Booking booking : bookings) {
            content += booking.getEmail() + "/";
            content += booking.getUName() + "/";
            content += booking.getDate() + "/";
            content += booking.getTime() + "/";
            content += booking.getGuestNum() + "/";
            content += booking.getStatus();
            if (bookings.indexOf(booking) < bookings.size() - 1 ) {
                content += "\n";
            }
        }
        try {
            fos = new FileOutputStream(file);
            fos.write(content.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Booking> getBookingsForUser(User user){

        ArrayList<Booking> userBookings = new ArrayList<Booking>();

        for (Booking booking : bookings) {
            if (booking.getEmail().equals(user.getEmail())) {
                userBookings.add(booking);
            }
        }
        return userBookings;
    }
}
