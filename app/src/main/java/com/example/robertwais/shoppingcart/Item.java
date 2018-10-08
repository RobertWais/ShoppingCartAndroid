package com.example.robertwais.shoppingcart;

import android.support.v7.app.AppCompatActivity;

public class Item extends AppCompatActivity {

    private String name;
    private double price;
    private String description;

    public Item(String name, double price, String description){

        this.name = name;
        this.price = price;
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
