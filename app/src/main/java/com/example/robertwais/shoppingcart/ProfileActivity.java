package com.example.robertwais.shoppingcart;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Model.Item;
import Model.Profile;

public class ProfileActivity extends AppCompatActivity {


    //**This is needed for database
    private FirebaseDatabase db;
    private DatabaseReference database, profileRef;
    private FirebaseAuth mAuth;

    public TextView shippingTextView, creditCard;
    public Button update, change;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        //UI Setup
        shippingTextView = (TextView) findViewById(R.id.profileAddressInfo);
        creditCard = (TextView) findViewById(R.id.profileCreditCard);
        change = findViewById(R.id.changeCreditBtn);
        update = findViewById(R.id.updateAddressBtn);

        //**This code grabs sets up everything needed for database
        //Follow this to set up the references
        //in the "OnDataChange" it shows you how to set values!!!
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        database = db.getReference();
        if(mAuth.getCurrentUser()!=null){
            profileRef = database.child(mAuth.getCurrentUser().getUid()).child("ProfileHistory");
        }else{
            profileRef = database.child("Guest").child("ProfileHistory");
        }

        //**This retrieves from database do line below this comment

        profileRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //**Once we get profile you have access to methods
                Toast.makeText(ProfileActivity.this, dataSnapshot.getKey(), Toast.LENGTH_LONG).show();

                Profile profile = dataSnapshot.getValue(Profile.class);
                shippingTextView.setText(profile.getShippingAddress());
                creditCard.setText(profile.getBillingAddress());

                //**To set values build a Profile, Profile newProfile = new Profile(......)
                //**To change in database do -> profileRef.setValue(newProfile)
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

}
