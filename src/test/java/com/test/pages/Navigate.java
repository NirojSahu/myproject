package com.test.pages;

import com.google.inject.Inject;
import com.test.LoadProperties;
import com.test.Utils.SeleniumHelper;
import com.test.configuration.Configuration;
import com.test.exceptions.StopTestException;
import com.test.helper.BasePage;
import com.test.helper.NavigationHelper;
import org.openqa.selenium.By;

public class Navigate extends BasePage {
    @Inject
    protected NavigationHelper navigationHelper;
    @Inject
    public SeleniumHelper seleniumHelper;

   // private final String urlLink ;

    public void toGoogleHomePage() {

        System.out.println("neo......."+LoadProperties.NEO_URL);
        driver.navigate().to(LoadProperties.NEO_URL);
    }
    public void toAuthURLHomePage(String AUTH_URL) {
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        System.out.println("Load......."+AUTH_URL);
        navigationHelper.navigateTo(AUTH_URL);
        try {

            if (Configuration.getConfiguration().getProperty("browser").contentEquals("internet explorer"))
            {
                seleniumHelper.waitForJSandJQueryToLoad();
                driver.findElement(By.xpath("//a[text()='More information']")).click();
                driver.navigate().to("javascript:document.getElementById('overridelink').click()");
                Thread.sleep(4000);
                seleniumHelper.waitForJSandJQueryToLoad();
                driver.findElement(By.xpath("//a[text()='More information']")).click();
                driver.navigate().to("javascript:document.getElementById('overridelink').click()");
            }
        } catch (StopTestException | InterruptedException e) {
            e.printStackTrace();
        }
        // driver.navigate().to(AUTH_URL);
        waitForPageLoaded();

    }
    public void toSanHomePage() {

        System.out.println("neo......."+LoadProperties.SAN_URL);
        driver.navigate().to(LoadProperties.SAN_URL);
    }
}
