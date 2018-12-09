package com.example.robertwais.shoppingcart;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import Adapter.FullOrderAdapter;
import Adapter.ItemAdapter;
import Model.Item;
import Model.Order;

public class OrderHistory extends AppCompatActivity {


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Order> theList;

    private FirebaseDatabase db;
    private DatabaseReference database, orderHistoryRef;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);


        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        database = db.getReference();
        if(mAuth.getCurrentUser()!=null){
            orderHistoryRef = database.child(mAuth.getCurrentUser().getUid()).child("OrderHistory");
        }else{
            orderHistoryRef = database.child("Guest").child("OrderHistory");
        }






        recyclerView = (RecyclerView) findViewById(R.id.orderHistoryActivityRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        theList = new ArrayList<>();

        adapter = new FullOrderAdapter(OrderHistory.this, theList);
        recyclerView.setAdapter(adapter);
//        recyclerView.setNestedScrollingEnabled(false);
        Toast.makeText(OrderHistory.this, "Here: "+recyclerView.isNestedScrollingEnabled(), Toast.LENGTH_SHORT).show();




        adapter.notifyDataSetChanged();

        //Listeners for Orders
        orderHistoryRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Order order = dataSnapshot.getValue(Order.class);

                theList.add(order);
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
