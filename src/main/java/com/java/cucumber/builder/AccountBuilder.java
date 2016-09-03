package com.java.cucumber.builder;

import com.java.cucumber.impl.Account;

/**
 * Created by mgupta on 9/1/16.
 */
public class AccountBuilder {

    private String accountHolderName = "first last";
    private int    balance           = 0;

    public Account build() {
        return new Account(accountHolderName, balance);
    }

    public AccountBuilder withAccountHolderName(String name) {
        this.accountHolderName = name;
        return this;
    }

    public AccountBuilder withAccountBalance(int amount) {
        this.balance = amount;
        return this;
    }

}
