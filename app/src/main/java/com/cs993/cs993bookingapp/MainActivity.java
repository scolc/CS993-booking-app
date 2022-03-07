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

    public void onClickRegisterAccount(View view) {

        Intent intent = new Intent(this, RegisterUser.class);
        startActivity(intent);

    }

    /**
     * The activity when the user clicks on the log in button
     * @param view The view
     */
    public void onClickLogIn(View view) {

        UserList storedUsers = getStoredUsers();

        EditText loginEmailView = findViewById(R.id.LogInEmailAddress);
        String  loginEmailText = loginEmailView.getText().toString();

        EditText loginPasswordView = findViewById(R.id.LogInPassword);
        String loginPasswordText = loginPasswordView.getText().toString();

        String[] result;
        result = storedUsers.checkLoginDetails(loginEmailText, loginPasswordText);

        TextView resultOutput = findViewById(R.id.loginResult);

        User sessionUser;

        if (result[0].equals("true")){
            StoredUser currentStoredUser = storedUsers.getStoredUserAt(Integer.valueOf(result[1]));
            if (currentStoredUser.getAccessLevel().equals("customer")) {
                sessionUser = new Customer(currentStoredUser.getEmail(), currentStoredUser.getUName(), currentStoredUser.getPassword());
            } else if (currentStoredUser.getAccessLevel().equals("staff")) {
                sessionUser = new Staff(currentStoredUser.getEmail(), currentStoredUser.getUName(), currentStoredUser.getPassword());
            }
            Toast.makeText(this, R.string.LoginSuccess, Toast.LENGTH_LONG).show();
        } else {
            resultOutput.setText(getResources().getString(R.string.LoginFailed));
        }
    }

    public UserList getStoredUsers() {

        File file = null;
        FileInputStream fis = null;
        InputStream is = null;
        UserList storedUsers = new UserList();

        try {
            fis = openFileInput("users.txt");
            storedUsers.openStoredUsersList(fis);

        } catch (IOException e) {
            e.printStackTrace();
            try {
                is = getAssets().open("users.txt");
                storedUsers.openStoredUsersList(is);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return storedUsers;
    }
}