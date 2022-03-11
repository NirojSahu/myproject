package com.test.pages;

import Utilities.WebKit.helper.BasePage;
import Utilities.WebKit.helper.NavigationHelper;
import Utilities.seleniumcustomframework.extension.PageElement;
import com.google.inject.Inject;
import com.test.Utils.CommonFunctions;
import com.test.Utils.SeleniumHelper;
//import com.test.helper.BasePage;
//import com.test.helper.NavigationHelper;
//import com.test.seleniumcustomframework.extension.PageElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PaymentServices extends BasePage {
    @Inject
    SeleniumHelper selenium_helper;
    @Inject
    protected NavigationHelper navigationHelper;

    @FindBy(xpath = "//div[@title='Payment services']")
    private PageElement PaymentServices;
    @FindBy(xpath = "//center[text()='Account transfer']")
    private PageElement AccountTransfer;
    @FindBy(xpath = "//center[text()='Foreign payments']")
    private PageElement ForeignPayments;
    @FindBy(xpath = "//center[text()[contains(.,'Add new payee')]]")
    private PageElement AddNewPayee;
    @FindBy(xpath = "//center[text()[contains(.,'Manage payee')]]")
    private PageElement ManagePayee;
    @FindBy(xpath = "//center[text()[contains(.,'Direct debits')]]")
    private PageElement DirectDebits;
    @FindBy(xpath = "//center[text()[contains(.,'Manage standing orders')]]")
    private PageElement ManageStandingOrders;
    @FindBy(xpath = "//center[text()[contains(.,'Bulk payment upload')]]")
    private PageElement BulkPaymentUpload;
    @FindBy(xpath = "//center[text()[contains(.,'Bulk payment upload history')]]")
    private PageElement BulkPaymentUploadHistory;
    @FindBy(xpath = "//center[text()[contains(.,'Transactions to authorise')]]")
    private PageElement TransactionsToAuthorise;
    @FindBy(xpath = "//div[@class='x-panel-body x-panel-body-noheader LayoutAppsContainerOpacNone']/div[2]/center[text()='Payments history']")
    private PageElement PaymentsHistory;
    String PaymentsHistory1="xpath://center[text()='Payments history')]";

    String FutureDatedPayments="xpath://center[text()[contains(.,'Future dated payments')]]";
    // ----------------Direct Debits page element --------------------
    @FindBy(xpath = "//div[@name='DDM_DEBIT_ACC_NO']")
    private PageElement debitAccount;
    @FindBy(xpath = "//div[@name='DDM_DIRECT_DEBIT_NO']")
    private PageElement directDebitNumber;
    @FindBy(xpath = "//div[@name='DDM_BENE_NAME']")
    private PageElement payeeName;
    @FindBy(xpath = "//div[@name='DDM_REF_NO']")
    private PageElement orgIdentificationNumber;
    @FindBy(xpath = "//div[@name='PACSIM_REF_NO']")
    private PageElement referenceNumber;
    @FindBy(xpath = "//div[@name='DDM_START_DT']")
    private PageElement startDate;
    @FindBy(xpath = "//div[@name='DDM_LAST_PYMT_DT']")
    private PageElement lastPayment;
    @FindBy(xpath = "//input[@name='DDM_LAST_PYMT_AMT']")
    private PageElement lastAmount;
    @FindBy(xpath = "//span[@class='linkedCCy-PRE DDM_LAST_PYMT_AMT-CCy amountVmode']")
    private PageElement poundSymbol;
    // ----------------Standing Order  page element --------------------
    @FindBy(xpath = "//div[@name='TXT_REFERENCE_NO']")
    private PageElement transactionReference;
    @FindBy(xpath = "//div[@name='TXT_STATUS']")
    private PageElement status;
    @FindBy(xpath = "//div[@name='TXT_DEBIT_ACCOUNT_NUMBER']")
    private PageElement debitAccountNumber;
    @FindBy(xpath = "//div[@name='TXT_BENE_ACC_NO']")
    private PageElement payeeAccount;
    @FindBy(xpath = "//div[@name='TXT_BENE_NAME']")
    private PageElement payeeNameSO;
    @FindBy(xpath = "//div[@name='TXT_TRANSFER_CCY']")
    private PageElement currency;
    @FindBy(xpath = "//div[@name='TXT_TRANSFER_AMOUNT']")
    private PageElement transferAmount;
    @FindBy(xpath = "//input[@name='DT_SI_SETUP_DATE']")
    private PageElement sOSetupDate;
    @FindBy(xpath = "//input[@name='DT_SI_START_DATE']")
    private PageElement startDateSO;
    @FindBy(xpath = "//input[@name='DT_SI_END_DATE']")
    private PageElement endDate;
    @FindBy(xpath = "//div[@name='TXT_NO_OF_EX_PYMNT']")
    private PageElement noOfPayment;
    @FindBy(xpath = "//div[@name='TXT_FREQUENCY']")
    private PageElement frequency;
    @FindBy(xpath = "//input[@class='x-form-text x-form-field canvas-staticcombo SI_DROPDOWN_YEARLY1 x-trigger-noedit']")
    private PageElement month;
    @FindBy(xpath = "//input[@class='x-form-text x-form-field canvas-staticcombo SI_DROPDOWN_YEARLY2 x-trigger-noedit']")
    private PageElement day;
    @FindBy(xpath = "//div[@name='TXT_REMARKS']")
    private PageElement comments;
    // ----------------Manage Payee  page element --------------------
    @FindBy(xpath = "//div[@name='NEW_BENE_NAME']")
    private PageElement managePayeePayeeName;
    @FindBy(xpath = "//div[@name='BENE_REF_NO']")
    private PageElement managePayeePayeeRefNum;
    @FindBy(xpath = "//div[@name='SORT_CODE_1']")
    private PageElement managePayeeSortCode1;
    @FindBy(xpath = "//div[@name='SORT_CODE_2']")
    private PageElement managePayeeSortCode2;
    @FindBy(xpath = "//div[@name='SORT_CODE_3']")
    private PageElement managePayeeSortCode3;
    @FindBy(xpath = "//div[@name='NEW_BENE_BANKNAME_Y']")
    private PageElement managePayeeBankName;
    @FindBy(xpath = "//div[@name='NEW_BENE_ACC_NO']")
    private PageElement managePayeePayeeAccountNum;
    // ----------------Payment History page element --------------------
    @FindBy(xpath = "//div[@class='x-grid3-cell-inner x-grid3-col-TPH_EXECUTION_DATE']")
    private PageElement pHExecutionDate;
    @FindBy(xpath = "//div[@class='x-grid3-cell-inner x-grid3-col-TPH_PAYEE_REFERENCE']")
    private PageElement pHPayeeReference;
    @FindBy(xpath = "//div[@class='x-grid3-cell-inner x-grid3-col-TPH_TXN_TYPE']")
    private PageElement pHPayeeType;
    @FindBy(xpath = "//div[@class='x-grid3-cell-inner x-grid3-col-TPH_TRANSFER_AMT']")
    private PageElement pHTransferAmount;
    String coocies="xpath://a[text()='I agree']";

    //----------------DIrect Debit------------
    String directDebitTable="xpath://table[@class='x-grid3-row-table']";
    String originatorIdentificationNumber ="xpath://div[@class='x-grid3-cell-inner x-grid3-col-DDM_REF_NO']";
    String payeeNameDD="xpath://div[@class='x-grid3-cell-inner x-grid3-col-DDM_BENEFICIARY_NAME']";
    String lastPaymentDD="xpath://div[@class='x-grid3-col x-grid3-cell x-grid3-td-DDM_LAST_PYMT_DT']";
    String lastAmountDD="xpath://div[@class='x-grid3-cell-inner x-grid3-col-DDM_LAST_AMOUNT']";
    String statusDD="xpath://div[@class='x-grid3-cell-inner x-grid3-col-DDM_STATUS']";
    String edit="xpath://div[@class='x-grid3-cell-inner x-grid3-col-DDM_ACTION_DROPDOWN']";

    public void clickOnPaymentServiceLink(String PaymentServiceLink) throws InterruptedException {
        switch (PaymentServiceLink) {
            case "Payment Services":
                Thread.sleep(4000);
                PaymentServices.click();
                System.out.println("Payment Services List click successful");
                break;
            case "Account Transfer":
                Thread.sleep(4000);
                /*if(selenium_helper.isVisibleElementExisting(coocies)){
                    selenium_helper.click(coocies);
                }*/
                AccountTransfer.click();
                System.out.println("Account Transfer List click successful");
                break;
            case "Add New Payee":
                Thread.sleep(4000);
                /*if(selenium_helper.isVisibleElementExisting(coocies)){
                    selenium_helper.click(coocies);
                }*/
                AddNewPayee.click();
                System.out.println("Add New Payee List click successful");
            case "Manage Payee":
                Thread.sleep(4000);
                /*if(selenium_helper.isVisibleElementExisting(coocies)){
                    selenium_helper.click(coocies);
                }*/
                ManagePayee.click();
                System.out.println("Manage Payee List click successful");
                break;
            case "Foreign Payments":
                Thread.sleep(4000);
                /*if(selenium_helper.isVisibleElementExisting(coocies)){
                    selenium_helper.click(coocies);
                }*/
                ForeignPayments.click();
                System.out.println("Foreign Payments click successful");
                break;
            case "Direct Debits":
                Thread.sleep(4000);
                /*if(selenium_helper.isVisibleElementExisting(coocies)){
                    selenium_helper.click(coocies);
                }*/
                DirectDebits.click();
                System.out.println("Direct Debits List click successful");
                break;
            case "Manage Standing Orders":
                Thread.sleep(4000);
                /*if(selenium_helper.isVisibleElementExisting(coocies)){
                    selenium_helper.click(coocies);
                }*/
                selenium_helper.enterByKeyboardPageDown();
                ManageStandingOrders.click();
                System.out.println("Manage Standing Orders List click successful");
                break;
            case "Bulk Payment Upload":
                Thread.sleep(4000);
                /*if(selenium_helper.isVisibleElementExisting(coocies)){
                    selenium_helper.click(coocies);
                }*/
                selenium_helper.enterByKeyboardPageDown();
                BulkPaymentUpload.click();
                System.out.println("Bulk Payment Upload List click successful");
                break;
            case "Bulk Payment Upload History":
                Thread.sleep(4000);
                /*if(selenium_helper.isVisibleElementExisting(coocies)){
                    selenium_helper.click(coocies);
                }*/
                selenium_helper.enterByKeyboardPageDown();
                BulkPaymentUploadHistory.click();
                System.out.println("Bulk Payment Up load History List click successful");
                break;
            case "Transactions To Authorise":
                Thread.sleep(4000);
                /*if(selenium_helper.isVisibleElementExisting(coocies)){
                    selenium_helper.click(coocies);
                }*/
                selenium_helper.enterByKeyboardPageDown();
                TransactionsToAuthorise.click();
                System.out.println("Transactions To Authorise List click successful");
                break;
            case "Payments History":
                Thread.sleep(4000);
                /*if(selenium_helper.isVisibleElementExisting(coocies)){
                    selenium_helper.click(coocies);
                }*/
                selenium_helper.enterByKeyboardPageDown();
                PaymentsHistory.click();
                Thread.sleep(5000);
                System.out.println("Payments History List click successful");
                Thread.sleep(5000);
                break;

            case "Future Dated Payments":
                Thread.sleep(4000);
                /*if(selenium_helper.isVisibleElementExisting(coocies)){
                    selenium_helper.click(coocies);
                }*/
                selenium_helper.enterByKeyboardPageDown();
                selenium_helper.enter(FutureDatedPayments);
//                FutureDatedPayments.click();
                Thread.sleep(5000);
                System.out.println("Future Dated Payments click successful");
                Thread.sleep(5000);
                break;
        }

    }

    //-------------------     Direct Debits Tab methods --------------------------
    public HashMap<Integer, DDTableDataSet> getDirectDebitData() throws InterruptedException {
        return getTableDataDD();
    }

    public HashMap<Integer, DDTableDataSet> getTableDataDD() throws InterruptedException {
        Thread.sleep(4000);

//        List<WebElement> transactionTableWE= new ArrayList<>();
//        transactionTableWE=selenium_helper.getElementList(directDebitTable);
        List<WebElement> originatorIdentificationNumberWE = selenium_helper.getElementList(originatorIdentificationNumber);
        List<WebElement> payeeNameWE = selenium_helper.getElementList(payeeNameDD);
        List<WebElement> lastPaymentWE = selenium_helper.getElementList(lastPaymentDD);
        List<WebElement> lastAmountWE = selenium_helper.getElementList(lastAmountDD);
        List<WebElement> statusWE = selenium_helper.getElementList(statusDD);
        List<WebElement> editWE = selenium_helper.getElementList(edit);
        Map<Integer, DDTableDataSet> transactionDateDate= new HashMap<Integer, DDTableDataSet>();
        for(int i=0;i<originatorIdentificationNumberWE.size();i++){
            DDTableDataSet obj = new DDTableDataSet(originatorIdentificationNumberWE.get(i).getText(),payeeNameWE.get(i).getText(),
                    "no value",lastAmountWE.get(i).getText(),statusWE.get(i).getText()
            );
            transactionDateDate.put(i, obj);
        }
        return (HashMap<Integer, DDTableDataSet>) transactionDateDate;
    }
    public Map<String, String> getViewDirectDebitData(String ReqPayeeName, String Amount, String status ) throws InterruptedException {
        String originatorIdentificationNumber ="xpath://div[@class='x-grid3-cell-inner x-grid3-col-DDM_REF_NO']";
        String edit="xpath://div[@class='x-grid3-cell-inner x-grid3-col-DDM_ACTION_DROPDOWN']";
        String view="xpath://div[@class='x-grid3-cell-inner x-grid3-col-DDM_ACTION_DROPDOWN']/div/div[2]/div[text()='View']";
        List<WebElement> payeeNameWE = selenium_helper.getElementList(payeeNameDD);
        List<WebElement> lastAmountWE = selenium_helper.getElementList(lastAmountDD);
        List<WebElement> statusWE = selenium_helper.getElementList(statusDD);
        List<WebElement> editWE = selenium_helper.getElementList(edit);
        List<WebElement> viewWE = selenium_helper.getElementList(view);
        Map<String,String> accountCompleteDetails=new LinkedHashMap<String,String>();
        for(int i=0;i<payeeNameWE.size();i++){
            String  payeeNameVal=payeeNameWE.get(i).getText();
            String  lastAmountVal=lastAmountWE.get(i).getText();
            String  statusVal=statusWE.get(i).getText();
            if(ReqPayeeName.equals(payeeNameVal) && Amount.equals(lastAmountVal) && status.equals(statusVal)){
                editWE.get(i).click();
                Thread.sleep(1000);
                viewWE.get(i).click();
                accountCompleteDetails.put("Debit Account",debitAccount .getText());
                accountCompleteDetails.put("Direct Debit Number",directDebitNumber.getText());
                accountCompleteDetails.put("Payee Name",payeeName.getText());
                accountCompleteDetails.put("originator Identification Number",orgIdentificationNumber.getText());
                accountCompleteDetails.put("Reference Number",referenceNumber.getText());
                accountCompleteDetails.put("Start Date", startDate.getText());
                accountCompleteDetails.put("Last Payment",lastPayment.getText());
                accountCompleteDetails.put("Last Amount",lastAmount.getAttribute("value"));
                accountCompleteDetails.put("Currency Type",CommonFunctions.convertCurrencySymbolToCurrencyType(poundSymbol.getText()));
                break;
            }
        }
        return accountCompleteDetails;
    }

    //---------------------Standing Order -------------------
    public Map<String, String> getViewStandingOrderData(String payee_Reference,String payee_Name, String execution_Frequency, String status_1, String amount) throws InterruptedException {
        Thread.sleep(1000);
        //String payee_Name,
        String payeeReference ="xpath://div[@class='x-grid3-cell-inner x-grid3-col-PAYEE_REFERENCE']";
        String beneficiaryName ="xpath://div[@class='x-grid3-cell-inner x-grid3-col-BENEFICIARY_NAME']";
        String executionFrequency ="xpath://div[@class='x-grid3-cell-inner x-grid3-col-EXECUTION_FREQUENCY']";
        String status1 ="xpath://div[@class='x-grid3-cell-inner x-grid3-col-STATUS']";
        String amountSO ="xpath://div[@class='x-grid3-cell-inner x-grid3-col-SI_AMOUNT']";
        String edit="xpath://div[@class='x-grid3-cell-inner x-grid3-col-ACTION_ID']";
        String view="xpath://div[text()='View']";
        List<WebElement> payeeReferenceWE = selenium_helper.getElementList(payeeReference);
        List<WebElement> beneficiaryNameWE = selenium_helper.getElementList(beneficiaryName);
        List<WebElement> executionFrequencyWE = selenium_helper.getElementList(executionFrequency);
        List<WebElement> status1WE = selenium_helper.getElementList(status1);
        List<WebElement> amountWE = selenium_helper.getElementList(amountSO);
        List<WebElement> editWE = selenium_helper.getElementList(edit);
        List<WebElement> viewWE = selenium_helper.getElementList(view);
        Map<String,String> accountCompleteDetails=new LinkedHashMap<String,String>();
        for(int i=0;i<payeeReferenceWE.size();i++){
            String  orgIdftnNum=payeeReferenceWE.get(i).getText();
            String  beneficiaryNameVal=beneficiaryNameWE.get(i).getText();
            String  executionFrequencyVal=executionFrequencyWE.get(i).getText();
            String  status1Val=status1WE.get(i).getText();
            String  amoutVal=amountWE.get(i).getText().substring(2);

            if(payee_Reference.equals(orgIdftnNum)&& payee_Name.equals(beneficiaryNameVal)&& execution_Frequency.equals(executionFrequencyVal)&& status_1.equals(status1Val)&& amount.equals(amoutVal)){
                //payee_Name.equals(beneficiaryNameVal)&&
                editWE.get(i).click();
                Thread.sleep(1000);
                viewWE.get(i).click();
                Thread.sleep(4000);
                transactionReference.click();
                accountCompleteDetails.put("Transaction Reference",transactionReference .getText());
                accountCompleteDetails.put("Status",status.getText());
                accountCompleteDetails.put("Debit Account Number",debitAccountNumber.getText());
                accountCompleteDetails.put("Payee Account",payeeAccount.getText());
                accountCompleteDetails.put("Payee Name",payeeNameSO.getText());
                accountCompleteDetails.put("Currency", currency.getText());
                selenium_helper.enterByKeyboardPageDown();
                accountCompleteDetails.put("Transfer Amount",transferAmount.getText());
                accountCompleteDetails.put("SO Setup Date",sOSetupDate.getAttribute("value"));
                accountCompleteDetails.put("Start Date",startDateSO.getAttribute("value"));
                accountCompleteDetails.put("End Date",endDate.getAttribute("value"));
                accountCompleteDetails.put("No. Of Payment",noOfPayment.getText());
                selenium_helper.enterByKeyboardPageDown();
                accountCompleteDetails.put("Frequency",frequency.getText());
                accountCompleteDetails.put("Month",month.getAttribute("value"));
                accountCompleteDetails.put("Day",day.getAttribute("value"));
                accountCompleteDetails.put("Comments",comments.getText());
                break;
            }
        }
        return accountCompleteDetails;
    }


    //---------------------Manage Payee  -------------------
    public Map<String, String> getAmendManagePayeeData(String accountNo ) throws InterruptedException {
        Thread.sleep(1000);
        String beneAccountNo ="xpath://div[@class='x-grid3-cell-inner x-grid3-col-BENE_ACC_NO']";
        String edit="xpath://div[@class='x-grid3-cell-inner x-grid3-col-ACCOUNT_NO_ID']";
        String amend="xpath://div[text()='Amend payee']";
        List<WebElement> beneAccountNoWE = selenium_helper.getElementList(beneAccountNo);
        List<WebElement> editWE = selenium_helper.getElementList(edit);
        List<WebElement> amendWE = selenium_helper.getElementList(amend);
        Map<String,String> accountCompleteDetails=new LinkedHashMap<String,String>();
        for(int i=0;i<beneAccountNoWE.size();i++){
            String  beneRefNoVal=beneAccountNoWE.get(i).getText();
            if(accountNo.equals(beneRefNoVal)){
                selenium_helper.enterByKeyboardPageDown();
                Thread.sleep(1000);
                editWE.get(i).click();
                Thread.sleep(1000);
                amendWE.get(i).click();
                Thread.sleep(2000);
                accountCompleteDetails.put("Payee Name",managePayeePayeeName .getText());
                Thread.sleep(1000);
                accountCompleteDetails.put("Payee Reference Number",managePayeePayeeRefNum.getText());
                String SortCode=managePayeeSortCode1.getText()+"-"+managePayeeSortCode2.getText()+"-"+managePayeeSortCode3.getText();
                accountCompleteDetails.put("Sort Code",SortCode);
                accountCompleteDetails.put("Currency", managePayeeBankName.getText());
//                selenium_helper.enterByKeyboardPageDown();
                accountCompleteDetails.put("Transfer Amount",managePayeePayeeAccountNum.getText());
                break;
            }
        }
        return accountCompleteDetails;
    }
    //----------------------------- Scheduled Payment Data  -------------------
    public Map<String, String> getScheduledPaymentData(String status_Val ) throws InterruptedException {
        Thread.sleep(5000);
        String status ="xpath://div[@class='x-grid3-cell-inner x-grid3-col-TPH_STATUS']";
//    String edit="xpath://div[@class='x-grid3-cell-inner x-grid3-col-ACCOUNT_NO_ID']";
//    String amend="xpath://div[text()='Amend payee']";
        List<WebElement> statusWE = selenium_helper.getElementList(status);
//    List<WebElement> editWE = selenium_helper.getElementList(edit);
//    List<WebElement> amendWE = selenium_helper.getElementList(amend);
        Map<String,String> accountCompleteDetails=new LinkedHashMap<String,String>();
        for(int i=0;i<statusWE.size();i++){
            String  statusVal=statusWE.get(i).getText();
            if(status_Val.equals(statusVal)){
                Thread.sleep(1000);
                accountCompleteDetails.put("Execution Date",pHExecutionDate .getText());
                accountCompleteDetails.put("Payee Reference",pHPayeeReference.getText());
                accountCompleteDetails.put("Payee Type", pHPayeeType.getText());
                accountCompleteDetails.put("Transfer Amount",pHTransferAmount.getText());
                break;
            }
        }
        return accountCompleteDetails;
    }
}
