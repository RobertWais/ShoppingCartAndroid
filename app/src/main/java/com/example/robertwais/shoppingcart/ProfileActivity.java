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
import android.widget.EditText;
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
    private CreditCardVerification ccVerif = new CreditCardVerification();

    public EditText shippingTextView, creditCard, billingInfo, ccv, shippingState, shippingCode, billingState, billingCode;
    public TextView verification;
    public Button change;
    public Profile newProfile;

    public String ship = "", shipState = "", shipCode = "", bill = "", billState = "", billCode = "", credit = "", cc = "", ccType = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //UI Setup
        shippingTextView = (EditText) findViewById(R.id.profileAddressInfo);
        shippingState = (EditText) findViewById(R.id.shippingState);
        shippingCode = (EditText) findViewById(R.id.shippingCode);
        creditCard = (EditText) findViewById(R.id.profileCreditCard);
        ccv = (EditText) findViewById(R.id.ccv);
        billingInfo = (EditText) findViewById(R.id.profileBilling);
        billingState = (EditText) findViewById(R.id.billingState);
        billingCode = (EditText) findViewById(R.id.billingCode);
        change = findViewById(R.id.updateInfo);
        verification = (TextView) findViewById(R.id.verify);

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

                Profile profile = dataSnapshot.getValue(Profile.class);
                shippingTextView.setText(profile.getShippingAddress());
                shippingState.setText(profile.getShippingState());
                shippingCode.setText(profile.getShippingCode());
                billingInfo.setText(profile.getBillingAddress());
                billingState.setText(profile.getBillingState());
                billingCode.setText(profile.getBillingCode());
                creditCard.setText(profile.getCreditCard());
                ccv.setText(profile.getCcv());

                //Added by Brian
                credit = creditCard.getText().toString();
                verification.setText(ccVerif.verifyCardNumber(credit));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

////        Uncommment this if it crashes upon loading the profile page, then comment again and run
//        newProfile = new Profile("a", "a", "a", "a", "a", "a", "a", "a");
//        profileRef.setValue(newProfile);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ship = shippingTextView.getText().toString();
                shipState = shippingState.getText().toString();
                shipCode = shippingCode.getText().toString();
                bill = billingInfo.getText().toString();
                billState = billingState.getText().toString();
                billCode = billingCode.getText().toString();
                credit = creditCard.getText().toString();
                cc = ccv.getText().toString();
                newProfile = new Profile(ship, bill, credit, shipState, shipCode, billState, billCode, cc);

                //Added by Brian
                verification.setText(ccVerif.verifyCardNumber(credit));
                ccType = verification.getText().toString();

                if (!ccType.equals("Unknown Card Type")) {
                    profileRef.setValue(newProfile);
                    Toast.makeText(ProfileActivity.this, "Updated Profile Information", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ProfileActivity.this, "Credit Card Data Invalid\nProfile Information NOT Updated", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
