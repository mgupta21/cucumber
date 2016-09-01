package com.java.cucumber.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.junit.Assert;

import com.java.cucumber.impl.Checkout;
import com.java.cucumber.impl.Store;

/**
 * Created by mgupta on 7/25/16.
 */
public class CheckoutStepDef {

    Checkout checkout = new Checkout();

    @Given("^the price of a \"([^\"]*)\" is (\\d+)c$")
    public void the_price_of_a_is_c(String itemName, int price) throws Throwable {
        Store.addItem(itemName, price);
    }

    @When("^I checkout (\\d+) \"([^\"]*)\"$")
    public void i_checkout(int itemCount, String itemName) throws Throwable {
        checkout.addItemToCheckout(itemCount, itemName);
    }

    @Then("^the total price should be (\\d+)c$")
    public void the_total_price_should_be_c(int priceTotal) throws Throwable {
        Assert.assertEquals(priceTotal, checkout.total());
    }

}
