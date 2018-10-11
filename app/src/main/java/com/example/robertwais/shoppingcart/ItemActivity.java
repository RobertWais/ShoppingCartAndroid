package com.example.robertwais.shoppingcart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemActivity extends AppCompatActivity {

    private Bundle itemPassed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

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
