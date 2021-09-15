//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.test.screenshothook;

import com.google.inject.Inject;
import com.google.inject.Provider;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotHooks {
    @Inject
    Provider<WebDriver> driver;

    public ScreenshotHooks() {
    }

    @After
    public void takeScreenshot(Scenario scenario) throws Exception {
        try {
            scenario.embed((byte[])((TakesScreenshot)this.driver.get()).getScreenshotAs(OutputType.BYTES), "image/png");
        } catch (NoSuchSessionException var3) {
            System.out.println("No screenshot taken for Test => " + scenario.getName());
        }

    }
}
