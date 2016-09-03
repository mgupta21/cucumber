package com.java.cucumber.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * Created by mgupta on 9/1/16.
 */
public class CreditCardMatcher {

    private static final Logger                logger             = Logger.getLogger(CreditCardMatcher.class);
    private static volatile Map<Long, Integer> creditCardDataBase = new HashMap<>();

    private CreditCardMatcher() {
    }

    static void addCreditCard(long cardNumber, int pinNumber) {
        if (!hasCreditCard(cardNumber)) {
            creditCardDataBase.put(cardNumber, pinNumber);
            return;
        }
        logger.warn("Credit card add request abandoned, credit card '" + cardNumber + "' already exist in database.");
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

    public static void clear() {
        creditCardDataBase.clear();
    }

    private static boolean hasCreditCard(long cardNumber) {
        return creditCardDataBase.containsKey(cardNumber);
    }

}
