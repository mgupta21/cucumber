package com.java.cucumber.steps;

import cucumber.api.java.Before;
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
import com.java.cucumber.impl.CreditCardMatcher;
import com.java.cucumber.impl.Store;

import static com.java.cucumber.TestConstants.TEST_EXPIRED_DATE;
import static com.java.cucumber.TestConstants.TEST_PIN_NUMBER;

/**
 * Created by mgupta on 7/25/16.
 */
public class CreditCardStepDef {

    private static Account TEST_ACCOUNT = new AccountBuilder().build();
    private Checkout       checkout     = new Checkout();
    private CreditCard     card;
    private boolean        isPinReset;

    private void testSetup() {
        Store.addItem("banana", 40);
        Store.addItem("apple", 25);
        checkout.addItemToCheckout(3, "banana");
        checkout.addItemToCheckout(2, "apple");
    }

    @Before("@CreditCard")
    public void init() {
        CreditCardMatcher.clear();
    }

    @Given("^I have choosen some items to buy$")
    public void i_have_choosen_some_items_to_buy() throws Throwable {
        testSetup();
    }

    @Given("^I have been issued a new card$")
    public void iHaveBeenIssuedANewCard() throws Throwable {
        card = new CreditCardBuilder().withCardPinNumber(TEST_PIN_NUMBER).build();
    }

    @When("^I enter a card number that's only four (\\d+) digits long$")
    public void i_enter_a_card_number_that_s_only_digits_long(int number) throws Throwable {
        card = new CreditCardBuilder().withCardNumber(number).withCardPinNumber(TEST_PIN_NUMBER).withAccount(TEST_ACCOUNT).build();
    }

    @When("^all the other details are correct$")
    public void all_the_other_details_are_correct() throws Throwable {
        // No-Opt
    }

    @When("^I submit the form$")
    public void i_submit_the_form() throws Throwable {
        // No-Opt
    }

    @When("^I enter a card expiry date that's in the past$")
    public void i_enter_a_card_expiry_date_that_s_in_the_past() throws Throwable {
        card = getExpiredCreditCard();
    }

    @When("^I choose \"([^\"]*)\" from the menu$")
    public void iChooseFromTheMenu(String arg0) throws Throwable {
        // No-Opt
    }

    @When("^I insert the card, entering the correct PIN$")
    public void iInsertTheCardEnteringTheCorrectPIN() throws Throwable {
        Assert.assertTrue(card.isPinValid(TEST_PIN_NUMBER));
    }

    @When("^I change the PIN to (\\d+)$")
    public void iChangeThePINTo(int pinNumber) throws Throwable {
        isPinReset = card.resetPin(TEST_PIN_NUMBER, pinNumber);
    }

    @When("^I try to change the PIN to the original PIN number$")
    public void iTryToChangeThePINToTheOriginalPINNumber() throws Throwable {
        isPinReset = card.resetPin(TEST_PIN_NUMBER, TEST_PIN_NUMBER);
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

    private CreditCard getExpiredCreditCard() {
        return new CreditCardBuilder().withAccount(TEST_ACCOUNT).withCardPinNumber(TEST_PIN_NUMBER).withExpirationDate(TEST_EXPIRED_DATE).build();
    }

    @Then("^the system should remember my PIN is now (\\d+)$")
    public void theSystemShouldRememberMyPINIsNow(int pinNumber) throws Throwable {
        Assert.assertFalse(card.isPinValid(TEST_PIN_NUMBER));
        Assert.assertTrue(isPinReset);
        Assert.assertTrue(card.isPinValid(pinNumber));
    }

    @Then("^I should see a warning message$")
    public void iShouldSeeAWarningMessage() throws Throwable {
        // No-opt
    }

    @Then("^the system should not have changed my PIN$")
    public void theSystemShouldNotHaveChangedMyPIN() throws Throwable {
        Assert.assertFalse(isPinReset);
    }

}
