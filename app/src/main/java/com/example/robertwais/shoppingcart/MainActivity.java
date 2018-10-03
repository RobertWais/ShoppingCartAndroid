package com.example.robertwais.shoppingcart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import Firebase.FirebaseService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FirebaseService.getInstance().login("wais.robert@uwlax.edu","dirk1234", this);
    }
}
