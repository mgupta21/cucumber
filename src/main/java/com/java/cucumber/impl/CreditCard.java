package com.java.cucumber.impl;


import com.java.cucumber.exceptions.CardExpiredException;
import com.java.cucumber.exceptions.InvalidCardNumberException;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 * Created by mgupta on 7/25/16.
 */
public class CreditCard {

    private String holderName;
    private int cardNumber;
    private String expirationDate;

    public void number(int number) {
        this.cardNumber = number;
    }

    public void addName(String name) {
        this.holderName = name;
    }

    public void addExpiry(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void submit() throws RuntimeException {
        validateCard();
    }

    private void validateCard() {
        validateExpiration(expirationDate);
        validateCardNumber(cardNumber);
        validateCardName(holderName);
    }

    private void validateCardName(String holderName) {
        if (holderName.isEmpty())
            throw new RuntimeException();
    }

    private void validateCardNumber(int cardNumber) {
        if ((1 + (int) Math.floor(Math.log10(cardNumber)) != 16)) {
            throw new InvalidCardNumberException();
        }
    }

    private void validateExpiration(String dateStr) throws CardExpiredException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate date = LocalDate.parse(dateStr, formatter);
        Period duration = Period.between(LocalDate.now(), date);
        if (duration.isNegative())
            throw new CardExpiredException();
    }
}
