package com.example.final_exam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    Fragment booking = new Booking();
    Fragment restaurant_query = new Restaurant_query();
    Fragment aboutUs = new About_us();
    NavigationBarView bottom_nav_bar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottom_nav_bar = findViewById(R.id.bottom_nav_bar);
        bottom_nav_bar.setOnItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // Remove the aboutUs fragment in everytime when user click the nav bar
        if (aboutUs.isHidden()) {
            transaction.remove(aboutUs);
        }
        switch (item.getItemId()) {
            case R.id.nav_bar_ticket:
                if (booking.isAdded()) {
                    transaction.show(booking);
                } else {
                    transaction.add(R.id.nav_host_fragment, booking, "booking");
                }
                transaction.hide(restaurant_query);
                transaction.hide(aboutUs);
                transaction.commit();
                return true;
            case R.id.nav_bar_restaurant:
                if (restaurant_query.isAdded()) {
                    transaction.show(restaurant_query);
                } else {
                    transaction.add(R.id.nav_host_fragment, restaurant_query, "meal");
                }
                transaction.hide(booking);
                transaction.hide(aboutUs);
                transaction.commit();
                return true;
            case R.id.nav_bar_aboutUS:
                if (aboutUs.isAdded()) {
                    transaction.show(aboutUs);
                } else {
                    transaction.add(R.id.nav_host_fragment, aboutUs, "aboutUs");
                }
                transaction.hide(booking);
                transaction.hide(restaurant_query);
                transaction.commit();
                return true;
        }
        return false;
    }
}