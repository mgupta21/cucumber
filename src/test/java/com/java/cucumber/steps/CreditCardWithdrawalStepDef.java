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
import com.java.cucumber.impl.Money;

import static com.java.cucumber.TestConstants.TEST_EXPIRED_DATE;
import static com.java.cucumber.TestConstants.TEST_PIN_NUMBER;

/**
 * Created by mgupta on 9/2/16.
 */
public class CreditCardWithdrawalStepDef {

    private CreditCard     cardA;
    private CreditCard     cardB;
    private static Account testAccount;

    @Before("@CreditCard.Withdraw")
    public void init() {
        CreditCardMatcher.clear();
    }

    @Given("^I have \\$(\\d+) in my bank account$")
    public void i_have_$_in_my_account(int amount) throws Throwable {
        testAccount = new AccountBuilder().withAccountBalance(amount).build();
    }

    @Given("^my card is valid$")
    public void my_card_is_valid() throws Throwable {
        cardA = new CreditCardBuilder().withAccount(testAccount).withCardPinNumber(TEST_PIN_NUMBER).build();
    }

    @Given("^my card is invalid$")
    public void my_card_is_invalid() throws Throwable {
        cardA = getExpiredCreditCard();
    }

    private CreditCard getExpiredCreditCard() {
        return new CreditCardBuilder().withAccount(testAccount).withCardPinNumber(TEST_PIN_NUMBER).withExpirationDate(TEST_EXPIRED_DATE).build();
    }

    @Given("^I have a card A \"([^\"]*)\" associated with my account$")
    public void iHaveACardAAssociatedWithMyAccount(String cardNumber) throws Throwable {
        cardA = new CreditCardBuilder().withAccount(testAccount).withCardNumber(Long.parseLong(cardNumber)).withCardPinNumber(TEST_PIN_NUMBER).build();
    }

    @Given("^I have a card B \"([^\"]*)\" associated with my account$")
    public void iHaveBCardAAssociatedWithMyAccount(String cardNumber) throws Throwable {
        cardB = new CreditCardBuilder().withAccount(testAccount).withCardNumber(Long.parseLong(cardNumber)).withCardPinNumber(TEST_PIN_NUMBER).build();
    }

    @When("^I request \\$(\\d+) via invalid card$")
    public void i_request_$_via_card(Money amount) throws Throwable {
        boolean flag = false;
        try {
            withdrawFromCard(cardA, amount, TEST_PIN_NUMBER);
        } catch (CardExpiredException e) {
            flag = true;
        }
        Assert.assertTrue(flag);
    }

    private void withdrawFromCard(CreditCard card, Money amount, int cardPinNumber) {
        card.withdraw(amount, cardPinNumber);
    }

    @When("^I request \\$(\\d+) via valid card$")
    public void i_request_$_via_valid_card(Money amount) throws Throwable {
        withdrawFromCard(cardA, amount, TEST_PIN_NUMBER);
    }

    @When("^I request \\$(\\d+) via card A$")
    public void iRequest$ViaCardA(Money amount) throws Throwable {
        withdrawFromCard(cardA, amount, TEST_PIN_NUMBER);

    }

    @When("^I request \\$(\\d+) via card B$")
    public void iRequest$ViaCardB(Money amount) throws Throwable {
        withdrawFromCard(cardB, amount, TEST_PIN_NUMBER);
    }

    @Then("^I should get invalid card exception$")
    public void i_should_get_invalid_card_exception() throws Throwable {
        // Do Nothing
    }

    @Then("^I should get \\$(\\d+) withdrawn$")
    public void i_should_get_$_withdrawn(int balance) throws Throwable {
        Assert.assertEquals(balance, cardA.getAccount().getBalance());
    }

    @Then("^I should have \\$(\\d+) in my account$")
    public void iShouldHave$InMyAccount(int balance) throws Throwable {
        Assert.assertEquals(balance, testAccount.getBalance());
    }

    @Then("^Insufficient account balance warning is displayed$")
    public void insufficientAccountBalanceWarningIsDisplayed() throws Throwable {
        // No-Opt. Warning logged.
    }

}
