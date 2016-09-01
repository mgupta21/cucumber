package com.java.cucumber.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.junit.Assert;

import com.java.cucumber.builder.AccountBuilder;
import com.java.cucumber.builder.CreditCardBuilder;
import com.java.cucumber.exceptions.CardExpiredException;
import com.java.cucumber.exceptions.InvalidCardNumberException;
import com.java.cucumber.impl.Account;
import com.java.cucumber.impl.Checkout;
import com.java.cucumber.impl.CreditCard;
import com.java.cucumber.impl.Store;

/**
 * Created by mgupta on 7/25/16.
 */
public class CreditCardStepDef {

    private static final int    PIN_NUMBER   = 1234;
    private static final String EXPIRED_DATE = "20001010";
    private static Account      TEST_ACCOUNT = new AccountBuilder().build();
    private Checkout            checkout     = new Checkout();
    private CreditCard          card;

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

    @When("^I enter a card number that's only four (\\d+) digits long$")
    public void i_enter_a_card_number_that_s_only_digits_long(int number) throws Throwable {
        card = new CreditCardBuilder().withCardNumber(number).withCardPinNumber(PIN_NUMBER).withAccount(TEST_ACCOUNT).build();
    }

    @When("^all the other details are correct$")
    public void all_the_other_details_are_correct() throws Throwable {
        // No-Opt
    }

    @When("^I submit the form$")
    public void i_submit_the_form() throws Throwable {
        // Do Nothing
    }

    @When("^I enter a card expiry date that's in the past$")
    public void i_enter_a_card_expiry_date_that_s_in_the_past() throws Throwable {
        card = getExpiredCreditCard();
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
        TEST_ACCOUNT = new AccountBuilder().withAccountBalance(amount).build();
    }

    @When("^I request \\$(\\d+)$")
    public void i_request_$(int amount) throws Throwable {
        TEST_ACCOUNT.withdraw(amount);
    }

    @Then("^\\$(\\d+) should be dispensed$")
    public void $_should_be_dispensed(int amount) throws Throwable {
        Assert.assertEquals(80, TEST_ACCOUNT.getBalance());
    }

    @Given("^my card is invalid$")
    public void my_card_is_invalid() throws Throwable {
        card = getExpiredCreditCard();
    }

    @When("^I request \\$(\\d+) via invalid card$")
    public void i_request_$_via_card(int amount) throws Throwable {
        boolean flag = false;
        try {
            card.withdraw(amount, PIN_NUMBER);
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
        card = new CreditCardBuilder().withAccount(TEST_ACCOUNT).withCardPinNumber(PIN_NUMBER).build();
    }

    private CreditCard getExpiredCreditCard() {
        return new CreditCardBuilder().withAccount(TEST_ACCOUNT).withCardPinNumber(PIN_NUMBER).withExpirationDate(EXPIRED_DATE).build();
    }

    @When("^I request \\$(\\d+) via valid card$")
    public void i_request_$_via_valid_card(int amount) throws Throwable {
        card.withdraw(amount, PIN_NUMBER);
    }

    @Then("^I should get \\$(\\d+) withdrawn$")
    public void i_should_get_$_withdrawn(int balance) throws Throwable {
        Assert.assertEquals(balance, card.getAccount().getBalance());
    }

}
