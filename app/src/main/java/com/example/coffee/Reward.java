package com.example.coffee;
import android.app.Activity;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.content.Intent;

import androidx.activity.result.ActivityResultCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.appcompat.widget.Toolbar;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Reward extends AppCompatActivity {
    Toolbar toolbar_reward;
    Button redeem_drink;
    private ActivityResultLauncher<Intent> redeemLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            int redeemedPoints = data.getIntExtra("redeemed_points", 0);
                            updatePoints(redeemedPoints);
                        }
                    }
                }
            }
    );
    private void updatePoints(int redeemedPoints) {
        TextView myPointsTextView = findViewById(R.id.mypoints);
        int currentPoints = Integer.parseInt(myPointsTextView.getText().toString());
        int updatedPoints = currentPoints - redeemedPoints;

        if (updatedPoints < 0) {
            Toast.makeText(this, "Cannot redeem. Not enough points.", Toast.LENGTH_SHORT).show();
        } else if (updatedPoints < 50) {
            Toast.makeText(this, "Cannot redeem. Minimum points required for redemption is 50.", Toast.LENGTH_SHORT).show();
        } else {
            myPointsTextView.setText(String.valueOf(updatedPoints));
            savePoints(updatedPoints);
        }
    }


    private void savePoints(int points) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("my_points", points);
        editor.apply();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rewards);
        Anhxa();
        ActionToolbar();
        int myPoints = readPointsFromSharedPreferences();
        TextView myPointsTextView = findViewById(R.id.mypoints);
        myPointsTextView.setText(String.valueOf(myPoints));
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
                    startActivity(new Intent(Reward.this, MyCart.class));
                    return true;
                }
                return false;
            }
        });
        redeem_drink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Reward.this, Redeem.class);
                redeemLauncher.launch(intent);
            }
        });
    }
    private int readPointsFromSharedPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        return sharedPreferences.getInt("my_points", 0);
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