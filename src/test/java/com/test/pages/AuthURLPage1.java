package com.test.pages;

import com.google.inject.Inject;
import com.test.CustomHooks.GlobalHooks;
import com.test.Utils.App_genericFunction;
import com.test.Utils.SeleniumHelper;
import com.test.helper.BasePage;
import com.test.helper.NavigationHelper;
import com.test.seleniumcustomframework.extension.PageElement;
import org.eclipse.jetty.server.Authentication;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.LinkedList;
import java.util.List;

public class AuthURLPage1 extends BasePage {
    @Inject
    SeleniumHelper selenium_helper;
    @Inject
    protected NavigationHelper navigationHelper;

    @FindBy(xpath = "//*[@id='username']")
    private PageElement Username;
    @FindBy(xpath = "//*[@id='LOGIN']")
    private PageElement LoginButton;

/*    String Username = "xpath://*[@id='username']";
    String LoginButton = "xpath://*[@id='LOGIN']";*/

    public  String provide_customerdetail(String Customer_no ) {
        try{
            App_genericFunction.putScreenshotInStep();
            Username.click();
            Username.sendKeys(Customer_no);
            LoginButton.click();

            /*selenium_helper.getElement(Username).sendKeys(Customer_no);
            selenium_helper.enter(LoginButton);*/
            App_genericFunction.putScreenshotInStep();
            System.out.println("Page2:"+driver.getCurrentUrl());
            System.out.println("Portal-Page1 connect Successful");
            return "Portal-Page1 connect successful";

        }catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Portal-Page1 connect Not Successfull");
            return "Portal-Page1 connect not successfull";
        }
    }

}
