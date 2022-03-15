package com.cs993.cs993bookingapp;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StoredUserListTest {

    private StoredUserList testStoredUserList;
    private User testUser1;
    private User testUser2;

    @Before
    public void setUp() throws Exception {
        // Setup user list
        testStoredUserList = new StoredUserList();
        testUser1 = new User("email", "pass", "name", "customer");
        testStoredUserList.updateList(testUser1);
        testUser2 = new User("address", "password", "name", "customer");
        testStoredUserList.updateList(testUser2);
    }

    @After
    public void tearDown() throws Exception {
        testUser1 = null;
        testUser2 = null;
        testStoredUserList = null;
    }

    /**
     * Checks login details using a set of test details entered into the list directly
     */
    @Test
    public void testCheckLoginDetails() {

        // Test correct login details
        assertEquals(testUser1, testStoredUserList.checkLoginDetails("email", "pass"));
        assertEquals(testUser2, testStoredUserList.checkLoginDetails("address", "password"));

        // Test incorrect login details
        assertNull(testStoredUserList.checkLoginDetails("email", "password"));
        assertNull(testStoredUserList.checkLoginDetails("address", "pass"));
        assertNull(testStoredUserList.checkLoginDetails("test", "test"));
        assertNull(testStoredUserList.checkLoginDetails("test", "pass"));
        assertNull(testStoredUserList.checkLoginDetails("email", "test"));
        assertNull(testStoredUserList.checkLoginDetails("test", "password"));
        assertNull(testStoredUserList.checkLoginDetails("address", "test"));
    }

    /**
     * Checks a user object with the given email String exists in the list
     */
    @Test
    public void checkEmailExists() {

        assertEquals(true, testStoredUserList.checkEmailExists("email"));
        assertEquals(true, testStoredUserList.checkEmailExists("address"));
        assertEquals(false, testStoredUserList.checkEmailExists("pass"));
        assertEquals(false, testStoredUserList.checkEmailExists("password"));
        assertEquals(false, testStoredUserList.checkEmailExists("test"));
    }
}