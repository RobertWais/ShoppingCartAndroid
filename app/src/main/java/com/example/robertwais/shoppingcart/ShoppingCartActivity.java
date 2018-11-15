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

import java.util.ArrayList;
import java.util.List;

import Adapter.ItemAdapter;
import Adapter.ItemCartAdapter;
import Model.Item;
import Model.Promotion;

public class ShoppingCartActivity extends AppCompatActivity {

    private Promotion promotion;
    private Button backToBrowse, enterCode;
    private Button addToCart;
    private TextView totalItems, totalPrice, cartPromoSavings, subtotalPrice, cartTaxes, cartShipping;
    private EditText promoCode;

    private FirebaseDatabase db;
    private DatabaseReference database, cartRef, promotionReference;
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
        promoCode = (EditText) findViewById(R.id.UserPromoCodeField);
        enterCode = findViewById(R.id.UserPromoCodeEnter);
        totalItems = (TextView) findViewById(R.id.cartTotalItems);
        totalPrice = (TextView) findViewById(R.id.cartTotalPrice);
        subtotalPrice = (TextView) findViewById(R.id.cartSubtotalPrice);
        cartPromoSavings = (TextView) findViewById(R.id.cartPromoSavings);
        cartTaxes = (TextView) findViewById(R.id.cartTaxes);
        cartShipping = (TextView) findViewById(R.id.cartShipping);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        database = db.getReference();
        promotionReference = database.child("Promotions");
        if(mAuth.getCurrentUser()!=null){
            cartRef = database.child(mAuth.getCurrentUser().getUid()).child("Cart");
        }else{
            cartRef = database.child("Guest").child("Cart");
            promoCode.setVisibility(View.GONE);
            enterCode.setVisibility(View.GONE);
        }



        recyclerView = (RecyclerView) findViewById(R.id.shoppingCartRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        theList = new ArrayList<>();

        adapter = new ItemCartAdapter(ShoppingCartActivity.this, theList);
        recyclerView.setAdapter(adapter);


        //Check For Promo Code
        enterCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!promoCode.getText().toString().equals("")){
                    String tempCode = promoCode.getText().toString();


                    promotionReference.child(tempCode).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            //Check if valid promotion
                            if(dataSnapshot.getValue()!=null){
                                Promotion prom = dataSnapshot.getValue(Promotion.class);
                                String id = prom.getItemID();

                                if(theList.size()<1){
                                    Toast.makeText(ShoppingCartActivity.this, "No items in Cart", Toast.LENGTH_SHORT).show();
                                }

                                for(int i=0;i<theList.size();i++){
                                    if(theList.get(i).getKey().equals(id)){
                                        promotion = prom;
                                        Toast.makeText(ShoppingCartActivity.this, "Promotion Applied", Toast.LENGTH_SHORT).show();
                                        checkPromotions();
                                        break;
                                    }
                                    if(i==theList.size()-1){
                                        Toast.makeText(ShoppingCartActivity.this, "Promotion Not Valid For Cart", Toast.LENGTH_SHORT).show();
                                    }
//                                        Toast.makeText(ShoppingCartActivity.this, "Promotion Not Applied", Toast.LENGTH_SHORT).show()
                                }
                            }else{
                                Toast.makeText(ShoppingCartActivity.this, "Promotion Invalid", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        });


        //Check for new items added to Cart
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
                        break;
                    }
                }
                if(rem!=-1){
                    theList.remove(rem);
                }
                adapter.notifyDataSetChanged();
                checkPromotions();
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

    public void checkPromotions(){
        //
        int totalItemCount = 0;
        double dubTotalPrice = 0;
        for(int i = 0;i < theList.size();i++){
            if(promotion!=null && promotion.getItemID().equals(theList.get(i).getKey())){
                Double tempPrice = (theList.get(i).getPrice()*theList.get(i).getQuantity());
                dubTotalPrice += tempPrice - (tempPrice*(promotion.getAmount()/100));
            }else{
                dubTotalPrice += (theList.get(i).getPrice()*theList.get(i).getQuantity());
            }
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


    public void setTotals(@NonNull DataSnapshot dataSnapshot, @Nullable String s, Boolean add){

        Item newItem = dataSnapshot.getValue(Item.class);
        if (add) {
            theList.add(newItem);
            adapter.notifyDataSetChanged();
        }

        checkPromotions();

    }

}
