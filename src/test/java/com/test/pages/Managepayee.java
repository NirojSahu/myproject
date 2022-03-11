package com.test.pages;

import Utilities.WebKit.helper.BasePage;
import com.google.inject.Inject;
import com.test.Utils.App_genericFunction;
import com.test.Utils.SeleniumHelper;
//import com.test.helper.BasePage;
//import com.test.seleniumcustomframework.extension.PageElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Managepayee extends BasePage {

    @Inject
    SeleniumHelper selenium_helper;

   /* @FindBy(xpath = "//div[@name='NEW_BENE_NAME']")
    private PageElement managePayeePayeeName;*/
    String managePayeePayeeName = "xpath://div[@name='NEW_BENE_NAME']";
    /*@FindBy(xpath = "//div[@name='BENE_REF_NO']")
    private PageElement managePayeePayeeRefNum;*/
    String managePayeePayeeRefNum = "xpath://div[@name='BENE_REF_NO']";
    /*@FindBy(xpath = "//div[@name='SORT_CODE_1']")
    private PageElement managePayeeSortCode1;*/
    String managePayeeSortCode1 = "xpath://div[@name='SORT_CODE_1']";
    /*@FindBy(xpath = "//div[@name='SORT_CODE_2']")
    private PageElement managePayeeSortCode2;*/
    String managePayeeSortCode2 = "xpath://div[@name='SORT_CODE_2']";
    /*@FindBy(xpath = "//div[@name='SORT_CODE_3']")
    private PageElement managePayeeSortCode3;*/
    String managePayeeSortCode3 = "xpath://div[@name='SORT_CODE_3']";
    /*@FindBy(xpath = "//div[@name='NEW_BENE_BANKNAME_Y']")
    private PageElement managePayeeBankName;*/
    String managePayeeBankName = "xpath://div[@name='NEW_BENE_BANKNAME_Y']";
    /*@FindBy(xpath = "//div[@name='NEW_BENE_ACC_NO']")
    private PageElement managePayeePayeeAccountNum;*/
    String managePayeePayeeAccountNum = "xpath://div[@name='NEW_BENE_ACC_NO']";

    public Map<String, String> getAmendManagePayeeData(String accountNo ) throws InterruptedException {
        Thread.sleep(1000);
        String beneAccountNo ="xpath://div[@class='x-grid3-cell-inner x-grid3-col-BENE_ACC_NO']";
        String edit="xpath://div[@class='x-grid3-cell-inner x-grid3-col-ACCOUNT_NO_ID']";
        String amend="xpath://div[text()='Amend payee']";

        List<WebElement> beneAccountNoWE = selenium_helper.getElementList(beneAccountNo);
        List<WebElement> editWE = selenium_helper.getElementList(edit);
        List<WebElement> amendWE = selenium_helper.getElementList(amend);
        Map<String,String> accountCompleteDetails=new LinkedHashMap<String,String>();
        App_genericFunction.putScreenshotInStep();
        for(int i=0;i<beneAccountNoWE.size();i++){
            String  beneRefNoVal=beneAccountNoWE.get(i).getText();
            if(accountNo.equals(beneRefNoVal)){
                selenium_helper.enterByKeyboardPageDown();
                Thread.sleep(1000);
                editWE.get(i).click();
                Thread.sleep(1000);
                amendWE.get(i).click();
                Thread.sleep(2000);
                accountCompleteDetails.put("Payee Name",selenium_helper.getElement(managePayeePayeeName).getText());
                Thread.sleep(1000);
                accountCompleteDetails.put("Payee Reference Number",selenium_helper.getElement(managePayeePayeeRefNum).getText());
                String SortCode=selenium_helper.getElement(managePayeeSortCode1).getText()+"-"+selenium_helper.getElement(managePayeeSortCode2).getText()+"-"+selenium_helper.getElement(managePayeeSortCode3).getText();
                accountCompleteDetails.put("Sort Code",SortCode);
                accountCompleteDetails.put("BankName", selenium_helper.getElement(managePayeeBankName).getText());
//                selenium_helper.enterByKeyboardPageDown();
                accountCompleteDetails.put("Account",selenium_helper.getElement(managePayeePayeeAccountNum).getText());
                selenium_helper.enter("xpath://*[contains(text(),'Back')]");
                break;
            }
        }
        return accountCompleteDetails;
    }
}
