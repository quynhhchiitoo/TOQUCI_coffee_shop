package com.example.coffee;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.HashMap;

public class Details extends AppCompatActivity {
    Toolbar toolbardetails;
    ImageView imgdetails;
    TextView textname;
    Spinner spinner;
    Button addtocart, buttonYesspicy, buttonNospicy, buttoncheese1, buttoncheese2, buttonYessauce, buttonNosauce;
    TextView total;
    private boolean isSpicySelected = false;
    private boolean isCheeseOption1Selected = false;
    private boolean isSauceSelected = false;
    private HashMap<String, Object> cartMap;
    public class ProductDetails {
        private String name;
        private int totalPrice;
        private boolean isSpicy;
        private int cheeseQuantity;
    }

    private int originalTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);
        Anhxa();
        ActionToolbar();
        GetInformation();
        CatchEventSpinner();
        originalTotal = Integer.parseInt(total.getText().toString());
//        initControl();
    }
//    private void initControl(){
//        addtocart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                addedtoCart();
//            }
//        });
//    }

//    private void addedtoCart(){
//        String itemName = textname.getText().toString();
//        int itemPrice = Integer.parseInt(total.getText().toString());
//        int itemQuantity = Integer.parseInt(spinner.getSelectedItem().toString());
//        boolean isSpicy = isSpicySelected;
//        boolean isSauce = isSauceSelected;
//        int cheeseQuantity = isCheeseOption1Selected ? 1 : 0;
//
//        // Create a new MyCart object with the selected item details
//        MyCart newItem = new MyCart(itemName, itemPrice, itemQuantity, isSpicy, cheeseQuantity, isSauce);
//
//        // Add the new item to the cart list in the Utils class
//        Utils.manggiohang.add(newItem);
//
//        // Show a toast message to indicate that the item has been added to the cart
//        Toast.makeText(this, "Item added to cart", Toast.LENGTH_SHORT).show();
//    }
    private void CatchEventButtons(){
        // Button click listeners for options
        buttonYesspicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSpicySelected = true;
                updateTotal();
            }
        });

        buttonNospicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSpicySelected = false;
                updateTotal();
            }
        });

        buttoncheese1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCheeseOption1Selected = true;
                updateTotal();
            }
        });

        buttoncheese2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCheeseOption1Selected = false;
                updateTotal();
            }
        });

        buttonYessauce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSauceSelected = true;
                updateTotal();
            }
        });

        buttonNosauce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSauceSelected = false;
                updateTotal();
            }
        });
    }
    private void CatchEventSpinner() {
        Integer[] quantity = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
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
    private void updateTotal() {
        int quantity = Integer.parseInt(spinner.getSelectedItem().toString());
        int basePrice = Integer.parseInt(total.getText().toString());

        // Calculate the new total based on selected options
        int spicyPrice = isSpicySelected ? 5 : 0;
        int cheesePrice = isCheeseOption1Selected ? 0 : 10;
        int saucePrice = isSauceSelected ? 2 : 0;

        int newTotal = (basePrice + spicyPrice + cheesePrice + saucePrice) * quantity;
        total.setText(String.valueOf(newTotal));
    }


    private void GetInformation() {
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

    private void ActionToolbar() {
        setSupportActionBar(toolbardetails);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbardetails.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void Anhxa() {
        toolbardetails = (Toolbar) findViewById(R.id.toolbar_details);
        imgdetails = (ImageView) findViewById(R.id.imageviewsdetails);
        textname = (TextView) findViewById(R.id.nameviewsdetails);
        spinner = (Spinner) findViewById(R.id.spinner);
        addtocart = (Button) findViewById(R.id.addtocart);
        total = (TextView) findViewById(R.id.total);
    }
}

