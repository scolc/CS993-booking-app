package com.cs993.cs993bookingapp;

import junit.framework.TestCase;

public class UserListTest extends TestCase {

    /**
     * Checks login details using a set of test details entered into the list directly
     */
    public void testCheckLoginDetails() {

        // Setup user list
        StoredUserList testStoredUserList = new StoredUserList();
        User testUser1 = new User("email", "pass", "name", "customer");
        testStoredUserList.updateList(testUser1);
        User testUser2 = new User("address", "password", "name", "customer");
        testStoredUserList.updateList(testUser2);


        // Test correct login details
        assertEquals(testUser1, testStoredUserList.checkLoginDetails("email", "pass"));
        assertEquals(testUser2, testStoredUserList.checkLoginDetails("address", "password"));

        // Test incorrect login details
        assertNull(testStoredUserList.checkLoginDetails("email", "password"));
        assertNull(testStoredUserList.checkLoginDetails("address", "pass"));



    }
}