package com.java.cucumber.utils;

import com.java.cucumber.builder.AccountBuilder;
import com.java.cucumber.impl.Account;
import com.java.cucumber.impl.CashSlot;

/**
 * Created by mgupta on 9/4/16.
 */
// Sharing state between steps
public class KnowsMyDomain {

    private Account  testAccount;
    private CashSlot cashSlot;

    public Account getTestAccount() {
        if (testAccount == null) {
            testAccount = new AccountBuilder().build();
        }
        return testAccount;
    }

    public CashSlot getCashSlot() {
        if (cashSlot == null) {
            cashSlot = new CashSlot();
        }
        return cashSlot;
    }
}
