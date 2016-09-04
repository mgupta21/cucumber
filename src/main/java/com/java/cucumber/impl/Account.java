package com.java.cucumber.impl;

import org.apache.log4j.Logger;

/**
 * Created by mgupta on 7/27/16.
 */
public class Account {

    private String              accountHolderName;
    private long                accountNumber;
    private Money               balance;

    private static final Logger logger = Logger.getLogger(Account.class);

    public Account(String accountHolderName, int openingBalance) {
        this.accountHolderName = accountHolderName;
        this.balance = new Money(openingBalance);
        this.accountNumber = generateAccountNumber();
    }

    public Money getBalance() {
        return balance;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public int withdraw(Money amount) {
        return balance.deduct(amount);
    }

    private long generateAccountNumber() {
        long accountNumber = System.currentTimeMillis();
        logger.info("Created new account with number '" + accountNumber + "'");
        return accountNumber;
    }

    public void deposit(Money money) {
        this.balance.add(money);
    }
}
