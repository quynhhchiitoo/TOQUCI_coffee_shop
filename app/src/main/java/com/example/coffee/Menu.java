package com.example.coffee;

import android.content.Intent;
import java.util.ArrayList;
import android.widget.ListView;
import android.os.Bundle;
import android.os.Handler;
import java.io.Serializable;

import androidx.appcompat.app.AppCompatActivity;

public class Menu extends AppCompatActivity {
    public String Name;
    public Integer Pics;

    public Integer Price;

    public Menu(String name, Integer pics){
        Name = name;
        Pics = pics;
    }
    public Menu(String name, Integer price, Integer pics){
        Name = name;
        Price = price;
        Pics = pics;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
    }
    public String getName() {
        return Name;
    }

    public int getImageResource() {
        return Pics;
    }
    public Integer getPrice() {
        return Price;
    }
}

