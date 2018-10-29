package com.example.robertwais.shoppingcart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemActivity extends AppCompatActivity {

    private Bundle itemPassed;
    private Button addToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        addToCart = findViewById(R.id.addBtnCart);
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Grab quantity
                //Grab itemId
                //Enter in cart
                //Switch to Shopping Cart View
                Intent i = new Intent(ItemActivity.this, ShoppingCartActivity.class);
                startActivity(i);
            }
        });


        itemPassed = getIntent().getExtras();

        TextView prodName = findViewById(R.id.ItemActivityName);
        prodName.setText(itemPassed.getString("Name"));

        TextView prodPrice = findViewById(R.id.ItemActivityPrice);
        prodPrice.setText(itemPassed.getString("Price"));

        TextView prodDescription = findViewById(R.id.ItemActivityDescription);
        prodDescription.setText(itemPassed.getString("Description"));

        ImageView prodImage = findViewById(R.id.ItemActivityImage);
        int position = itemPassed.getInt("Position");
        switch (position){
            case 0:
                prodImage.setImageResource(R.drawable.android0);
                break;
            case 1:
                prodImage.setImageResource(R.drawable.android1);
                break;
            case 2:
                prodImage.setImageResource(R.drawable.android2);
                break;
            case 3:
                prodImage.setImageResource(R.drawable.android3);
                break;
            case 4:
                prodImage.setImageResource(R.drawable.android4);
                break;
            case 5:
                prodImage.setImageResource(R.drawable.android5);
                break;

        }
    }
}
