package com.example.robertwais.shoppingcart;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

import java.util.ArrayList;
import java.util.List;

import Adapter.ItemAdapter;
import Adapter.ItemCartAdapter;
import Model.Item;

public class ShoppingCartActivity extends AppCompatActivity {

    private Button backToBrowse;
    private Button addToCart;
    private TextView totalItems, totalPrice, cartPromoSavings, subtotalPrice, cartTaxes, cartShipping;

    private FirebaseDatabase db;
    private DatabaseReference database, cartRef;
    private FirebaseAuth mAuth;
    private TaxesHandler theTaxesHandler = new TaxesHandler();


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Item> theList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        backToBrowse = findViewById(R.id.returnToBrowse);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        database = db.getReference();
        if(mAuth.getCurrentUser()!=null){
            Log.d("Diddddd: ","guest");
            cartRef = database.child(mAuth.getCurrentUser().getUid()).child("Cart");
        }else{
            Log.d("Ref: ","guest");
            cartRef = database.child("Guest").child("Cart");
        }




        totalItems = (TextView) findViewById(R.id.cartTotalItems);
        totalPrice = (TextView) findViewById(R.id.cartTotalPrice);
        subtotalPrice = (TextView) findViewById(R.id.cartSubtotalPrice);
        cartPromoSavings = (TextView) findViewById(R.id.cartPromoSavings);
        cartTaxes = (TextView) findViewById(R.id.cartTaxes);
        cartShipping = (TextView) findViewById(R.id.cartShipping);


        recyclerView = (RecyclerView) findViewById(R.id.shoppingCartRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        theList = new ArrayList<>();

        adapter = new ItemCartAdapter(ShoppingCartActivity.this, theList);
        recyclerView.setAdapter(adapter);
//        Item[] items = {
//                new Item("Spider Man","Marvel's Spider-Man, commonly referred to as Spider-Man, is an action-adventure game developed by Insomniac Games and published by Sony Interactive Entertainment for the PlayStation 4, " +
//                        "based on the Marvel Comics superhero Spider-Man.",59.99),
//                new Item("Fallout 4","Fallout 4 is a post-apocalyptic action role-playing video game developed by Bethesda Game Studios and published by Bethesda Softworks. It is the fifth major installment in the Fallout series and was released worldwide" +
//                        " on November 10, 2015, for Microsoft Windows, PlayStation 4 and Xbox One.",15.00),
//                new Item("Madden 19","Gameplay in Madden NFL 19 was developed with two primary objectives: 1. Player Control across the field; 2.Immersive NFL Experience from start to finish. To achieve these objectives, EA's goal" +
//                        " is to deliver the most significant animation upgrade in Madden history.",59.99),
//                new Item("Red Dead Redemption 2 Ultimate Edition","After a robbery goes badly wrong in the western town of Blackwater, Arthur Morgan and the Van der Linde gang are forced to flee. With federal agents and the best bounty hunters in the nation massing on their heels, the gang must rob, steal and fight their way across the rugged heartland of America in order to survive. As deepening internal divisions threaten to tear the gang apart, Arthur must make a choice between his own idea" +
//                        "ls and loyalty to the gang who raised him.",99.99),
//                new Item("Assassin's Creed Odyssey","Write your own epic odyssey and become a legendary Spartan hero in Assassin's Creed® Odyssey, an inspiring adventure where you must forge your destiny and define your own path in a world on the brink of tearing itself apart. Influence how history unfolds as you ex" +
//                        "perience a rich and ever-changing world shaped by your decisions.",59.99),
//                new Item("Spyro Reignited Trilogy","Spyro's back and he's all scaled up! The original roast master is back! Same sick burns, same smoldering attitude, now all scaled up in stunning HD. Spyro is bringing the heat like never before in the Spyro Reignited Trilogy game collection. Rekindle the fire with the original three games, Spyro the Dragon, Spyro 2 Ripto's Rage! and Spyro Year of the Dragon. Explore the expansive realms, re-encounter the fiery personalities and relive the adventure in fully remastered glory. Because when there's a realm that" +
//                        " needs saving, there's only one dragon to call.",39.99),
//
//        };




        cartRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                setTotals(dataSnapshot,s,true);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                setTotals(dataSnapshot,s,false);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                String key = dataSnapshot.getKey();
                int rem = -1;
                for(int i = 0;i<theList.size();i++){
                    if(theList.get(i).getKey().equals(key)){
                        rem = i;
                        Toast.makeText(ShoppingCartActivity.this, "Found it", Toast.LENGTH_SHORT).show();

                        break;
                    }
                }
                if(rem!=-1){
                    theList.remove(rem);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        backToBrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void setTotals(@NonNull DataSnapshot dataSnapshot, @Nullable String s, Boolean add){
        int totalItemCount = 0;
        Item newItem = dataSnapshot.getValue(Item.class);

        if (add) {
            theList.add(newItem);
            adapter.notifyDataSetChanged();

        }
        double dubTotalPrice = 0;
        for(int i = 0;i < theList.size();i++){
            dubTotalPrice += (theList.get(i).getPrice()*theList.get(i).getQuantity());
            totalItemCount += theList.get(i).getQuantity();
        }

        //Reset Variables
        //Move to function when anything is changed
        totalItems.setText("Total Items:\n" + String.valueOf(theList.size()));


        dubTotalPrice = Math.round(dubTotalPrice * 1000.0);
        dubTotalPrice = dubTotalPrice / 1000;

        subtotalPrice.setText("Subtotal Price:\n$" + String.valueOf(dubTotalPrice));

        double promoSavings = 0.0;
        cartPromoSavings.setText("Promo Savings:\n$" + String.format("%.2f", promoSavings));
        if (promoSavings > 0.0)
            cartPromoSavings.setVisibility(View.VISIBLE);

        double taxesValue = 0.0;
        taxesValue = theTaxesHandler.calculateTaxes(ShoppingCartActivity.this, "WI", 54601, dubTotalPrice);
        taxesValue = Math.round(taxesValue * 100.0);
        taxesValue = taxesValue / 100;
        cartTaxes.setText("Estimated Tax:\n$" + String.format("%.2f", taxesValue));

        double shippingValue = 0.0;
        cartShipping.setText("Shipping:\n$" + String.format("%.2f", shippingValue));

        dubTotalPrice -= promoSavings;
        dubTotalPrice += taxesValue;
        dubTotalPrice += shippingValue;
        dubTotalPrice = Math.round(dubTotalPrice * 1000.0);
        dubTotalPrice = dubTotalPrice / 1000;

        totalPrice.setText("Total Price:\n$" + String.valueOf(dubTotalPrice));

    }

}
