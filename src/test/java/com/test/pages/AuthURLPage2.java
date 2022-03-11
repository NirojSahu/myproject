package com.test.pages;

import Utilities.WebKit.configuration.Configuration;
import Utilities.WebKit.helper.BasePage;
import Utilities.seleniumcustomframework.extension.PageElement;
import com.google.inject.Inject;
import com.test.Utils.App_genericFunction;
import com.test.Utils.SeleniumHelper;
//import com.test.configuration.Configuration;
//import com.test.helper.BasePage;
//import com.test.helper.NavigationHelper;
//import com.test.seleniumcustomframework.extension.PageElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.LinkedList;
import java.util.List;

public class AuthURLPage2 extends BasePage {
    @Inject
    SeleniumHelper selenium_helper;


    @FindBy(xpath = "//*[@id='LOGIN']")
    private PageElement LoginButton;
    @FindBy(xpath = "//*[@class='useridfield1']/div/div")
    private List<PageElement> PACObjects;
    @FindBy(xpath = "//*[@class='useridfield']/input")
    private PageElement Password;

    /*String LoginButton = "xpath://*[@id='LOGIN']";
    String PACObjects ="xpath://*[@class='useridfield1']/div/div";
    String Password ="xpath://*[@class='useridfield']/input";*/

    public  String provide_pac_password(String Pac_code,String Password) {
        try{

            List<String> list_c = new LinkedList<>();
            String PAC_values = Pac_code;
            char[] PAC_code = PAC_values.toCharArray();
            for (char c : PAC_code) {
                list_c.add(Character.toString(c));
            }

            int size =PACObjects.size();
            System.out.println("Size of PACObjects : "+size );
            for (int i = 1; i < 7; i++) {
                if (PACObjects.get(i).getAttribute("class").contentEquals("box")) {
                    Thread.sleep(1000);
                    //selenium_helper.enter("xpath://*[@class='useridfield1']/div/div[" + (i + 1) + "]/input");
                    PACObjects.get(i).findElement(By.tagName("input")).click();
                    //selenium_helper.sendkeysenterText("xpath://*[@class='useridfield1']/div/div[" + (i + 1) + "]/input", list_c.get(i-1).toString());
                    PACObjects.get(i).findElement(By.tagName("input")).sendKeys(list_c.get(i-1));
                }
            }
            this.Password.sendKeys(Password);
            LoginButton.click();
            if (Configuration.getConfiguration().getProperty("browser").contentEquals("internet explorer"))
            {
                selenium_helper.waitForJSandJQueryToLoad();
                driver.findElement(By.xpath("//a[text()='More information']")).click();
                driver.navigate().to("javascript:document.getElementById('overridelink').click()");
                Thread.sleep(4000);
            //    driver.findElement(By.xpath("//a[text()='More information']")).click();
            //    driver.navigate().to("javascript:document.getElementById('overridelink').click()");
            }
            App_genericFunction.putScreenshotInStep();


            /*List<WebElement> PACObject  = selenium_helper.getElementList(PACObjects);
            int size =selenium_helper.getElementList(PACObjects).size();
            System.out.println("Size of PACObjects : "+size );
            for (int i = 1; i < 7; i++) {
                if (PACObject.get(i).getAttribute("class").contentEquals("box")) {
                    PACObject.get(i).findElement(By.tagName("input")).click();
                    PACObject.get(i).findElement(By.tagName("input")).sendKeys(list_c.get(i-1));
                }
            }
            selenium_helper.enterText(this.Password,Password);
            selenium_helper.enter(LoginButton);*/
            //waitForPageLoaded();
            System.out.println("Page3:"+driver.getCurrentUrl());
            System.out.println("Portal-Page2 connect Successful");
            return "Portal-Page2 connect successful";

        }catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Portal-Page2 connect Not Successfull");
            return "Portal-Page2 connect not successfull";
        }
    }

}
