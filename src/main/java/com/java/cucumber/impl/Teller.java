package com.java.cucumber.impl;

/**
 * Created by mgupta on 9/4/16.
 */
public class Teller {

    private CashSlot cashSlot;

    public Teller(CashSlot cashSlot) {
        this.cashSlot = cashSlot;
    }

    public void withdrawFrom(Account account, Money amount) {
        cashSlot.dispense(account.withdraw(amount));
    }
}
