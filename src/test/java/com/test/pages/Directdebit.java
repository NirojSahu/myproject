package com.test.pages;

import com.google.inject.Inject;
import com.test.Utils.CommonFunctions;
import com.test.Utils.SeleniumHelper;
//import com.test.seleniumcustomframework.extension.PageElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Directdebit {

    @Inject
    SeleniumHelper selenium_helper;

    /*@FindBy(xpath = "//div[@name='DDM_DEBIT_ACC_NO']")
    private PageElement debitAccount;*/
    String debitAccount = "xpath://div[@name='DDM_DEBIT_ACC_NO']";
    /*@FindBy(xpath = "//div[@name='DDM_DIRECT_DEBIT_NO']")
    private PageElement directDebitNumber;*/
    String directDebitNumber = "xpath://div[@name='DDM_DIRECT_DEBIT_NO']";
    /*@FindBy(xpath = "//div[@name='DDM_BENE_NAME']")
    private PageElement payeeName;*/
    String payeeName = "xpath://div[@name='DDM_BENE_NAME']";
    /*@FindBy(xpath = "//div[@name='DDM_REF_NO']")
    private PageElement orgIdentificationNumber;*/
    String orgIdentificationNumber = "xpath://div[@name='DDM_REF_NO']";
    /*@FindBy(xpath = "//div[@name='PACSIM_REF_NO']")
    private PageElement referenceNumber;*/
    String referenceNumber = "xpath://div[@name='PACSIM_REF_NO']";
    /*@FindBy(xpath = "//div[@name='DDM_START_DT']")
    private PageElement startDate;*/
    String startDate = "xpath://div[@name='DDM_START_DT']";
    /*@FindBy(xpath = "//div[@name='DDM_LAST_PYMT_DT']")
    private PageElement lastPayment;*/
    String lastPayment = "xpath://div[@name='DDM_LAST_PYMT_DT']";
    /*@FindBy(xpath = "//input[@name='DDM_LAST_PYMT_AMT']")
    private PageElement lastAmount;*/
    String lastAmount = "xpath://input[@name='DDM_LAST_PYMT_AMT']";
   /* @FindBy(xpath = "//span[@class='linkedCCy-PRE DDM_LAST_PYMT_AMT-CCy amountVmode']")
    private PageElement poundSymbol;*/
    String poundSymbol = "xpath://span[@class='linkedCCy-PRE DDM_LAST_PYMT_AMT-CCy amountVmode']";

    //----------------DIrect Debit------------
    String directDebitTable="xpath://table[@class='x-grid3-row-table']";
    String originatorIdentificationNumber ="xpath://div[@class='x-grid3-cell-inner x-grid3-col-DDM_REF_NO']";
    String payeeNameDD="xpath://div[@class='x-grid3-cell-inner x-grid3-col-DDM_BENEFICIARY_NAME']";
    String lastPaymentDD="xpath://div[@class='x-grid3-col x-grid3-cell x-grid3-td-DDM_LAST_PYMT_DT']";
    String lastAmountDD="xpath://div[@class='x-grid3-cell-inner x-grid3-col-DDM_LAST_AMOUNT']";
    String statusDD="xpath://div[@class='x-grid3-cell-inner x-grid3-col-DDM_STATUS']";
    String edit="xpath://div[@class='x-grid3-cell-inner x-grid3-col-DDM_ACTION_DROPDOWN']";

    public Map<String, String> getViewDirectDebitData(String ReqPayeeName, String Amount, String status ) throws InterruptedException {
        String originatorIdentificationNumber ="xpath://div[@class='x-grid3-cell-inner x-grid3-col-DDM_REF_NO']";
        String edit="xpath://div[@class='x-grid3-cell-inner x-grid3-col-DDM_ACTION_DROPDOWN']";
        String view="xpath://div[@class='x-grid3-cell-inner x-grid3-col-DDM_ACTION_DROPDOWN']/div/div[2]/div[text()='View']";
        selenium_helper.getdriver().manage().timeouts().implicitlyWait(5, TimeUnit.MILLISECONDS);
        Map<String,String> accountCompleteDetails=new LinkedHashMap<String,String>();

        if(selenium_helper.getdriver().findElements(By.xpath("//*[text() ='Sorry, there is no data to act upon.']")).size() != 0)
        {
            accountCompleteDetails.put("KEY", "EMPTY");
        }else
            {
            selenium_helper.getdriver().manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
            List<WebElement> payeeNameWE = selenium_helper.getElementList(payeeNameDD);
            List<WebElement> lastAmountWE = selenium_helper.getElementList(lastAmountDD);
            List<WebElement> statusWE = selenium_helper.getElementList(statusDD);
            List<WebElement> editWE = selenium_helper.getElementList(edit);
            List<WebElement> viewWE = selenium_helper.getElementList(view);
            for(int i=0;i<payeeNameWE.size();i++){
                String  payeeNameVal=payeeNameWE.get(i).getText();
                String  lastAmountVal=lastAmountWE.get(i).getText();
                String  statusVal=statusWE.get(i).getText();
                if(ReqPayeeName.equals(payeeNameVal) && Amount.equals(lastAmountVal) && status.equals(statusVal)){
                    editWE.get(i).click();
                    Thread.sleep(1000);
                    viewWE.get(i).click();
                    accountCompleteDetails.put("Debit Account",selenium_helper.getElement(debitAccount).getText());
                    accountCompleteDetails.put("Direct Debit Number",selenium_helper.getElement(directDebitNumber).getText());
                    accountCompleteDetails.put("Payee Name",selenium_helper.getElement(payeeName).getText());
                    accountCompleteDetails.put("originator Identification Number",selenium_helper.getElement(orgIdentificationNumber).getText());
                    accountCompleteDetails.put("Reference Number",selenium_helper.getElement(referenceNumber).getText());
                    accountCompleteDetails.put("Start Date",selenium_helper.getElement( startDate).getText());
                    accountCompleteDetails.put("Last Payment",selenium_helper.getElement(lastPayment).getText());
                    accountCompleteDetails.put("Last Amount",selenium_helper.getElement(lastAmount).getAttribute("value"));
                    accountCompleteDetails.put("Currency Type", CommonFunctions.convertCurrencySymbolToCurrencyType(selenium_helper.getElement(poundSymbol).getText()));
                    accountCompleteDetails.put("KEY", "NOTEMPTY");
                    selenium_helper.enter("xpath://*[contains(text(),'Back')]");
                    Thread.sleep(3000);
                    break;
                }
            }
        }
        return accountCompleteDetails;
    }

}
