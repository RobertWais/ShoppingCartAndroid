package com.example.robertwais.shoppingcart;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class AdminPage extends AppCompatActivity {

    Button promoButton, browseBtn, addItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

        promoButton = findViewById(R.id.addPromoButton);
        browseBtn = findViewById(R.id.browseForAdminBtn);
        addItem = findViewById(R.id.adminAddItemBtn);

        promoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminPage.this, PromoActivity.class);
                startActivity(i);
            }
        });

        browseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminPage.this, BrowseActivity.class);
                startActivity(i);
            }
        });

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminPage.this, AdminAddItem.class);
                startActivity(i);
            }
        });
    }
}
