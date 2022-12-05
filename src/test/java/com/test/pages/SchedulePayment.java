package com.test.pages;

import com.google.inject.Inject;
import com.test.Utils.SeleniumHelper;
//import com.test.seleniumcustomframework.extension.PageElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class SchedulePayment {
    @Inject
    SeleniumHelper selenium_helper;

    //----------------Future Dated Payments------------
    String ScheduledPaymentPayeeName="xpath://div[@name='BENEFICIARY_NAME']";
    String ScheduledPaymentPayeeAccountNumber ="xpath://div[@name='FD_PAYEE_ACC_NO']";
    String ScheduledPaymentSortCode="xpath://div[@name='FD_PAYEE_SORT_CODE']";
    String ScheduledPaymentReference="xpath://div[@name='PAYEE_REFERENCE']";
//    @FindBy(xpath = "//input[@name='PAYMENT_DATE']")
//    public PageElement ScheduledPaymentDate;
    String ScheduledPaymentDate="xpath://input[@name='PAYMENT_DATE']";
    String ScheduledPaymentType="xpath://div[@name='PYMT_TYPE']";
    String ScheduledPaymentAmount="xpath://div[@name='FD_AMOUNT']";
    String ScheduledPaymentStatus="xpath://div[@name='STATUS']";
    String ScheduledPaymentBackBtn="xpath://table[@class='x-btn portal_neg_btn FDPAY_BACK x-btn-noicon']";
    //---------------------Future Dated Payments  -------------------

    public List<HashMap<String, String>> getSPData() throws InterruptedException {
        List<HashMap<String, String>> accountCompleteDetails = new ArrayList<HashMap<String, String>>();
        Thread.sleep(5000);
        selenium_helper.getdriver().manage().timeouts().implicitlyWait(5, TimeUnit.MILLISECONDS);
        if(selenium_helper.getdriver().findElements(By.xpath("//*[text() ='There are no future dated payments set up on this account']")).size() != 0)
        {
            Map<String, String> str =new HashMap<>();
            str.put("KEY", "EMPTY");
            accountCompleteDetails.add((HashMap<String, String>) str);
        }else {
            selenium_helper.getdriver().manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
            String payeeName = "xpath://div[@class='x-grid3-cell-inner x-grid3-col-BENEFICIARY_NAME']";
            String payeeReference = "xpath://div[@class='x-grid3-cell-inner x-grid3-col-PAYEE_REFERENCE']";
            String paymentDate = "xpath://div[@class='x-grid3-cell-inner x-grid3-col-PAYMENT_DATE']";
            String amount1 = "xpath://div[@class='x-grid3-cell-inner x-grid3-col-FD_AMOUNT']";
            String status1 = "xpath://div[@class='x-grid3-cell-inner x-grid3-col-STATUS']";
            String view = "xpath://div[@class='x-grid3-cell-inner x-grid3-col-ACTION']";
            List<WebElement> payeeReferenceWE = selenium_helper.getElementList(payeeReference);

            for (int i = 0; i < payeeReferenceWE.size(); i++) {
                List<WebElement> payeeNameWE = selenium_helper.getElementList(payeeName);
                List<WebElement> payeeReferenceWE1 = selenium_helper.getElementList(payeeReference);
                List<WebElement> paymentDateWE = selenium_helper.getElementList(paymentDate);
                List<WebElement> amount1WE = selenium_helper.getElementList(amount1);
                List<WebElement> status1WE = selenium_helper.getElementList(status1);
                List<WebElement> viewWE = selenium_helper.getElementList(view);
                String payeeNameVal = payeeNameWE.get(i).getText();
                String payeeReferenceVal = payeeReferenceWE1.get(i).getText();
                String paymentDateVal = paymentDateWE.get(i).getText();
                String amount1Val = amount1WE.get(i).getText();
                String status1Val = status1WE.get(i).getText();
                Map<String, String> str = getViewFutureDatedPaymentsData(payeeNameVal, payeeReferenceVal, paymentDateVal, amount1Val, status1Val, i, payeeReferenceWE.size(), status1WE);
                accountCompleteDetails.add((HashMap<String, String>) str);
            }
//
        }
        System.out.println("---------------------------------------");
            Iterator it = accountCompleteDetails.iterator();
            while (it.hasNext()) {
                Map<String, String> map = (Map<String, String>) it.next();
                for (Map.Entry entry : map.entrySet()) {
                    System.out.println(entry.getKey() + " = " + entry.getValue());
                }
                System.out.println("---------------------------------------------");
            }

        return accountCompleteDetails;
    }
    public Map<String, String> getViewFutureDatedPaymentsData(String payee_Name, String payee_Reference, String payment_Date, String amount, String status, int j, int k, List<WebElement> status1WE) throws InterruptedException {
        Thread.sleep(1000);
        Map<String,String> accountCompleteDetails=new LinkedHashMap<String,String>();
        for(int i=j;i<k;i++){
            selenium_helper.scrolltoWebelement(status1WE.get(i));
                status1WE.get(i).click();
                Thread.sleep(1000);
                accountCompleteDetails.put("SP_Payee_Name",selenium_helper.getText(ScheduledPaymentPayeeName));
                accountCompleteDetails.put("SP_Account_Number",selenium_helper.getText(ScheduledPaymentPayeeAccountNumber));
                accountCompleteDetails.put("SP_Sort_Code",selenium_helper.getText(ScheduledPaymentSortCode));
                accountCompleteDetails.put("SP_Reference",selenium_helper.getText(ScheduledPaymentReference));
                //selenium_helper.enterByKeyboardPageDown();
                selenium_helper.scrollDownUsingJavascript();
                accountCompleteDetails.put("SP_Date", selenium_helper.getAttributeValue("xpath://input[@name='FD_PAYMENT_DATE']","value"));
                accountCompleteDetails.put("SP_Type", selenium_helper.getText(ScheduledPaymentType));
                //selenium_helper.enterByKeyboardPageDown();
                selenium_helper.scrollDownUsingJavascript();
                accountCompleteDetails.put("SP_Amount",selenium_helper.getText(ScheduledPaymentAmount));
                accountCompleteDetails.put("SP_Status",selenium_helper.getText(ScheduledPaymentStatus));
                selenium_helper.enter("xpath://table[@class='x-btn portal_neg_btn FDPAY_BACK x-btn-noicon']");
                break;
            }
        return accountCompleteDetails;
    }
}
