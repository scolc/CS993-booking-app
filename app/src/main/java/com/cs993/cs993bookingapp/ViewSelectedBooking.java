package com.cs993.cs993bookingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class ViewSelectedBooking extends AppCompatActivity {

    private Booking booking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_selected_booking);

        booking = getIntent().getParcelableExtra("booking");
        TextView details = findViewById(R.id.YourBookingDetails);

        String detailList = "Date: " + booking.getDate() + "\n";
        detailList += "Time: " + booking.getTime() + "\n";
        detailList += "Guests: " + booking.getGuestNum() + "\n";
        detailList += "Status: " + booking.getStatus();

        details.setText(detailList);
    }

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

    public void onClickBack(View view) {

        finish();
    }

    public void onClickDelete(View view) {

        File bookingFile = new File(getFilesDir(), "bookings.txt");
        BookingList bookingList = new BookingList(bookingFile);

        boolean result = bookingList.removeBooking(booking);
        bookingList.saveBookings(bookingFile);

        if (result) {
            Toast.makeText(this, R.string.DeleteSuccess, Toast.LENGTH_SHORT).show();
        }

        Intent intent = new Intent(this, ViewBookings.class);
        startActivity(intent);

    }
}