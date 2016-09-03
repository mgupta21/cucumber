package com.java.cucumber.impl;

import org.apache.log4j.Logger;

/**
 * Created by mgupta on 7/27/16.
 */
public class Account {

    private String              accountHolderName;
    private long                accountNumber;
    private int                 balance;

    private static final Logger logger = Logger.getLogger(Account.class);

    public Account(String accountHolderName, int amount) {
        this.accountHolderName = accountHolderName;
        this.balance = amount;
        this.accountNumber = generateAccountNumber();
    }

    public int getBalance() {
        return balance;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void withdraw(int amount) {
        balance -= amount;
    }

    public void withdraw(int amount, long cardNumber, int pinNumber) {
        if (CreditCardMatcher.isPinValid(cardNumber, pinNumber)) {
            logger.info("Successfully withdrew amount : " + amount);
            withdraw(amount);
        } else {
            logger.error("Invalid PIN. Incorrect pin number entered for credit card '" + cardNumber + "'");
        }
    }

    private long generateAccountNumber() {
        long accountNumber = System.currentTimeMillis();
        logger.info("Created new account with number '" + accountNumber + "'");
        return accountNumber;
    }
}
