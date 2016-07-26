package com.java.cucumber.steps;

import com.java.cucumber.exceptions.InvalidCardNumberException;
import com.java.cucumber.impl.Checkout;
import com.java.cucumber.impl.CreditCard;
import com.java.cucumber.impl.Store;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

/**
 * Created by mgupta on 7/25/16.
 */
public class CreditCardSteps {

    private Checkout checkout = new Checkout();
    private CreditCard card;

    private void testSetup() {
        Store.addItem("banana", 40);
        Store.addItem("apple", 25);
        checkout.addItemToCheckout(3, "banana");
        checkout.addItemToCheckout(2, "apple");
    }

    @Given("^I have choosen some items to buy$")
    public void i_have_choosen_some_items_to_buy() throws Throwable {
        testSetup();
    }

    @Given("^I am about to enter my credit card details$")
    public void i_am_about_to_enter_my_credit_card_details() throws Throwable {
        card = new CreditCard();
    }

    @When("^I enter a card number that's only four (\\d+) digits long$")
    public void i_enter_a_card_number_that_s_only_digits_long(int number) throws Throwable {
        card.number(number);
    }

    @When("^all the other details are correct$")
    public void all_the_other_details_are_correct() throws Throwable {
        card.addName("John Doe");
        card.addExpiry("20200101");
    }

    @When("^I submit the form$")
    public void i_submit_the_form() throws Throwable {
        // Do Nothing
    }

    @Then("^invalid card number error should be displayed$")
    public void invalid_card_number_error_should_be_displayed() throws Throwable {
        boolean flag = false;
        try {
            card.submit();
        } catch (InvalidCardNumberException e) {
            flag = true;
        }
        Assert.assertTrue(flag);
    }

}
