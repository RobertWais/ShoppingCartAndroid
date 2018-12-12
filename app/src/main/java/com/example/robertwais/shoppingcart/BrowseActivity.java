package com.example.robertwais.shoppingcart;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Adapter.ItemAdapter;
import Firebase.FirebaseService;
import Model.Item;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BrowseActivity extends AppCompatActivity {

private RecyclerView recyclerView;
private RecyclerView.Adapter adapter;
private List<Item> theList;
private TextView currUserView;
private Button cart, orderHistory,removeBtn;

private FirebaseDatabase db;
private DatabaseReference database, itemsRef;
private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basebrowse);


        //Toolbar settings
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Database connections
        db = FirebaseDatabase.getInstance();
        database = db.getReference();
        itemsRef = database.child("Items");


        currUserView = (TextView) findViewById(R.id.usernameFieldMain);
        String name = FirebaseService.getInstance().isUser();
        currUserView.setText(name);
        Toast.makeText(this, "Hello "+name, Toast.LENGTH_SHORT).show();
        cart = findViewById(R.id.toCartBtn);


        //If admin remove cart as well as disable settings
        /*Connect to database*/
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        database = db.getReference();
        db.getReference().child("Admin").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Check if guest user, regular user, or admin
                if (mAuth.getCurrentUser() != null) {
                    if (mAuth.getCurrentUser().getUid().equals(dataSnapshot.getValue())) {
                        cart.setVisibility(View.GONE);
                        toolbar.setVisibility(View.GONE);
                        currUserView.setVisibility(View.GONE);
                        //Remove btn
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        /*Should be invisible*/
        orderHistory = findViewById(R.id.toOrderHistory);
        orderHistory.setVisibility(View.GONE);
        /*                   */


        //Setup adapter and recyclerview
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        theList = new ArrayList<>();
        adapter = new ItemAdapter(BrowseActivity.this, theList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(BrowseActivity.this, ShoppingCartActivity.class);
                startActivity(i);
            }
        });

        orderHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(BrowseActivity.this, OrderHistory.class);
                startActivity(i);
            }
        });


        //Listeners for Items
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
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    //Sets up the toolbar and menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //Redirects on click of option
        if (id == R.id.action_OrderHistory) {
            Intent i = new Intent(BrowseActivity.this, OrderHistory.class);
            startActivity(i);
            return true;
        }else if(id == R.id.action_Profile){
            Intent i = new Intent(BrowseActivity.this, ProfileActivity.class);
            startActivity(i);
            return true;
        }else if (id == R.id.action_Docs ){
            //Add Docs Activity
        }else{
            //Close
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

