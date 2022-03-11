package com.test.stepDefs;

import Utilities.WebKit.exceptions.StopTestException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.inject.Inject;
import com.test.APIFunctions.TokenService;
import com.test.CustomHooks.GlobalHooks;
import com.test.Utils.App_genericFunction;
import com.test.Utils.SeleniumHelper;
//import com.test.exceptions.StopTestException;
//import com.test.helper.NavigationHelper;
import com.test.pages.*;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.util.List;

import static com.test.CustomHooks.GlobalHooks.Featureid;
import static com.test.CustomHooks.GlobalHooks.values;


public class TokenStepDef {
    @Inject
    TokenService ts;
    @Inject
    AuthURLPage1 authURLPage1;
    @Inject
    AuthURLPage2 authURLPage2;
    @Inject
    AuthURLPage3 authURLPage3;
    @Inject
    AuthURLPage4 authURLPage4;
    @Inject
    App_genericFunction app_genericFunction ;
    @Inject
    Navigate navigate;

    public String Verbose;
    Response response;
    JsonPath JsonPathEvaluator;
    public static String USER_DI= System.getProperty("user.dir");
    String Auth_URL;
    String Auth_code;

    @Given("I have the data available to create {string} request for application access consent token")
    public void i_have_the_data_available_to_create_request_for_application_access_consent_token(String Method) throws JsonProcessingException {
        Verbose = Method;
        ts.setRequestbody_post();
    }

    @When("I want to make a call to applicationaccesstoken_service to create token")
    public void i_want_to_make_a_call_to_applicationaccesstoken_service_to_create_token() throws UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, StopTestException, StopTestException {
        response = ts.hitService_post();
    }

    @Then("I want to validate application access token service Response and store the token with Status code {string}")
    public void i_want_to_validate_application_access_token_service_Response_and_store_the_token_with_Status_code(String Status_Code) {
        App_genericFunction.putcommentinStep(response.print());
        JsonPathEvaluator= ts.getResponse(response);
        if(Status_Code.contentEquals("201")) {

        }else if(Status_Code.contentEquals("200"))
        {
            Assert.assertEquals("Test Failed",Integer.parseInt(Status_Code),response.getStatusCode());
            Assert.assertEquals(values.get(0).get("scope_value"),JsonPathEvaluator.get("scope"));
            App_genericFunction.putcommentinStep("ResponseSuccessful -- Application Access token Generated");
            App_genericFunction.putcommentinStep("access_token : "+JsonPathEvaluator.get("access_token"));
            App_genericFunction.putcommentinStep("scope : "+JsonPathEvaluator.get("scope"));
            App_genericFunction.putcommentinStep("access_token : "+JsonPathEvaluator.get("token_type"));
            App_genericFunction.putcommentinStep("expires_in : "+JsonPathEvaluator.get("expires_in"));
            App_genericFunction.WritePropertiesFile_common("account_access_token",JsonPathEvaluator.get("access_token"));
            App_genericFunction.WritePropertiesFile_feature("UJ_account_access_token",JsonPathEvaluator.get("access_token"));
        }else if(Status_Code.contentEquals("400"))
        {
            Assert.assertEquals("Test Failed",Integer.parseInt(Status_Code),response.getStatusCode());
            int count = 0;
            Headers allHeaders = response.headers();
            response.prettyPrint();
            for(Header header : allHeaders)
            {
                count ++;
            }
            if(count == 7)
            {

                App_genericFunction.putcommentinStep("Header Count is Correct with status code : " + response.getStatusCode());
                App_genericFunction.putcommentinStep("==========================================================================");
                Assert.assertEquals("DENY",response.getHeader("X-Frame-Options"));
                Assert.assertEquals("nosniff",response.getHeader("X-Content-Type-Options"));
                Assert.assertEquals("1; mode=block",response.getHeader("X-XSS-Protection"));
                //Assert.assertEquals("Thu, 26 Mar 2020 08:26:10 GMT",response.getHeader("Date"));
                Assert.assertEquals("application/json",response.getHeader("Content-Type"));
                Assert.assertEquals("chunked",response.getHeader("Transfer-Encoding"));
                Assert.assertEquals("Close",response.getHeader("Connection"));

                App_genericFunction.putcommentinStep("error_description is: " + JsonPathEvaluator.get("error_description"));
                App_genericFunction.putcommentinStep("error is: " + JsonPathEvaluator.get("error"));
                Assert.assertEquals("invalid_request",JsonPathEvaluator.get("error"));
                //Assert.assertEquals("JWT Token with jti: pen04 has been replayed",JsonPathEvaluator.get("error_description"));
            }

        }
    }


    @Given("I have the data available to create {string} request for application access consent token with JWT")
    public void iHaveTheDataAvailableToCreateRequestForApplicationAccessConsentTokenWithJWT(String arg0) throws Exception {
        App_genericFunction.createPropertyfile(Featureid);
        ts.setRequestbody_post_withdynamic_JWT();
    }

    @Given("I have the ConsentID for creating Authorization URL")
    public void iHaveTheConsentIDForCreatingAuthorizationURL() throws JsonProcessingException, JSONException, StopTestException {
        Auth_URL =ts.setAuthURL();
        if(GlobalHooks.scenario_type.contains("UJ"))
        {
            App_genericFunction.WritePropertiesFile_feature("UJ_Auth_URL",Auth_URL);
        }else
        {
            App_genericFunction.WritePropertiesFile_common("Auth_URL",Auth_URL);
        }

    }

    @When("I login to the portal with AuthURL")
    public void iLoginToThePortalWithAuthURL() {
          navigate.toAuthURLHomePage(Auth_URL);
          //authURLPage1.provide_customerdetail("700017701");
        authURLPage1.provide_customerdetail(values.get(0).get("web_customer_no"));
         // authURLPage2.provide_pac_password("112233","Welcome@01");
        authURLPage2.provide_pac_password(values.get(0).get("web_pac_no"),values.get(0).get("web_password"));
        List<String>  account_nos = App_genericFunction.ListofValues(values.get(0).get("web_accounts"));
        App_genericFunction.putcommentinStep(account_nos);
        authURLPage3.click_Acccounts_forConsent(account_nos);
        authURLPage4.submissions();

        //app_genericFunction.getintoAuthPortal(Auth_URL,"700017701","112233","Welcome@01");
    }

    @Then("I get authorization code and store it for further processing")
    public void iGetAuthorizationCodeAndStoreItForFurtherProcessing() {

        if(GlobalHooks.scenario_type.contains("UJ"))
        {
            Auth_code = App_genericFunction.ReadPropertiesFile_feature("UJ_Auth_code");
            App_genericFunction.putcommentinStep("UJ_Auth_code : " +Auth_code);
        }else
        {
            Auth_code =App_genericFunction.ReadPropertiesFile_common("Auth_code");
            App_genericFunction.putcommentinStep("Auth_code : "+Auth_code);
        }
    }


    @When("I want to make a call to customeraccesstoken_service to create token")
    public void iWantToMakeACallToCustomeraccesstoken_serviceToCreateToken() throws UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, StopTestException {
        response = ts.hitService_post();
    }

    @Then("I want to validate customer access token service Response and store the token with Status code {string}")
    public void iWantToValidateCustomerAccessTokenServiceResponseAndStoreTheTokenWithStatusCode(String Status_Code) {
        JsonPathEvaluator= ts.getResponse(response);
        if(Status_Code.contentEquals("201")) {
            Assert.assertEquals("Test Failed",Integer.parseInt(Status_Code),response.getStatusCode());
        }else if(Status_Code.contentEquals("200"))
        {
            Assert.assertEquals("Test Failed",Integer.parseInt(Status_Code),response.getStatusCode());
            response.prettyPrint();
            if(GlobalHooks.scenario_type.contains("UJ"))
            {
                App_genericFunction.WritePropertiesFile_feature("UJ_Client_access_token",JsonPathEvaluator.get("access_token"));
                    App_genericFunction.putcommentinStep("UJ_Client_access_token : " +JsonPathEvaluator.get("access_token"));
                App_genericFunction.WritePropertiesFile_feature("UJ_refresh_token",JsonPathEvaluator.get("refresh_token"));
                App_genericFunction.putcommentinStep("UJ_refresh_token : " +JsonPathEvaluator.get("refresh_token"));
            }else
            {
                App_genericFunction.WritePropertiesFile_common("Client_access_token",JsonPathEvaluator.get("access_token"));
                App_genericFunction.putcommentinStep("Client_access_token : " +JsonPathEvaluator.get("access_token"));
                App_genericFunction.WritePropertiesFile_common("refresh_token",JsonPathEvaluator.get("refresh_token"));
                App_genericFunction.putcommentinStep("refresh_token : " +JsonPathEvaluator.get("refresh_token"));
            }

        }else if(Status_Code.contentEquals("400")) {
            Assert.assertEquals("Test Failed",Integer.parseInt(Status_Code),response.getStatusCode());
            App_genericFunction.putcommentinStep("error_description is: " + JsonPathEvaluator.get("error_description"));
            App_genericFunction.putcommentinStep("error is: " + JsonPathEvaluator.get("error"));
            Assert.assertEquals("invalid_request",JsonPathEvaluator.get("error"));
        }
    }

    @Given("I have the data available to create {string} request for customer access token")
    public void iHaveTheDataAvailableToCreateRequestForCustomerAccessToken(String arg0) throws JsonProcessingException {
        ts.setRequestbody_post_cct();
    }

    @Given("I have the data available to create {string} request for customer access token with jwt")
    public void iHaveTheDataAvailableToCreateRequestForCustomerAccessTokenWithJwt(String arg0) throws JsonProcessingException, JSONException, StopTestException {
    ts.setRequestbody_post_withdynamic_cat_JWT();
    }

    @Given("I have the data available to create {string} request for customer access token with jwt for refreshtoken")
    public void iHaveTheDataAvailableToCreateRequestForCustomerAccessTokenWithJwtForRefreshtoken(String arg0) throws StopTestException, JSONException, JsonProcessingException {
        ts.setRequestbody_post_withrefreshtoken();
    }


    @Then("I want to validate customer access token generated post refresh token service and store the token with Status code {string}")
    public void iWantToValidateCustomerAccessTokenGeneratedPostRefreshTokenServiceAndStoreTheTokenWithStatusCode(String Status_Code) {
        JsonPathEvaluator= ts.getResponse(response);
        if(Status_Code.contentEquals("200")) {
            Assert.assertEquals("Test Failed",Integer.parseInt(Status_Code),response.getStatusCode());
            response.prettyPrint();
            if(GlobalHooks.scenario_type.contains("UJ"))
            {
                App_genericFunction.WritePropertiesFile_feature("UJ_Client_access_token",JsonPathEvaluator.get("access_token"));
                App_genericFunction.putcommentinStep("UJ_Client_access_token : " +JsonPathEvaluator.get("access_token"));
                App_genericFunction.WritePropertiesFile_feature("UJ_refresh_token",JsonPathEvaluator.get("refresh_token"));
                App_genericFunction.putcommentinStep("UJ_refresh_token : " +JsonPathEvaluator.get("refresh_token"));
            }else
            {
                App_genericFunction.WritePropertiesFile_common("Client_access_token",JsonPathEvaluator.get("access_token"));
                App_genericFunction.putcommentinStep("Client_access_token : " +JsonPathEvaluator.get("access_token"));
                App_genericFunction.WritePropertiesFile_common("refresh_token",JsonPathEvaluator.get("refresh_token"));
                App_genericFunction.putcommentinStep("refresh_token : " +JsonPathEvaluator.get("refresh_token"));
            }
        }
    }
}

