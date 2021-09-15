package com.test.pages;

import com.google.inject.Inject;
import com.test.CustomHooks.GlobalHooks;
import com.test.Utils.App_genericFunction;
import com.test.Utils.CommonFunctions;
import com.test.Utils.SeleniumHelper;
import com.test.helper.BasePage;
import com.test.seleniumcustomframework.extension.PageElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import scala.App;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AuthURLPage3 extends BasePage {
    @Inject
    SeleniumHelper selenium_helper;

    @Inject
    CommonFunctions com_func;

    //@FindBy(xpath = "//*[@id='accselect']//following-sibling::span")
    @FindBy(xpath = "//*[@class='accountTr']/div/div/label/input//following-sibling::span")
    private List<PageElement> accounts_checkbox;
    @FindBy(xpath = "//*[@class='accountTr']/div/div//following-sibling::p")
    private List<PageElement> accounts_detail;
    @FindBy(id = "approve")
    private PageElement btn_approve;
    @FindBy(xpath = "//*[@id='oauth2_authz_confirm']/div[1]/div/span")
    private PageElement validity_value;
    @FindBy(xpath = "//*[@id='oauth2_authz_confirm']/center/div/div/div/p[2]")
    private PageElement tpp_name;
    @FindBy(xpath = "//*[@id='oauth2_authz_confirm']/center/div/div/div/p[2]")
    private PageElement tpp_name_joint;




    /*String LoginButton = "xpath://*[@id='LOGIN']";
    String PACObjects ="xpath://*[@class='useridfield1']/div/div";
    String Password ="xpath://*[@class='useridfield']/input";*/

    public  String click_Acccounts_forConsent(List<String> AccountsInfosheet) {
        try{

            int count =0;
            List<String> last4digits = new ArrayList<>();
            AccountsInfosheet.forEach(account -> {
                last4digits.add(account.substring(4,8));
            });
            System.out.println("List with last 4 digit :" +last4digits);

            int size =AccountsInfosheet.size();
            System.out.println("Accounts to be selcted for Consent : "+size +  " Accounts :" +AccountsInfosheet);
            //if(size == 1)
            //{
                driver.manage().timeouts().implicitlyWait(5, TimeUnit.MILLISECONDS);
                int checkboxcount = accounts_checkbox.size();
                if (accounts_checkbox.size()  != 0 )
                {
                    for (int i = 0; i < accounts_checkbox.size(); i++) {
                        int co = accounts_detail.get(i).getText().length();
                        if (last4digits.contains(accounts_detail.get(i).getText().substring(co-4,co))) {
                            selenium_helper.scrollViewUsingJavascript(accounts_checkbox.get(i));
                            accounts_checkbox.get(i).click();
                            count++;
                        }
                    }
                }else {count=1;}
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
            //     }else
              /*  {
                    for (int i = 0; i < accounts_checkbox.size(); i++) {
                     int co = accounts_detail.get(i).getText().length();
                       if (last4digits.contains(accounts_detail.get(i).getText().substring(co-4,co))) {
                           selenium_helper.scrollViewUsingJavascript(accounts_checkbox.get(i));
                           accounts_checkbox.get(i).click();
                           count++;
                        }
                     }
                }
*/
            if(size == count)
            {
                System.out.println("-------Correct accounts selected-------");
            }
            else {
                Assert.fail("Please check sheet for correct Accounts mentioned");}

            if(count == 1 && checkboxcount == 0)
            {
                if(tpp_name.getText().contains("behalf"))
                {
                    System.out.println("In Single account Block");
                    System.out.println("tpp_name : " + tpp_name.getText().split("behalf of")[1].trim());
                    App_genericFunction.putcommentinStep("tpp_name : " + tpp_name.getText().split("behalf of")[1].trim());
                    Assert.assertEquals("Test Failed for TPP Value", GlobalHooks.values.get(0).get("tpp_name"),tpp_name.getText().split("behalf of")[1].trim());

                }else
                {
                    System.out.println("In Single account Block");
                    System.out.println("tpp_name : " + tpp_name.getText().split("with")[1].trim());
                    App_genericFunction.putcommentinStep("tpp_name : " + tpp_name.getText().split("with")[1].trim());
                    Assert.assertEquals("Test Failed for TPP Value", GlobalHooks.values.get(0).get("tpp_name"),tpp_name.getText().split("with")[1].trim());
                }
                if(GlobalHooks.scenario_type.contains("UJ"))
                {
                    System.out.println("validity_value_UJ : " + validity_value.getText().split("until")[1].trim().split(" ")[0]);
                    App_genericFunction.putcommentinStep("validity_value_UJ : " + validity_value.getText().split("until")[1].trim().split(" ")[0]);
                    Assert.assertEquals("Test Failed for Validity Value", com_func.convertedformat(App_genericFunction.ReadPropertiesFile_feature("UJ_Validity")),validity_value.getText().split("until")[1].trim().split(" ")[0]);
                }else
                {
                    System.out.println("validity_value : " + validity_value.getText().split("until")[1].trim().split(" ")[0]);
                    App_genericFunction.putcommentinStep("validity_value : " + validity_value.getText().split("until")[1].trim().split(" ")[0]);
                    Assert.assertEquals("Test Failed for Validity Value", com_func.convertedformat(App_genericFunction.ReadPropertiesFile_common("Validity")),validity_value.getText().split("until")[1].trim().split(" ")[0]);
                }
            }
            else {
                System.out.println("In Joint account Block");
                if(tpp_name_joint.getText().contains("behalf"))
                {
                    System.out.println("tpp_agent_name : " + tpp_name_joint.getText().split("on behalf")[0].trim());
                    App_genericFunction.putcommentinStep("tpp_agent_name : " + tpp_name_joint.getText().split("on behalf")[0].trim());
                    Assert.assertEquals("Test Failed for TPP Agent Value", GlobalHooks.values.get(0).get("tpp_agent_name"),tpp_name_joint.getText().split("on behalf")[0].trim());

                    System.out.println("tpp_name : " + tpp_name_joint.getText().split("will")[0].split("of")[1].trim());
                    App_genericFunction.putcommentinStep("tpp_name : " + tpp_name_joint.getText().split("will")[0].split("of")[1].trim());
                    Assert.assertEquals("Test Failed for TPP Value", GlobalHooks.values.get(0).get("tpp_name"),tpp_name_joint.getText().split("will")[0].split("of")[1].trim());
                }else
                    {
                        System.out.println("tpp_name : " + tpp_name_joint.getText().split("will")[0].trim());
                        App_genericFunction.putcommentinStep("tpp_name : " + tpp_name_joint.getText().split("will")[0].trim());
                        Assert.assertEquals("Test Failed for TPP Value", GlobalHooks.values.get(0).get("tpp_name"),tpp_name_joint.getText().split("will")[0].trim());
                    }

                if(GlobalHooks.scenario_type.contains("UJ"))
                {
                    System.out.println("validity_value_UJ : " + tpp_name_joint.getText().split("until")[1].trim().split(" ")[0]);
                    App_genericFunction.putcommentinStep("validity_value_UJ : " + tpp_name_joint.getText().split("until")[1].trim().split(" ")[0]);
                    Assert.assertEquals("Test Failed for Validity Value", com_func.convertedformat(App_genericFunction.ReadPropertiesFile_feature("UJ_Validity")),tpp_name_joint.getText().split("until")[1].trim().split(" ")[0]);
                }else
                {
                    System.out.println("validity_value : " + tpp_name_joint.getText().split("until")[1].trim().split(" ")[0]);
                    App_genericFunction.putcommentinStep("validity_value : " + tpp_name_joint.getText().split("until")[1].trim().split(" ")[0]);
                    Assert.assertEquals("Test Failed for Validity Value", com_func.convertedformat(App_genericFunction.ReadPropertiesFile_common("Validity")),tpp_name_joint.getText().split("until")[1].trim().split(" ")[0]);
                }
            }

            selenium_helper.scrollDownUsingJavascript();
            App_genericFunction.putScreenshotInStep();
            btn_approve.click();
            //selenium_helper.enter(btn_approve);
            //waitForPageLoaded();
            //Thread.sleep(15000);
            selenium_helper.waitForJSandJQueryToLoad();
            System.out.println("Page4:"+driver.getCurrentUrl());
            System.out.println("Portal-Page3 connect Successful");
            return "Portal-Page3 connect successful";

        }catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Portal-Page3 connect Not Successfull");
            return "Portal-Page3 connect not successfull";
        }
    }

}
