package com.java.cucumber.impl;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mgupta on 7/25/16.
 */
public class Checkout {

    private int total;
    private Map map = new HashMap<String, Integer>();

    public void addItemToCheckout(int itemCount, String itemName) {
        total += itemCount * (int) map.get(itemName);
    }

    public int total() {
        return total;
    }

    public void addItemToStore(String itemName, int price) {
        map.put(itemName, price);
    }
}
