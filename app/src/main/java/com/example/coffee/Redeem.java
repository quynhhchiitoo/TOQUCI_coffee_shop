package com.example.coffee;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.Button;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

public class Redeem extends AppCompatActivity {
    Toolbar toolbar_redeem;
    Button redeem1, redeem2,  redeem3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.redeempoints);

        Anhxa();
        ActionToolbar();
        Button redeem1 = findViewById(R.id.redeem1);
        Button redeem2 = findViewById(R.id.redeem2);
        Button redeem3 = findViewById(R.id.redeem3);

        redeem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int redeemedPoints = 50;
                Intent resultIntent = new Intent();
                resultIntent.putExtra("redeemed_points", redeemedPoints);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
        redeem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int redeemedPoints = 50;
                Intent resultIntent = new Intent();
                resultIntent.putExtra("redeemed_points", redeemedPoints);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
        redeem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int redeemedPoints = 50;
                Intent resultIntent = new Intent();
                resultIntent.putExtra("redeemed_points", redeemedPoints);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbar_redeem);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar_redeem.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void Anhxa() {
        toolbar_redeem = findViewById(R.id.toolbar_redeem);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
