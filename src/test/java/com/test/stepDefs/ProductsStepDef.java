package com.test.stepDefs;

import Responses.OBReadProducts2.OBReadProducts;
import Utilities.JsonUtilities;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.google.inject.Inject;
import com.test.APIFunctions.DirectDebitsService;
import com.test.APIFunctions.ProductsService;
import com.test.CustomHooks.GlobalHooks;
import com.test.Utils.App_genericFunction;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.codehaus.jettison.json.JSONException;
import org.junit.Assert;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ProductsStepDef {
    @Inject
    ProductsService Obj;

    public String Verbose;
    Response response;
    JsonPath JsonPathEvaluator;
    public static String USER_DI= System.getProperty("user.dir");

    @Given("I have the data available to create {string} request for products_service with Headers")
    public void i_have_the_data_available_to_create_request_for_products_service_with_Headers(String Method) throws JsonProcessingException, FileNotFoundException, JSONException {
        Verbose = Method;
        if(Verbose.contentEquals("POST"))
        {

        }else if(Verbose.contentEquals("GET"))
        {
            Obj.accountsServiceSetHeaders_get();
        }
    }
    @Then("I want to validate products service Response for Valid Schema")
    public void iWantToValidateProductsServiceResponseForValidSchema() throws IOException, ProcessingException {
        System.out.println(response.getBody().asString());
        App_genericFunction.putcommentinStep(JsonUtilities.validateSchema(response,"/Schemas/Products_1.json").toString());
        if(JsonUtilities.validateSchema(response,"/Schemas/Products_1.json").toString().contains("error"))
        {
            Assert.fail("Schemavalidation failed : issue with the response");
        }
        //response.then().assertThat().body(matchesJsonSchemaInClasspath("Schemas/account-access-consents_1.json"));
    }
    @When("I want to make a call to products_service to get consentID status")
    public void iWantToMakeACallToProducts_serviceToGetConsentIDStatus() {
        response = Obj.hitAccountService_get();
    }


    @Then("I want to validate products service Response and store with Status code {string}")
    public void i_want_to_validate_Products_service_Response_and_store_with_Status_code(String Status_Code) {
        JsonPathEvaluator= Obj.getResponse(response);
        if(Status_Code.contentEquals("200"))
        {
            Assert.assertEquals("Test Failed",Integer.parseInt(Status_Code),response.getStatusCode());
            OBReadProducts Obj1 = response.as(OBReadProducts.class);
            for(int i=0;i<Obj1.getData().getProduct().size();i++)
            {
                Obj.validateProductType(Obj1.getData().getProduct().get(i).getProductType());
            }

            for(int i=0;i<Obj1.getData().getProduct().size();i++)
            {
                Assert.assertEquals("Test Failed",GlobalHooks.values.get(i).get("accountid"),Obj1.getData().getProduct().get(i).getAccountId());
                App_genericFunction.OptionalField("productid",GlobalHooks.values.get(i).get("productid"),Obj1.getData().getProduct().get(i).getProductId());
                Assert.assertEquals("Test Failed",GlobalHooks.values.get(i).get("producttype"),Obj1.getData().getProduct().get(i).getProductType());
                App_genericFunction.OptionalField("productname",GlobalHooks.values.get(i).get("productname"),Obj1.getData().getProduct().get(i).getProductName());
                //Assert.assertEquals("Test Failed",GlobalHooks.values.get(0).get("productname"),response.jsonPath().get("Data.Product[0].ProductName").toString());
                App_genericFunction.putcommentinStep ("PRODUCT ID : " +Obj1.getData().getProduct().get(i).getProductId());
                App_genericFunction.putcommentinStep ("PRODUCT NAME : " +Obj1.getData().getProduct().get(i).getProductName());
                App_genericFunction.putcommentinStep (response.print());

            }

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
            App_genericFunction.putcommentinStep((response.print()));
        }

    }

}
