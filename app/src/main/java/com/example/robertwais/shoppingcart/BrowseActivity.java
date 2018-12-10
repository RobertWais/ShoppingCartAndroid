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

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BrowseActivity extends AppCompatActivity {

private RecyclerView recyclerView;
private RecyclerView.Adapter adapter;
private List<Item> theList;
private TextView currUserView;
private Button cart, orderHistory;

private FirebaseDatabase db;
private DatabaseReference database, itemsRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basebrowse);


        //Toolbar settings
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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
        orderHistory = findViewById(R.id.toOrderHistory);
        orderHistory.setVisibility(View.GONE);

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

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    //Menu stuff
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
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

//        Item[] items = {
//                new Item("Spider Man","Marvel's Spider-Man, commonly referred to as Spider-Man, is an action-adventure game developed by Insomniac Games and published by Sony Interactive Entertainment for the PlayStation 4, " +
//                        "based on the Marvel Comics superhero Spider-Man.",59.99),
//                new Item("Fallout 4","Fallout 4 is a post-apocalyptic action role-playing video game developed by Bethesda Game Studios and published by Bethesda Softworks. It is the fifth major installment in the Fallout series and was released worldwide" +
//                        " on November 10, 2015, for Microsoft Windows, PlayStation 4 and Xbox One.",15.00),
//                new Item("Madden 19","Gameplay in Madden NFL 19 was developed with two primary objectives: 1. Player Control across the field; 2.Immersive NFL Experience from start to finish. To achieve these objectives, EA's goal" +
//                        " is to deliver the most significant animation upgrade in Madden history.",59.99),
//                new Item("Red Dead Redemption 2 Ultimate Edition","After a robbery goes badly wrong in the western town of Blackwater, Arthur Morgan and the Van der Linde gang are forced to flee. With federal agents and the best bounty hunters in the nation massing on their heels, the gang must rob, steal and fight their way across the rugged heartland of America in order to survive. As deepening internal divisions threaten to tear the gang apart, Arthur must make a choice between his own idea" +
//                        "ls and loyalty to the gang who raised him.",99.9),
//                new Item("Assassin's Creed Odyssey","Write your own epic odyssey and become a legendary Spartan hero in Assassin's Creed® Odyssey, an inspiring adventure where you must forge your destiny and define your own path in a world on the brink of tearing itself apart. Influence how history unfolds as you ex" +
//                        "perience a rich and ever-changing world shaped by your decisions.",59.99),
//                new Item("Spyro Reignited Trilogy","Spyro's back and he's all scaled up! The original roast master is back! Same sick burns, same smoldering attitude, now all scaled up in stunning HD. Spyro is bringing the heat like never before in the Spyro Reignited Trilogy game collection. Rekindle the fire with the original three games, Spyro the Dragon, Spyro 2 Ripto's Rage! and Spyro Year of the Dragon. Explore the expansive realms, re-encounter the fiery personalities and relive the adventure in fully remastered glory. Because when there's a realm that" +
//                        " needs saving, there's only one dragon to call.",39.99),
//
//        };

//        //Loop to add to Database
//        for(int i = 0; i< items.length; i++){
//            Item item = new Item(items[i].getName(), items[i].getDescription(), items[i].getPrice());
//            String key = database.child("Items").push().getKey();
//            item.setKey(key);
//            database.child("Items").child(key).setValue(item);
//        }