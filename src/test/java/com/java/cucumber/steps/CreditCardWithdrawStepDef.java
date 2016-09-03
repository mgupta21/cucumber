package com.java.cucumber.steps;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.junit.Assert;

import com.java.cucumber.builder.AccountBuilder;
import com.java.cucumber.builder.CreditCardBuilder;
import com.java.cucumber.exceptions.CardExpiredException;
import com.java.cucumber.impl.Account;
import com.java.cucumber.impl.CreditCard;
import com.java.cucumber.impl.CreditCardMatcher;

import static com.java.cucumber.TestConstants.TEST_EXPIRED_DATE;
import static com.java.cucumber.TestConstants.TEST_PIN_NUMBER;

/**
 * Created by mgupta on 9/2/16.
 */
public class CreditCardWithdrawStepDef {

    private CreditCard     card;
    private static Account testAccount;

    @Before("@CreditCard.Withdraw")
    public void init() {
        CreditCardMatcher.clear();
    }

    @Given("^I have \\$(\\d+) in my account$")
    public void i_have_$_in_my_account(int amount) throws Throwable {
        testAccount = new AccountBuilder().withAccountBalance(amount).build();
    }

    @Given("^my card is valid$")
    public void my_card_is_valid() throws Throwable {
        card = new CreditCardBuilder().withAccount(testAccount).withCardPinNumber(TEST_PIN_NUMBER).build();
    }

    @Given("^my card is invalid$")
    public void my_card_is_invalid() throws Throwable {
        card = getExpiredCreditCard();
    }

    private CreditCard getExpiredCreditCard() {
        return new CreditCardBuilder().withAccount(testAccount).withCardPinNumber(TEST_PIN_NUMBER).withExpirationDate(TEST_EXPIRED_DATE).build();
    }

    @When("^I request \\$(\\d+) via invalid card$")
    public void i_request_$_via_card(int amount) throws Throwable {
        boolean flag = false;
        try {
            card.withdraw(amount, TEST_PIN_NUMBER);
        } catch (CardExpiredException e) {
            flag = true;
        }
        Assert.assertTrue(flag);
    }

    @When("^I request \\$(\\d+) via valid card$")
    public void i_request_$_via_valid_card(int amount) throws Throwable {
        card.withdraw(amount, TEST_PIN_NUMBER);
    }

    @Then("^I should get invalid card exception$")
    public void i_should_get_invalid_card_exception() throws Throwable {
        // Do Nothing
    }

    @Then("^I should get \\$(\\d+) withdrawn$")
    public void i_should_get_$_withdrawn(int balance) throws Throwable {
        Assert.assertEquals(balance, card.getAccount().getBalance());
    }

}
