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
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class StaffCreateBooking extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private Table table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_create_booking);

        String tableNum = getIntent().getStringExtra("tableNum");

        TextView tv = findViewById(R.id.BookTableNum);
        String title = "Book Table " + tableNum;
        tv.setText(title);

        File file = new File(getFilesDir(), "table" + tableNum + "_bookings.txt");
        table = new Table(tableNum, file);
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
        TextView textview = findViewById(R.id.TimeReservation);
        textview.setVisibility(View.VISIBLE);
        Spinner spinner = findViewById(R.id.TimeReservationChoice);
        spinner.setVisibility(View.VISIBLE);

        ArrayList<String> reservationSlots = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.TimeReservationSlots)));

        for (Booking booking : table.getTableBookingList().getBookings()) {
            if (booking.getDate().equals(date)) {
                reservationSlots.remove(booking.getTime());
            }
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, reservationSlots);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
    }

    /**
     * The activity when the user clicks on the Back button
     * @param view The view
     */
    public void onClickBack(View view) {

        Intent intent = new Intent(this, StaffViewSelectedTable.class);
        intent.putExtra("tableNum", table.getTableNum());
        startActivity(intent);
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
            Booking newBooking = new Booking(currentUser.getEmail(), currentUser.getUName(), datePicked, timePicked, guestNum, "Table " + table.getTableNum());

            File bookingFile = new File(getFilesDir(), "bookings.txt");
            BookingList bookingList = new BookingList(bookingFile);
            bookingList.addBooking(newBooking);
            bookingList.saveBookings(bookingFile);

            table.getTableBookingList().addBooking(newBooking);
            File fileTable = new File(getFilesDir(), "table" + table.getTableNum() + "_bookings.txt");
            table.getTableBookingList().saveBookings(fileTable);
            Toast.makeText(this, R.string.BookingAssigned, Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, StaffViewSelectedTable.class);
            intent.putExtra("tableNum", table.getTableNum());
            startActivity(intent);
        }
    }
}