package com.cs993.cs993bookingapp;

import junit.framework.TestCase;

public class UserListTest extends TestCase {

    /**
     * Checks login details using a set of test details entered into the list directly
     */
    public void testCheckLoginDetails() {

        // Setup user list
        UserList testUserList = new UserList();
        StoredUser testUser1 = new StoredUser("email", "pass", "name", "customer");
        testUserList.updateList(testUser1);
        StoredUser testUser2 = new StoredUser("address", "password", "name", "customer");
        testUserList.updateList(testUser2);



        assertEquals("true", testUserList.checkLoginDetails("email", "pass")[0]);
        assertEquals("0", testUserList.checkLoginDetails("email", "pass")[1]);

        assertEquals("false", testUserList.checkLoginDetails("email", "password")[0]);
        assertEquals("", testUserList.checkLoginDetails("email", "password")[1]);

        assertEquals("false", testUserList.checkLoginDetails("address", "pass")[0]);
        assertEquals("", testUserList.checkLoginDetails("address", "pass")[1]);

        assertEquals("true", testUserList.checkLoginDetails("address", "password")[0]);
        assertEquals("1", testUserList.checkLoginDetails("address", "password")[1]);

    }
}