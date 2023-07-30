package com.example.coffee;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MyOrders_OnGoing extends AppCompatActivity {
    private Toolbar toolbar_myorders;
    TextView history;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myorders_ongoing);

        history = findViewById(R.id.history);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyOrders_OnGoing.this, MyOrders_History.class);
                startActivity(intent);
            }
        });
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_orders);
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.bottom_store) {
                    startActivity(new Intent(MyOrders_OnGoing.this, HomePage.class));
                    return true;
                } else if (item.getItemId() == R.id.bottom_giftbox) {
                    startActivity(new Intent(MyOrders_OnGoing.this, Reward.class));
                    return true;
                } else if (item.getItemId() == R.id.bottom_bill) {
                    return true;
                }
                return false;
            }
        });

        toolbar_myorders = findViewById(R.id.toolbar_myorders);
        ActionToolbar();
    }
    private void ActionToolbar() {
        setSupportActionBar(toolbar_myorders);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar_myorders.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}


