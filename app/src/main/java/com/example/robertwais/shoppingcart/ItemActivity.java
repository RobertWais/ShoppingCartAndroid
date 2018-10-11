package com.example.robertwais.shoppingcart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.ImageView;

public class ItemActivity extends AppCompatActivity {

    private Bundle itemPassed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        itemPassed = getIntent().getExtras();

        TextView prodName = findViewById(R.id.name);
        prodName.setText(itemPassed.getString("Name:\n" + prodName));

        TextView prodPrice = findViewById(R.id.price);
        prodPrice.setText(itemPassed.getString("Price: $" + prodPrice));

        TextView prodDescription = findViewById(R.id.ItemActivityDescription);
        prodDescription.setText(itemPassed.getString("Description:\n" + prodDescription));

        ImageView prodImage = findViewById(R.id.productImage);
        //prodImage.setImageResource(R.drawable.computer);
    }
}
