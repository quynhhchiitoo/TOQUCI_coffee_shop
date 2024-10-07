package com.example.coffee;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffee.adapter.OrderAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MyOrders_History extends AppCompatActivity {
    private Toolbar toolbar_myorders;
    RecyclerView recyclerView;
    OrderAdapter adapter;
    ArrayList<Order> items;
    TextView ongoing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myorders_history);
        initView();

        ongoing = findViewById(R.id.ongoing);
        ongoing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyOrders_History.this, MyOrders_OnGoing.class);
                startActivity(intent);
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_ordershis);
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.bottom_store) {
                    startActivity(new Intent(MyOrders_History.this, HomePage.class));
                    return true;
                } else if (item.getItemId() == R.id.bottom_giftbox) {
                    startActivity(new Intent(MyOrders_History.this, Reward.class));
                    return true;
                } else if (item.getItemId() == R.id.bottom_bill) {
                    return true;
                }
                return false;
            }
        });

        toolbar_myorders = findViewById(R.id.toolbar_myordershis);
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

    private void initView(){
        recyclerView = findViewById(R.id.recycler_history);
        Database db = Database.getInstance(this);
        items = db.getAllOrdersFromHistory();
        setUpRecyclerView();
    }
    private void setUpRecyclerView() {
        // Set up the RecyclerView with the MyCartAdapter
        adapter = new OrderAdapter(this, items);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}