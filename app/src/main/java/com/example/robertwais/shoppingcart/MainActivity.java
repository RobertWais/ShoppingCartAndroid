package com.example.robertwais.shoppingcart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Button;

import Firebase.FirebaseService;

public class MainActivity extends AppCompatActivity {
    Button loginButton, signupButton, guestButton, documentationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseService.getInstance().signUp("wais.robert@uwlax.edu", "dirk1234", this);
//        FirebaseService.getInstance().signUp("wais.robert@uwlax.edu","dirk1234", MainActivity.this);
//        Intent intent = new Intent(MainActivity.this, BrowseActivity.class);
//        startActivity(intent);

        loginButton = findViewById(R.id.loginbutton);
        loginButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, LogInActivity.class);
                startActivity(i);
            }
        });

        signupButton = findViewById(R.id.signupbutton);
        signupButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(i);
            }
        });

        guestButton = findViewById(R.id.guestbutton);
        guestButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, BrowseActivity.class);
                startActivity(i);
            }
        });

        documentationButton = findViewById(R.id.helpbutton);
        documentationButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, DocumentationActivity.class);
                startActivity(i);
            }
        });



    }
}
