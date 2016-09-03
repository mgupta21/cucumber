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
        if (hasSufficientBalance(amount)) {
            balance -= amount;
            return;
        }
        logger.warn("Transaction aborted. Insufficient balance. Current balance '" + balance + "' requested amount '" + amount + "'");
    }

    private boolean hasSufficientBalance(int amount) {
        return balance >= amount;
    }

    private long generateAccountNumber() {
        long accountNumber = System.currentTimeMillis();
        logger.info("Created new account with number '" + accountNumber + "'");
        return accountNumber;
    }
}
