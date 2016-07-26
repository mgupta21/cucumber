package com.java.cucumber.impl;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mgupta on 7/25/16.
 */
public class Store {

    private static Map map = new HashMap<String, Integer>();

    public static void addItem(String itemName, int price) {
        map.put(itemName, price);
    }

    public static int getItemPrice(String itemName) {
        return (int) map.get(itemName);
    }
}
