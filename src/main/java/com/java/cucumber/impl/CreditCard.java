package com.java.cucumber.impl;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import org.apache.log4j.Logger;

import com.java.cucumber.exceptions.CardExpiredException;
import com.java.cucumber.exceptions.InvalidCardNumberException;

/**
 * Created by mgupta on 7/25/16.
 */
public class CreditCard {

    private String              cardHolderName;
    private long                cardNumber;
    private LocalDate           expirationDate;
    private Account             account;

    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final Logger logger       = Logger.getLogger(CreditCard.class);

    public CreditCard(Account account, long cardNumber, int cardPinNumber) {
        this(account, cardNumber, cardPinNumber, generateExpirationDate());
    }

    public CreditCard(Account account, long cardNumber, int cardPinNumber, String expirationDate) {
        this(account, cardNumber, cardPinNumber, parseDate(expirationDate));
    }

    public CreditCard(Account account, long cardNumber, int cardPinNumber, LocalDate expirationDate) {
        this.account = account;
        this.cardHolderName = account.getAccountHolderName();
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        addCreditCardToDataBase(cardNumber, cardPinNumber);
        newCardInfo();
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public Account getAccount() {
        return account;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public boolean resetPin(int oldPinNumber, int newPinNumber) {
        return CreditCardMatcher.resetCreditCardNumber(this.cardNumber, oldPinNumber, newPinNumber);
    }

    public boolean isPinValid(int pinNumber) {
        return CreditCardMatcher.isPinValid(this.cardNumber, pinNumber);
    }

    public void submit() throws RuntimeException {
        validateCard();
    }

    public void withdraw(int amount, int pinNumber) {
        validateCard();
        getAccount().withdraw(amount, this.cardNumber, pinNumber);
    }

    private void validateCard() {
        validateExpiration(getExpirationDate());
        validateCardNumber(getCardNumber());
    }

    private void validateCardNumber(long cardNumber) {
        if ((1 + (int) Math.floor(Math.log10(cardNumber)) != 16)) {
            throw new InvalidCardNumberException();
        }
    }

    private void addCreditCardToDataBase(long cardNumber, int pinNumber) {
        CreditCardMatcher.addCreditCard(cardNumber, pinNumber);
    }

    private static LocalDate parseDate(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        return LocalDate.parse(dateStr, formatter);
    }

    private void validateExpiration(LocalDate date) throws CardExpiredException {
        Period duration = Period.between(LocalDate.now(), date);
        if (duration.isNegative()) {
            throw new CardExpiredException();
        }
    }

    private static LocalDate generateExpirationDate() {
        LocalDate date = LocalDate.now().plusYears(2);
        logger.info("Generated new credit card expiration date '" + date + "'");
        return date;
    }

    private void newCardInfo() {
        logger.info("Created new credit card '" + cardNumber + "' for account '" + getAccount().getAccountNumber() + "'");
    }
}
