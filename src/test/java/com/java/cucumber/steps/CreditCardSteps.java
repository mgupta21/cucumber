package com.java.cucumber.steps;

import com.java.cucumber.exceptions.CardExpiredException;
import com.java.cucumber.exceptions.InvalidCardNumberException;
import com.java.cucumber.impl.Account;
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
    private Account account;

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
        card = new CreditCard(new Account(0));
    }

    @When("^I enter a card number that's only four (\\d+) digits long$")
    public void i_enter_a_card_number_that_s_only_digits_long(int number) throws Throwable {
        card.number(number);
        card.addExpiry("20200101");
    }

    @When("^all the other details are correct$")
    public void all_the_other_details_are_correct() throws Throwable {
        card.addName("John Doe");
    }

    @When("^I submit the form$")
    public void i_submit_the_form() throws Throwable {
        // Do Nothing
    }

    @When("^I enter a card expiry date that's in the past$")
    public void i_enter_a_card_expiry_date_that_s_in_the_past() throws Throwable {
        card.addExpiry("20000101");
        card.number(9999999977777777L);
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

    @Then("^card expiration error should be displayed$")
    public void card_expiration_error_should_be_displayed() throws Throwable {
        boolean flag = false;
        try {
            card.submit();
        } catch (CardExpiredException e) {
            flag = true;
        }
        Assert.assertTrue(flag);
    }

    @Given("^I have \\$(\\d+) in my account$")
    public void i_have_$_in_my_account(int amount) throws Throwable {
        account = new Account(amount);
    }

    @When("^I request \\$(\\d+)$")
    public void i_request_$(int amount) throws Throwable {
        account.withdraw(amount);
    }

    @Then("^\\$(\\d+) should be dispensed$")
    public void $_should_be_dispensed(int amount) throws Throwable {
        Assert.assertEquals(80, account.getBalance());
    }

    @Given("^my card is invalid$")
    public void my_card_is_invalid() throws Throwable {
        card = new CreditCard(account);
        card.addName("test");
        card.number(1234123412341234L);
        card.addExpiry("20001010");
    }

    @When("^I request \\$(\\d+) via invalid card$")
    public void i_request_$_via_card(int amount) throws Throwable {
        boolean flag = false;
        try {
            card.withdraw(amount);
        } catch (CardExpiredException e) {
            flag = true;
        }
        Assert.assertTrue(flag);

    }

    @Then("^I should get invalid card exception$")
    public void i_should_get_invalid_card_exception() throws Throwable {
        // Do Nothing
    }

    @Given("^my card is valid$")
    public void my_card_is_valid() throws Throwable {
        card = new CreditCard(account);
        card.addName("test");
        card.number(1234123412341234L);
        card.addExpiry("20201010");
    }

    @When("^I request \\$(\\d+) via valid card$")
    public void i_request_$_via_valid_card(int amount) throws Throwable {
        card.withdraw(amount);
    }

    @Then("^I should get \\$(\\d+) withdrawn$")
    public void i_should_get_$_withdrawn(int balance) throws Throwable {
        Assert.assertEquals(balance, card.getAccount().getBalance());
    }

}
