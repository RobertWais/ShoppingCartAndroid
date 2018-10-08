package com.example.robertwais.shoppingcart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import Firebase.FirebaseService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseService.getInstance().signUp("wais.robert@uwlax.edu", "dirk1234", this);
//        FirebaseService.getInstance().signUp("wais.robert@uwlax.edu","dirk1234", MainActivity.this);
//        Intent intent = new Intent(MainActivity.this, BrowseActivity.class);
//        startActivity(intent);





    }
}
