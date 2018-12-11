package com.example.robertwais.shoppingcart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Model.Item;

public class AdminAddItem extends AppCompatActivity {

    private FirebaseDatabase db;
    private DatabaseReference database, itemsRef;

    private EditText name,description,price;
    private Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_item);

        db = FirebaseDatabase.getInstance();
        database = db.getReference();
        itemsRef = database.child("Items");



        confirm = findViewById(R.id.adminConfirmAdd);

        name = (EditText) findViewById(R.id.adminItemNameField);
        description = (EditText) findViewById(R.id.adminItemDescriptionField);
        price = (EditText) findViewById(R.id.adminItemPriceField);


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Database connections
                String newKey = itemsRef.push().getKey();
                Item newItem = new Item(name.getText().toString(), description.getText().toString(), Double.valueOf(price.getText().toString()));
                itemsRef.child(newKey).setValue(newItem);
                finish();
            }
        });



    }
}
