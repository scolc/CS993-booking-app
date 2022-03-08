package com.cs993.cs993bookingapp;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class StoredUserList {

    ArrayList<User> usersList;

    public StoredUserList() {

        usersList = new ArrayList<>();

    }

    // Getters and Setters

    public User getStoredUserAt(int index){
        return usersList.get(index);
    }

    public void updateList(User user) {
        usersList.add(user);
    }

    public ArrayList<User> getStoredUsersList() {
        return usersList;
    }

    // Methods

    /**
     * Fills the user list with user data from a text file
     * @param is The text file data
     */
    public void openStoredUsersList(InputStream is) {

        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            String line;
            while ((line = reader.readLine()) != null) {
                addUser(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Saves the current users in the list to file
     * @param file The text file
     */
    public void saveStoredUsersList(File file) {

        String content = "";
        FileOutputStream fos = null;

        for (User user : usersList) {
            content += user.getEmail() + "/";
            content += user.getPassword() + "/";
            content += user.getUName() + "/";
            content += user.getAccessLevel();
            if (usersList.indexOf(user) < usersList.size() - 1 ) {
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
     * Formats a text string and creates a user entry in the list
     * @param line The String containing a single users data
     */
    public void addUser(String line) {

        String[] tokens = line.split("/");

        User nextUser = new User(tokens[0], tokens[1], tokens[2], tokens[3]);
        usersList.add(nextUser);
    }

    /**
     * Takes a storedUser object created from registering a new user and adds it to the list
     * @param newUser the new storedUser object
     */
    public void addNewUser(User newUser) {
        usersList.add(newUser);
    }

    /**
     * Compares the entered login details with the stored users list
     * @param email The users entered Email
     * @param password The users entered Password
     * @return "true" if details are correct with the index of the user in the list, "false" otherwise
     */
    public User checkLoginDetails(String email, String password){

        User currentUser = null;

        for (User user : usersList) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                currentUser = user;
                break;
            }
        }

        return currentUser;
    }

    /**
     * Compares the entered email with the stored users list
     * @param email The users entered email
     * @return "true" if details are found within the list
     */
    public boolean checkEmailExists(String email){

        for (User user : usersList) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }

        return false;
    }
}
