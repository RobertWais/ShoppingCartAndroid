//Brian LaPlante Credit Card Prefix Validation
package com.example.robertwais.shoppingcart;

import android.util.Log;

public class CreditCardVerification {


    public String verifyCardNumber(String cardNumber) {
        String cardType = "Unknown Card Type";

        cardNumber = cardNumber.replaceAll("-", "");

        if ((cardNumber.length() == 13 || cardNumber.length() == 16) &&
                cardNumber.startsWith("4")) {
            //Visa
            cardType = "Visa";
        } else if (cardNumber.length() == 16 &&
                (cardNumber.startsWith("51") ||
                        cardNumber.startsWith("52") ||
                        cardNumber.startsWith("53") ||
                        cardNumber.startsWith("54") ||
                        cardNumber.startsWith("55"))) {
            //MasterCard
            cardType = "MasterCard";
        } else if (cardNumber.length() == 16 &&
                (cardNumber.startsWith("6011") ||
                        cardNumber.startsWith("65"))) {
            //Discover
            cardType = "Discover";
        } else if (cardNumber.length() == 15 &&
                (cardNumber.startsWith("34") ||
                        cardNumber.startsWith("37"))) {
            //American Express
            cardType = "American Express";
        }

        Log.i("GOOD", "Card type: " + cardType);
        return cardType;
    }
}
