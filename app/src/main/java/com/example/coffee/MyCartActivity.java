package com.example.coffee;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffee.adapter.MyCartAdapter;

import java.util.ArrayList;

public class MyCartActivity extends AppCompatActivity{
    RecyclerView recyclerView;
    Toolbar toolbarmycart;

    ArrayList<MyCart> items;
    Button btn_checkout;
    TextView total;
    MyCartAdapter adapter;
    AlertDialog.Builder builder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mycart);
        initView();
        ActionToolbar();
        updateTotal();
        setUpRecyclerView();
        btn_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database database = Database.getInstance(MyCartActivity.this);
                User user = database.getFirstUser();
                if(user == null) {
                    builder.setTitle("Stop!!!")
                            .setMessage("Before we can proceed, you need to provide us some information")
                            .setCancelable(true)
                            .setPositiveButton("Provide", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    startActivity(new Intent(MyCartActivity.this, Profile.class));
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            })
                            .show();
                }
                else {
                    for(int i = 0; i < items.size(); i++)
                    {
                        String item_name = items.get(i).getName();
                        String address = user.getAddress();
                        int item_quantity = items.get(i).getQuantity();
                        int item_price = items.get(i).getPrice() * item_quantity;
                        Order item = new Order(item_name, address, item_price, item_quantity);
                        Database db = Database.getInstance(MyCartActivity.this);
                        db.insertOrder(item);
                        db.updatePointsForUser(user.getFullname(), user.getPoint() + item_quantity * 10);
                    }

                    // clear mycart table
                    Database db = Database.getInstance(MyCartActivity.this);
                    db.clear("mycart");


                    // new Intent for order tracking
                    startActivity(new Intent(MyCartActivity.this, OrderSuccess.class));
                }
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int pos = viewHolder.getAdapterPosition();
            MyCart itemRemoved = items.remove(pos);
            adapter.notifyItemRemoved(pos);
            Database db = Database.getInstance(MyCartActivity.this);
            db.deleteItem(itemRemoved);
            updateTotal();
        }
    };

    private void initView() {
        toolbarmycart = findViewById(R.id.toolbar_mycart);
        recyclerView = findViewById(R.id.recycler_mycart);
        btn_checkout = findViewById(R.id.btn_checkout);
        total = findViewById(R.id.total);
        builder = new AlertDialog.Builder(this);
    }

    private void setUpRecyclerView() {
        // Set up the RecyclerView with the MyCartAdapter
        adapter = new MyCartAdapter(this, items);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public int updateTotal(){
        Database database = Database.getInstance(this);
        items = database.getAllItemsFromCart();

        int totalQuantity = 0;
        for(int i = 0; i < items.size(); i++)
            totalQuantity += items.get(i).getQuantity() * items.get(i).getPrice();
        total.setText("$" + String.valueOf(totalQuantity));
        return totalQuantity;
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbarmycart);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarmycart.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}