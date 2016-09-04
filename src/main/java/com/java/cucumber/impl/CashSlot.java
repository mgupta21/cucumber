package com.java.cucumber.impl;

/**
 * Created by mgupta on 9/4/16.
 */
public class CashSlot {

    private double contents;

    public double contents() {
        return contents;
    }

    public void dispense(double amount) {
        contents = amount;
    }
}
