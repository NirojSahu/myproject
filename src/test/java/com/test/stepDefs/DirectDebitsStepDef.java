package com.test.stepDefs;

import Responses.OBReadDirectDebits1.OBReadDirectDebit;
import Utilities.JsonUtilities;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.google.inject.Inject;
import com.test.APIFunctions.DirectDebitsService;
import com.test.CustomHooks.GlobalHooks;
import com.test.Utils.App_genericFunction;
import com.test.configuration.Configuration;
import com.test.exceptions.StopTestException;
import com.test.pages.Directdebit;
import com.test.pages.MyAccounts;
import com.test.pages.PaymentServices;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.codehaus.jettison.json.JSONException;
import org.junit.Assert;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class DirectDebitsStepDef {
    @Inject
    DirectDebitsService Obj;
    @Inject
    App_genericFunction general;
    @Inject
    PaymentServices payment_details;
    @Inject
    MyAccounts account_details;
    @Inject
    Directdebit direct_debit;

    public String Verbose;
    Response response;
    JsonPath JsonPathEvaluator;
    public static String USER_DI= System.getProperty("user.dir");

    @Given("I have the data available to create {string} request for direct debits_service with Headers")
    public void i_have_the_data_available_to_create_request_for_direct_debits_service_with_Headers(String Method) throws JsonProcessingException, FileNotFoundException, JSONException {
        Verbose = Method;
        if(Verbose.contentEquals("POST"))
        {

        }else if(Verbose.contentEquals("GET"))
        {
            Obj.accountsServiceSetHeaders_get();
        }
    }
    @Then("I want to validate direct debits service Response for Valid Schema")
    public void iWantToValidateDirectDebitsServiceResponseForValidSchema() throws IOException, ProcessingException {
        System.out.println(response.getBody().asString());
        App_genericFunction.putcommentinStep(JsonUtilities.validateSchema(response,"/Schemas/DirectDebits_1.json").toString());
        if(JsonUtilities.validateSchema(response,"/Schemas/DirectDebits_1.json").toString().contains("error"))
        {
            Assert.fail("Schemavalidation failed : issue with the response");
        }
        //response.then().assertThat().body(matchesJsonSchemaInClasspath("Schemas/account-access-consents_1.json"));
    }
    @When("I want to make a call to direct debits_service")
    public void iWantToMakeACallToDirectDebits_service() {
        response = Obj.hitAccountService_get();
    }


    @Then("I want to validate direct debits service Response and store the ConsentID with Status code {string}")
    public void i_want_to_validate_direct_debits_service_Response_and_store_the_ConsentID_with_Status_code(String Status_Code) {
        JsonPathEvaluator= Obj.getResponse(response);
        if(Status_Code.contentEquals("200"))
        {
            Assert.assertEquals("Test Failed",Integer.parseInt(Status_Code),response.getStatusCode());
            OBReadDirectDebit Obj1 = response.as(OBReadDirectDebit.class);
            try
            {
            for(int i =0 ;i<Obj1.getData().getDirectDebit().size();i++)
            {
                if(!Obj1.getData().getDirectDebit().get(i).getDirectDebitStatusCode().isEmpty()) {
                    Obj.validateDirecrDebitStatusCode(Obj1.getData().getDirectDebit().get(i).getDirectDebitStatusCode());
                }
            }

            for(int i =0 ;i<Obj1.getData().getDirectDebit().size();i++)
            {
                Assert.assertEquals("Test Failed",GlobalHooks.values.get(i).get("accountid"),Obj1.getData().getDirectDebit().get(i).getAccountId());
                App_genericFunction.OptionalField("directdebit_id",GlobalHooks.values.get(i).get("directdebit_id"),Obj1.getData().getDirectDebit().get(i).getDirectDebitId());
                Assert.assertEquals("Test Failed",GlobalHooks.values.get(i).get("mandateidentification"),Obj1.getData().getDirectDebit().get(i).getMandateIdentification());
                App_genericFunction.OptionalField("directdebitstatus_code",GlobalHooks.values.get(i).get("directdebitstatus_code"),Obj1.getData().getDirectDebit().get(i).getDirectDebitStatusCode());
                Assert.assertEquals("Test Failed",GlobalHooks.values.get(i).get("name"),Obj1.getData().getDirectDebit().get(i).getName());
                App_genericFunction.OptionalField("previouspayment_datetime",GlobalHooks.values.get(i).get("previouspayment_datetime"),Obj1.getData().getDirectDebit().get(i).getPreviousPaymentDateTime());
                if(Obj1.getData().getDirectDebit().get(i).getPreviousPaymentAmount() != null && (!GlobalHooks.values.get(i).get("previouspayment_amount").isEmpty()))
                {
                    Assert.assertEquals("Test Failed",GlobalHooks.values.get(i).get("previouspayment_amount"),Obj1.getData().getDirectDebit().get(i).getPreviousPaymentAmount().getAmount());
                    general.validateReularExpression("^\\d{1,13}\\.\\d{1,5}$", Obj1.getData().getDirectDebit().get(i).getPreviousPaymentAmount().getAmount());
                    Assert.assertEquals("Test Failed",GlobalHooks.values.get(i).get("previouspayment_currency"),Obj1.getData().getDirectDebit().get(i).getPreviousPaymentAmount().getCurrency());
                }else
                {
                    App_genericFunction.putcommentinStep("Error : Optional Field previouspayment_amount Skipped");
                    Assert.fail();
                }
            }

 //           Assert.assertEquals("Test Failed",GlobalHooks.values.get(0).get("accountid"),response.jsonPath().get("Data.Beneficiary[0].AccountId").toString());
//            Assert.assertEquals("Test Failed",GlobalHooks.values.get(0).get("beneficiary_id"),response.jsonPath().get("Data.Beneficiary[0].BeneficiaryId").toString());
//            Assert.assertEquals("Test Failed",GlobalHooks.values.get(0).get("reference"),response.jsonPath().get("Data.Beneficiary[0].Reference").toString());
            App_genericFunction.putcommentinStep (response.print());

            }catch (NullPointerException exception){
                System.out.println("There is No DirectDebit Available");
            }
        }

            if (Status_Code.contentEquals("401"))
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

    @Then("I verify the cases against Banking portal for DirectDebit with response status code {string}")
    public void iVerifyTheCasesAgainstBankingPortalForDirectDebitWithResponseStatusCode(String Status_Code) throws InterruptedException, StopTestException {
        if( Configuration.getConfiguration().getProperty("digibank").startsWith("enabled")) {
            if (Status_Code.contentEquals("200")) {
                Assert.assertEquals("Test Failed", Integer.parseInt(Status_Code), response.getStatusCode());
                OBReadDirectDebit Obj1 = response.as(OBReadDirectDebit.class);
                general.getintoCAInternetBanking("", GlobalHooks.values.get(0).get("customer_no"), GlobalHooks.values.get(0).get("pac_no"), GlobalHooks.values.get(0).get("password"));
                payment_details.clickOnPaymentServiceLink("Payment Services");
                Thread.sleep(2000);
                payment_details.clickOnPaymentServiceLink("Direct Debits");
                Thread.sleep(3000);
                account_details.selctPersonalAccountType(GlobalHooks.values.get(0).get("currency"), GlobalHooks.values.get(0).get("accountid"));
                Thread.sleep(3000);
                App_genericFunction.putScreenshotInStep();
                try {
                    for (int i = 0; i < Obj1.getData().getDirectDebit().size(); i++) {
                        if (!Obj1.getData().getDirectDebit().get(i).getDirectDebitStatusCode().isEmpty()) {
                            Obj.validateDirecrDebitStatusCode(Obj1.getData().getDirectDebit().get(i).getDirectDebitStatusCode());
                        }
                    }
                } catch (Exception e) {
                    System.out.println("No Data Returned from Service");
                }
                Thread.sleep(3000);
                Map<String, String> accountCompleteDetails = new LinkedHashMap<String, String>();

                try {
                    for (int i = 0; i < Obj1.getData().getDirectDebit().size(); i++) {
                        accountCompleteDetails = direct_debit.getViewDirectDebitData(Obj1.getData().getDirectDebit().get(i).getName(), Obj1.getData().getDirectDebit().get(i).getPreviousPaymentAmount().getAmount(), Obj1.getData().getDirectDebit().get(i).getDirectDebitStatusCode().toUpperCase());
                        System.out.println("---------------------------------------");
                        System.out.println(accountCompleteDetails);
                        System.out.println("---------------------------------------");
                        if (accountCompleteDetails.get("KEY") == "EMPTY") {
                            Assert.fail("Debit Information not there for Account");
                            System.out.println("Debit Information not there for Account");
                        } else {
                            Assert.assertEquals("Test Failed", accountCompleteDetails.get("Debit Account"), Obj1.getData().getDirectDebit().get(i).getAccountId());
                            //App_genericFunction.OptionalField("directdebit_id",GlobalHooks.values.get(i).get("directdebit_id"),Obj1.getData().getDirectDebit().get(i).getDirectDebitId());
                            Assert.assertEquals("Test Failed", accountCompleteDetails.get("originator Identification Number"), Obj1.getData().getDirectDebit().get(i).getMandateIdentification());
                            //App_genericFunction.OptionalField("directdebitstatus_code",GlobalHooks.values.get(i).get("directdebitstatus_code"),Obj1.getData().getDirectDebit().get(i).getDirectDebitStatusCode());
                            Assert.assertEquals("Test Failed", accountCompleteDetails.get("Payee Name"), Obj1.getData().getDirectDebit().get(i).getName());
                            //App_genericFunction.OptionalField("previouspayment_datetime",GlobalHooks.values.get(i).get("previouspayment_datetime"),Obj1.getData().getDirectDebit().get(i).getPreviousPaymentDateTime());
                            if (Obj1.getData().getDirectDebit().get(i).getPreviousPaymentAmount() != null) {
                                Assert.assertEquals("Test Failed", accountCompleteDetails.get("Last Amount"), Obj1.getData().getDirectDebit().get(i).getPreviousPaymentAmount().getAmount());
                                general.validateReularExpression("^\\d{1,13}\\.\\d{1,5}$", Obj1.getData().getDirectDebit().get(i).getPreviousPaymentAmount().getAmount());
                                Assert.assertEquals("Test Failed", accountCompleteDetails.get("Currency Type"), Obj1.getData().getDirectDebit().get(i).getPreviousPaymentAmount().getCurrency());
                                general.validateReularExpression("^[A-Z]{3,3}$", Obj1.getData().getDirectDebit().get(i).getPreviousPaymentAmount().getCurrency());
                            } else {
                                App_genericFunction.putcommentinStep("Error : Optional Field previouspayment_amount Skipped");
                                Assert.fail();
                            }
                            accountCompleteDetails.clear();
                        }
                    }
                } catch (Exception e) {
                    accountCompleteDetails = direct_debit.getViewDirectDebitData("", "", "");
                    System.out.println("---------------------------------------");
                    System.out.println(accountCompleteDetails);
                    System.out.println("---------------------------------------");
                    if (accountCompleteDetails.get("KEY") == "EMPTY") {
                        System.out.println("Debit Information not there for Account");
                    }
                }
                System.out.println("Response : " + response.prettyPrint());
            }
            else if (Status_Code.contentEquals("400")) {
                App_genericFunction.putcommentinStep("StatusCode : " + response.getStatusCode());
                Assert.assertEquals("Test Failed", Integer.parseInt(Status_Code), response.getStatusCode());
                App_genericFunction.putcommentinStep("ErrorCode : " + JsonPathEvaluator.get("Errors[0].ErrorCode"));
                App_genericFunction.putcommentinStep("Message : " + JsonPathEvaluator.get("Errors[0].Message"));
            }
            else if (Status_Code.contentEquals("401")) {
                Assert.assertEquals("Test Failed", Integer.parseInt(Status_Code), response.getStatusCode());
                App_genericFunction.putcommentinStep("StatusCode : " + response.getStatusCode());
                Assert.assertTrue("Error Message is not valid", response.asString().contains("Invalid Credentials. Make sure you have given the correct access token"));
                App_genericFunction.putcommentinStep((response.print()));
            }
            else if (Status_Code.contentEquals("403")) {
                Assert.assertEquals("Test Failed", Integer.parseInt(Status_Code), response.getStatusCode());
                App_genericFunction.putcommentinStep("StatusCode : " + response.getStatusCode() + " Forbidden");
                //Assert.assertTrue("Error Message is not valid", response.asString().contains("Invalid Credentials. Make sure you have given the correct access token"));
                App_genericFunction.putcommentinStep((response.print()));
            }
        }else
        {
            response.prettyPrint();
        }
    }
}
