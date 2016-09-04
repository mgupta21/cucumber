package com.java.cucumber.impl;

import org.apache.log4j.Logger;

/**
 * Created by mgupta on 9/4/16.
 */
public class Money {

	Logger logger = Logger.getLogger(Money.class);

	private int amount;

	public int getAmount() {
		return amount;
	}

	public Money(int amount) {
		this.amount = amount;
	}

	public void add(Money money) {
		this.amount += money.amount;
	}

	public int deduct(Money money) {
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
}
