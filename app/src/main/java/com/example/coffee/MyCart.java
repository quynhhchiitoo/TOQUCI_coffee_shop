package com.example.coffee;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

//public class MyCart extends AppCompatActivity {
//    TextView mycart, tongtien;
//    Toolbar toolbarmycart;
//    RecyclerView recyclerView;
//    Button btnbuy;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.mycart);
//        initView();
//    }
//
//    private void initView() {
////        mycart = findViewById(R.id.mycart);
//        tongtien = findViewById(R.id.total);
//        toolbarmycart = findViewById(R.id.toolbar_mycart);
//        recyclerView = findViewById(R.id.recycler_mycart);
//        btnbuy = findViewById(R.id.btn_checkout);
//    }
//}
public class MyCart {
    private String name;
    private int price, quantity, cheeseQuantity, image;
    private boolean isSpicy, isSauce;

    public MyCart(String name, int price, int quantity, boolean isSpicy, boolean isSauce, int cheeseQuantity, int image) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.isSpicy = isSpicy;
        this.cheeseQuantity = cheeseQuantity;
        this.isSauce = isSauce;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isSpicy() {
        return isSpicy;
    }

    public int getCheeseQuantity() {
        return cheeseQuantity;
    }

    public boolean isSauce() {
        return isSauce;
    }

    public int getImage() {
        return image;
    }
}