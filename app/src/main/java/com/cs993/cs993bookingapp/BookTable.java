package com.cs993.cs993bookingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Calendar;

public class BookTable extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_table);
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
     * The activity when the user clicks on the Date button
     * @param view The view
     */
    public void onClickDatePicker(View view) {

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    /**
     * Formats a String based on the date the user selected and displays it
     * @param datePicker The datePicker object
     * @param year The year integer
     * @param month The month integer starting from 0
     * @param dayOfMonth The day integer
     */
    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        String date = dayOfMonth + "/" + (month + 1) + "/" + year;
        TextView dateChoice = findViewById(R.id.DateChoice);
        dateChoice.setText(date);
    }

    /**
     * The activity when the user clicks on the Submit button
     * @param view The view
     */
    public void onClickSubmit(View view) {

        Spinner guestNumChoice = findViewById(R.id.GuestNumberChoice);
        String guestNum = String.valueOf(guestNumChoice.getSelectedItem());

        TextView datePickerChoice = findViewById(R.id.DateChoice);
        String datePicked = String.valueOf(datePickerChoice.getText());

        Spinner timePickerChoice = findViewById(R.id.TimeReservationChoice);
        String timePicked = String.valueOf(timePickerChoice.getSelectedItem());

        TextView resultOutput = findViewById(R.id.SubmitResult);

        if (guestNum.equals("") || datePicked.equals("") || timePicked.equals("")) {
            resultOutput.setText(getResources().getString(R.string.MissingDetails));
        } else {
            File userFile = new File(getFilesDir(), "current_user.txt");
            Customer currentUser = new Customer(userFile);
            Booking newBooking = new Booking(currentUser.getEmail(), currentUser.getUName(), datePicked, timePicked, guestNum, "Not Confirmed");

            File bookingFile = new File(getFilesDir(), "bookings.txt");
            BookingList bookingList = new BookingList(bookingFile);
            bookingList.addBooking(newBooking);
            bookingList.saveBookings(bookingFile);

            Intent intent = new Intent(this, BookingCreated.class);
            startActivity(intent);
        }
    }
}