package com.java.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

import org.junit.runner.RunWith;

/**
 * Created by mgupta on 7/25/16.
 */
@RunWith(Cucumber.class)
@CucumberOptions(format = {"pretty", "html:target/cucumber"}, features = "classpath:com/java/cucumber/")
public class TestsRunner {
}
