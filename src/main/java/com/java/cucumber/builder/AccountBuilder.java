package com.java.cucumber.builder;

import com.java.cucumber.impl.Account;

/**
 * Created by mgupta on 9/1/16.
 */
public class AccountBuilder {

    private long   accountNumber     = 1L;
    private String accountHolderName = "first last";
    private int    balance           = 0;

    public Account build() {
        return new Account(accountHolderName, accountNumber, balance);
    }

    public AccountBuilder withAccountHolderName(String name) {
        this.accountHolderName = name;
        return this;
    }

    public AccountBuilder withAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public AccountBuilder withAccountBalance(int amount) {
        this.balance = amount;
        return this;
    }

}
