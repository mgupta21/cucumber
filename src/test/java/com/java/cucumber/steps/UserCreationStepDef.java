package com.java.cucumber.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import org.junit.Assert;

import com.java.cucumber.impl.User;

/**
 * Created by mgupta on 9/3/16.
 */
public class UserCreationStepDef {

    private static final String TEST_USERNAME = "test";
    private User                user;

    @Given("^I try to create an account with password \"([^\"]*)\"$")
    public void iTryToCreateAnAccountWithPassword(String password) throws Throwable {
        user = User.create(TEST_USERNAME, password);
    }

    @Then("^I should see that the password is valid$")
    public void iShouldSeeThatThePasswordIsValid() throws Throwable {
        Assert.assertNotNull(user);
    }

    @Then("^I should see that the password is invalid$")
    public void iShouldSeeThatThePasswordIsInValid() throws Throwable {
        Assert.assertNull(user);
    }

}
