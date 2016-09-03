package com.java.cucumber.builder;

import com.java.cucumber.impl.Account;
import com.java.cucumber.impl.CreditCard;

/**
 * Created by mgupta on 9/1/16.
 */
public class CreditCardBuilder {

    private String  cardHolderName = "first last";
    private long    cardNumber     = 1111222233334444L;
    private int     cardPinNumber  = 0000;
    private String  expirationDate = "20201010";

    private Account account;

    public CreditCard build() {
        if (account == null) {
            account = new AccountBuilder().build();
        }
        return new CreditCard(account, cardHolderName, cardNumber, cardPinNumber, expirationDate);
    }

    public CreditCardBuilder withCardHolderName(String name) {
        this.cardHolderName = name;
        return this;
    }

    public CreditCardBuilder withAccount(Account account) {
        this.account = account;
        return this;
    }

    public CreditCardBuilder withCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    public CreditCardBuilder withCardPinNumber(int pin) {
        this.cardPinNumber = pin;
        return this;
    }

    public CreditCardBuilder withExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
        return this;
    }

}
