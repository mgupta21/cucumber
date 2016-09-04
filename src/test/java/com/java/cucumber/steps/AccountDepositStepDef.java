package com.java.cucumber.steps;

import cucumber.api.Transform;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import com.java.cucumber.builder.AccountBuilder;
import com.java.cucumber.convertor.MoneyConvertor;
import com.java.cucumber.impl.Account;
import com.java.cucumber.impl.Money;

/**
 * Created by mgupta on 9/4/16.
 */
public class AccountDepositStepDef {

	private Account testAccount;

	@Given("^I have deposited \"([^\"]*)\" in my account$")
	public void iHaveDeposited$InMyAccount(@Transform(MoneyConvertor.class) Money amount) throws Throwable {
		testAccount = new AccountBuilder().build();
		testAccount.deposit(amount);
	}

	@When("^I request \"([^\"]*)\"$")
	public void iRequest$(@Transform(MoneyConvertor.class) Money amount) throws Throwable {
		// TODO
	}

	@Then("\"([^\"]*)\" should be dispensed$")
	public void $ShouldBeDispensed(@Transform(MoneyConvertor.class) Money amount) throws Throwable {
		// TODO
	}
}
