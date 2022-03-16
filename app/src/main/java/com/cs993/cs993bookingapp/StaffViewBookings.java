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
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

public class StaffViewBookings extends AppCompatActivity {

    private ListView listView;
    private ArrayList<Booking> bookings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_view_bookings);

        listView = findViewById(R.id.Lv_StaffBookings);

        File fileBookings = new File(getFilesDir(), "bookings.txt");
        BookingList allBookings = new BookingList(fileBookings);
        bookings = allBookings.getBookings();

        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_list_item_1, bookings);

        listView.setAdapter(ad);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(StaffViewBookings.this, StaffViewSelectedBooking.class);
                intent.putExtra("booking", bookings.get(i));
                startActivity(intent);
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
     * The activity when the user clicks on the home button
     * @param view The view
     */
    public void onClickHome(View view) {
        Intent intent = new Intent(this, StaffHome.class);
        startActivity(intent);
    }
}