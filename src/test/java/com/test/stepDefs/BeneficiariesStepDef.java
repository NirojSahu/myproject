package com.test.stepDefs;

import Responses.OBReadBeneficiaries3.OBReadBeneficiaries;
import Utilities.JsonUtilities;
import Utilities.WebKit.configuration.Configuration;
import Utilities.WebKit.exceptions.StopTestException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.google.inject.Inject;
import com.test.APIFunctions.BeneficiariesService;
import com.test.CustomHooks.GlobalHooks;
import com.test.Utils.App_genericFunction;
import com.test.Utils.SeleniumHelper;
//import com.test.configuration.Configuration;
//import com.test.exceptions.StopTestException;
import com.test.pages.Managepayee;
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

public class BeneficiariesStepDef {
    @Inject
    BeneficiariesService Obj;

    @Inject
    App_genericFunction general;

    @Inject
    PaymentServices payment_details;

    @Inject
    Managepayee Payment_detail;

    @Inject
    SeleniumHelper selenium_Helper;

    public String Verbose;
    Response response;
    JsonPath JsonPathEvaluator;
    public static String USER_DI= System.getProperty("user.dir");

    @Given("I have the data available to create {string} request for beneficiaries_service with Headers")
    public void i_have_the_data_available_to_create_request_for_beneficiaries_service_with_Headers(String Method) throws JsonProcessingException, FileNotFoundException, JSONException {
        Verbose = Method;
        if(Verbose.contentEquals("POST"))
        {

        }else if(Verbose.contentEquals("GET"))
        {
            Obj.accountsServiceSetHeaders_get();
        }
    }
    @Then("I want to validate beneficiaries service Response for Valid Schema")
    public void iWantToValidateBeneficiariesServiceResponseForValidSchema() throws IOException, ProcessingException {
        System.out.println(response.getBody().asString());
        App_genericFunction.putcommentinStep(JsonUtilities.validateSchema(response,"/Schemas/beneficiaries_1.json").toString());
        //response.then().assertThat().body(matchesJsonSchemaInClasspath("Schemas/account-access-consents_1.json"));
        if(JsonUtilities.validateSchema(response,"/Schemas/beneficiaries_1.json").toString().contains("error"))
        {
            Assert.fail("Schemavalidation failed : issue with the response");
        }
    }
    @When("I want to make a call to beneficiaries_service to get consentID status")
    public void iWantToMakeACallToBeneficiaries_serviceToGetConsentIDStatus() {
        response = Obj.hitAccountService_get();
    }


    @Then("I want to validate beneficiaries service Response and store the ConsentID with Status code {string}")
    public void i_want_to_validate_beneficiaries_service_Response_and_store_the_ConsentID_with_Status_code(String Status_Code) {
        JsonPathEvaluator= Obj.getResponse(response);

        if(Status_Code.contentEquals("200"))
        {
            Assert.assertEquals("Test Failed",Integer.parseInt(Status_Code),response.getStatusCode());
            Assert.assertEquals("Test Failed", GlobalHooks.values.get(0).get("ClientID"),response.getHeader("x-wso2-client-id"));

            OBReadBeneficiaries Obj1 = response.as(OBReadBeneficiaries.class);
            try
            {
            for(int i =0;i<Obj1.getData().getBeneficiary().size();i++)
            {
                App_genericFunction.OptionalField("accountid",GlobalHooks.values.get(i).get("accountid"),Obj1.getData().getBeneficiary().get(i).getAccountId());
                App_genericFunction.OptionalField("beneficiary_id",GlobalHooks.values.get(i).get("beneficiary_id"),Obj1.getData().getBeneficiary().get(i).getBeneficiaryId());
                App_genericFunction.OptionalField("reference",GlobalHooks.values.get(i).get("reference"),Obj1.getData().getBeneficiary().get(i).getReference());
                if(Obj1.getData().getBeneficiary().get(i).getCreditorAccount() !=null && (!GlobalHooks.values.get(i).get("ca_identification").isEmpty())) {
                    Assert.assertEquals("Test Failed", GlobalHooks.values.get(i).get("ca_identification"), Obj1.getData().getBeneficiary().get(i).getCreditorAccount().getIdentification());
                    App_genericFunction.OptionalField( "ca_name",GlobalHooks.values.get(i).get("ca_name"),Obj1.getData().getBeneficiary().get(i).getCreditorAccount().getName());
                    Assert.assertEquals("Test Failed", GlobalHooks.values.get(i).get("ca_schemename"), Obj1.getData().getBeneficiary().get(i).getCreditorAccount().getSchemeName());
                }else
                {
                    App_genericFunction.putcommentinStep("Error : Optional Field CreditAccount Skipped");
                    Assert.fail();
                }

            }
            App_genericFunction.putcommentinStep("-----:Validation completed for Beneficiaries:----");
            App_genericFunction.putcommentinStep ("Total Beneficiarie Count : "+ Obj1.getData().getBeneficiary().size());
           /* Assert.assertEquals("Test Failed",GlobalHooks.values.get(0).get("accountid"),response.jsonPath().get("Data.Beneficiary[0].AccountId").toString());
            Assert.assertEquals("Test Failed",GlobalHooks.values.get(0).get("accountid"),response.jsonPath().get("Data.Beneficiary[0].AccountId").toString());
            Assert.assertEquals("Test Failed",GlobalHooks.values.get(0).get("beneficiary_id"),response.jsonPath().get("Data.Beneficiary[0].BeneficiaryId").toString());
            Assert.assertEquals("Test Failed",GlobalHooks.values.get(0).get("reference"),response.jsonPath().get("Data.Beneficiary[0].Reference").toString());*/
           App_genericFunction.putcommentinStep (response.print());

            }catch (NullPointerException exception){
                System.out.println("There is No Beneficiaries Available");
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

    @Then("I verify the cases against Banking portal for Beneficiaries with response status code {string}")
    public void iVerifyTheCasesAgainstBankingPortalForBeneficiariesWithResponseStatusCode(String Status_Code) throws InterruptedException, StopTestException {
        if( Configuration.getConfiguration().getProperty("digibank").startsWith("enabled")) {
            if (Status_Code.contentEquals("200")) {
                Assert.assertEquals("Test Failed", Integer.parseInt(Status_Code), response.getStatusCode());
                OBReadBeneficiaries Obj1 = response.as(OBReadBeneficiaries.class);
                general.getintoCAInternetBanking("", GlobalHooks.values.get(0).get("customer_no"), GlobalHooks.values.get(0).get("pac_no"), GlobalHooks.values.get(0).get("password"));
                payment_details.clickOnPaymentServiceLink("Payment Services");
                Thread.sleep(2000);
                payment_details.clickOnPaymentServiceLink("Manage Payee");
                //general.getintoCAInternetBanking("", GlobalHooks.values.get(0).get("customer_no"),GlobalHooks.values.get(0).get("pac_no"), GlobalHooks.values.get(0).get("password"));
                for (int i = 0; i < Obj1.getData().getBeneficiary().size(); i++) {
                    Map<String, String> accountCompleteDetails = new LinkedHashMap<String, String>();
                    System.out.println("Provide Account for filteration: " + Obj1.getData().getBeneficiary().get(i).getCreditorAccount().getIdentification().substring(6));
                    App_genericFunction.putcommentinStep("Provided Account for filteration: " + Obj1.getData().getBeneficiary().get(i).getCreditorAccount().getIdentification().substring(6));
                    accountCompleteDetails = Payment_detail.getAmendManagePayeeData(Obj1.getData().getBeneficiary().get(i).getCreditorAccount().getIdentification().substring(6));
                    System.out.println("Beneficairy Details" + accountCompleteDetails);
                    Thread.sleep(3000);
                    //App_genericFunction.OptionalField("accountid",accountCompleteDetails.get("Account"),Obj1.getData().getBeneficiary().get(i).getAccountId());
                    App_genericFunction.OptionalField("accountid", GlobalHooks.values.get(0).get("accountid"), Obj1.getData().getBeneficiary().get(i).getAccountId());
                    // App_genericFunction.OptionalField("beneficiary_id",GlobalHooks.values.get(i).get("beneficiary_id"),Obj1.getData().getBeneficiary().get(i).getBeneficiaryId());
                    App_genericFunction.OptionalField("reference", accountCompleteDetails.get("Payee Reference Number"), Obj1.getData().getBeneficiary().get(i).getReference());
                    // if(Obj1.getData().getBeneficiary().get(i).getCreditorAccount() !=null && (!GlobalHooks.values.get(i).get("ca_identification").isEmpty())) {
                    //Assert.assertEquals("Test Failed", accountCompleteDetails.get("Sort Code").replace("-","")+accountCompleteDetails.get("Account"), Obj1.getData().getBeneficiary().get(i).getCreditorAccount().getIdentification());
                    Assert.assertEquals("Test Failed", "165710" + accountCompleteDetails.get("Account"), Obj1.getData().getBeneficiary().get(i).getCreditorAccount().getIdentification());
                    App_genericFunction.OptionalField("ca_name", accountCompleteDetails.get("Payee Name"), Obj1.getData().getBeneficiary().get(i).getCreditorAccount().getName());
                    // Assert.assertEquals("Test Failed", GlobalHooks.values.get(i).get("ca_schemename"), Obj1.getData().getBeneficiary().get(i).getCreditorAccount().getSchemeName());
                    //}else
               /* {
                    App_genericFunction.putcommentinStep("Error : Optional Field CreditAccount Skipped");
                    Assert.fail();
                }*/
                /*selenium_Helper.enter("id:logoff");
                selenium_Helper.enter("xpath://*[@class='x-btn portal_pos_btn x-btn-noicon']/tbody/tr[2]/td[2]/em/button");*/
                    accountCompleteDetails.clear();
                }
                App_genericFunction.putcommentinStep("-----:Validation completed for Beneficiaries:----");
                App_genericFunction.putcommentinStep("Total Beneficiarie Count : " + Obj1.getData().getBeneficiary().size());
                App_genericFunction.putcommentinStep(response.print());

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
            Assert.assertEquals("Test Failed", Integer.parseInt(Status_Code), response.getStatusCode());
            response.prettyPrint();
        }
    }
}
