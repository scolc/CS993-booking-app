package com.cs993.cs993bookingapp;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class BookingListTest {

    private Booking booking1;
    private Booking booking2;
    private Booking booking3;
    private Booking booking4;
    private Booking booking5;
    private Booking booking6;
    private BookingList bookingList;
    private ArrayList<Booking> bookingListUser1;
    private ArrayList<Booking> bookingListUser2;
    private ArrayList<Booking> bookingListUser3;
    private User user1;
    private User user2;
    private User user3;

    @Before
    public void setUp() throws Exception {
        user1 = new User("user1email", "user1pass", "user1name", "customer");
        user2 = new User("user2email", "user2pass", "user2name", "customer");
        user3 = new User("user3email", "user3pass", "user3name", "customer");
        booking1 = new Booking("user1email", "user1name", "date1", "time1", "1", "not confirmed");
        booking2 = new Booking("user1email", "user1name", "date2", "time2", "1", "not confirmed");
        booking3 = new Booking("user1email", "user1name", "date3", "time3", "1", "not confirmed");
        booking4 = new Booking("user1email", "user1name", "date4", "time4", "1", "not confirmed");
        booking5 = new Booking("user2email", "user1name", "date5", "time5", "1", "not confirmed");
        booking6 = new Booking("user2email", "user1name", "date6", "time6", "1", "not confirmed");
        bookingList = new BookingList();
        bookingList.addBooking(booking1);
        bookingList.addBooking(booking2);
        bookingList.addBooking(booking3);
        bookingList.addBooking(booking4);
        bookingList.addBooking(booking5);
        bookingList.addBooking(booking6);
        bookingListUser1 = new ArrayList<Booking>();
        bookingListUser1.add(booking1);
        bookingListUser1.add(booking2);
        bookingListUser1.add(booking3);
        bookingListUser1.add(booking4);
        bookingListUser2 = new ArrayList<Booking>();
        bookingListUser2.add(booking5);
        bookingListUser2.add(booking6);
        bookingListUser3 = new ArrayList<Booking>();
    }

    @After
    public void tearDown() throws Exception {
        booking1 = null;
        booking2 = null;
        booking3 = null;
        booking4 = null;
        booking5 = null;
        booking6 = null;
        bookingList = null;
        bookingListUser1 = null;
        bookingListUser2 = null;
        bookingListUser3 = null;
        user1 = null;
        user2 = null;
        user3 = null;
    }

    /**
     * Tests removing a booking from the list
     */
    @Test
    public void removeBooking() {
        assertTrue(bookingList.removeBooking(booking1));
        assertTrue(bookingList.removeBooking(booking2));
        assertFalse(bookingList.removeBooking(booking1));
        assertFalse(bookingList.removeBooking(booking2));
    }

    /**
     * Tests checking the list for bookings for a given user
     */
    @Test
    public void getBookingsForUser() {
        assertEquals(bookingListUser1, bookingList.getBookingsForUser(user1));
        assertEquals(bookingListUser2, bookingList.getBookingsForUser(user2));
        assertEquals(bookingListUser3, bookingList.getBookingsForUser(user3));
    }

    /**
     * Tests updating a booking status
     */
    @Test
    public void updateBooking() {
        assertTrue(bookingList.updateBooking(booking1, "Confirmed"));
        assertEquals("Confirmed", booking1.getStatus());
        assertTrue(bookingList.updateBooking(booking1, "Denied"));
        assertEquals("Denied", booking1.getStatus());
        assertTrue(bookingList.updateBooking(booking2, "Confirmed"));
        assertEquals("Confirmed", booking2.getStatus());
        assertTrue(bookingList.updateBooking(booking2, "Denied"));
        assertEquals("Denied", booking2.getStatus());
    }
}