package com.java.cucumber.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import com.java.cucumber.builder.AccountBuilder;
import com.java.cucumber.impl.Account;
import com.java.cucumber.impl.Money;

/**
 * Created by mgupta on 9/4/16.
 */
public class AccountDepositStepDef {

    private Account testAccount;

    @Given("^I have deposited \\$(\\d+) in my account$")
    public void iHaveDeposited$InMyAccount(Money amount) throws Throwable {
        testAccount = new AccountBuilder().build();
        testAccount.deposit(amount);
    }

    @When("^I request \\$(\\d+)$")
    public void iRequest$(int arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^\\$(\\d+) should be dispensed$")
    public void $ShouldBeDispensed(int arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
