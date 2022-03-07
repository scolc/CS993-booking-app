package com.cs993.cs993bookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class RegisterUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
    }

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
        String result;
        UserList storedUsers = getStoredUsers();

        if (regNameText.equals("") || regEmailText.equals("") || regPasswordText.equals("") || regPasswordConfirmText.equals("")) {
            resultOutput.setText(getResources().getString(R.string.MissingDetails));
        } else if (!regPasswordText.equals(regPasswordConfirmText)){
            resultOutput.setText(getResources().getString(R.string.PasswordMismatch));
        } else {
            result = storedUsers.checkEmail(regEmailText);

            if (result.equals("true")) {
                resultOutput.setText(getResources().getString(R.string.UserAlreadyExists));
            } else {
                StoredUser newUser = new StoredUser(regEmailText, regPasswordText, regNameText, "customer");
                storedUsers.addNewUser(newUser);
                saveStoredUsers(storedUsers);
                finish();
            }
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

    public void saveStoredUsers(UserList storedUsers){

        FileOutputStream fos = null;

        try {
            fos = openFileOutput("users.txt", MODE_PRIVATE);
            storedUsers.saveStoredUsersList(fos);
            Toast.makeText(this, R.string.NewUserCreated, Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}