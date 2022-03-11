package com.test.stepDefs;

import Utilities.BasePage;
import com.google.inject.Inject;
//import com.test.helper.BasePage;
import com.test.pages.SceenshotCapturePage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.junit.Assert;

public class ScreenshotCapture_Steps extends BasePage {

    @Inject
    SceenshotCapturePage sceenshotCapturePage;

    @When("^Login to Jenkins Customized Job")
    public void hitJenkinsUrl(){
        sceenshotCapturePage.hitCustomisedURL();
        sceenshotCapturePage.isValidPageDisplayed();
        sceenshotCapturePage.submitValidCredentials();
        Assert.assertTrue(sceenshotCapturePage.isValidPageDisplayedDashboards());
    }

    @Given("^capture report Screenshots")
    public void capture_Report_Screenshot() throws Throwable {
        sceenshotCapturePage.takeReportScreenshots();
    }
}
