package com.java.cucumber.impl;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mgupta on 9/1/16.
 */
class CreditCardMatcher {

    private static volatile Map<Long, Integer> creditCardDataBase = new HashMap<>();

    private CreditCardMatcher() {
    }

    static void addCreditCard(long cardNumber, int pinNumber) {
        if (!hasCreditCard(cardNumber)) {
            creditCardDataBase.put(cardNumber, pinNumber);
        }
    }

    static void resetCreditCardNumber(long cardNumber, int oldPinNumber, int pinNumber) {
        if (isPinValid(cardNumber, oldPinNumber)) {
            creditCardDataBase.put(cardNumber, pinNumber);
        }
    }

    static boolean isPinValid(long cardNumber, int pinNumber) {
        if (hasCreditCard(cardNumber)) {
            return creditCardDataBase.get(cardNumber) == pinNumber;
        }
        return false;
    }

    private static boolean hasCreditCard(long cardNumber) {
        return creditCardDataBase.containsKey(cardNumber);
    }

}
