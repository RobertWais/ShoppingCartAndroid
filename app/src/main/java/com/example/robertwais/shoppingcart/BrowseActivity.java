package com.example.robertwais.shoppingcart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import Adapter.ItemAdapter;
import Model.Item;

public class BrowseActivity extends AppCompatActivity {

private RecyclerView recyclerView;
private RecyclerView.Adapter adapter;
private List<Item> theList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);



        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        theList = new ArrayList<>();

        adapter = new ItemAdapter(BrowseActivity.this, theList);
        recyclerView.setAdapter(adapter);

        for(int i = 0;i<10;i++){
            Item tempItem = new Item("Test"+i,"This is a test description to see how this will look in each item",0.0);
            theList.add(tempItem);
        }
        adapter.notifyDataSetChanged();
    }
}
