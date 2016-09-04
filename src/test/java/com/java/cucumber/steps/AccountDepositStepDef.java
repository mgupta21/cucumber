package com.java.cucumber.steps;

import cucumber.api.Transform;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.testng.Assert;

import com.java.cucumber.impl.Money;
import com.java.cucumber.impl.Teller;
import com.java.cucumber.utils.KnowsMyDomain;
import com.java.cucumber.utils.MoneyConverter;

import static com.java.cucumber.TestConstants.DELTA;

/**
 * Created by mgupta on 9/4/16.
 */
public class AccountDepositStepDef {

    private final KnowsMyDomain helper;

    public AccountDepositStepDef(KnowsMyDomain helper) {
        this.helper = helper;
    }

    @Given("^I have deposited \"([^\"]*)\" in my account$")
    public void iHaveDeposited$InMyAccount(@Transform(MoneyConverter.class) Money amount) throws Throwable {
        helper.getTestAccount().deposit(amount);
    }

    @When("^I request \"([^\"]*)\"$")
    public void iRequest$(@Transform(MoneyConverter.class) Money amount) throws Throwable {
        Teller teller = new Teller(helper.getCashSlot());
        teller.withdrawFrom(helper.getTestAccount(), amount);
    }

    @Then("\"([^\"]*)\" should be dispensed$")
    public void $ShouldBeDispensed(@Transform(MoneyConverter.class) Money amount) throws Throwable {
        Assert.assertEquals(amount.getAmount(), helper.getCashSlot().contents(), DELTA);
    }
}
