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

    public BookingList() {
        bookings = new ArrayList<>();
    }

    // Getters
    public ArrayList<Booking> getBookings() {
        return bookings;
    }

    /**
     * Reads and stores all the booking information from file
     * @param file The file the booking information is stored in
     */
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

    /**
     * Handles a String made from a line of text from file and creates a booking object
     * @param line The String from the file
     */
    public void addNewBooking(String line){

        String[] tokens = line.split("/");
        String date = tokens[2] + "/" + tokens[3] + "/" + tokens[4];
        Booking booking = new Booking(tokens[0], tokens[1], date, tokens[5], tokens[6], tokens[7]);
        addBooking(booking);
    }

    /**
     * Adds a booking to the list of bookings
     * @param booking The booking object
     */
    public void addBooking(Booking booking){
        bookings.add(booking);
    }

    /**
     * Removes a booking from the list of bookings
     * @param booking The booking to remove from the list
     * @return True if the booking was removed successfully, false otherwise
     */
    public boolean removeBooking(Booking booking){
        for (Booking entry : bookings) {
            if (entry.equals(booking)) {
                bookings.remove(entry);
                return true;
            }
        }
        return false;
    }

    public boolean updateBooking(Booking booking, String status){
        for (Booking entry : bookings) {
            if (entry.equals(booking)) {
                entry.setStatus(status);
                return true;
            }
        }
        return false;
    }

    /**
     * Saves the booking list to file
     * @param file The file to save the bookings list in
     */
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

    /**
     * Checks the list of bookings for ones made by a given user
     * @param user The user to check the list for
     * @return The list of bookings made by the user
     */
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
