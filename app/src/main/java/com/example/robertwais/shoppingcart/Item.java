package com.example.robertwais.shoppingcart;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

public class Item extends AppCompatActivity {

    private String productName;
    private double price;
    private String description;

    public Item(String name, double price, String description){

        this.productName = name;
        this.price = price;
        this.description = description;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
