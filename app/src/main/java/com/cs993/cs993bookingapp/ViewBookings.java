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
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class ViewBookings extends AppCompatActivity {

    private ListView listView;
    private ArrayList<Booking> yourBookings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bookings);

        listView = findViewById(R.id.Lv_YourBookings);

        File fileBookings = new File(getFilesDir(), "bookings.txt");
        BookingList allBookings = new BookingList(fileBookings);
        File fileUser = new File(getFilesDir(), "current_user.txt");
        User currentUser = new User(fileUser);
        yourBookings = allBookings.getBookingsForUser(currentUser);

        ArrayList<String> list = new ArrayList<>();

        for (Booking booking : yourBookings){
            list.add(booking.toString());
        }

        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);

        listView.setAdapter(ad);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(ViewBookings.this, "Clicked\n" + list.get(i), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ViewBookings.this, ViewSelectedBooking.class);
                intent.putExtra("booking", yourBookings.get(i));
                startActivity(intent);
            }
        });

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

    public void onClickHome(View view) {
        Intent intent = new Intent(this, CustomerHome.class);
        startActivity(intent);
    }
}