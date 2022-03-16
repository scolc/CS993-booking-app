package com.cs993.cs993bookingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class StaffAssignBooking extends AppCompatActivity {

    private Table table;
    private Booking assignedBooking;
    private BookingList allBookings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_assign_booking);

        String tableNum = getIntent().getStringExtra("tableNum");

        TextView tv = findViewById(R.id.StaffViewSelectedTable);
        String title = "Assign Table " + tableNum + " Booking";
        tv.setText(title);

        File fileTable = new File(getFilesDir(), "table" + tableNum + "_bookings.txt");
        table = new Table(tableNum, fileTable);

        File fileBookings = new File(getFilesDir(), "bookings.txt");
        allBookings = new BookingList(fileBookings);

        ArrayList<Booking> bookings = new ArrayList<>();

        for (Booking booking : allBookings.getBookings()) {
            if (booking.getStatus().equals("Confirmed")) {
                boolean include = true;
                for (Booking tableBooking : table.getTableBookingList().getBookings()) {
                    if (booking.getDate().equals(tableBooking.getDate()) && booking.getTime().equals(tableBooking.getTime())) {
                        include = false;
                    }
                }
                if (include) {
                    bookings.add(booking);
                }
            }
        }


        ListView listView = findViewById(R.id.Lv_UnassignedBookings);

        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_list_item_1, bookings);

        listView.setAdapter(ad);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                assignedBooking = bookings.get(i);
                Button button = findViewById(R.id.ConfirmSelectedBooking);
                button.setEnabled(true);
            }
        });
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
     * The activity when the user clicks on the Confirm button
     * @param view The view
     */
    public void onClickConfirmSelectedBooking(View view) {

        allBookings.updateBooking(assignedBooking, "Table " + table.getTableNum());
        File fileBookings = new File(getFilesDir(), "bookings.txt");
        allBookings.saveBookings(fileBookings);

        assignedBooking.setStatus("Table " + table.getTableNum());
        table.getTableBookingList().addBooking(assignedBooking);
        File fileTable = new File(getFilesDir(), "table" + table.getTableNum() + "_bookings.txt");
        table.getTableBookingList().saveBookings(fileTable);
        Toast.makeText(this, R.string.BookingAssigned, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, StaffViewSelectedTable.class);
        intent.putExtra("tableNum", table.getTableNum());
        startActivity(intent);

    }

    /**
     * The activity when the user clicks on the Back button
     * @param view The view
     */
    public void onClickBack(View view) {

        finish();
    }
}