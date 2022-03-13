package com.cs993.cs993bookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickForgotPassword(View view) {

    }

    /**
     * The activity when the user clicks on the register link
     * @param view The view
     */
    public void onClickRegisterAccount(View view) {

        TextView resultOutput = findViewById(R.id.LoginResult);
        resultOutput.setText("");
        Intent intent = new Intent(this, RegisterUser.class);
        startActivity(intent);

    }

    /**
     * The activity when the user clicks on the log in button
     * @param view The view
     */
    public void onClickLogIn(View view) {

        StoredUserList storedUsers = getStoredUsers();

        EditText loginEmailView = findViewById(R.id.LogInEmailAddress);
        String  loginEmailText = loginEmailView.getText().toString();

        EditText loginPasswordView = findViewById(R.id.LogInPassword);
        String loginPasswordText = loginPasswordView.getText().toString();

        User currentUser = storedUsers.checkLoginDetails(loginEmailText, loginPasswordText);

        TextView resultOutput = findViewById(R.id.LoginResult);

        if (currentUser != null){
            Toast.makeText(this, R.string.LoginSuccess, Toast.LENGTH_SHORT).show();

            // Save a file with current user details so all activities can access without having to pass with intents
            File file = new File(getFilesDir(), "current_user.txt");
            currentUser.saveCurrentUserToFile(file);

            if (currentUser.getAccessLevel().equals("customer")) {
                Intent intent = new Intent(this, CustomerHome.class);
                startActivity(intent);
            } else if (currentUser.getAccessLevel().equals("staff")) {
                Intent intent = new Intent(this, StaffHome.class);
                startActivity(intent);
            }
        } else {
            resultOutput.setText(getResources().getString(R.string.LoginFailed));
        }
    }

    /**
     * Fetches the text file with store users from storage and loads it into a UserList object
     * @return The filled UserList object
     */
    public StoredUserList getStoredUsers() {

        FileInputStream fis = null;
        InputStream is = null;
        StoredUserList storedUsers = new StoredUserList();

        try {
            fis = openFileInput("users.txt");
            storedUsers.openStoredUsersList(fis);
            fis.close();
        } catch (IOException e) {
            // File not found in local storage so use the default one in assets
            try {
                is = getAssets().open("users.txt");
                storedUsers.openStoredUsersList(is);
                is.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        return storedUsers;
    }
}