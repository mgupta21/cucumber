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

    private static Account TEST_ACCOUNT;

    @Given("^I have balance of \\$(\\d+) in my account$")
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

}
