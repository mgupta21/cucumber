package com.java.cucumber.steps;

import com.java.cucumber.impl.Checkout;
import com.java.cucumber.impl.Store;
import cucumber.api.CucumberOptions;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;
import org.junit.Assert;
import org.junit.runner.RunWith;

/**
 * Created by mgupta on 7/25/16.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        format = {"pretty", "html:target/cucumber"},
        features = "classpath:features/checkout.feature"
)
public class CheckoutSteps {

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
