package com.java.cucumber.steps;

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

    @Given("^I have balance of \\$(\\d+) in my account$")
    public void i_have_$_in_my_account(int amount) throws Throwable {
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
}
