package com.java.cucumber.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.junit.Assert;

import com.java.cucumber.builder.AccountBuilder;
import com.java.cucumber.impl.Account;

/**
 * Created by mgupta on 9/2/16.
 */
public class AccountWithdrawalStepDef {

    private static Account testAccount;
    private int            amountWithdrew;

    @Given("^I have \\$(\\d+) in my account$")
    public void iHaveBalanceInMyAccount(int amount) throws Throwable {
        testAccount = new AccountBuilder().withAccountBalance(amount).build();
    }

    @When("^I choose to withdraw the fixed amount of \\$(\\d+)$")
    public void iChooseToWithdrawTheFixedAmountOfWithdrawal(int amount) throws Throwable {
        amountWithdrew = testAccount.withdraw(amount);
    }

    @Then("^I should receive \\$(\\d+) cash$")
    public void iShouldReceiveReceivedCash(int cash) throws Throwable {
        Assert.assertEquals(cash, amountWithdrew);
    }

    @Then("^I should see an error message$")
    public void i_should_see_error_an_message() throws Throwable {
        // No-Opt
    }

    @And("^the balance of my account should be \\$(\\d+)$")
    public void theBalanceOfMyAccountShouldBeRemaining(int balance) throws Throwable {
        Assert.assertEquals(balance, testAccount.getBalance());
    }

}
