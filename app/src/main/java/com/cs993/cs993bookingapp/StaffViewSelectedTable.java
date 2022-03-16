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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

public class StaffViewSelectedTable extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private Table table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_view_selected_table);

        String tableNum = getIntent().getStringExtra("tableNum");

        TextView tv = findViewById(R.id.StaffViewSelectedTable);
        String title = "Table " + tableNum + " Bookings";
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
        TextView selectTime = findViewById(R.id.StaffTimeReservation);
        selectTime.setText(getResources().getString(R.string.SelectReservedTime));
        ListView listView = findViewById(R.id.Lv_StaffTableBookings);
        listView.setVisibility(View.VISIBLE);

        ArrayList<Booking> bookings = table.getTableBookingList().getBookings();
        ArrayList<Booking> reservations = new ArrayList<>();

        for (Booking booking : bookings){
            if (booking.getDate().equals(date)) {
                reservations.add(booking);
            }
        }

        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_list_item_1, reservations);

        listView.setAdapter(ad);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(StaffViewSelectedTable.this, StaffViewSelectedTableBooking.class);
                intent.putExtra("booking", reservations.get(i));
                startActivity(intent);
            }
        });

    }

    /**
     * The activity when the user clicks on the Back button
     * @param view The view
     */
    public void onClickBack(View view) {

        Intent intent = new Intent(this, StaffViewTables.class);
        startActivity(intent);
    }

    /**
     * The activity when the user clicks on the Assign Booking button
     * @param view The view
     */
    public void onClickAssign(View view) {

        Intent intent = new Intent(this, StaffAssignBooking.class);
        intent.putExtra("tableNum", table.getTableNum());
        startActivity(intent);
    }

    /**
     * The activity when the user clicks on the Create Booking button
     * @param view The view
     */
    public void onClickCreate(View view) {

        Intent intent = new Intent(this, StaffCreateBooking.class);
        intent.putExtra("tableNum", table.getTableNum());
        startActivity(intent);
    }
}