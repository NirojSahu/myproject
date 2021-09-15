package com.test.pages;

import com.google.inject.Inject;
import com.test.helper.NavigationHelper;
import com.test.seleniumcustomframework.extension.PageElement;
import com.test.Utils.CaptureScreenshotUtils;
import com.test.Utils.SeleniumHelper;
import org.openqa.selenium.support.FindBy;

public class SceenshotCapturePage {

    @Inject
    NavigationHelper navigationHelper;
    @Inject
    CaptureScreenshotUtils screnshotUtils;
    @Inject
    SeleniumHelper seleniumHelper;
    String LdapButton = "xpath:/html/body/div/div/div[2]/a[1]";
    String userIDLocator = "id:inputUsername";
    String passwordLocator = "id:inputPassword";
    String loginButtonLocator= "xpath://button[contains(.,'Log In')]";
    String featuresLinkLocator = "partiallink:Features";
    String tagsLinkLocator = "partiallink:Tags";
    String failuresLinkLocator = "partiallink:Failures";
    String clickNext="xpath://a/i[@class='fa fa-chevron-right']";
    // String stepChart="id:steps-chart";

    @FindBy(xpath = "//div[@class='item active']/canvas[@id='steps-chart']")
    private PageElement stepChart;

    @FindBy(xpath = "//div[@id='featureChartCarousel']//li[@data-target='#featureChartCarousel']")
    private PageElement carhouse;

    String mouseOver = "xpath://canvas[@id='steps-chart']";
    String url_part2 = "/cucumber-html-reports/overview-features.html";

    String JENKINS_USERNAME = "s0811959";
    String JENKINS_PASSWORD = "r64jKe9P3u4H";

    public void hitCustomisedURL() {
//       String url_to_hit = "https://jenkins-u1-automationqatesting-dev.appls.cap2.paas.gsnetcloud.corp/job/ITC_Space/job/Pipeline_HealthCheck_Retail_BDP/65"+url_part2;
      //  String url_to_hit ="https://jenkins-automationqatesting-dev.appls-ukdev01a.paas.santanderuk.dev.corp/job/CAOpenBanking/job/CAOB_Autoscript_3.1_Job1/71/"+url_part2;
        String url_to_hit =System.getProperty("Host")+System.getProperty("BuildNo")+url_part2;
        System.out.println("Jenkins_URL : "+url_to_hit);
        navigationHelper.navigateTo(url_to_hit);
        //screnshotUtils.takeScreenShot();
        seleniumHelper.sleepms(5000);


    }

    @Deprecated
    public boolean isValidPageDisplayed() {
        seleniumHelper.enter(LdapButton);
        seleniumHelper.waitTillClickableElementExist(loginButtonLocator);
        return seleniumHelper.isClickableElementExisting(loginButtonLocator);
    }

    public void submitValidCredentials()  {

        seleniumHelper.enterText(userIDLocator,JENKINS_USERNAME);
        seleniumHelper.enterText(passwordLocator,JENKINS_PASSWORD);
        //screnshotUtils.takeScreenShot();
        seleniumHelper.enter(loginButtonLocator);
        //screnshotUtils.takeScreenShot();
        seleniumHelper.sleepms(5000);

    }

    @Deprecated
    public boolean isValidPageDisplayedDashboards() {
        seleniumHelper.waitTillClickableElementExist(featuresLinkLocator);
        //screnshotUtils.takeScreenShot();
        seleniumHelper.sleepms(5000);
        return seleniumHelper.isClickableElementExisting(featuresLinkLocator);
    }

    public void takeReportScreenshots() throws InterruptedException {
        getOverviewReportScreenshot();
        getTagsReportScreenshot();
        // getFailureReportScreenshot();

    }
    @Deprecated
    private void getOverviewReportScreenshot() throws InterruptedException {
        //Thread.sleep(5000);
        seleniumHelper.waitTillClickableElementExist(clickNext);
        seleniumHelper.enter(clickNext);
        seleniumHelper.sleepms(1000);
        seleniumHelper.waitTillClickableElementExist(clickNext);
        seleniumHelper.enter(clickNext);
        seleniumHelper.sleepms(1000);
        //seleniumHelper.waitTillClickableElementExist(stepChart);
        // seleniumHelper.enter(carhouse);
        //seleniumHelper.enter(mouseOver);
        // seleniumHelper.sleepms(1000);
        //seleniumHelper.waitforElementVisible(stepChart);
        // //screnshotUtils.takeScreenShot();
        seleniumHelper.scrollDownUsingJavascript();
        screnshotUtils.takeScreenShot("FeatureReport");

        //seleniumHelper.sleepms(3000);
        // screnshotUtils.takeScreenShot("FeatureReport");
        seleniumHelper.sleepms(5000);
    }
    @Deprecated
    private void getTagsReportScreenshot() {
        seleniumHelper.waitTillClickableElementExist(tagsLinkLocator);
        seleniumHelper.enter(tagsLinkLocator);
        //screnshotUtils.takeScreenShot();
        seleniumHelper.sleepms(3000);
        seleniumHelper.scrollDownUsingJavascript();
        screnshotUtils.takeScreenShot("TagsReport");
        seleniumHelper.sleepms(3000);
        //FileUtils.copyFile();
        //FileUtils.de

    }
}
