package com.example.robertwais.shoppingcart;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Adapter.ItemAdapter;
import Adapter.PromotionAdminAdapter;
import Model.Item;
import Model.Promotion;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class PromoActivity extends AppCompatActivity {

    public String id = "";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Item> theList;
    private FirebaseDatabase db;
    private DatabaseReference itemsRef;
    //pName = promotion Name, promoAmount = percent off of item, pItemID = the item ID of the item for promo
    private String pName, sDate, eDate, pAmt, pItemID;
    Double promoAmount;
    EditText promoName;
    EditText startDate;
    EditText endDate;
    EditText promoAmt;
    public EditText promoItemID;

    Button promoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promo);


        db = FirebaseDatabase.getInstance();
        recyclerView = (RecyclerView) findViewById(R.id.itemSelectorForPromotion);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        theList = new ArrayList<>();

        adapter = new PromotionAdminAdapter(PromoActivity.this, theList);
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();



        promoName = findViewById(R.id.promoName);
        startDate = findViewById(R.id.startDate);
        endDate = findViewById(R.id.endDate);
        promoAmt = findViewById(R.id.promoAmt);
        promoItemID = findViewById(R.id.promoItemID);

        promoButton = findViewById(R.id.promoButton);
        promoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //set the strings from the info given in the text boxes
                try{
                    pName = promoName.getText().toString();
                    sDate = startDate.getText().toString();
                    eDate = endDate.getText().toString();
                    pAmt = promoAmt.getText().toString();
                    pItemID = promoItemID.getText().toString();
                    promoAmount = Double.parseDouble(pAmt);

                    if(promoAmount > 0 && promoAmount <= 100){
                        if(sDate.equals("") || eDate.equals("") || pAmt.equals("") || pItemID.equals("")){
                            Toast.makeText(PromoActivity.this, "Fill out all fields", Toast.LENGTH_SHORT).show();
                        }else{
                            //proceed to add the promo to the database
                            Promotion addPromotion = new Promotion(pName, sDate, eDate, promoAmount, pItemID);
                            db.getReference().child("Promotions").child(pItemID).setValue(addPromotion);
                        }
                    } else {
                        Toast.makeText(PromoActivity.this, "Invalid promotion amount: must be 0 < % <= 100", Toast.LENGTH_SHORT).show();
                    }
                }catch( Exception error){
                    Toast.makeText(PromoActivity.this, "Fill out all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        itemsRef = db.getReference().child("Items");
        itemsRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                DatabaseReference newItemRef = dataSnapshot.getRef();
                Item newItem = dataSnapshot.getValue(Item.class);

                theList.add(newItem);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
