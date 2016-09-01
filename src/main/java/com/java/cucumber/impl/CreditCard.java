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

    private String  cardHolderName;
    private long    cardNumber;
    private String  expirationDate;
    private Account account;

    public CreditCard(Account account, long cardNumber, int cardPinNumber) {
        this.account = account;
        this.cardHolderName = account.getAccountHolderName();
        this.cardNumber = cardNumber;
        this.expirationDate = generateDefaultExpirationDate();
        addCreditCardToDataBase(cardNumber, cardPinNumber);
    }

    public CreditCard(Account account, String cardHolderName, long cardNumber, int cardPinNumber, String expirationDate) {
        this.account = account;
        this.cardHolderName = cardHolderName;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        addCreditCardToDataBase(cardNumber, cardPinNumber);
    }

    public void resetPin(int oldPinNumber, int newPinNumber) {
        CreditCardMatcher.resetCreditCardNumber(this.cardNumber, oldPinNumber, newPinNumber);
    }

    public boolean isPinValid(int pinNumber) {
        return CreditCardMatcher.isPinValid(this.cardNumber, pinNumber);
    }

    private void addCreditCardToDataBase(long cardNumber, int pinNumber) {
        CreditCardMatcher.addCreditCard(cardNumber, pinNumber);
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Account getAccount() {
        return account;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    private long generateCardNumber() {
        return System.currentTimeMillis();
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void submit() throws RuntimeException {
        validateCard();
    }

    public void validateCard() {
        validateExpiration(getExpirationDate());
        validateCardNumber(getCardNumber());
        validateCardName(getCardHolderName());
    }

    public void withdraw(int amount, int pinNumber) {
        validateCard();
        getAccount().withdraw(amount, this.cardNumber, pinNumber);
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

    private String generateDefaultExpirationDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate localDate = LocalDate.parse(LocalDate.now().plusYears(2).toString(), formatter);
        return localDate.toString();

    }
}
