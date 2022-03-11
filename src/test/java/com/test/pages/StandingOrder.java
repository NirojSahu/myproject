package com.test.pages;

import com.google.inject.Inject;
import com.test.Utils.SeleniumHelper;
//import com.test.seleniumcustomframework.extension.PageElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class StandingOrder {

    @Inject
    SeleniumHelper selenium_helper;

    // ----------------Standing Order  page element --------------------
    /*@FindBy(xpath = "//div[@name='TXT_REFERENCE_NO']")
    private PageElement transactionReference;*/
    String transactionReference = "xpath://div[@name='TXT_REFERENCE_NO']";

   /* @FindBy(xpath = "//div[@name='TXT_STATUS']")
    private PageElement status;*/
    String status = "xpath://div[@name='TXT_STATUS']";

   /* @FindBy(xpath = "//div[@name='TXT_DEBIT_ACCOUNT_NUMBER']")
    private PageElement debitAccountNumber;*/
    String debitAccountNumber = "xpath://div[@name='TXT_DEBIT_ACCOUNT_NUMBER']";

  /*  @FindBy(xpath = "//div[@name='TXT_BENE_ACC_NO']")
    private PageElement payeeAccount;*/
    String payeeAccount = "xpath://div[@name='TXT_BENE_ACC_NO']";

    /*@FindBy(xpath = "//div[@name='TXT_BENE_NAME']")
    private PageElement payeeNameSO;*/
    String payeeNameSO = "xpath://div[@name='TXT_BENE_NAME']";

 /*   @FindBy(xpath = "//div[@name='TXT_TRANSFER_CCY']")
    private PageElement currency;*/
    String currency = "xpath://div[@name='TXT_TRANSFER_CCY']";

    /*@FindBy(xpath = "//div[@name='TXT_TRANSFER_AMOUNT']")
    private PageElement transferAmount;*/
    String transferAmount = "xpath://div[@name='TXT_TRANSFER_AMOUNT']";

    /*@FindBy(xpath = "//input[@name='DT_SI_SETUP_DATE']")
    private PageElement sOSetupDate;*/
    String sOSetupDate = "xpath://input[@name='DT_SI_SETUP_DATE']";

    /*@FindBy(xpath = "//input[@name='DT_SI_START_DATE']")
    private PageElement startDateSO;*/
    String startDateSO = "xpath://input[@name='DT_SI_START_DATE']";

    /*@FindBy(xpath = "//input[@name='DT_SI_END_DATE']")
    private PageElement endDate;*/
    String endDate = "xpath://input[@name='DT_SI_END_DATE']";

    /*@FindBy(xpath = "//div[@name='TXT_NO_OF_EX_PYMNT']")
    private PageElement noOfPayment;*/
    String noOfPayment = "xpath://div[@name='TXT_NO_OF_EX_PYMNT']";

   /* @FindBy(xpath = "//div[@name='TXT_FREQUENCY']")
    private PageElement frequency;*/
    String frequency = "xpath://div[@name='TXT_FREQUENCY']";

   /* @FindBy(xpath = "//input[@class='x-form-text x-form-field canvas-staticcombo SI_DROPDOWN_YEARLY1 x-trigger-noedit']")
    private PageElement month;*/
    String month = "xpath://input[@class='x-form-text x-form-field canvas-staticcombo SI_DROPDOWN_YEARLY1 x-trigger-noedit']";
    String month_halfyearly = "xpath://input[@class='x-form-text x-form-field canvas-staticcombo SI_DROPDOWN_HALFY1 x-trigger-noedit']";
    String month_qa = "xpath://input[@class='x-form-text x-form-field canvas-staticcombo SI_DROPDOWN_QUARTER1 x-trigger-noedit']";
    String monthly = "xpath://input[@class='x-form-text x-form-field canvas-staticcombo SI_DROPDOWN_MONTH x-trigger-noedit']";

    /*@FindBy(xpath = "//input[@class='x-form-text x-form-field canvas-staticcombo SI_DROPDOWN_YEARLY2 x-trigger-noedit']")
    private PageElement day;*/
    String day = "xpath://input[@class='x-form-text x-form-field canvas-staticcombo SI_DROPDOWN_YEARLY2 x-trigger-noedit']";
    String day_halfyearly = "xpath://input[@class='x-form-text x-form-field canvas-staticcombo SI_DROPDOWN_HALFY2 x-trigger-noedit']";
    String day_qa = "xpath://input[@class='x-form-text x-form-field canvas-staticcombo SI_DROPDOWN_QUARTER2 x-trigger-noedit']";
    String day_weekly = "xpath://input[@class='x-form-text x-form-field canvas-staticcombo SI_DROPDOWN_WEEK x-trigger-noedit']";

    String payment1 = "xpath://input[@class='x-form-text x-form-field canvas-staticcombo SI_DROPDOWN_FORT_NIGHT1 x-trigger-noedit']";
    String payment2 = "xpath://input[@class='x-form-text x-form-field canvas-staticcombo SI_DROPDOWN_FORT_NIGHT2 x-trigger-noedit']";

    /*@FindBy(xpath = "//div[@name='TXT_REMARKS']")
    private PageElement comments;*/
    String comments = "xpath://div[@name='TXT_REMARKS']";

    public Map<String, String> getViewStandingOrderData(String payee_Reference, String payee_Name, String execution_Frequency, String status_1, String amount) throws InterruptedException {
        Thread.sleep(1000);

        System.out.println("Data that is coming for check in SO :");
        System.out.println(payee_Reference);
        System.out.println(payee_Name);
        System.out.println(execution_Frequency);
        System.out.println(status_1);
        System.out.println(amount);

        //String payee_Name,
        String payeeReference ="xpath://div[@class='x-grid3-cell-inner x-grid3-col-PAYEE_REFERENCE']";
        String beneficiaryName ="xpath://div[@class='x-grid3-cell-inner x-grid3-col-BENEFICIARY_NAME']";
        String executionFrequency ="xpath://div[@class='x-grid3-cell-inner x-grid3-col-EXECUTION_FREQUENCY']";
        String status1 ="xpath://div[@class='x-grid3-cell-inner x-grid3-col-STATUS']";
        String amountSO ="xpath://div[@class='x-grid3-cell-inner x-grid3-col-SI_AMOUNT']";
        String edit="xpath://div[@class='x-grid3-cell-inner x-grid3-col-ACTION_ID']";
        String view="xpath://div[text()='View']";
        selenium_helper.getdriver().manage().timeouts().implicitlyWait(5, TimeUnit.MILLISECONDS);
        Map<String,String> accountCompleteDetails=new LinkedHashMap<String,String>();

        if(selenium_helper.getdriver().findElements(By.xpath("//*[text() ='No standing instruction found']")).size() != 0)
        {
            accountCompleteDetails.put("KEY", "EMPTY");
        }else
        {
            selenium_helper.getdriver().manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
            List<WebElement> payeeReferenceWE = selenium_helper.getElementList(payeeReference);
            List<WebElement> beneficiaryNameWE = selenium_helper.getElementList(beneficiaryName);
            List<WebElement> executionFrequencyWE = selenium_helper.getElementList(executionFrequency);
            List<WebElement> status1WE = selenium_helper.getElementList(status1);
            List<WebElement> amountWE = selenium_helper.getElementList(amountSO);
            List<WebElement> editWE = selenium_helper.getElementList(edit);
            List<WebElement> viewWE = selenium_helper.getElementList(view);

            for(int i=0;i<payeeReferenceWE.size();i++){
                String  orgIdftnNum=payeeReferenceWE.get(i).getText();
                String  beneficiaryNameVal=beneficiaryNameWE.get(i).getText();
                String  executionFrequencyVal=executionFrequencyWE.get(i).getText();
                String  status1Val=status1WE.get(i).getText();
                if(status1Val.contentEquals("CANCELLED"))
                {
                    status1Val = "INACTIVE";
                }
                String  amoutVal=amountWE.get(i).getText().substring(2);

                if(payee_Reference.equals(orgIdftnNum)&& payee_Name.equals(beneficiaryNameVal)&& execution_Frequency.equals(executionFrequencyVal)&& status_1.equals(status1Val)&& amount.equals(amoutVal)){
                    //payee_Name.equals(beneficiaryNameVal)&&
                    selenium_helper.scrolltoWebelement(editWE.get(i));
                    editWE.get(i).click();
                    Thread.sleep(1000);
                    viewWE.get(i).click();
                    Thread.sleep(4000);
                    selenium_helper.waitForJSandJQueryToLoad();
                    selenium_helper.enter(transactionReference);
                    accountCompleteDetails.put("Transaction Reference",selenium_helper.getElement(transactionReference).getText());
                    accountCompleteDetails.put("Status",selenium_helper.getElement(status).getText());
                    accountCompleteDetails.put("Debit Account Number",selenium_helper.getElement(debitAccountNumber).getText());
                    accountCompleteDetails.put("Payee Account",selenium_helper.getElement(payeeAccount).getText());
                    accountCompleteDetails.put("Payee Name",selenium_helper.getElement(payeeNameSO).getText());
                    accountCompleteDetails.put("Currency", selenium_helper.getElement(currency).getText());
                    //selenium_helper.scrollDownUsingJavascript();
                    selenium_helper.enterByKeyboardPageDown();
                    accountCompleteDetails.put("Transfer Amount",selenium_helper.getElement(transferAmount).getText());
                    accountCompleteDetails.put("SO Setup Date",selenium_helper.getElement(sOSetupDate).getAttribute("value"));
                    accountCompleteDetails.put("Start Date",selenium_helper.getElement(startDateSO).getAttribute("value"));
                    accountCompleteDetails.put("End Date",selenium_helper.getElement(endDate).getAttribute("value"));
                    accountCompleteDetails.put("No. Of Payment",selenium_helper.getElement(noOfPayment).getText());
                    selenium_helper.enterByKeyboardPageDown();
                    //selenium_helper.scrollDownUsingJavascript();
                    accountCompleteDetails.put("Frequency",selenium_helper.getElement(frequency).getText());
                    if(selenium_helper.getElement(frequency).getText().contentEquals("HALF YEARLY"))
                    {
                       // accountCompleteDetails.put("Month",selenium_helper.getElement(month_halfyearly).getAttribute("value"));
                       // accountCompleteDetails.put("Day",selenium_helper.getElement(day_halfyearly).getAttribute("value"));
                    }else if(selenium_helper.getElement(frequency).getText().contentEquals("YEARLY"))
                    {
                     //   accountCompleteDetails.put("Month",selenium_helper.getElement(month).getAttribute("value"));
                     //   accountCompleteDetails.put("Day",selenium_helper.getElement(day).getAttribute("value"));
                    }else if(selenium_helper.getElement(frequency).getText().contentEquals("QUARTERLY"))
                    {
                        //accountCompleteDetails.put("Month",selenium_helper.getElement(month_qa).getAttribute("value"));
                        //accountCompleteDetails.put("Day",selenium_helper.getElement(day_qa).getAttribute("value"));
                    }else if(selenium_helper.getElement(frequency).getText().contentEquals("MONTHLY"))
                    {
                        //accountCompleteDetails.put("Month",selenium_helper.getElement(monthly).getAttribute("value"));
                       // accountCompleteDetails.put("Day",selenium_helper.getElement(day).getAttribute("value"));
                    }else if(selenium_helper.getElement(frequency).getText().contentEquals("FORTNIGHTLY"))
                    {
                        //accountCompleteDetails.put("Payment1",selenium_helper.getElement(payment1).getAttribute("value"));
                        //accountCompleteDetails.put("Payment2",selenium_helper.getElement(payment2).getAttribute("value"));
                    }else if(selenium_helper.getElement(frequency).getText().contentEquals("WEEKLY"))
                    {
                        //accountCompleteDetails.put("Month",selenium_helper.getElement(month).getAttribute("value"));
                        //accountCompleteDetails.put("Day",selenium_helper.getElement(day_weekly).getAttribute("value"));
                    }else if(selenium_helper.getElement(frequency).getText().contentEquals("DAILY"))
                    { }

                    accountCompleteDetails.put("Comments",selenium_helper.getElement(comments).getText());
                    accountCompleteDetails.put("KEY", "NONEMPTY");
                    selenium_helper.enter("xpath://*[contains(text(),'Back')]");
                    break;
                }
            }
        }

        return accountCompleteDetails;
    }
}
