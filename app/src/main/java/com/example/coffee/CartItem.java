package com.example.coffee;
public class CartItem {
    private String name;
    private String description;
    private boolean isSpicy;
    private int cheeseQuantity;
    private boolean isSauceSelected;
    private int quantity;
    private int totalPrice;

    public CartItem(String name, int price, int quantity) {
        this.name = name;
        this.totalPrice = price;
        this.quantity = quantity;
    }
    public CartItem(String name, String description, boolean isSpicy, int cheeseQuantity, boolean isSauceSelected, int quantity, int totalPrice) {
        this.name = name;
        this.description = description;
        this.isSpicy = isSpicy;
        this.cheeseQuantity = cheeseQuantity;
        this.isSauceSelected = isSauceSelected;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isSpicy() {
        return isSpicy;
    }

    public int getCheeseQuantity() {
        return cheeseQuantity;
    }

    public boolean isSauceSelected() {
        return isSauceSelected;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }
}
