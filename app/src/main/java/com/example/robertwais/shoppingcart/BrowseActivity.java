package com.example.robertwais.shoppingcart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Adapter.ItemAdapter;
import Firebase.FirebaseService;
import Model.Item;

public class BrowseActivity extends AppCompatActivity {

private RecyclerView recyclerView;
private RecyclerView.Adapter adapter;
private List<Item> theList;
private TextView currUserView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);
        currUserView = (TextView) findViewById(R.id.usernameFieldMain);
        String name = FirebaseService.getInstance().isUser();
        currUserView.setText(name);
        Toast.makeText(this, "Hello "+name, Toast.LENGTH_SHORT).show();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        theList = new ArrayList<>();

        adapter = new ItemAdapter(BrowseActivity.this, theList);
        recyclerView.setAdapter(adapter);
        Item[] items = {
                new Item("Spider Man","Marvel's Spider-Man, commonly referred to as Spider-Man, is an action-adventure game developed by Insomniac Games and published by Sony Interactive Entertainment for the PlayStation 4, " +
                        "based on the Marvel Comics superhero Spider-Man.",59.99),
                new Item("Fallout 4","Fallout 4 is a post-apocalyptic action role-playing video game developed by Bethesda Game Studios and published by Bethesda Softworks. It is the fifth major installment in the Fallout series and was released worldwide" +
                        " on November 10, 2015, for Microsoft Windows, PlayStation 4 and Xbox One.",15.00),
                new Item("Madden 19","Gameplay in Madden NFL 19 was developed with two primary objectives: 1. Player Control across the field; 2.Immersive NFL Experience from start to finish. To achieve these objectives, EA's goal" +
                        " is to deliver the most significant animation upgrade in Madden history.",59.99),
                new Item("Red Dead Redemption 2 Ultimate Edition","After a robbery goes badly wrong in the western town of Blackwater, Arthur Morgan and the Van der Linde gang are forced to flee. With federal agents and the best bounty hunters in the nation massing on their heels, the gang must rob, steal and fight their way across the rugged heartland of America in order to survive. As deepening internal divisions threaten to tear the gang apart, Arthur must make a choice between his own idea" +
                        "ls and loyalty to the gang who raised him.",99.9),
                new Item("Assassin's Creed Odyssey","Write your own epic odyssey and become a legendary Spartan hero in Assassin's Creed® Odyssey, an inspiring adventure where you must forge your destiny and define your own path in a world on the brink of tearing itself apart. Influence how history unfolds as you ex" +
                        "perience a rich and ever-changing world shaped by your decisions.",59.99),
                new Item("Spyro Reignited Trilogy","Spyro's back and he's all scaled up! The original roast master is back! Same sick burns, same smoldering attitude, now all scaled up in stunning HD. Spyro is bringing the heat like never before in the Spyro Reignited Trilogy game collection. Rekindle the fire with the original three games, Spyro the Dragon, Spyro 2 Ripto's Rage! and Spyro Year of the Dragon. Explore the expansive realms, re-encounter the fiery personalities and relive the adventure in fully remastered glory. Because when there's a realm that" +
                        " needs saving, there's only one dragon to call.",39.99),

        };

        for(int i = 0;i< items.length;i++){
            theList.add(items[i]);
        }
        adapter.notifyDataSetChanged();
    }
}
