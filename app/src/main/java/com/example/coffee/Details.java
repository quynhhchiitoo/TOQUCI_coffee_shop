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

import com.nex3z.notificationbadge.NotificationBadge;

import java.util.ArrayList;
import java.util.HashMap;

public class Details extends AppCompatActivity {
    Toolbar toolbardetails;
    ImageView imgdetails, imageCart;
    TextView textname;
    Spinner spinner;
    Button addtocart, buttonYesspicy, buttonNospicy, buttoncheese1, buttoncheese2, buttonYessauce, buttonNosauce;
    TextView total;
    private boolean isSpicySelected = false;
    private boolean isCheeseOption1Selected = true;
    private boolean isSauceSelected = false;
    NotificationBadge badge;
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
        CatchEventButtons();
        originalTotal = Integer.parseInt(total.getText().toString());
        initControl();
        imageCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Details.this, MyCartActivity.class);
                startActivity(intent);
            }
        });
    }
    private void initControl(){
        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addedtoCart();
            }
        });
    }

    private void addedtoCart(){
        String itemName = textname.getText().toString();
        int itemPrice = updateTotal();
        int itemQuantity = Integer.parseInt(spinner.getSelectedItem().toString());
        boolean isSpicy = isSpicySelected;
        boolean isSauce = isSauceSelected;
        int cheeseQuantity = isCheeseOption1Selected ? 1 : 0;
        int image = getIntent().getIntExtra("menuImage", R.drawable.default_image);

        // Create a new MyCart object with the selected item details
        MyCart newItem = new MyCart(itemName, itemPrice, itemQuantity, isSpicy, isSauce, cheeseQuantity, image);

        Database database = Database.getInstance(this);
        database.insertItem(newItem);

        ArrayList<MyCart> items = database.getAllItemsFromCart();
        int totalQuantity = 0;
        for(int i = 0; i < items.size(); i++)
            totalQuantity += items.get(i).getQuantity();
        badge.setText(String.valueOf(totalQuantity));

        reset();

        // Show a toast message to indicate that the item has been added to the cart
        Toast.makeText(getApplicationContext(), "Item added to cart", Toast.LENGTH_SHORT).show();
    }
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
                int selectedValue = (int) parent.getItemAtPosition(position);
                int newTotal = originalTotal * selectedValue;
                updateTotal();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
    }
    private int updateTotal() {
        int quantity = Integer.parseInt(spinner.getSelectedItem().toString());
        int basePrice = getIntent().getIntExtra("menuPrice", 0);

        // Calculate the new total based on selected options
        int spicyPrice = isSpicySelected ? 5 : 0;
        int cheesePrice = isCheeseOption1Selected ? 0 : 10;
        int saucePrice = isSauceSelected ? 2 : 0;

        int newTotal = (basePrice + spicyPrice + cheesePrice + saucePrice) * quantity;
        total.setText(String.valueOf(newTotal));
        return newTotal / quantity;
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
        toolbardetails = findViewById(R.id.toolbar_details);
        imgdetails = findViewById(R.id.imageviewsdetails);
        textname = findViewById(R.id.nameviewsdetails);
        spinner = findViewById(R.id.spinner);
        addtocart = findViewById(R.id.addtocart);
        total = findViewById(R.id.total);
        buttonYessauce = findViewById(R.id.buttonYessauce);
        buttonNosauce = findViewById(R.id.buttonNosauce);
        buttonYesspicy = findViewById(R.id.buttonYesspicy);
        buttonNospicy = findViewById(R.id.buttonNospicy);
        buttoncheese1 = findViewById(R.id.buttoncheese1);
        buttoncheese2 = findViewById(R.id.buttoncheese2);
        imageCart = findViewById(R.id.imageCart);
        initBadge();
    }

    private void initBadge()
    {
        badge = findViewById(R.id.noti_details);
        Database database = Database.getInstance(this);
        ArrayList<MyCart> items = database.getAllItemsFromCart();
        int totalQuantity = 0;
        for(int i = 0; i < items.size(); i++)
            totalQuantity += items.get(i).getQuantity();
        badge.setText(String.valueOf(totalQuantity));
    }

    private void reset()
    {
        total.setText(String.valueOf(getIntent().getIntExtra("menuPrice", 0)));
        isSpicySelected = false;
        isCheeseOption1Selected = true;
        isSauceSelected = false;
        spinner.setSelection(0);
    }
}