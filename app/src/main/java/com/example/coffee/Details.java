package com.example.coffee;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Details extends AppCompatActivity{
    Toolbar toolbardetails;
    ImageView imgdetails;
    TextView textname;
    Spinner spinner;
    Button addtocart;
    TextView total;

    private int originalTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);
        Anhxa();
        ActionToolbar();
        GetInformation();
        CatchEventSpinner();
        originalTotal = Integer.parseInt(total.getText().toString());
    }

    private void CatchEventSpinner(){
        Integer[] quantity = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayadapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item, quantity);
        spinner.setAdapter(arrayadapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int selectedValue = (int) parent.getItemAtPosition(position); // Get the selected value from the spinner

                int newTotal = originalTotal * selectedValue; // Calculate the new total by multiplying the selected value with the original total

                total.setText(String.valueOf(newTotal)); // Update the total TextView with the new total
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
    });
    }
    private void GetInformation(){
        String Name = "";
        Integer Price = 0;
        String Pics = "";
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("menuName") && intent.hasExtra("menuPrice") && intent.hasExtra("menuImage")) {
            String name = intent.getStringExtra("menuName");
            int price = intent.getIntExtra("menuPrice", 0);
            int imageResource = intent.getIntExtra("menuImage", R.drawable.default_image); // Set a default image if the value is not provided in the intent

            // Set the retrieved data to the respective views
            textname.setText(name);
            total.setText(String.valueOf(price));
            imgdetails.setImageResource(imageResource);
        }
    }

    private void ActionToolbar(){
        setSupportActionBar(toolbardetails);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbardetails.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void Anhxa(){
        toolbardetails = (Toolbar) findViewById(R.id.toolbar_details);
        imgdetails = (ImageView) findViewById(R.id.imageviewsdetails);
        textname = (TextView) findViewById(R.id.nameviewsdetails);
        spinner = (Spinner) findViewById(R.id.spinner);
        addtocart = (Button) findViewById(R.id.addtocart);
        total = (TextView) findViewById(R.id.total);
    }

}

