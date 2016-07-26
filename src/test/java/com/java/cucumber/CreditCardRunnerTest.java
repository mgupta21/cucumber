package com.java.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by mgupta on 7/25/16.
 */
@CucumberOptions(
        format = {"pretty", "html:target/cucumber"},
        features = "classpath:features/creditcard.feature",
        glue = {"com.java.cucumber.steps"}
)
@RunWith(Cucumber.class)
public class CreditCardRunnerTest {
}
