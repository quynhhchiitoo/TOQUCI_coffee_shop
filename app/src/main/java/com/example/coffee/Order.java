package com.example.coffee;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Order {
    private String day, time, item_name, address;
    private int item_price, item_quantity;

    public Order(String day, String time, String item_name, String address, int item_price, int item_quantity) {
        this.day = day;
        this.time = time;
        this.item_name = item_name;
        this.address = address;
        this.item_price = item_price;
        this.item_quantity = item_quantity;
    }

    public Order(String item_name, String address, int item_price, int item_quantity) {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        this.day = dateFormat.format(currentDate).toString();
        dateFormat = new SimpleDateFormat("hh:mm a");
        this.time = dateFormat.format(currentDate).toString();
        this.item_name = item_name;
        this.address = address;
        this.item_price = item_price;
        this.item_quantity = item_quantity;
    }

    public String getDay() {
        return day;
    }

    public String getTime() {
        return time;
    }

    public String getItem_name() {
        return item_name;
    }

    public String getAddress() {
        return address;
    }

    public int getItem_price() {
        return item_price;
    }

    public int getItem_quantity() {
        return item_quantity;
    }
}