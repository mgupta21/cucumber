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
public class AccountStepDef {

    private static Account testAccount;
    private int            initialBalance;

    @Given("^I have balance of \\$(\\d+) in my account$")
    public void i_have_$_in_my_account(int amount) throws Throwable {
        initialBalance = amount;
        testAccount = new AccountBuilder().withAccountBalance(amount).build();
    }

    @When("^I request \\$(\\d+)$")
    public void i_request_$(int amount) throws Throwable {
        testAccount.withdraw(amount);
    }

    @Then("^my new account balance should be \\$(\\d+)$")
    public void myNewAccountBalanceIs$(int balance) throws Throwable {
        Assert.assertEquals(balance, testAccount.getBalance());
    }

    @Then("^Insufficient balance warning is displayed$")
    public void iShouldGetInsufficientBalanceWarning() throws Throwable {
        // No-Opt. Warning logged
    }

    @And("^I should not be able to withdraw$")
    public void iShouldNotBeAbleToWithdraw() throws Throwable {
        Assert.assertEquals(initialBalance, testAccount.getBalance());
    }
}
