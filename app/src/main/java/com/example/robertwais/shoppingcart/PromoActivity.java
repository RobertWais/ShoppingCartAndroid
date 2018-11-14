package com.example.robertwais.shoppingcart;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PromoActivity extends AppCompatActivity {

    //pName = promotion Name, pAmt = percent off of item, pItemID = the item ID of the item for promo
    String pName, sDate, eDate, pAmt, pItemID;
    EditText promoName;
    EditText startDate;
    EditText endDate;
    EditText promoAmt;
    EditText promoItemID;

    Button promoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promo);

        promoName = findViewById(R.id.promoName);
        startDate = findViewById(R.id.startDate);
        endDate = findViewById(R.id.endDate);
        promoAmt = findViewById(R.id.promoAmt);
        promoItemID = findViewById(R.id.promoItemID);

        promoButton = findViewById(R.id.promoButton);
        promoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //set the strings from the info given in the text boxes
                pName = promoName.getText().toString();
                sDate = startDate.getText().toString();
                eDate = endDate.getText().toString();
                pAmt = promoAmt.getText().toString();
                pItemID = promoItemID.getText().toString();
            }
        });
    }
}
