package com.example.robertwais.shoppingcart;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Firebase.FirebaseService;

public class SignUpActivity extends AppCompatActivity {

    String username, password;
    EditText usernameInput;
    EditText passwordInput;

    Button createAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        usernameInput = findViewById(R.id.usernameInput);
        passwordInput = findViewById(R.id.passwordInput);

        createAccount = findViewById(R.id.createAccountButton);
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = usernameInput.getText().toString();
                password = passwordInput.getText().toString();
                if (!(usernameInput.equals("") || passwordInput.equals(""))) {
                    FirebaseService.getInstance().signUp(username, password, SignUpActivity.this);
                    Toast.makeText(SignUpActivity.this, "Enter E-Mail and Password", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SignUpActivity.this, "Enter E-Mail and Password", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

}
