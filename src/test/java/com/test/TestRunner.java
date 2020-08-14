package com.test;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty","html:target/cucumber.html",
                "junit:target/cucumber.xml",
                "junit:target/cucumber-report.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdopter:"},
        tags = {""},
        glue = {"classpath:com/test/stepDefs","classpath:com/test/screenshothook","classpath:com/test/hooksnoteardown","classpath:com/test/CustomHooks"},
        junit = "--step-notifications",
        features = "classpath:features")

public class TestRunner {
}
