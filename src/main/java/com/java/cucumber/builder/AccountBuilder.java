package com.java.cucumber.builder;

import com.java.cucumber.impl.Account;
import com.java.cucumber.impl.Money;

/**
 * Created by mgupta on 9/1/16.
 */
public class AccountBuilder {

    private String accountHolderName = "first last";
    private Money  balance           = new Money(0, 0);

    public Account build() {
        return new Account(accountHolderName, balance);
    }

    public AccountBuilder withAccountHolderName(String name) {
        this.accountHolderName = name;
        return this;
    }

    public AccountBuilder withAccountBalance(Money amount) {
        this.balance = amount;
        return this;
    }

}
