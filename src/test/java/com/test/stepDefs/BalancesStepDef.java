package com.test.stepDefs;

import Responses.OBReadBalance1.Balance;
import Responses.OBReadBalance1.CreditLine;
import Responses.OBReadBalance1.OBReadBalance;
import Utilities.APIMethods;
import Utilities.JsonUtilities;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.google.inject.Inject;
import com.test.APIFunctions.AccountAccessConsentService;
import com.test.APIFunctions.BalancesService;
import com.test.CustomHooks.GlobalHooks;
import com.test.Utils.App_genericFunction;
import com.test.Utils.SeleniumHelper;
import com.test.Utils.jwt;
import com.test.configuration.Configuration;
import com.test.exceptions.StopTestException;
import com.test.helper.NavigationHelper;
import com.test.pages.MyAccounts;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.codehaus.jettison.json.JSONException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import com.test.helper.BasePage;


public class BalancesStepDef extends BasePage {

    @Inject
    BalancesService Obj;
    @Inject
    SeleniumHelper seleniumHelper;
    @Inject
    App_genericFunction general;
    @Inject
    MyAccounts account_details;

    //WebDriver driver = SeleniumHelper.getdriver();
    public String Verbose;
    Response response;
    JsonPath JsonPathEvaluator;
    JsonPath JsonPathEvaluator1;
    JsonPath JsonPathEvaluator2;

    public static String USER_DI = System.getProperty("user.dir");

    @Given("I have the data available to create {string} request for balances_service with Headers")
    public void i_have_the_data_available_to_create_request_for_balances_service_with_Headers(String string) throws FileNotFoundException, JSONException, JsonProcessingException {
        Obj.setHeaders_get();
    }

    @When("I want to make a call to balances_service to get balance information")
    public void i_want_to_make_a_call_to_balances_service_to_get_balance_information() {
        response = Obj.hitService_get();
    }

    @Then("I want to validate service Response for Valid Schema for balances")
    public void i_want_to_validate_service_Response_for_Valid_Schema_for_balances() throws IOException, ProcessingException {
        App_genericFunction.putcommentinStep(response.getBody().asString());
        App_genericFunction.putcommentinStep(JsonUtilities.validateSchema(response, "/Schemas/balances_1.json").toString());
        if (JsonUtilities.validateSchema(response, "/Schemas/balances_1.json").toString().contains("error")) {
            Assert.fail("Schemavalidation failed : issue with the response");
        }
    }

    @Then("I want to validate service Response and store the BalanceInfo with Status code {string}")
    public void iWantToValidateServiceResponseAndStoreTheBalanceInfoWithStatusCode(String Status_Code) throws org.json.JSONException {
        JsonPathEvaluator = Obj.getResponse(response);
        if (Status_Code.contentEquals("200")) {
            Assert.assertEquals("Test Failed", Integer.parseInt(Status_Code), response.getStatusCode());
            App_genericFunction.putcommentinStep("AccountId : " + JsonPathEvaluator.get("Data.Balance[0].AccountId"));
            App_genericFunction.putcommentinStep("Amount : " + JsonPathEvaluator.get("Data.Balance[0].Amount.Amount"));
            App_genericFunction.putcommentinStep("Currency : " + JsonPathEvaluator.get("Data.Balance[0].Amount.Currency"));
            App_genericFunction.putcommentinStep(response.print());

            /*JsonPathEvaluator1 = JsonUtilities.verifyResponse(App_genericFunction.getDatafromDatabase("600007","48894458"));
            Assert.assertEquals("Test Failed for Balances from DB ",JsonPathEvaluator.get("Data.Balance[0].Amount.Amount").toString(),JsonPathEvaluator1.get("ResponseMessage.Payload.AcAvlBal").toString());
            App_genericFunction.putcommentinStep ("Validation against Database Successfull");
            JsonPathEvaluator2 = JsonUtilities.verifyResponse(App_genericFunction.getDatafromDatabase_transaction("600002","48894458","27-02-2019","27-02-2019","D","GBP"));
            App_genericFunction.putcommentinStep("Transactions from DB for date range :"+JsonPathEvaluator2.get("ResponseMessage.Payload.ErrorMsg").toString());*/
            //AcAvlBal
            OBReadBalance res_values = response.as(OBReadBalance.class);
            App_genericFunction.putcommentinStep("Balances thru POJO : " + res_values.getData().getBalance().size());
            App_genericFunction.putcommentinStep("Accounts Amount thru POJO: " + res_values.getData().getBalance().get(0).getAmount().getAmount());

            //Java5
            for (int i = 0; i < res_values.getData().getBalance().size(); i++) {
                Obj.validateBalancetype(res_values.getData().getBalance().get(i).getType());
                 /*Assert.assertEquals("Test Failed for AccountID ", GlobalHooks.values.get(0).get("account_id"),JsonPathEvaluator.get("Data.Balance[0].AccountId").toString());
                 Assert.assertEquals("Test Failed for Amount ", GlobalHooks.values.get(0).get("amount"),JsonPathEvaluator.get("Data.Balance[0].Amount.Amount").toString());
                 Assert.assertEquals("Test Failed for Currency ", GlobalHooks.values.get(0).get("currency"),JsonPathEvaluator.get("Data.Balance[0].Amount.Currency").toString());
                 Assert.assertEquals("Test Failed for CreditDebitIndicator ", GlobalHooks.values.get(0).get("creditdebitindicator"),JsonPathEvaluator.get("Data.Balance[0].CreditDebitIndicator").toString());
                 Assert.assertEquals("Test Failed for type ", GlobalHooks.values.get(0).get("type"),JsonPathEvaluator.get("Data.Balance[0].Type").toString());*/
                Assert.assertEquals("Test Failed for AccountID ", GlobalHooks.values.get(i).get("account_id"), res_values.getData().getBalance().get(i).getAccountId().toString());
                Assert.assertEquals("Test Failed for Amount ", GlobalHooks.values.get(i).get("amount"), res_values.getData().getBalance().get(i).getAmount().getAmount().toString());
                Assert.assertEquals("Test Failed for Currency ", GlobalHooks.values.get(i).get("currency"), res_values.getData().getBalance().get(i).getAmount().getCurrency().toString());
                Assert.assertEquals("Test Failed for CreditDebitIndicator ", GlobalHooks.values.get(i).get("creditdebitindicator"), res_values.getData().getBalance().get(i).getCreditDebitIndicator().toString());
                Assert.assertEquals("Test Failed for type ", GlobalHooks.values.get(i).get("type"), res_values.getData().getBalance().get(i).getType().toString());

                for (int j = 0; j < res_values.getData().getBalance().get(i).getCreditLine().size(); j++) {
                    Assert.assertEquals("Test Failed for CreditLine Included ", App_genericFunction.ListofValues(GlobalHooks.values.get(j).get("creditline_included")).get(j), res_values.getData().getBalance().get(i).getCreditLine().get(j).getIncluded());
                    Assert.assertEquals("Test Failed for CreditLine Amounts ", App_genericFunction.ListofValues(GlobalHooks.values.get(j).get("creditline_amount")).get(j), res_values.getData().getBalance().get(i).getCreditLine().get(j).getAmount().getAmount());
                    Assert.assertEquals("Test Failed for CreditLine Currency ", App_genericFunction.ListofValues(GlobalHooks.values.get(j).get("creditline_currency")).get(j), res_values.getData().getBalance().get(i).getCreditLine().get(j).getAmount().getCurrency());
                    App_genericFunction.OptionalField("creditline_type", App_genericFunction.ListofValues(GlobalHooks.values.get(j).get("creditline_type")).get(j), res_values.getData().getBalance().get(i).getCreditLine().get(j).getType());
                    if (!GlobalHooks.values.get(j).get("creditline_type").isEmpty()) {
                        Obj.validateCreditlinetype(res_values.getData().getBalance().get(i).getCreditLine().get(j).getType());
                    }

                }
                App_genericFunction.putcommentinStep(response.print());
            }
            App_genericFunction.putcommentinStep("Validation against Excel source Successful");
           /* List<Balance> balance = res_values.getData().getBalance();
             //Java7
             for(Balance balance_block:balance)
             {
                 for(CreditLine c : balance_block.getCreditLine())
                 {
                     Assert.assertEquals("Test Failed for CreditLine Amounts ",c.getAmount().getAmount(),"0.00");
                     Assert.assertEquals("Test Failed for CreditLine Currency ",c.getAmount().getCurrency(),"GBP");
                 }
             }*/

            //Java8
               /*balance.forEach(balanceblock -> balanceblock.getCreditLine()
               .forEach(Creditline -> {
                           Assert.assertEquals("Test Failed for CreditLine Amounts ",Creditline.getAmount().getAmount(),"0.00");
                           Assert.assertEquals("Test Failed for CreditLine Currency ",Creditline.getAmount().getCurrency(),"GBP");
               }
               ));*/

        } else if (Status_Code.contentEquals("400")) {
            App_genericFunction.putcommentinStep("StatusCode : " + response.getStatusCode());
            Assert.assertEquals("Test Failed", Integer.parseInt(Status_Code), response.getStatusCode());
            App_genericFunction.putcommentinStep("ErrorCode : " + JsonPathEvaluator.get("Errors[0].ErrorCode"));
            App_genericFunction.putcommentinStep("Message : " + JsonPathEvaluator.get("Errors[0].Message"));
        } else if (Status_Code.contentEquals("401")) {
            Assert.assertEquals("Test Failed", Integer.parseInt(Status_Code), response.getStatusCode());
            App_genericFunction.putcommentinStep("StatusCode : " + response.getStatusCode());
            Assert.assertTrue("Error Message is not valid", response.asString().contains("Invalid Credentials. Make sure you have given the correct access token"));
            App_genericFunction.putcommentinStep((response.print()));
        }else if (Status_Code.contentEquals("403")) {
            Assert.assertEquals("Test Failed", Integer.parseInt(Status_Code), response.getStatusCode());
            App_genericFunction.putcommentinStep("StatusCode : " + response.getStatusCode() + " Forbidden");
            //Assert.assertTrue("Error Message is not valid", response.asString().contains("Invalid Credentials. Make sure you have given the correct access token"));
            App_genericFunction.putcommentinStep((response.print()));
        }
    }

    @Then("I verify the cases against Banking portal")
    public void iVerifyTheCasesAgainstBankingPortal() throws InterruptedException {
        JsonPathEvaluator = Obj.getResponse(response);
        general.getintoCAInternetBanking("", GlobalHooks.values.get(0).get("customer_no"), GlobalHooks.values.get(0).get("pac_no"), GlobalHooks.values.get(0).get("password"));

        seleniumHelper.enter("xpath://*[@class=' x-panel appicon-panel-wrap WGT_MY_ACC_SERVICES-app-btn x-column']/div/div/div[2]/center[text()='Account details']");
        seleniumHelper.enter("xpath://*[@class='x-panel-header x-unselectable']/span[text()='Account details']/..//following-sibling::div//descendant::table[@class ='x-table-layout']/tbody/tr[2]/descendant::img");
        seleniumHelper.waitForJSandJQueryToLoad();
        Actions a = new Actions(seleniumHelper.getdriver());
        WebElement dropdown = seleniumHelper.getElement("xpath://*[contains(text(),'" + GlobalHooks.values.get(0).get("currency") + "-" + GlobalHooks.values.get(0).get("account_id") + "')][1]");
        /// WebElement dropdown = seleniumHelper.getElement("xpath://*[@class='x-combo-list-item x-combo-selected' and contains(text(),'GBP-48894458')]");
        a.moveToElement(dropdown).click().build().perform();
        App_genericFunction.putScreenshotInStep();
        seleniumHelper.waitForJSandJQueryToLoad();
        int x;
        String Key = null;
        String value = null;
        LinkedHashMap data = new LinkedHashMap();
        Integer count_tr = driver.findElement(By.xpath("//*[@class='x-panel-header x-unselectable']/span[text()='Account details']/..//following-sibling::div//descendant::table[@class ='x-table-layout']/tbody")).findElements(By.tagName("tr")).size();
        for (x = 1; x < count_tr; x++) {
            SeleniumHelper.getdriver().manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
            if (seleniumHelper.getdriver().findElements(By.xpath("//*[@class='x-panel-header x-unselectable']/span[text()='Account details']/..//following-sibling::div//descendant::table[@class ='x-table-layout']/tbody/tr[" + x + "]/td/div/label")).size() != 0) {
                {
                    Thread.sleep(1000);
                    Key = driver.findElement(By.xpath("//*[@class='x-panel-header x-unselectable']/span[text()='Account details']/..//following-sibling::div//descendant::table[@class ='x-table-layout']/tbody/tr[" + x + "]/td/div/label")).getText();
                    if (seleniumHelper.getdriver().findElements(By.xpath("//*[@class='x-panel-header x-unselectable']/span[text()='Account details']/..//following-sibling::div//descendant::table[@class ='x-table-layout']/tbody/tr[" + x + "]/td/div/div/div")).size() != 0) {
                        value = driver.findElement(By.xpath("//*[@class='x-panel-header x-unselectable']/span[text()='Account details']/..//following-sibling::div//descendant::table[@class ='x-table-layout']/tbody/tr[" + x + "]/td/div/div/div")).getText();
                    }
                }
            }
            data.put(Key, value);
            SeleniumHelper.getdriver().manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        }
        App_genericFunction.putcommentinStep("Values from CA Internet Portal : " + data);
        Assert.assertEquals("Test Failed for AccountID ", JsonPathEvaluator.get("Data.Balance[0].AccountId").toString(), data.get("Account number"));
        Assert.assertEquals("Test Failed for Amount ", JsonPathEvaluator.get("Data.Balance[0].Amount.Amount").toString(), data.get("Current balance").toString().split(" ")[1].replace(",", ""));
        Assert.assertEquals("Test Failed for Currency ", JsonPathEvaluator.get("Data.Balance[0].Amount.Currency").toString(), data.get("Currency").toString().split(" ")[0]);
        App_genericFunction.putcommentinStep("Validation against InternetPortal Successfull");
        seleniumHelper.enter("id:logoff");
        //SeleniumHelper.getdriver().switchTo().alert().accept();
        seleniumHelper.enter("xpath://*[@class='x-btn portal_pos_btn x-btn-noicon']/tbody/tr[2]/td[2]/em/button");
        //*[@name='ACDT_ACC_NO']
        //driver.findElement(By.id("userNo")).sendKeys("123456");
        // driver.navigate().to("https://www.google.com/");
        // Thread.sleep(15000);

    }


    @Then("I verify the cases against Banking portal for Balances with response status code {string}")
    public void iVerifyTheCasesAgainstBankingPortalForBalancesWithResponseStatusCode(String Status_Code) throws InterruptedException, StopTestException {
        //JsonPathEvaluator = Obj.getResponse(response);
        if( Configuration.getConfiguration().getProperty("digibank").startsWith("enabled"))
        {
            OBReadBalance res_values = response.as(OBReadBalance.class);
            if (Status_Code.contentEquals("200")){
                Assert.assertEquals("Test Failed for Status code", Integer.parseInt(Status_Code), response.getStatusCode());
                for (int i = 0; i < res_values.getData().getBalance().size(); i++) {

                    Map<String, String> fromIE = new HashMap<>();
                    general.getintoCAInternetBanking("", GlobalHooks.values.get(i).get("customer_no"), GlobalHooks.values.get(i).get("pac_no"), GlobalHooks.values.get(i).get("password"));
                    account_details.clickOnMyAccountsServiceLink("Account details");
                    account_details.selctPersonalAccountType(res_values.getData().getBalance().get(i).getAmount().getCurrency(), res_values.getData().getBalance().get(i).getAccountId());
                    App_genericFunction.putScreenshotInStep();
                    //account_details.selctPersonalAccountType("GBP","90010553");
                    fromIE = account_details.getAccountCompleteDetails();
                    System.out.println(fromIE);

                    Obj.validateBalancetype(res_values.getData().getBalance().get(i).getType());
                    Assert.assertEquals("Test Failed for AccountID ", fromIE.get("Account Number"), res_values.getData().getBalance().get(i).getAccountId().toString());
                    Assert.assertEquals("Test Failed for Amount ", fromIE.get("Account Balance").split(" ")[1].replace(",", ""), res_values.getData().getBalance().get(i).getAmount().getAmount());
                    Assert.assertEquals("Test Failed for Currency ", fromIE.get("Account Currency").split(" ")[0], res_values.getData().getBalance().get(i).getAmount().getCurrency());
                    if (res_values.getData().getBalance().get(i).getAmount().getAmount() == "0.00") {
                        Assert.assertEquals("Test Failed for CreditDebitIndicator ", "Credit", res_values.getData().getBalance().get(i).getCreditDebitIndicator());
                    }
                    //Assert.assertEquals("Test Failed for type ", fromIE.get("type"), res_values.getData().getBalance().get(i).getType().toString());

                /*for (int j = 0; j < res_values.getData().getBalance().get(i).getCreditLine().size(); j++) {
                    Assert.assertEquals("Test Failed for CreditLine Included ", App_genericFunction.ListofValues(GlobalHooks.values.get(j).get("creditline_included")).get(j), res_values.getData().getBalance().get(i).getCreditLine().get(j).getIncluded());
                    Assert.assertEquals("Test Failed for CreditLine Amounts ", App_genericFunction.ListofValues(GlobalHooks.values.get(j).get("creditline_amount")).get(j), res_values.getData().getBalance().get(i).getCreditLine().get(j).getAmount().getAmount());
                    Assert.assertEquals("Test Failed for CreditLine Currency ", App_genericFunction.ListofValues(GlobalHooks.values.get(j).get("creditline_currency")).get(j), res_values.getData().getBalance().get(i).getCreditLine().get(j).getAmount().getCurrency());
                    App_genericFunction.OptionalField("creditline_type", App_genericFunction.ListofValues(GlobalHooks.values.get(j).get("creditline_type")).get(j), res_values.getData().getBalance().get(i).getCreditLine().get(j).getType());
                    if (!GlobalHooks.values.get(j).get("creditline_type").isEmpty()) {
                        Obj.validateCreditlinetype(res_values.getData().getBalance().get(i).getCreditLine().get(j).getType());
                    }
                }*/
                    App_genericFunction.putcommentinStep(response.print());
                    App_genericFunction.putScreenshotInStep();
                }
                seleniumHelper.enter("id:logoff");
                seleniumHelper.enter("xpath://*[@class='x-btn portal_pos_btn x-btn-noicon']/tbody/tr[2]/td[2]/em/button");
            }
        }else
            {
                Assert.assertEquals("Test Failed for Status code", Integer.parseInt(Status_Code), response.getStatusCode());
                App_genericFunction.putcommentinStep(response.getStatusCode());
                response.prettyPrint();
            }

    }
}
