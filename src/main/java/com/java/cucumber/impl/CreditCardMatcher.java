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
            logger.info("Added new credit card '" + cardNumber + "' to database.");
            return;
        }
        logger.warn("Credit card add request abandoned, credit card '" + cardNumber + "' already exist in database.");
    }

    static boolean resetCreditCardNumber(long cardNumber, int oldPinNumber, int newPinNumber) {
        if (isPinValid(cardNumber, oldPinNumber)) {
            if (isPinDifferent(oldPinNumber, newPinNumber)) {
                creditCardDataBase.put(cardNumber, newPinNumber);
                return true;
            }
            logger.warn("Couldn't reset PIN. New PIN number must be different from old.");
        }
        return false;
    }

    private static boolean isPinDifferent(int oldPinNumber, int newPinNumber) {
        return oldPinNumber != newPinNumber;
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
