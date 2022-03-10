package com.test.pages;

import com.google.inject.Inject;
import com.test.Utils.App_genericFunction;
import com.test.Utils.SeleniumHelper;
import com.test.helper.BasePage;
import com.test.helper.NavigationHelper;
import com.test.seleniumcustomframework.extension.PageElement;
import cucumber.api.java.ca.Cal;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class MyAccounts extends BasePage {
    @Inject
    SeleniumHelper selenium_helper;
    @Inject
    protected NavigationHelper navigationHelper;
    static WebDriver driver;
    @FindBy(xpath = "//center/div[text()='My accounts']")
    private PageElement MyAccountsService;
    @FindBy(xpath = "//center[text()='My accounts']")
    private PageElement MyAccounts;
    @FindBy(xpath = "//center[text()='Last 30 days transactions']")
    private PageElement Last30DaysTransactions;
    @FindBy(xpath = "//*[text()='Transaction list']")
    private PageElement TransactionList;
    @FindBy(xpath = "//*[text()='Account details']")
    private PageElement AccountDetails;
    @FindBy(xpath = "//center[text()='Account statement']")
    private PageElement AccountStatement;
    @FindBy(xpath = "//center[text()='Statement preferences']")
    private PageElement StatementPreferences;
    @FindBy(xpath = "//center[text()='Service request tracker']")
    private PageElement ServiceRequestTracker;
    @FindBy(xpath = "//center[text()='Access management']")
    private PageElement AccessManagement;
    @FindBy(xpath = "//center[text()='Account closure']")
    private PageElement AccountClosure;
    @FindBy(xpath = "//center[text()='Cheque book request']")
    private PageElement ChequeBookRequest;
    @FindBy(xpath = "//center[text()='Enquire cheque status']")
    private PageElement EnquireChequeStatus;
    @FindBy(xpath = "//center[text()='Cancel a cheque']")
    private PageElement CancelACheque;
    @FindBy(xpath = "//span[@class='portfolio_txt']/p[1]")
    private PageElement accountName;
    @FindBy(xpath = "//span[@class='portfolio_txt']/p[3]")
    private PageElement accountId;
    @FindBy(xpath = "//div[@class='portfolio_valuebx']/span[1]")
    private PageElement availableBalance;
    @FindBy(xpath = "//label[text()='Personal']/preceding-sibling::input")
    private PageElement accountTypePersonal;
    @FindBy(xpath = "//label[text()='Personal']")
    private PageElement clickAccountTypePersonal;
    @FindBy(xpath = "//label[text()='Business & other']/preceding-sibling::input")
    private PageElement accountTypeBusinessAndOther;
    @FindBy(xpath = "//label[text()='Business & other']")
    private PageElement clickAccountTypeBusinessAndOther;
    @FindBy(xpath = "//table/tbody/tr[3]//label/span[text()='Select account']/parent::label/parent::div/div/div/input[2]")
    private PageElement selectAccountBusinessAndOther;
    @FindBy(xpath = "//label/span[text()='Select account']/parent::label/parent::div/div/div/input[2]")
    private List<PageElement> selectAccountPersonal;
    @FindBy(xpath = "//div[@name='ACDT_ACC_NO']")
    private PageElement accountNumber;
    //    @FindBy(xpath = "//label/span[text()='Select account']/parent::label/parent::div/div/div/input[2]")
    @FindBy(xpath = "//div[@name='ACDT_ACC_NAME']")
    private PageElement accountNameDetails;
    @FindBy(xpath = "//div[@name='ACDT_ACC_NAME2']")
    private PageElement accountName2;
    @FindBy(xpath = "//div[@name='ACDT_ACC_TYPE']")
    private PageElement accountType;
    @FindBy(xpath = "//div[@name='ACDT_CURRENCY']")
    private PageElement accountCurrency;
    @FindBy(xpath = "//div[@name='ACDT_ACC_CURBAL']")
    private PageElement accountBalance;
    @FindBy(xpath = "//div[@name='ACDT_ACC_CREDIT_INT_RATE']")
    private PageElement accountInterestRate;
    @FindBy(xpath = "//div[@name='ACDT_ACC_STATUS']")
    private PageElement accountStatus;
    @FindBy(xpath = "//input[@name='AST_FROM_DATE_RNG']")
    private PageElement transactionListDateFrom;
    @FindBy(xpath = "//input[@name='AST_TO_DATE_RNG']")
    private PageElement transactionListDateTo;
    @FindBy(xpath = "//button[text()='Show']")
    private PageElement showBtn;
    String coocies="xpath://a[text()='I agree']";


// --------------------------Global Variables--------------------

    //-------------------Generic Methods --------------------
    public void clickOnMyAccountsServiceLink(String link) throws InterruptedException{
        switch (link){
            case "My Accounts Service":
                Thread.sleep(5000);
                MyAccountsService.doubleClick();
                System.out.println("Transaction List click successful");
                break;
            case "My Accounts":
                Thread.sleep(5000);
                /*if(selenium_helper.isVisibleElementExisting(coocies)){
                    selenium_helper.click(coocies);
                }*/
                MyAccounts.click();
                System.out.println("MyAccounts click successful");
                break;
            case "Last Thirty Days Transactions":
                Thread.sleep(5000);
                Last30DaysTransactions.click();
                /*if(selenium_helper.isVisibleElementExisting(coocies)){
                    selenium_helper.click(coocies);
                }*/
                System.out.println("Last 30 Days Transactions click successful");
                break;
            case "Account details":
                Thread.sleep(5000);
                /*if(selenium_helper.isVisibleElementExisting(coocies)){
                    selenium_helper.click(coocies);
                }*/
                AccountDetails.click();
                System.out.println("Transaction List click successful");
                break;
            case "Transaction List":
                Thread.sleep(5000);
                /*if(selenium_helper.isVisibleElementExisting(coocies)){
                    selenium_helper.click(coocies);
                }*/
                TransactionList.click();
                System.out.println("Transaction List click successful");
                break;
            case "Account Statement":
                Thread.sleep(5000);
                /*if(selenium_helper.isVisibleElementExisting(coocies)){
                    selenium_helper.click(coocies);
                }*/
                AccountStatement.click();
                System.out.println("Account Statement List click successful");
                break;
            case "Statement Preferences":
                Thread.sleep(5000);
                /*if(selenium_helper.isVisibleElementExisting(coocies)){
                    selenium_helper.click(coocies);
                }*/
                StatementPreferences.click();
                System.out.println("Statement Preferences List click successful");
                break;
            case "Service Request Tracker":
                Thread.sleep(5000);
                /*if(selenium_helper.isVisibleElementExisting(coocies)){
                    selenium_helper.click(coocies);
                }*/
                ServiceRequestTracker.click();
                System.out.println("Service Request Tracker List click successful");
                break;
            case "Access Management":
                Thread.sleep(5000);
                /*if(selenium_helper.isVisibleElementExisting(coocies)){
                    selenium_helper.click(coocies);
                }*/
                MyAccounts.click();
                selenium_helper.enterByKeyboardPageDown();
                AccessManagement.click();
                System.out.println("Access Management List click successful");
                break;
            case "Account Closure":
                Thread.sleep(5000);
                /*if(selenium_helper.isVisibleElementExisting(coocies)){
                    selenium_helper.click(coocies);
                }*/
                selenium_helper.enterByKeyboardPageDown();
                AccountClosure.click();
                System.out.println("Account Closure List click successful");
                break;
            case "Cheque Book Request":
                Thread.sleep(5000);
                /*if(selenium_helper.isVisibleElementExisting(coocies)){
                    selenium_helper.click(coocies);
                }*/
                selenium_helper.enterByKeyboardPageDown();
                ChequeBookRequest.click();
                System.out.println("Cheque Book Request List click successful");
                break;
            case "Enquire Cheque Status":
                Thread.sleep(5000);
                /*if(selenium_helper.isVisibleElementExisting(coocies)){
                    selenium_helper.click(coocies);
                }*/
                selenium_helper.enterByKeyboardPageDown();
                EnquireChequeStatus.click();
                System.out.println("Enquire Cheque Status List click successful");
                break;
            case "Cancel A Cheque":
                Thread.sleep(5000);
                /*if(selenium_helper.isVisibleElementExisting(coocies)){
                    selenium_helper.click(coocies);
                }*/
                selenium_helper.enterByKeyboardPageDown();
                CancelACheque.click();
                System.out.println("Cancel A Cheque List click successful");
        }
    }

    public void selctPersonalAccountType(String currency, String accountNumber) throws InterruptedException {
       selectAccountTypePersonal();
        selectAccountPersonal(currency,accountNumber);
    }
    public void selctBusinessAndOtherAccountType(String currency, String accountNumber) throws InterruptedException {
        //selectAccountTypeBusinessAndOther();
        selectAccountBusinessAndOther(currency,accountNumber);
    }
    public void selectAccountTypePersonal() throws InterruptedException {
        if(clickAccountTypeBusinessAndOther.isDisplayed()){
            clickAccountTypeBusinessAndOther.click();
        }
        Thread.sleep(5000);
        if(!accountTypePersonal.isSelected()){
            clickAccountTypePersonal.click();
        }
    }
    public void selectAccountTypeBusinessAndOther() throws InterruptedException {
        Thread.sleep(5000);
        if(!accountTypeBusinessAndOther.isSelected()){
            clickAccountTypeBusinessAndOther.click();
        }
    }
    public void selectAccountPersonal(String currency, String accountNumber) throws InterruptedException {
        String selAcc="xpath://div[text()='"+currency+"-"+accountNumber+"']";
//        String selAcc1="xpath://table/tbody/tr[3]//label/span[text()='Select account']/parent::label/parent::div/div/div/input[2]";
        Thread.sleep(1000);
//        selenium_helper.click(selAcc1);
     //   selenium_helper.enterByKeyboardPageDown();
        selenium_helper.getdriver().manage().timeouts().implicitlyWait(5, TimeUnit.MILLISECONDS);
        if (SeleniumHelper.getdriver().findElements(By.id("cookiescript_accept")).size() != 0)
        {
            SeleniumHelper.getdriver().findElement(By.id("cookiescript_accept")).click();
        }
        selenium_helper.waitForJSandJQueryToLoad();
       List <WebElement> acct = SeleniumHelper.getdriver().findElements(By.xpath("//label/span[text()='Select account']/parent::label/parent::div/div/div/input[2]"));
       try
       {
           acct.get(1).click();
           //selectAccountPersonal.get(1).click();
       }catch (Exception e)
       {
           acct.get(0).click();
           //selectAccountPersonal.get(0).click();
       }

        Thread.sleep(1000);
        selenium_helper.click(selAcc);
        selenium_helper.waitForJSandJQueryToLoad();
        selenium_helper.getdriver().manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
    }
    public void selectAccountBusinessAndOther(String currency, String accountNumber) throws InterruptedException {
        String selAcc="xpath://div[text()='"+currency+"-"+accountNumber+"']";
//        String selAcc1="xpath://table/tbody/tr[3]//label/span[text()='Select account']/parent::label/parent::div/div/div/input[2]";
        Thread.sleep(1000);
//        selenium_helper.click(selAcc1);
        selectAccountBusinessAndOther.click();
        Thread.sleep(1000);
        selenium_helper.click(selAcc);
    }

    //------------------- My Accounts tab methods ------------------------------------------
    public List getAccountDetails() throws InterruptedException {
        return getAccountValues();
    }
    public List getAccountValues() throws InterruptedException {
        List<String> accountDetails=new ArrayList<>();
        Thread.sleep(5000);
        accountDetails.add(accountName.getText());
        accountDetails.add(accountId.getText());
        accountDetails.add(availableBalance.getText());
        return accountDetails;
    }
    //-------------------     Last 30 Days Transactions Tab methods --------------------------
    public Map<Integer, TableDataSet > getLast30DaysTransactionData() throws InterruptedException {
        return (Map<Integer, TableDataSet >) getTableData();
    }
    public Map<Integer, TableDataSet > getTableData() throws InterruptedException {
        Map<Integer, TableDataSet> transactionDateDate = new HashMap<Integer, TableDataSet>();
        selenium_helper.getdriver().manage().timeouts().implicitlyWait(5, TimeUnit.MILLISECONDS);

        App_genericFunction.putScreenshotInStep();

        if(selenium_helper.getdriver().findElements(By.xpath("//*[text() ='No transaction found']")).size() != 0)
        {

            transactionDateDate.put(0, null);

        }else {

            Thread.sleep(2000);
//        Map<Integer, TableDataSet > tableData = new HashMap<Integer, TableDataSet>();
            String transactionTable = "xpath://table[@class='x-grid3-row-table']";
            String transactionDate = "xpath://td[@class='x-grid3-col x-grid3-cell x-grid3-td-AST19_TRANS_DATE x-grid3-cell-first ']/div";
            String valueDate = "xpath://td[@class='x-grid3-col x-grid3-cell x-grid3-td-AST21_VALUE_DATE ']/div";
            String transactionDetails = "xpath://td[@class='x-grid3-col x-grid3-cell x-grid3-td-AST20_PARTICULARS ']/div";
            String debit = "xpath://td[@class='x-grid3-col x-grid3-cell x-grid3-td-AST21_DEBIT_AMOUNT ']/div";
            String credit = "xpath://td[@class='x-grid3-col x-grid3-cell x-grid3-td-AST21_CREDIT_AMOUNT ']/div";
            String balance = "xpath://td[@class='x-grid3-col x-grid3-cell x-grid3-td-AST22_BALANCE ']/div";
            List<WebElement> transactionTableWE = new ArrayList<>();
            transactionTableWE = selenium_helper.getElementList(transactionTable);
            List<WebElement> transactionDateWE = selenium_helper.getElementList(transactionDate);
            List<WebElement> valueDateWE = selenium_helper.getElementList(valueDate);
            List<WebElement> transactionDetailsWE = selenium_helper.getElementList(transactionDetails);
            List<WebElement> debitsWE = selenium_helper.getElementList(debit);
            List<WebElement> creditWE = selenium_helper.getElementList(credit);
            List<WebElement> balanceWE = selenium_helper.getElementList(balance);
            //Map<Integer, TableDataSet> transactionDateDate = new HashMap<Integer, TableDataSet>();
            for (int i = 1; i <= transactionDateWE.size(); i++) {
                int j = i - 1;
                TableDataSet obj = new TableDataSet(transactionDateWE.get(j).getText(), valueDateWE.get(j).getText(),
                        transactionDetailsWE.get(j).getText(), debitsWE.get(j).getText(), creditWE.get(j).getText(),
                        balanceWE.get(j).getText()
                );
               /* TableDataSet obj = new TableDataSet(transactionDateWE.get(i).getText(), valueDateWE.get(i).getText(),
                        transactionDetailsWE.get(i).getText(), debitsWE.get(i).getText(), creditWE.get(i).getText(),
                        balanceWE.get(i).getText()
                );*/
                transactionDateDate.put(j, obj);
            }

        }
        return transactionDateDate;
    }

    //----------------------Account Details Tab methods ------------------------------
    public Map<String, String> getAccountCompleteDetails() throws InterruptedException {
        Thread.sleep(1000);
        return accountCompleteDetails();
    }
    private Map<String, String> accountCompleteDetails() {
        Map<String,String> accountCompleteDetails=new HashMap<String,String>();
        accountCompleteDetails.put("Account Number", accountNumber.getText());
        accountCompleteDetails.put("Account Name Details",accountNameDetails.getText());
        accountCompleteDetails.put("Account Name 2",accountName2.getText());
        accountCompleteDetails.put("AccountType",accountType.getText());
        accountCompleteDetails.put("Account Currency",accountCurrency.getText());
        accountCompleteDetails.put("Account Balance", accountBalance.getText());
        accountCompleteDetails.put("Account Interest Rate",accountInterestRate.getText());
        accountCompleteDetails.put("accountStatus",accountStatus.getText());
        return accountCompleteDetails;
    }
    //--------------------Transaction list methods -----------------------------------------
//    String dateFron=GlobalHooks.values.get(0).get("transaction_list_from");
//    String dateTo=GlobalHooks.values.get(0).get("transaction_list_to");
    public Map<Integer, TableDataSet > selectDateRangeAndSearch(String dateFrom, String dateTo ) throws InterruptedException {
        Thread.sleep(1000);
        selectFromDate();
        Thread.sleep(5000);
        selectDateFrom("From",dateFrom);
        Thread.sleep(1000);
        selenium_helper.enterByKeyboardPageDown();
        selectToDateAndClickOnShow();
    selectToDateAndClickOnShow();
        Thread.sleep(1000);
        selectDateFrom("To", dateTo);
        return getTransactionListData();
    }
    public void selectFromDate() throws InterruptedException {
        transactionListDateFrom.click();
    }
    public void selectToDateAndClickOnShow() throws InterruptedException {
        transactionListDateTo.click();
    }
    public Map<Integer, TableDataSet > getTransactionListData() throws InterruptedException {
        showBtn.click();
        selenium_helper.enterByKeyboardPageDown();
        Thread.sleep(1000);
        return getTableData();
    }
    private void selectDateFrom(String from,String date) throws InterruptedException {
        System.out.println("Date ---"+date);
        String[] ls1=date.split("-");
        String day=ls1[0];
        String month=ls1[1];
        String year=ls1[2];
        String month1;
        switch (month){
            case "01": month1="January";break;
            case "02": month1="February";break;
            case "03": month1="March";break;
            case "04": month1="April";break;
            case "05": month1="May";break;
            case "06": month1="June";break;
            case "07": month1="July";break;
            case "08": month1="August";break;
            case "09": month1="September";break;
            case "10": month1="October";break;
            case "11": month1="November";break;
            case "12": month1="December";break;
            default: throw new IllegalStateException("Unexpected value: " + month);
        }
        //Calendar c = Calendar.getInstance();
        //int currentYear=c.get(Calendar.YEAR);
        //System.out.println("Current Year :"+currentYear);
        //table[@class='x-btn x-btn-noicon']
        //List<WebElement> monthYearBtn= selenium_helper.getElementList("xpath://button[contains(text(),'"+currentYear+"')]");
        List<WebElement> monthYearBtn= selenium_helper.getElementList("xpath://table[@class='x-btn x-btn-noicon']");
        if(from.equalsIgnoreCase("From")){
            monthYearBtn.get(0).click();
        }
        else {
            Thread.sleep(1000);
            monthYearBtn.get(1).click();
        }
        Thread.sleep(5000);
        List<WebElement> yearSel=selenium_helper.getElementList("xpath://tbody/tr/td/a[text()='"+year+"']");
        List<WebElement> MonthSel=selenium_helper.getElementList("xpath://tbody/tr/td/a[text()='"+month1.substring(0,3)+"']");
        List<WebElement> okButton = selenium_helper.getElementList("xpath://button[@class='x-date-mp-ok']");
        if(from.equalsIgnoreCase("From")){
            yearSel.get(0).click();
            MonthSel.get(0).click();
            okButton.get(0).click();
        }
        else {
            Thread.sleep(1500);
            yearSel.get(1).click();
            Thread.sleep(1500);
            MonthSel.get(1).click();
            Thread.sleep(1500);
            okButton.get(1).click();
        }
        if (day.startsWith("0")){
            for(int i=1;i<=4;i++){
                try {
                    List<WebElement> dayBtn1 = selenium_helper.getElementList("xpath://a[@class='x-date-date']/em/span[text()='"+day.charAt(1)+"']");
                    if(from.equalsIgnoreCase("From")) {
                        Thread.sleep(1000);
                        dayBtn1.get(0).click();
                        System.out.println("Date is selected ");
                    }
                    else if(from.equalsIgnoreCase("To")){
                        Thread.sleep(1000);
                        dayBtn1.get(2).click();
                        System.out.println("Date is selected ");
                    }
                    break;
                }
                catch (NoSuchElementException e){
                    System.out.println("Date is not found---- ");
                }
            }
        }
        else {

           // for(int i=2;i<=6;i++){
                try {
                    LinkedList <Integer> possitive_index = new LinkedList<>();
                    LinkedList <Integer> negative_index = new LinkedList<>();
                    List<WebElement> dayBtn1 = selenium_helper.getElementList("xpath://td/a[@class='x-date-date']/em/span[text()='"+day+"']/../../..");
                    List<WebElement> dayBtn = selenium_helper.getElementList("xpath://td/a[@class='x-date-date']/em/span[text()='"+day+"']");
                    if(dayBtn1.size() != 0)
                    {
                        for(int j=0;j<dayBtn1.size();j++)
                        {
                            if(dayBtn1.get(j).getAttribute("class").contentEquals("x-date-prevday") || dayBtn1.get(j).getAttribute("class").contentEquals("x-date-nextday"))
                            {
                                negative_index.add(j);
                            }else
                                {
                                    possitive_index.add(j);
                                }
                        }

                    }
                    System.out.println("possitive_index : " + possitive_index);
                    System.out.println("negative_index : " + negative_index);
                    if(from.equalsIgnoreCase("From"))
                    {
                        dayBtn.get(possitive_index.get(0)).click();
                    }else {
                        dayBtn.get(possitive_index.get(1)).click();
                    }
                    System.out.println("Date is selected ");
                    //break;
                }
                catch (NoSuchElementException e){
                    System.out.println("Date is not found---- ");
                }
            //}
        }
    }
}

