// Brian LaPlante - Shipping Calculator based on 11/2018 rates
// Zone and Shipping rate based on FedEx Ground site data for domestic USA shipments from 54601 (University zip code)
package com.example.robertwais.shoppingcart;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class ShippingHandler {

    double calculateShipping(Context context, int zipCode, int itemsInCart) {
        double shippingCost = 0.0;
        int zoneCode = getZoneCode(context, zipCode);


        shippingCost = getShippingCost(context, zoneCode, itemsInCart);


        return shippingCost;
    }

    int getZoneCode(Context context, int zipCode) {
        int zipPrefix = Integer.parseInt(Integer.toString(zipCode).substring(0, 3));
        int prefix1 = 0, prefix2 = 0;
        int code = 0;
        BufferedReader br;
        String line;
        String split = ",";

        try {
            String filename = "shippingdata/Zones.csv";

            br = new BufferedReader(new InputStreamReader(
                    context.getAssets().open(filename)));
            line = br.readLine();
            while ((line = br.readLine()) != null) {

                String[] separatedLine = line.split(split);
                prefix1 = Integer.parseInt(separatedLine[0]);
                prefix2 = Integer.parseInt(separatedLine[1]);
                if (zipPrefix >= prefix1 && zipPrefix <= prefix2) {
                    code = Integer.parseInt(separatedLine[2]);
                    break;
                }

            }
            Log.i("NEUTRAL", "prefix1: " + prefix1 + " prefix2: " + prefix2 + " zipPrefix: " + zipPrefix);
            if (line == null) {
                Log.i("BAD", "Error finding shipping zone");
                return -1;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.i("GOOD", "Shipping zone found!");
        return code;
    }

    double getShippingCost(Context context, int zoneCode, int itemsInCart) {
        double shippingCost = 0.0;
        BufferedReader br;
        String line = "";
        String split = ",";
        int lineNum = 0;
        int colNum = 0;

        try {
            String filename = "shippingdata/Ground.csv";

            br = new BufferedReader(new InputStreamReader(
                    context.getAssets().open(filename)));

            for (lineNum = 0; lineNum <= 2; lineNum++) {
                line = br.readLine();
            }
            String[] separatedLine = line.split(split);

            while (Integer.parseInt(separatedLine[colNum]) != zoneCode) {
                Log.i("NEUTRAL", "zoneCode " + zoneCode);

                colNum++;
            }

            while ((line = br.readLine()) != null) {
                separatedLine = line.split(split);

                if (Integer.parseInt(separatedLine[0]) == itemsInCart) {
                    shippingCost = Double.parseDouble(separatedLine[colNum]);
                    break;
                }

            }
            if (line == null) {
                Log.i("BAD", "Error finding rate");
                return -1;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.i("GOOD", "Shipping rate found!");

        return shippingCost;
    }

}
