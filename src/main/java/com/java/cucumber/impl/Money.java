package com.java.cucumber.impl;

import org.apache.log4j.Logger;

/**
 * Created by mgupta on 9/4/16.
 */
public class Money {

    Logger         logger = Logger.getLogger(Money.class);

    private double amount;

    public double getAmount() {
        return amount;
    }

    public Money(int dollars, int cents) {
        this.amount = dollars + (cents / 100);
    }

    public void add(Money money) {
        this.amount += money.amount;
    }

    public double deduct(Money money) {
        if (isDeductable(money)) {
            this.amount -= money.amount;
            return money.amount;
        }
        logger.warn("Transaction aborted. Couldn't deduct '" + money.amount + "' from '" + amount + "'");
        return 0;
    }

    public boolean isDeductable(Money money) {
        return this.amount >= money.amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Money money = (Money) o;

        return Double.compare(money.amount, amount) == 0;

    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(amount);
        return (int) (temp ^ (temp >>> 32));
    }
}
