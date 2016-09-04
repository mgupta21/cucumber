package com.java.cucumber.convertor;

import cucumber.api.Transformer;

import com.java.cucumber.impl.Money;

/**
 * Created by mgupta on 9/4/16.
 */
public class MoneyConvertor extends Transformer<Money> {

    @Override
    public Money transform(String s) {
        s = s.replace("$", "");
        String[] numbers = s.split("\\.");
        int dollars = Integer.parseInt(numbers[0]);
        if (numbers.length > 1) {
            int cents = Integer.parseInt(numbers[1]);
            return new Money(dollars, cents);
        }
        return new Money(dollars, 0);
    }
}
