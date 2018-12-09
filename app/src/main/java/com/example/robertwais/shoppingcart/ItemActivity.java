package com.example.robertwais.shoppingcart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Model.Item;


public class ItemActivity extends AppCompatActivity {

    private Bundle itemPassed;
    private Button addToCart;
    private EditText quantity;

    private FirebaseDatabase db;
    private DatabaseReference database, cartRef;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);


        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        database = db.getReference();

        if(mAuth.getCurrentUser()==null){
            cartRef = database.child("Guest").child("Cart");
        }else{
            cartRef = database.child(mAuth.getCurrentUser().getUid()).child("Cart");
        }



        addToCart = findViewById(R.id.addBtnCart);



        itemPassed = getIntent().getExtras();

        TextView prodName = findViewById(R.id.ItemActivityName);
        prodName.setText(itemPassed.getString("Name"));

        TextView prodPrice = findViewById(R.id.ItemActivityPrice);
        prodPrice.setText(String.valueOf(itemPassed.getDouble("Price")));

        TextView prodDescription = findViewById(R.id.ItemActivityDescription);
        prodDescription.setText(itemPassed.getString("Description"));

        quantity = (EditText) findViewById(R.id.itemQuantityLbl);
        quantity.setText(itemPassed.getString("#"));

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

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tempQuantity = quantity.getText().toString();
                int quantityInt = 0;

                try{
                    quantityInt = Integer.parseInt(tempQuantity);
                }catch(Exception e){
                    return;
                }


                Log.d("Integer: ", "qualityInt");

                Item addItem = new Item(itemPassed.getString("Name"), itemPassed.getString("Description"), itemPassed.getDouble("Price"),quantityInt);
                addItem.setKey(itemPassed.getString("key"));

                cartRef.child(addItem.getKey()).setValue(addItem);


                //Grab quantity
                //Grab itemId
                //Enter in cart
                //Switch to Shopping Cart View
                Intent i = new Intent(ItemActivity.this, ShoppingCartActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
