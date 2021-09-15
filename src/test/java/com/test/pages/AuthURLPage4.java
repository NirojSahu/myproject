package com.test.pages;

import com.google.inject.Inject;
import com.test.CustomHooks.GlobalHooks;
import com.test.Utils.App_genericFunction;
import com.test.Utils.SeleniumHelper;
import com.test.configuration.Configuration;
import com.test.helper.BasePage;
import com.test.seleniumcustomframework.extension.PageElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class AuthURLPage4 extends BasePage {

    @Inject
    SeleniumHelper selenium_helper;

    @FindBy(xpath = "//*[@id='pac']")
    private PageElement pac_code;

    @FindBy(xpath = "//*[@id='otp']")
    private PageElement otp_code;

    @FindBy(xpath = "//*[@id='otpContinue']")
    private PageElement otp_continue;

    public  String submissions() {
        String authcode;
        try{
               pac_code.sendKeys(GlobalHooks.values.get(0).get("web_pac_no"));
               otp_code.sendKeys(GlobalHooks.values.get(0).get("web_otp-code"));
                selenium_helper.scrollDownUsingJavascript();
            App_genericFunction.putScreenshotInStep();
               Thread.sleep(5000);
            otp_continue.click();
            //selenium_helper.waitForJSandJQueryToLoad();
            //waitForPageLoaded();
            if (Configuration.getConfiguration().getProperty("browser").contentEquals("internet explorer"))
            {
             //   Thread.sleep(15000);
            }
            WebDriverWait w = new WebDriverWait(driver,20);
            w.until(ExpectedConditions.urlContains(GlobalHooks.values.get(0).get("redirect_uri")));
            //driver.findElement(By.xpath("//img[@alt='Google']")).click();
            String temp = driver.getCurrentUrl();
            App_genericFunction.putcommentinStep("Redirect URL received : " +temp );
           // String temp ="https://www.google.com/#code=eaef7eec-3f4d-32af-b2fd-dc38fca49324&id_token=eyJ4NXQiOiJZMkkwWXpWbVl6ZzJZMlppTnpabE1qZ3haalkxTkRNNU5qUTFPVFZpTVdRMU1HUTVabUk0WkEiLCJraWQiOiJZMkkwWXpWbVl6ZzJZMlppTnpabE1qZ3haalkxTkRNNU5qUTFPVFZpTVdRMU1HUTVabUk0WkFfUlMyNTYiLCJhbGciOiJSUzI1NiJ9.eyJhdF9oYXNoIjoiOXcyckNPWkhGWUllUUxMblhEQWl0QSIsInN1YiI6IjcwMDAxNzcwMUBjYXJib24uc3VwZXIiLCJhbXIiOltdLCJpc3MiOiJodHRwczpcL1wvMTAuNi4xODQuMjY6ODI0M1wvdG9rZW4iLCJub25jZSI6Im4tMFM2X1d6QTJNIiwic2lkIjoiZWViMmM1OTgtOTNiZi00ZGI5LWJjMzMtZTI3NzcwNjRkZWM0IiwiYXVkIjpbIlhqcU9vd1Iwa1haZV9pWXFHV1ZmVmxRQzllc2EiLCJodHRwOlwvXC9vcmcud3NvMi5hcGltZ3RcL2dhdGV3YXkiXSwiY19oYXNoIjoidDNHdDJpTnl1cHBMYmhYVG1qN2VVZyIsIm9wZW5iYW5raW5nX2ludGVudF9pZCI6IjE1MmU0MzZmLTExOTQtNDc3NS1hYmNjLWVjYTFhNDZmNWEzYiIsImF6cCI6IlhqcU9vd1Iwa1haZV9pWXFHV1ZmVmxRQzllc2EiLCJzY29wZSI6WyJPQl8xNTJlNDM2Zi0xMTk0LTQ3NzUtYWJjYy1lY2ExYTQ2ZjVhM2IiLCJUSU1FXzE1OTA1ODE4Mjg0ODciLCJhY2NvdW50cyIsIm9wZW5pZCJdLCJleHAiOjE1OTA1ODU0MjgsImlhdCI6MTU5MDU4MTgyOH0.EIZyHh7pkocUiDirXt_bjd-QPob9NuQgWoAMrHZqyi13rHgHHlYxo2YfvRpk2ej9lsKzjj2KHqTdmLDYsF8aWE7HcVXYMuYC99ZBIJIHaXv7wyKO7SWt7O5AeGEDDwi6wsn9y4Fce7Z34ZMLH2uEWGyDo4VHSkMHlli7M3ofdXQPC7DQkuo09Us_c0nBZEgto0gBreW9ZmoV9y74Cu9Dug82zVv5Xe_aI_F2NwMYLIlimyE0bka0fM0K87NgJsxbioTiVMdp2Oj5oDx5Tj0oE6T4NuiO-9pfeunGMrd3JUH7kSMjiCMZ17MhfT89U3GKrGld2k8CuJQAvFBvaD9hgg&session_state=15284ed0bac7229ba878db2915f82195c61676e1bee3129c8723a945de80785c.hI8SURsr_7_8ESdC5oXW0A";
            if (temp.contains("null"))
            {
                Assert.fail("Authorizatio code came as null");
                authcode= null;
                return "Portal-Page5 connect Not-Successfull";

            }else {
                System.out.println("<------------Inside Else block------------->");
                authcode = temp.split("&")[0].split("code=")[1];
                if(GlobalHooks.scenario_type.contains("UJ"))
                {
                    App_genericFunction.WritePropertiesFile_feature("UJ_Auth_code",authcode);
                    System.out.println("UJ_Auth_code : " +authcode );
                }else
                    {
                        App_genericFunction.WritePropertiesFile_common("Auth_code",authcode);
                        System.out.println("Auth_code : " +authcode );
                    }
                App_genericFunction.putScreenshotInStep();
                //System.out.println("Page5-(REDIRECTED):"+driver.getCurrentUrl());
                System.out.println("Portal-Page4 connect successful");
                return "Portal-Page5 connect successfull";
            }
        }catch (Exception e)
        {
            Assert.fail("AuthCode not returned");
            e.printStackTrace();
            System.out.println("Portal-Page4 connect not successfull");
            return "Portal-Page4 connect not successfull";
        }
    }

}
