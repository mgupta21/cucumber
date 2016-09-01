package com.java.cucumber.impl;

/**
 * Created by mgupta on 7/27/16.
 */
public class Account {

    private int balance;

    public Account(int amount) {
        balance = amount;
    }

    public int getBalance() {
        return balance;
    }

    public void withdraw(int amount) {
        balance -= amount;
    }
}
