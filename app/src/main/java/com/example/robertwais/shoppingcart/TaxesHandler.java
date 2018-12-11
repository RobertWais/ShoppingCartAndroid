// Brian LaPlante - Taxes calculator based on State Code and Zip Code
// Tax rate based on 10/2018 Avalara dataset
package com.example.robertwais.shoppingcart;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class TaxesHandler {

    private String[] stateCodes = {
            "AK",
            "AL",
            "AR",
            "AZ",
            "CA",
            "CO",
            "CT",
            "DC",
            "DE",
            "FL",
            "GA",
            "HI",
            "IA",
            "ID",
            "IL",
            "IN",
            "KS",
            "KY",
            "LA",
            "MA",
            "MD",
            "ME",
            "MI",
            "MN",
            "MO",
            "MS",
            "MT",
            "NC",
            "ND",
            "NE",
            "NH",
            "NJ",
            "NM",
            "NV",
            "NY",
            "OH",
            "OK",
            "OR",
            "PA",
            "PR",
            "RI",
            "SC",
            "SD",
            "TN",
            "TX",
            "UT",
            "VA",
            "VT",
            "WA",
            "WI",
            "WV",
            "WY"
    };

    double calculateTaxes(Context context, String stateCode, int zipCode, double subtotal) {
        double taxRate = 0.0;
        double taxValue = 0.0;
        int i;

        for (i = 0; i < 52 && !stateCodes[i].equals(stateCode); i++) {

        }
        if (i == 52) {
            Log.i("BAD", "Error finding state code: " + stateCode);
            return 0.0;
        }

        taxRate = getTaxRate(context, i, zipCode);
        taxValue = taxRate * subtotal;

        return taxValue;
    }

    double getTaxRate(Context context, int i, int zipCode) {
        double taxRate = 0.0;
        BufferedReader br;
        String line;
        String split = ",";

        try {
            String filename = "taxdata/TAXRATES_ZIP5_" + stateCodes[i] + "201810.csv";

            br = new BufferedReader(new InputStreamReader(
                    context.getAssets().open(filename)));
            while ((line = br.readLine()) != null) {

                String[] separatedLine = line.split(split);
                if (separatedLine[1].equals("" + zipCode)) {
                    taxRate = Double.parseDouble(separatedLine[4]);
                    break;
                }

            }
            if (line == null) {
                Log.i("BAD", "Error finding Zip Code");
                return 0.0;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.i("GOOD", "Tax rate found!");
        return taxRate;
    }

}
