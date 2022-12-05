package com.test.stepDefs;


import com.test.pages.*;
import com.google.inject.Inject;
import com.test.Utils.App_genericFunction;
import com.test.Utils.CommonFunctions;
import com.test.Utils.SeleniumHelper;
//import com.test.configuration.PageFactory;
//import com.test.exceptions.StopTestException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.Map;

public class TestOnly {
    @Inject
     App_genericFunction general;
    @Inject
     MyAccounts account_details;
    @Inject
     PaymentServices payment_details;
    @Inject
     CommonFunctions cod;
    @Inject
     SeleniumHelper selenium_helper;
    @Inject
    Managepayee managepayee;
    @Inject
    Directdebit direct_debit;
    @Inject
    StandingOrder standing_order;

    @Given("test API in Jenkins with new stepdef")
    public void testAPIInJenkinsWithNewStepdef() throws InterruptedException, ParseException {
       general.getintoCAInternetBanking("", "700017729", "112233", "Welcome@01");
      /* account_details.clickOnMyAccountsServiceLink("Account details");
        account_details.selctPersonalAccountType("GBP","90011557");
        System.out.println(account_details.getAccountCompleteDetails());*/
       /* general.getintoCAInternetBanking("", "700018611", "112233", "Welcome@01");
        account_details.clickOnMyAccountsServiceLink("Account details");
        account_details.selctPersonalAccountType("GBP","90011545");
        System.out.println(account_details.getAccountCompleteDetails());*/

        /*account_details.clickOnMyAccountsServiceLink("Transaction List");
        account_details.selctPersonalAccountType("GBP","90010553");
        //System.out.println(account_details.selectDateRangeAndSearch("01-01-2020","01-05-2020"));
        Map<Integer, TableDataSet> TransactionListData= account_details.selectDateRangeAndSearch("01-01-2020","05-05-2020");
        System.out.println(TransactionListData);*/
        //Map<Integer, TableDataSet> TransactionListData= account_details.selectDateRangeAndSearch("15-11-2019","25-04-2020");
        /*for(int i=0; i<TransactionListData.size();i++){
            System.out.println("------------------------------------------------------------ ");
            System.out.println("[ TransactionDate "+TransactionListData.get(i).getTransactionDate());
            System.out.println("Value Date "+TransactionListData.get(i).getValueDate());
            System.out.println("Transaction Details "+TransactionListData.get(i).getTransactionDetails());
            System.out.println("Debits "+TransactionListData.get(i).getDebits());
            System.out.println("Credit "+TransactionListData.get(i).getCredit());
            System.out.println("Balance "+TransactionListData.get(i).getBalance()+"]");
            System.out.println("------------------------------------------------------------ ");
        }*/
        /*System.out.println(cod.convertedformat1("2020-04-01"));
        System.out.println(cod.convertedformat2(cod.getCurrentDatewithouttime()));
        System.out.println(cod.takepreviousyearwithcurrentdate());*/
        //System.out.println(cod.next6months("2020-04-01"));
       // System.out.println(cod.previous6months("2020-04-01"));
        payment_details.clickOnPaymentServiceLink("Payment Services");
        payment_details.clickOnPaymentServiceLink("Manage Standing Orders");
        Thread.sleep(3000);
        account_details.selctPersonalAccountType("GBP","90010590");
        Thread.sleep(5000);
        /*Map<String,String> accountCompleteDetails=new LinkedHashMap<String, String>();
        accountCompleteDetails=managepayee.getAmendManagePayeeData("90010589");
        System.out.println("---------------------------------------");
        for(Map.Entry<String, String> e: accountCompleteDetails.entrySet()){
            System.out.println(e.getKey()+"     "+e.getValue());
        }
        System.out.println("---------------------------------------------");*/
        Map<String,String> accountCompleteDetails=new LinkedHashMap<String, String>();
        //accountCompleteDetails=payment_details.getViewDirectDebitData("CRF OIL LTD","0.00","ACTIVE");
        //accountCompleteDetails=direct_debit.getViewDirectDebitData("CRF OIL LTD","0.00","ACTIVE");
        accountCompleteDetails = standing_order.getViewStandingOrderData("","", "","","");
       //accountCompleteDetails = standing_order.getViewStandingOrderData("700017729-90010590","OBTest Customerthree", "YEARLY","ACTIVE","1.00");
        //selenium_helper.enter("xpath://*[contains(text(),'Back')]");
        Thread.sleep(2000);
        //Map<String,String> accountCompleteDetails1=new LinkedHashMap<String, String>();
        //accountCompleteDetails1 = standing_order.getViewStandingOrderData("700017710-90010577","OBTest Customertwo", "YEARLY","ACTIVE","5.00");
        System.out.println("---------------------------------------");
        System.out.println(accountCompleteDetails);
        //System.out.println(accountCompleteDetails1);
        /*for(Map.Entry<String, String> e: accountCompleteDetails.entrySet()){
            System.out.println(e.getKey()+"     "+e.getValue());
        }*/
       // System.out.println("---------------------------------------------");
       // accountCompleteDetails=direct_debit.getViewDirectDebitData("OBTestDD01","0.00","ACTIVE");
       // System.out.println("---------------------------------------");
       // System.out.println(accountCompleteDetails);
        /*for(Map.Entry<String, String> e: accountCompleteDetails.entrySet()){
            System.out.println(e.getKey()+"     "+e.getValue());
        }*/
        System.out.println("---------------------------------------------");
       // selenium_helper.scrollTopUsingJavascript();
        Thread.sleep(4000);
       // payment_details.clickOnPaymentServiceLink("Payment Services");
       // payment_details.clickOnPaymentServiceLink("Manage Payee");
        //payment_details.clickOnPaymentServiceLink("Payment Services");
        //payment_details.clickOnPaymentServiceLink("Manage Payee");
        /*accountCompleteDetails.clear();
        accountCompleteDetails=managepayee.getAmendManagePayeeData("90010577");
        System.out.println(accountCompleteDetails);*/

        //  account_details.clickOnMyAccountsServiceLink("Transaction List");
    }
    /*@Then("I want to close driver")
    public void iWantToCloseDriver() throws StopTestException {
        PageFactory.tearDown();
    }*/
}
