package com.example.robertwais.shoppingcart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.ImageView;

public class ItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        TextView prodName = findViewById(R.id.name);
        prodName.setText(R.string.itemName);

        TextView prodPrice = findViewById(R.id.price);
        prodPrice.setText(R.string.itemPrice);

        TextView prodDescription = findViewById(R.id.description);
        prodDescription.setText(R.string.itemDescription);

        ImageView prodImage = findViewById(R.id.productImage);
        prodImage.setImageResource(R.drawable.computer);
    }
}
