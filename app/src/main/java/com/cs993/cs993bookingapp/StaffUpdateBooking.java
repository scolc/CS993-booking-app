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

public class StaffUpdateBooking extends AppCompatActivity {

    private Booking booking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_update_booking);

        booking = getIntent().getParcelableExtra("booking");
        TextView details = findViewById(R.id.StaffBookingDetails);

        String detailList = "Name: " + booking.getUName() + "\n";
        detailList += "Date: " + booking.getDate() + "\n";
        detailList += "Time: " + booking.getTime() + "\n";
        detailList += "Guests: " + booking.getGuestNum() + "\n";
        detailList += "Status: " + booking.getStatus();

        details.setText(detailList);
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
                Intent intent1 = new Intent(this, StaffHome.class);
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
     * The activity when the user clicks on the Back button
     * @param view The view
     */
    public void onClickBack(View view) {

        Intent intent = new Intent(this, StaffViewSelectedBooking.class);
        intent.putExtra("booking", booking);
        startActivity(intent);
    }

    /**
     * The activity when the user clicks on the Confirm button
     * @param view The view
     */
    public void onClickConfirm(View view) {

        updateBooking("Confirmed");
    }

    /**
     * The activity when the user clicks on the Deny button
     * @param view The view
     */
    public void onClickDeny(View view) {

        updateBooking("Denied");
    }

    /**
     * Updates the status of the booking based on which button was clicked
     * @param status The String to update the booking status with
     */
    public void updateBooking(String status){
        File bookingFile = new File(getFilesDir(), "bookings.txt");
        BookingList bookingList = new BookingList(bookingFile);

        boolean result = bookingList.updateBooking(booking, status);
        bookingList.saveBookings(bookingFile);
        booking.setStatus(status);

        if (result) {
            Toast.makeText(this, R.string.UpdateSuccess, Toast.LENGTH_SHORT).show();
        }

        Intent intent = new Intent(this, StaffViewSelectedBooking.class);
        intent.putExtra("booking", booking);
        startActivity(intent);
    }
}