package com.cs993.cs993bookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class RegisterUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
    }

    /**
     * The activity when the user clicks on the sign up button
     * @param view The view
     */
    public void onClickSignUp(View view) {

        EditText regNameView = findViewById(R.id.RegisterName);
        String regNameText = regNameView.getText().toString();

        EditText regEmailView = findViewById(R.id.RegisterEmail);
        String regEmailText = regEmailView.getText().toString();

        EditText regPasswordView = findViewById(R.id.RegisterPassword);
        String regPasswordText = regPasswordView.getText().toString();

        EditText regPasswordConfirmView = findViewById(R.id.RegisterPasswordConfirm);
        String regPasswordConfirmText = regPasswordConfirmView.getText().toString();

        TextView resultOutput = findViewById(R.id.RegisterResult);
        StoredUserList storedUsers = getStoredUsers();

        if (regNameText.equals("") || regEmailText.equals("") || regPasswordText.equals("") || regPasswordConfirmText.equals("")) {
            resultOutput.setText(getResources().getString(R.string.MissingDetails));
        } else if (!regPasswordText.equals(regPasswordConfirmText)){
            resultOutput.setText(getResources().getString(R.string.PasswordMismatch));
        } else {
            if (storedUsers.checkEmailExists(regEmailText)) {
                resultOutput.setText(getResources().getString(R.string.UserAlreadyExists));
            } else {
                User newUser = new User(regEmailText, regPasswordText, regNameText, "customer");
                storedUsers.addNewUser(newUser);
                saveStoredUsers(storedUsers);
                finish();
            }
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

    /**
     * Stores the user list in a text file in local storage
     * @param storedUsers The filled UserList object
     */
    public void saveStoredUsers(StoredUserList storedUsers){

        File file = new File(getFilesDir(), "users.txt");
        storedUsers.saveStoredUsersList(file);
        Toast.makeText(this, R.string.NewUserCreated, Toast.LENGTH_LONG).show();
    }
}