package com.cs993.cs993bookingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class BookingCreated extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_created);
    }

    // Menu items

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Home:
                Intent intent1 = new Intent(this, CustomerHome.class);
                startActivity(intent1);
                return true;
            case R.id.LogOut:
                Intent intent2 = new Intent(this, MainActivity.class);
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * The activity when the user clicks on the Home button
     * @param view The view
     */
    public void onClickHome(View view) {

        Intent intent = new Intent(this, CustomerHome.class);
        startActivity(intent);
    }
}