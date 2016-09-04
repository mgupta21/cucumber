package com.java.cucumber.steps;

import cucumber.api.Transform;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.junit.Assert;

import com.java.cucumber.builder.AccountBuilder;
import com.java.cucumber.convertor.MoneyConvertor;
import com.java.cucumber.impl.Account;
import com.java.cucumber.impl.Money;

import static com.java.cucumber.TestConstants.DELTA;

/**
 * Created by mgupta on 9/2/16.
 */
public class AccountWithdrawalStepDef {

    private static Account testAccount;
    private double         amountWithdrew;

    @Given("^I have \"([^\"]*)\" in my account$")
    public void iHaveBalanceInMyAccount(@Transform(MoneyConvertor.class) Money amount) throws Throwable {
        testAccount = new AccountBuilder().withAccountBalance(amount).build();
    }

    @When("^I choose to withdraw the fixed amount of \"([^\"]*)\"$")
    public void iChooseToWithdrawTheFixedAmountOfWithdrawal(@Transform(MoneyConvertor.class) Money amount) throws Throwable {
        amountWithdrew = testAccount.withdraw(amount);
    }

    @Then("^I should receive \"([^\"]*)\" cash$")
    public void iShouldReceiveReceivedCash(@Transform(MoneyConvertor.class) Money cash) throws Throwable {

        Assert.assertEquals(cash.getAmount(), amountWithdrew, DELTA);
    }

    @Then("^I should see an error message$")
    public void i_should_see_error_an_message() throws Throwable {
        // No-Opt
    }

    @And("^the balance of my account should be \"([^\"]*)\"$")
    public void theBalanceOfMyAccountShouldBeRemaining(@Transform(MoneyConvertor.class) Money balance) throws Throwable {
        Assert.assertEquals(balance, testAccount.getBalance());
    }
}
