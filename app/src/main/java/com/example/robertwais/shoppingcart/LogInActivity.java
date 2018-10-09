package com.example.robertwais.shoppingcart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Firebase.FirebaseService;

public class LogInActivity extends AppCompatActivity {
    String username, password;
    EditText usernameInput;
    EditText passwordInput;

    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        usernameInput = findViewById(R.id.usernameLogin);
        passwordInput = findViewById(R.id.passwordLogin);

        loginButton = findViewById(R.id.sendLoginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = usernameInput.getText().toString();
                password = passwordInput.getText().toString();
                if (!(username.equals("") || password.equals(""))) {
                    FirebaseService.getInstance().login(username, password, LogInActivity.this);
                } else {
                    Toast.makeText(LogInActivity.this, "Enter E-Mail and Password", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
