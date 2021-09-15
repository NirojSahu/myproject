//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.test.hooksnoteardown;

import com.google.inject.Inject;
import com.test.configuration.Configuration;
import com.test.exceptions.StopTestException;
import com.test.helper.EnvironmentConstants;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import java.net.MalformedURLException;
import org.openqa.selenium.WebDriver;

public class BrowserHooks {
    @Inject
    public WebDriver driver;

    public BrowserHooks() {
    }

    @Before
    public void setup(Scenario scenario) throws StopTestException, MalformedURLException {
        String browser = Configuration.getConfiguration().getSafeBrowser();
    }

    @Before
    public void getScenario(Scenario scenario) {
        String scenarioId = scenario.getName();
        System.out.println("\n Scenario Name => " + scenarioId + "\n");
        String[] parts = scenarioId.split(":");
        EnvironmentConstants.tagId = parts[0];
    }
}
