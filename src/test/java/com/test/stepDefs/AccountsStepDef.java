package com.test.stepDefs;

import Responses.OBReadAccounts3.OBReadAccounts;
import Responses.OBReadAccounts3.SortbyAccount;
import Utilities.JsonUtilities;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.google.inject.Inject;
import com.test.APIFunctions.AccountsService;
import com.test.CustomHooks.GlobalHooks;
import com.test.Utils.App_genericFunction;
import com.test.Utils.SeleniumHelper;
import com.test.configuration.Configuration;
import com.test.exceptions.StopTestException;
import com.test.pages.MyAccounts;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.codehaus.jettison.json.JSONException;
import org.junit.Assert;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;


public class AccountsStepDef {
    @Inject
    AccountsService Obj;

    @Inject
    SeleniumHelper seleniumHelper;
    @Inject
    App_genericFunction general;
    @Inject
    MyAccounts account_details;

    public String Verbose;
    Response response;
    JsonPath JsonPathEvaluator;
    public static String USER_DI= System.getProperty("user.dir");

    @Given("I have the data available to create {string} request for accounts_service with Headers")
    public void i_have_the_data_available_to_create_request_for_accountaccessconsent_service_with_Headers(String Method) throws JsonProcessingException, FileNotFoundException, JSONException {
        Verbose = Method;
        if(Verbose.contentEquals("POST"))
        {

        }else if(Verbose.contentEquals("GET"))
        {
            Obj.accountsServiceSetHeaders_get();
        }
    }
    @Then("I want to validate accounts service Response for Valid Schema")
    public void iWantToValidateAccountsServiceResponseForValidSchema() throws IOException, ProcessingException {
        System.out.println(response.getBody().asString());
        App_genericFunction.putcommentinStep(JsonUtilities.validateSchema(response,"/Schemas/accounts_1.json").toString());
        //response.then().assertThat().body(matchesJsonSchemaInClasspath("Schemas/account-access-consents_1.json"));
        if(JsonUtilities.validateSchema(response,"/Schemas/accounts_1.json").toString().contains("error"))
        {
            Assert.fail("Schemavalidation failed : issue with the response");
        }
    }
    @Then("I want to validate specific accounts service Response for Valid Schema")
    public void iWantToValidateSpecificAccountsServiceResponseForValidSchema() throws IOException, ProcessingException {
        System.out.println(response.getBody().asString());
        App_genericFunction.putcommentinStep(JsonUtilities.validateSchema(response,"/Schemas/accounts_2.json").toString());
        //response.then().assertThat().body(matchesJsonSchemaInClasspath("Schemas/account-access-consents_1.json"));
        if(JsonUtilities.validateSchema(response,"/Schemas/accounts_2.json").toString().contains("error"))
        {
            Assert.fail("Schemavalidation failed : issue with the response");
        }
    }
    @When("I want to make a call to accounts_service to get consentID status")
    public void iWantToMakeACallToAccounts_serviceToGetConsentIDStatus() {
        response = Obj.hitService_get();
    }
    @When("I want to make a call to specific accounts_service to get consentID status")
    public void iWantToMakeACallToSpecificAccounts_serviceToGetConsentIDStatus() {
        response = Obj.hitAccountService_get();
    }

    @Then("I want to validate accounts service Response and store with Status code {string}")
    public void i_want_to_validate_accounts_service_Response_and_store_with_Status_code(String Status_Code) {
        JsonPathEvaluator= Obj.getResponse(response);
        if(Status_Code.contentEquals("200"))
        {
            OBReadAccounts Obj1 = response.as(OBReadAccounts.class);
            Collections.sort(Obj1.getData().getAccount(), new SortbyAccount());
            for(int i =0;i<Obj1.getData().getAccount().size();i++)
            {
                Obj.validateAccountSubType(Obj1.getData().getAccount().get(i).getAccountSubType());
                Obj.validateAccountType(Obj1.getData().getAccount().get(i).getAccountType());
            }

            for(int i =0;i<Obj1.getData().getAccount().size();i++)
            {
                System.out.println("Accounts Validation : " + i);
                Assert.assertEquals("Test Failed",GlobalHooks.values.get(i).get("accountid"),Obj1.getData().getAccount().get(i).getAccountId() );
                Assert.assertEquals("Test Failed",GlobalHooks.values.get(i).get("accountsubtype"),Obj1.getData().getAccount().get(i).getAccountSubType());
                Assert.assertEquals("Test Failed" ,GlobalHooks.values.get(i).get("accounttype"),Obj1.getData().getAccount().get(i).getAccountType());
                Assert.assertEquals("Test Failed",GlobalHooks.values.get(i).get("currency"),Obj1.getData().getAccount().get(i).getCurrency() );
                App_genericFunction.OptionalField("nickname",GlobalHooks.values.get(i).get("nickname"),Obj1.getData().getAccount().get(i).getCurrency());
                if((Obj1.getData().getAccount().get(i).getSubAccount() !=null) && (!GlobalHooks.values.get(i).get("account_identification").isEmpty())) {
                    for (int j = 0; j < Obj1.getData().getAccount().get(i).getSubAccount().size(); j++) {
                        Assert.assertEquals("Test Failed", App_genericFunction.ListofValues(GlobalHooks.values.get(i).get("account_identification")).get(j),Obj1.getData().getAccount().get(i).getSubAccount().get(j).getIdentification());
                        App_genericFunction.OptionalField("account_name",App_genericFunction.ListofValues( GlobalHooks.values.get(i).get("account_name")).get(j),Obj1.getData().getAccount().get(i).getSubAccount().get(j).getName());
                        Assert.assertEquals("Test Failed", App_genericFunction.ListofValues(GlobalHooks.values.get(i).get("account_schemename")).get(j),Obj1.getData().getAccount().get(i).getSubAccount().get(j).getSchemeName());
                    }
                }else
                {
                    App_genericFunction.putcommentinStep("Error : Optional Field Balance Skipped");
                    Assert.fail();
                }

            }
            App_genericFunction.putcommentinStep("Total Accounts : " + Obj1.getData().getAccount().size());
            App_genericFunction.putcommentinStep(response.print());
            /*Assert.assertEquals("Test Failed", GlobalHooks.values.get(0).get("ClientID"),response.getHeader("x-wso2-client-id"));
            Assert.assertEquals("Test Failed",Integer.parseInt(Status_Code),response.getStatusCode());
            Assert.assertEquals("Test Failed",Integer.parseInt(Status_Code),response.getStatusCode());
            Assert.assertEquals("Test Failed",GlobalHooks.values.get(0).get("accountid"),response.jsonPath().get("Data.Account[0].AccountId").toString());
            Assert.assertEquals("Test Failed",GlobalHooks.values.get(0).get("currency"),response.jsonPath().get("Data.Account[0].Currency").toString());
            Assert.assertEquals("Test Failed",GlobalHooks.values.get(0).get("accounttype"),response.jsonPath().get("Data.Account[0].AccountType").toString());
            Assert.assertEquals("Test Failed",GlobalHooks.values.get(0).get("accountsubtype"),response.jsonPath().get("Data.Account[0].AccountSubType").toString());
            Assert.assertEquals("Test Failed",GlobalHooks.values.get(0).get("nickname"),response.jsonPath().get("Data.Account[0].Nickname").toString());*/
        }else if (Status_Code.contentEquals("401"))
        {
            Assert.assertEquals("Test Failed",Integer.parseInt(Status_Code),response.getStatusCode());
            App_genericFunction.putcommentinStep("StatusCode : "+response.getStatusCode());
            Assert.assertTrue("Error Message is not valid",response.asString().contains("Invalid Credentials. Make sure you have given the correct access token"));
            App_genericFunction.putcommentinStep((response.print()));

        }else if (Status_Code.contentEquals("400"))
        {
            App_genericFunction.putcommentinStep("StatusCode : "+response.getStatusCode());
            Assert.assertEquals("Test Failed",Integer.parseInt(Status_Code),response.getStatusCode());
            App_genericFunction.putcommentinStep("ErrorCode : "+JsonPathEvaluator.get("Errors[0].ErrorCode"));
            App_genericFunction.putcommentinStep("Message : "+JsonPathEvaluator.get("Errors[0].Message"));
        }
    }

    @Then("I want to validate allaccounts service Response and store with Status code {string}")
    public void iWantToValidateAllaccountsServiceResponseAndStoreWithStatusCode(String arg0) {
        Assert.assertEquals("Test Failed",Integer.parseInt(arg0),response.getStatusCode());
        OBReadAccounts Obj1 = response.as(OBReadAccounts.class);
        Collections.sort(Obj1.getData().getAccount(), new SortbyAccount());
        for(int i =0;i<Obj1.getData().getAccount().size();i++)
        {
            Obj.validateAccountSubType(Obj1.getData().getAccount().get(i).getAccountSubType());
            Obj.validateAccountType(Obj1.getData().getAccount().get(i).getAccountType());
        }
        App_genericFunction.putcommentinStep("Total Number of Accounts :"+ Obj1.getData().getAccount().size());
        Assert.assertEquals("Test failed for total accounts",GlobalHooks.values.get(0).get("accountid").split(",").length,Obj1.getData().getAccount().size());
        for(int i =0;i<Obj1.getData().getAccount().size();i++)
        {
            App_genericFunction.putcommentinStep("Account-"+i+" "+Obj1.getData().getAccount().get(i).getAccountId());
            Assert.assertFalse("Test Failed for Accounts Returned",(Obj1.getData().getAccount().get(i).getAccountId().contains(GlobalHooks.values.get(0).get("accountid"))));
        }
    }

    @Then("I verify the cases against Banking portal for account with response status code {string}")
    public void iVerifyTheCasesAgainstBankingPortalForAccountWithResponseStatusCode(String Status_Code) throws InterruptedException, StopTestException {
        if( Configuration.getConfiguration().getProperty("digibank").startsWith("enabled")) {
            if (Status_Code.contentEquals("200")) {
                OBReadAccounts Obj1 = response.as(OBReadAccounts.class);
                Collections.sort(Obj1.getData().getAccount(), new SortbyAccount());
                for (int i = 0; i < Obj1.getData().getAccount().size(); i++) {
                    Obj.validateAccountSubType(Obj1.getData().getAccount().get(i).getAccountSubType());
                    Obj.validateAccountType(Obj1.getData().getAccount().get(i).getAccountType());
                }
                List<String> account_types = new ArrayList<>();
                account_types.add("Personal");
                account_types.add("Business");
                for (int i = 0; i < Obj1.getData().getAccount().size(); i++) {
                    Map<String, String> fromIE = new HashMap<>();

                    general.getintoCAInternetBanking("", GlobalHooks.values.get(i).get("customer_no"), GlobalHooks.values.get(i).get("pac_no"), GlobalHooks.values.get(i).get("password"));
                    account_details.clickOnMyAccountsServiceLink("Account details");
                    account_details.selctPersonalAccountType(Obj1.getData().getAccount().get(i).getCurrency(), Obj1.getData().getAccount().get(i).getAccountId());
                    App_genericFunction.putScreenshotInStep();
                    //account_details.selctPersonalAccountType("GBP","90010553");
                    fromIE = account_details.getAccountCompleteDetails();
                    App_genericFunction.putScreenshotInStep();
                    App_genericFunction.putcommentinStep("Account_details from Web : " + fromIE);
                    System.out.println("Accounts Validation : " + i+1);
                    Assert.assertEquals("Test Failed", fromIE.get("Account Number"), Obj1.getData().getAccount().get(i).getAccountId());
                    //Assert.assertEquals("Test Failed",GlobalHooks.values.get(i).get("accountsubtype"),Obj1.getData().getAccount().get(i).getAccountSubType());
                    //Assert.assertEquals("Test Failed" ,"Personal",Obj1.getData().getAccount().get(i).getAccountType());
                    Assert.assertTrue("Test Failed", account_types.contains(Obj1.getData().getAccount().get(i).getAccountType()));
                    Assert.assertEquals("Test Failed", fromIE.get("Account Currency").split(" ")[0], Obj1.getData().getAccount().get(i).getCurrency());
                    Assert.assertEquals("Test Failed", fromIE.get("accountStatus"), "ACTIVE");
                    //App_genericFunction.OptionalField("nickname",GlobalHooks.values.get(i).get("nickname"),Obj1.getData().getAccount().get(i).getCurrency());
                    // if((Obj1.getData().getAccount().get(i).getSubAccount() !=null) && (!GlobalHooks.values.get(i).get("account_identification").isEmpty())) {
                    for (int j = 0; j < Obj1.getData().getAccount().get(i).getSubAccount().size(); j++) {
                        //Assert.assertEquals("Test Failed", App_genericFunction.ListofValues(GlobalHooks.values.get(i).get("account_identification")).get(j),Obj1.getData().getAccount().get(i).getSubAccount().get(j).getIdentification());
                        //App_genericFunction.OptionalField("account_name",App_genericFunction.ListofValues( GlobalHooks.values.get(i).get("account_name")).get(j),Obj1.getData().getAccount().get(i).getSubAccount().get(j).getName());
                        App_genericFunction.OptionalField("account_name", fromIE.get("Account Name Details"), Obj1.getData().getAccount().get(i).getSubAccount().get(j).getName());
                        //Assert.assertEquals("Test Failed", App_genericFunction.ListofValues(GlobalHooks.values.get(i).get("account_schemename")).get(j),Obj1.getData().getAccount().get(i).getSubAccount().get(j).getSchemeName());
                    }
                /*}else
                {
                    App_genericFunction.putcommentinStep("Error : Optional Field Balance Skipped");
                    Assert.fail();
                }*/

                }
                App_genericFunction.putcommentinStep("Total Accounts : " + Obj1.getData().getAccount().size());
                App_genericFunction.putcommentinStep(response.print());
            } else if (Status_Code.contentEquals("400")) {
                JsonPathEvaluator = Obj.getResponse(response);
                App_genericFunction.putcommentinStep("StatusCode : " + response.getStatusCode());
                Assert.assertEquals("Test Failed", Integer.parseInt(Status_Code), response.getStatusCode());
                App_genericFunction.putcommentinStep("ErrorCode : " + JsonPathEvaluator.get("Errors[0].ErrorCode"));
                App_genericFunction.putcommentinStep("Message : " + JsonPathEvaluator.get("Errors[0].Message"));
            } else if (Status_Code.contentEquals("403")) {
                JsonPathEvaluator = Obj.getResponse(response);
                Assert.assertEquals("Test Failed", Integer.parseInt(Status_Code), response.getStatusCode());
                App_genericFunction.putcommentinStep("StatusCode : " + response.getStatusCode() + " Forbidden");
                //Assert.assertTrue("Error Message is not valid", response.asString().contains("Invalid Credentials. Make sure you have given the correct access token"));
                App_genericFunction.putcommentinStep((response.print()));
            }
        } else
        {
            response.prettyPrint();
        }
    }
}
