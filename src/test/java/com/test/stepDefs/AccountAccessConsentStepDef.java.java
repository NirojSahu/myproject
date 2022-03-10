package com.test.stepDefs;

import Responses.OBReadConsentResponse1.OBReadConsentResponse;
import Utilities.JsonUtilities;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;

import com.google.inject.Inject;

import com.test.APIFunctions.AccountAccessConsentService;
import com.test.CustomHooks.GlobalHooks;
import com.test.Utils.App_genericFunction;
import com.test.Utils.SeleniumHelper;
import com.test.exceptions.StopTestException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.codehaus.jettison.json.JSONException;
import org.junit.Assert;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;


import static com.test.CustomHooks.GlobalHooks.ResourceURI;
import static com.test.CustomHooks.GlobalHooks.VERSION;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class AccountAccessConsentStepDef {
    @Inject
    AccountAccessConsentService Obj;

    public String Verbose;
    Response response;
    JsonPath JsonPathEvaluator;
    public static String USER_DI= System.getProperty("user.dir");

    @Given("I have the data available to create {string} request for accountaccessconsent_service with Headers")
    public void i_have_the_data_available_to_create_request_for_accountaccessconsent_service_with_Headers(String Method) throws JsonProcessingException, FileNotFoundException, JSONException, org.json.JSONException {
        Verbose = Method;
        if(Verbose.contentEquals("POST"))
        {
                Obj.setHeaders_post();
                Obj.setRequestbody_post();
        }else if(Verbose.contentEquals("GET"))
        {
            Obj.setHeaders_get();
        }else if(Verbose.contentEquals("DELETE"))
        {
            Obj.setHeaders_delete();
        }
    }

    @When("I want to make a call to accountaccessconsent_service to create consentID")
    public void i_want_to_make_a_call_to_accountaccessconsent_service_to_create_consentID() throws UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, StopTestException {
        response = Obj.hitService_post();
    }


    @Then("I want to validate service Response and store the ConsentID with Status code {string}")
    public void i_want_to_validate_service_Response_and_store_the_ConsentID_with_Status_code(String Status_Code) {
        JsonPathEvaluator= Obj.getResponse(response);

        if(Status_Code.contentEquals("201"))
        {
            OBReadConsentResponse Obj1 = response.as(OBReadConsentResponse.class);
            Assert.assertEquals("Test Failed", GlobalHooks.values.get(0).get("ClientID"),response.getHeader("x-wso2-client-id"));
            App_genericFunction.putcommentinStep("CustomerKey : " + response.getHeader("x-wso2-client-id"));

            Assert.assertEquals("Test Failed",Integer.parseInt(Status_Code),response.getStatusCode());
            App_genericFunction.putcommentinStep("ConsentId : "+JsonPathEvaluator.get("Data.ConsentId"));
            App_genericFunction.putcommentinStep("Status : "+JsonPathEvaluator.get("Data.Status"));
            App_genericFunction.putcommentinStep("StatusUpdateDateTime :" +JsonPathEvaluator.get("Data.StatusUpdateDateTime"));
            App_genericFunction.putcommentinStep ("Permissions from Pojo: "+ Obj1.getData().getPermissions());
            App_genericFunction.putcommentinStep ("Validity: "+ Obj1.getData().getExpirationDateTime());
            if(GlobalHooks.scenario_type.contains("UJ"))
            {
                App_genericFunction.WritePropertiesFile_feature("UJ_Consent_ID", JsonPathEvaluator.get("Data.ConsentId"));
                App_genericFunction.WritePropertiesFile_feature("UJ_Validity", JsonPathEvaluator.get("Data.ExpirationDateTime").toString().split("T")[0]);
            }else
            {
                App_genericFunction.WritePropertiesFile_common("Consent_ID" +"_"+ GlobalHooks.scenario_type, JsonPathEvaluator.get("Data.ConsentId"));
                App_genericFunction.WritePropertiesFile_common("Scenario_type_accountconsent", GlobalHooks.scenario_type);
                App_genericFunction.WritePropertiesFile_common("Validity", JsonPathEvaluator.get("Data.ExpirationDateTime").toString().split("T")[0]);
            }
            App_genericFunction.putcommentinStep (response.print());

        }else if (Status_Code.contentEquals("401"))
        {
            Assert.assertEquals("Test Failed",Integer.parseInt(Status_Code),response.getStatusCode());
            App_genericFunction.putcommentinStep("StatusCode : "+response.getStatusCode());
            App_genericFunction.putcommentinStep((response.print()));

        }else if (Status_Code.contentEquals("400"))
        {
            Assert.assertEquals("Test Failed",Integer.parseInt(Status_Code),response.getStatusCode());
            App_genericFunction.putcommentinStep("StatusCode : "+response.getStatusCode());
            App_genericFunction.putcommentinStep("errorCode : "+JsonPathEvaluator.get("error[0].errorCode"));
            App_genericFunction.putcommentinStep("message : "+JsonPathEvaluator.get("error[0].message"));
            App_genericFunction.putcommentinStep((response.print()));
        }else if (Status_Code.contentEquals("200"))
        {
            Assert.assertEquals("Test Failed",Integer.parseInt(Status_Code),response.getStatusCode());
        }

    }


    @Then("I want to validate service Response for Valid Schema")
    public void iWantToValidateServiceResponseForValidSchema() throws IOException, ProcessingException {
        if(GlobalHooks.resource_API.contentEquals("account-access-consents_1") || GlobalHooks.resource_API.contentEquals("account-access-consents_2")) {
            App_genericFunction.putcommentinStep(response.getBody().asString());
            App_genericFunction.putcommentinStep(JsonUtilities.validateSchema(response,"/Schemas/account-access-consents_1.json").toString());
            //response.then().assertThat().body(matchesJsonSchemaInClasspath("Schemas/account-access-consents_1.json"));
            if(JsonUtilities.validateSchema(response,"/Schemas/account-access-consents_1.json").toString().contains("error"))
            {
                Assert.fail("Schemavalidation failed : issue with the response");
            }
        }
    }

    @When("I want to make a call to accountaccessconsent_service to get consentID status")
    public void iWantToMakeACallToAccountaccessconsent_serviceToGetConsentIDStatus() {
        response = Obj.hitService_get();
    }

    @Then("I want to validate service Response and validate the ConsentID status as {string} with Status code {string}")
    public void iWantToValidateServiceResponseAndValidateTheConsentIDStatusAsWithStatusCode(String ConsentID_Status, String Status_Code) {
        JsonPathEvaluator= Obj.getResponse(response);
        if(Status_Code.contentEquals("200"))
        {
            OBReadConsentResponse Obj1 = response.as(OBReadConsentResponse.class);
            Assert.assertEquals("Test Failed",Integer.parseInt(Status_Code),response.getStatusCode());
            App_genericFunction.putcommentinStep("StatusCode : "+response.getStatusCode());
            Assert.assertEquals("Test Failed", GlobalHooks.values.get(0).get("Status"),JsonPathEvaluator.get("Data.Status"));
            Assert.assertEquals("Test Failed", GlobalHooks.values.get(0).get("ClientID"),response.getHeader("x-wso2-client-id"));

            App_genericFunction.putcommentinStep("CustomerKey : " + response.getHeader("x-wso2-client-id"));
            App_genericFunction.putcommentinStep("ConsentId : "+JsonPathEvaluator.get("Data.ConsentId"));
            App_genericFunction.putcommentinStep("Status : "+JsonPathEvaluator.get("Data.Status"));
            App_genericFunction.putcommentinStep("StatusUpdateDateTime :" +JsonPathEvaluator.get("Data.StatusUpdateDateTime"));
            App_genericFunction.putcommentinStep("ExpirationDateTime :" +JsonPathEvaluator.get("Data.ExpirationDateTime"));
            App_genericFunction.putcommentinStep("Permissions :" +Obj1.getData().getPermissions());
            App_genericFunction.putcommentinStep (response.print());

        }else if (Status_Code.contentEquals("401"))
        {
            Assert.assertEquals("Test Failed",Integer.parseInt(Status_Code),response.getStatusCode());
            App_genericFunction.putcommentinStep("StatusCode : "+response.getStatusCode());
            Assert.assertTrue("Error Message is not valid",response.asString().contains("Invalid Credentials. Make sure you have given the correct access token"));
            App_genericFunction.putcommentinStep((response.print()));

        }else if (Status_Code.contentEquals("400"))
        {
            Assert.assertEquals("Test Failed",Integer.parseInt(Status_Code),response.getStatusCode());
            App_genericFunction.putcommentinStep("StatusCode : "+response.getStatusCode());
            App_genericFunction.putcommentinStep("errorCode : "+JsonPathEvaluator.get("error[0].errorCode"));
            App_genericFunction.putcommentinStep("message : "+JsonPathEvaluator.get("error[0].message"));
            App_genericFunction.putcommentinStep((response.print()));
        }

    }

    @Then("I want to validate service Response and validate the ConsentID is deleted with Status code {string}")
    public void iWantToValidateServiceResponseAndValidateTheConsentIDIsDeletedWithStatusCode(String Status_Code) {
        JsonPathEvaluator= Obj.getResponse(response);
        if (Status_Code.contentEquals("204")) {
            Assert.assertEquals("Test Failed",Integer.parseInt(Status_Code),response.getStatusCode());
            App_genericFunction.putcommentinStep("Status code : " + response.getStatusCode() + " : Deleted Consent Successfully");
        } else if (Status_Code.contentEquals("401")) {
            Assert.assertEquals("Test Failed",Integer.parseInt(Status_Code),response.getStatusCode());
            App_genericFunction.putcommentinStep("StatusCode : "+response.getStatusCode());
            Assert.assertTrue("Error Message is not valid",response.asString().contains("Invalid Credentials. Make sure you have given the correct access token"));
            App_genericFunction.putcommentinStep((response.print()));

        } else if (Status_Code.contentEquals("400")){
            App_genericFunction.putcommentinStep("StatusCode : "+response.getStatusCode());
            App_genericFunction.putcommentinStep(response.print());
            Assert.assertEquals("Test Failed",Integer.parseInt(Status_Code),response.getStatusCode());
            App_genericFunction.putcommentinStep("ErrorCode : "+JsonPathEvaluator.get("error[0].errorCode"));
            App_genericFunction.putcommentinStep("Message : "+JsonPathEvaluator.get("error[0].message"));
        }
    }

    @When("I want to make a call to accountaccessconsent_service to delete active consentid")
    public void iWantToMakeACallToAccountaccessconsent_serviceToDeleteActiveConsentid() throws InterruptedException {
        String value;

        for(String key : App_genericFunction.ReadPropertiesFile_all().stringPropertyNames()) {
           value = App_genericFunction.ReadPropertiesFile_all().getProperty(key);
            if(key.contains("Consent_ID_1"))
            {
                System.out.println(key + " => " + value);
               response = Obj.hitService_delete(value);
                if (response.getStatusCode() != 204 && GlobalHooks.scenario_type.contentEquals("3.1"))
                {
                    Assert.fail("204 Status code not received...");
                }else
                {
                    App_genericFunction.putcommentinStep("Consentid : " + value);   //Thread.sleep(5000);
                    App_genericFunction.putcommentinStep("204 status code received");
                    SeleniumHelper.getdriver().navigate().to("https://www.google.com");
                    //previousvalue =value;
                }
            }
        }

    }

    @When("I want to make a call to accountaccessconsent_service to delete with given consentID")
    public void iWantToMakeACallToAccountaccessconsent_serviceToDeleteWithGivenConsentID() {
        response = Obj.hitService_delete(GlobalHooks.values.get(0).get("ConsentID"));
    }
}



