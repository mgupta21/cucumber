package com.java.cucumber.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.junit.Assert;

import com.java.cucumber.builder.CreditCardBuilder;
import com.java.cucumber.impl.CreditCard;

/**
 * Created by mgupta on 9/1/16.
 */
public class CardPinSetupStepDef {

    private static final int ORIGINAL_PIN = 0000;
    private CreditCard       creditCard;

    @Given("^I have been issued a new card$")
    public void iHaveBeenIssuedANewCard() throws Throwable {
        this.creditCard = new CreditCardBuilder().withCardPinNumber(ORIGINAL_PIN).build();
    }

    @And("^I insert the card, entering the correct PIN$")
    public void iInsertTheCardEnteringTheCorrectPIN() throws Throwable {
        Assert.assertTrue(creditCard.isPinValid(ORIGINAL_PIN));
    }

    @When("^I choose \"([^\"]*)\" from the menu$")
    public void iChooseFromTheMenu(String arg0) throws Throwable {
        // No-Opt
    }

    @And("^I change the PIN to (\\d+)$")
    public void iChangeThePINTo(int pinNumber) throws Throwable {
        creditCard.resetPin(ORIGINAL_PIN, pinNumber);
    }

    @Then("^the system should remember my PIN is now (\\d+)$")
    public void theSystemShouldRememberMyPINIsNow(int pinNumber) throws Throwable {
        Assert.assertFalse(creditCard.isPinValid(ORIGINAL_PIN));
        Assert.assertTrue(creditCard.isPinValid(pinNumber));
    }
}
