package com.example.robertwais.shoppingcart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LogInActivity extends AppCompatActivity {
    String username, password;
    EditText usernameInput;
    EditText passwordInput;

    Button loginAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        usernameInput = findViewById(R.id.usernameInput);
        passwordInput = findViewById(R.id.passwordInput);

        loginAccount = findViewById(R.id.sendloginbutton);
        loginAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = usernameInput.getText().toString();
                password = passwordInput.getText().toString();
                //On login success
                BrowseLoggedIn();

            }
        });

    }

    public void BrowseLoggedIn() {
        Intent intent = new Intent(this, BrowseActivity.class);
        startActivity(intent);
    }

}
