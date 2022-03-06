package com.cs993.cs993bookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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

    }

    /**
     * The activity when the user clicks on the log in button
     * @param view
     */
    public void onClickLogIn(View view) {

        UserList storedUsers = new UserList();
        InputStream userTextFile = null;
        try {
            userTextFile = getAssets().open("users.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        storedUsers.initialiseStoredUsersList(userTextFile);

        EditText loginEmailView = (EditText) findViewById(R.id.LogInEmailAddress);
        String  loginEmailText = loginEmailView.getText().toString();

        EditText loginPasswordView = findViewById(R.id.LogInPassword);
        String loginPasswordText = loginPasswordView.getText().toString();

        String[] result = {"false", ""};

        result = storedUsers.checkLoginDetails(loginEmailText, loginPasswordText);

        TextView resultOutput = findViewById(R.id.loginResult);
        resultOutput.setText(result[0]);




    }
}