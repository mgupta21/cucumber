package com.java.cucumber.impl;

/**
 * Created by mgupta on 7/25/16.
 */
public class Checkout {

    private int total;

    public void addItemToCheckout(int itemCount, String itemName) {
        total += itemCount * Store.getItemPrice(itemName);
    }

    public int total() {
        return total;
    }

}
