package com.example.coffee;
import android.media.Image;
import android.os.Bundle;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.MenuItem;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;

import androidx.appcompat.widget.Toolbar;
import android.widget.ViewFlipper;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Reward extends AppCompatActivity {
    Toolbar toolbar_reward;
    Button redeem_drink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rewards);
        Anhxa();
        ActionToolbar();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_reward);
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.bottom_store) {
                    startActivity(new Intent(Reward.this, HomePage.class));
                    return true;
                } else if (item.getItemId() == R.id.bottom_giftbox) {
                    return true;
                } else if (item.getItemId() == R.id.bottom_bill) {
//                    startActivity(new Intent(HomePage.this, BillActivity.class));
                    return true;
                }
                return false;
            }
        });
        redeem_drink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Reward.this, Redeem.class);
                startActivity(intent);
            }
        });
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbar_reward);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar_reward.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void Anhxa() {
        toolbar_reward = findViewById(R.id.toolbar_reward);
        redeem_drink = findViewById(R.id.redeem_drink);
    }
}