package com.java.cucumber.impl;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import com.java.cucumber.exceptions.CardExpiredException;
import com.java.cucumber.exceptions.InvalidCardNumberException;

/**
 * Created by mgupta on 7/25/16.
 */
public class CreditCard {

    private String  holderName;
    private long    cardNumber;
    private String  expirationDate;
    private Account account;

    public CreditCard(Account account) {
        this.account = account;
    }

    public void number(long number) {
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

    private void validateCardNumber(long cardNumber) {
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

    public void withdraw(int amount) {
        validateCard();
        getAccount().withdraw(amount);
    }

    public Account getAccount() {
        return account;
    }
}
