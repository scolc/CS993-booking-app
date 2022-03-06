package com.cs993.cs993bookingapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class UserList{

    ArrayList<StoredUser> storedUsersList;

    public UserList() {

        storedUsersList = new ArrayList<>();

    }

    public void updateList(StoredUser user) {
        storedUsersList.add(user);
    }

    public ArrayList<StoredUser> getStoredUsersList() {
        return storedUsersList;
    }

    /**
     * Fills the user list with user data from a text file
     * @param userTextFile The text file data
     */
    public void initialiseStoredUsersList(InputStream userTextFile) {

        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(userTextFile));

            String line = null;
            while ((line = reader.readLine()) != null) {
                addUser(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * Formats a text string and creates a user entry in the list
     * @param line The String containing a single users data
     */
    public void addUser(String line) {

        String[] tokens = line.split("/");

        StoredUser nextUser = new StoredUser(tokens[0], tokens[1], tokens[2], tokens[3]);
        storedUsersList.add(nextUser);
    }

    /**
     * Compares the entered login details with the stored users list
     * @param email The users entered Email
     * @param password The users entered Password
     * @return "true" if details are correct with the index of the user in the list, "false" otherwise
     */
    public String[] checkLoginDetails(String email, String password){

        String[] result = {"false", ""};

        for (StoredUser user : storedUsersList) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                result[0] = ("true");
                result[1] = String.valueOf(storedUsersList.indexOf(user));
                break;
            }
        }

        return result;
    }
}
