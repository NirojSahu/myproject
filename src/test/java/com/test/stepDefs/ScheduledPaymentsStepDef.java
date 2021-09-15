package com.test.stepDefs;

import Responses.OBReadScheduledPayments2.OBReadScheduledPayment;
import Responses.OBReadScheduledPayments2.ScheduledPayment;
import Responses.OBReadStandingOrders4.OBReadStandingOrders4;
import Utilities.JsonUtilities;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.google.inject.Inject;
import com.test.APIFunctions.DirectDebitsService;
import com.test.APIFunctions.ScheduledPaymentsService;
import com.test.CustomHooks.GlobalHooks;
import com.test.Utils.App_genericFunction;
import com.test.Utils.CommonFunctions;
import com.test.configuration.Configuration;
import com.test.exceptions.StopTestException;
import com.test.pages.MyAccounts;
import com.test.pages.PaymentServices;
import com.test.pages.SchedulePayment;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.codehaus.jettison.json.JSONException;
import org.junit.Assert;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ScheduledPaymentsStepDef {
    @Inject
    ScheduledPaymentsService Obj;

    @Inject
    CommonFunctions cf;

    @Inject
    PaymentServices payment_details;

    @Inject
    App_genericFunction general;

    @Inject
    SchedulePayment SP_portal;

    @Inject
    MyAccounts account_details;

    public String Verbose;
    Response response;
    JsonPath JsonPathEvaluator;
    public static String USER_DI= System.getProperty("user.dir");

    @Given("I have the data available to create {string} request for scheduled payments_service with Headers")
    public void i_have_the_data_available_to_create_request_for_scheduled_payments_service_with_Headers(String Method) throws JsonProcessingException, FileNotFoundException, JSONException {
        Verbose = Method;
        if(Verbose.contentEquals("POST"))
        {

        }else if(Verbose.contentEquals("GET"))
        {
            Obj.accountsServiceSetHeaders_get();
        }
    }
    @Then("I want to validate scheduled payments service Response for Valid Schema")
    public void iWantToValidateScheduledPaymentsServiceResponseForValidSchema() throws IOException, ProcessingException {
        System.out.println(response.getBody().asString());
        App_genericFunction.putcommentinStep(JsonUtilities.validateSchema(response,"/Schemas/ScheduledPayments_1.json").toString());
        //response.then().assertThat().body(matchesJsonSchemaInClasspath("Schemas/account-access-consents_1.json"));
        if(JsonUtilities.validateSchema(response,"/Schemas/ScheduledPayments_1.json").toString().contains("error"))
        {
            Assert.fail("Schemavalidation failed : issue with the response");
        }
    }
    @When("I want to make a call to scheduled payments_service to get consentID status")
    public void iWantToMakeACallToScheduledPayments_serviceToGetConsentIDStatus() {
        response = Obj.hitAccountService_get();
    }


    @Then("I want to validate scheduled payments service Response and store Status code {string}")
    public void i_want_to_validate_scheduled_payments_service_Response_and_store_Status_code(String Status_Code) {
        JsonPathEvaluator= Obj.getResponse(response);
        if(Status_Code.contentEquals("200"))
        {
            Assert.assertEquals("Test Failed",Integer.parseInt(Status_Code),response.getStatusCode());
            OBReadScheduledPayment Obj1 = response.as(OBReadScheduledPayment.class);
            try{
            for(int i=0;i<Obj1.getData().getScheduledPayment().size();i++)
            {
                Obj.validateScheduledType(Obj1.getData().getScheduledPayment().get(i).getScheduledType());
            }

            for(int i=0;i<Obj1.getData().getScheduledPayment().size();i++)
            {
                Assert.assertEquals("Test Failed",GlobalHooks.values.get(i).get("accountid"),Obj1.getData().getScheduledPayment().get(i).getAccountId());
                App_genericFunction.OptionalField("scheduledpaymentid",GlobalHooks.values.get(i).get("scheduledpaymentid"),Obj1.getData().getScheduledPayment().get(i).getScheduledPaymentId());
                Assert.assertEquals("Test Failed",GlobalHooks.values.get(i).get("schedulepaymentdatetime"),Obj1.getData().getScheduledPayment().get(i).getScheduledPaymentDateTime());
                Assert.assertEquals("Test Failed",GlobalHooks.values.get(i).get("scheduletype"),Obj1.getData().getScheduledPayment().get(i).getScheduledType());
                Assert.assertEquals("Test Failed",GlobalHooks.values.get(i).get("ia_amount"),Obj1.getData().getScheduledPayment().get(i).getInstructedAmount().getAmount());
                Assert.assertEquals("Test Failed",GlobalHooks.values.get(i).get("ia_currency"),Obj1.getData().getScheduledPayment().get(i).getInstructedAmount().getCurrency());
                if(Obj1.getData().getScheduledPayment().get(i).getCreditorAccount() != null && (!GlobalHooks.values.get(i).get("cacc_schemename").isEmpty()))
                {
                    Assert.assertEquals("Test Failed",GlobalHooks.values.get(i).get("cacc_schemename"),Obj1.getData().getScheduledPayment().get(i).getCreditorAccount().getSchemeName());
                    Assert.assertEquals("Test Failed",GlobalHooks.values.get(i).get("cacc_identification"),Obj1.getData().getScheduledPayment().get(i).getCreditorAccount().getIdentification());
                    App_genericFunction.OptionalField("cacc_name",GlobalHooks.values.get(i).get("cacc_name"),Obj1.getData().getScheduledPayment().get(i).getCreditorAccount().getName());
                }else
                {
                    App_genericFunction.putcommentinStep("Error : Optional Field cacc_schemename Skipped");
                    Assert.fail();
                }

                //App_genericFunction.OptionalField();
            }
//            Assert.assertEquals("Test Failed",GlobalHooks.values.get(0).get("productid"),response.jsonPath().get("Data.Product[0].ProductId").toString());
//            Assert.assertEquals("Test Failed",GlobalHooks.values.get(0).get("producttype"),response.jsonPath().get("Data.Product[0].ProductType").toString());
//            Assert.assertEquals("Test Failed",GlobalHooks.values.get(0).get("productname"),response.jsonPath().get("Data.Product[0].ProductName").toString());
            App_genericFunction.putcommentinStep (response.print());
        }catch (NullPointerException exception){
            System.out.println("There is No SP Available");
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
            App_genericFunction.putcommentinStep((response.print()));
        }
    }

    @Then("I want to validate scheduled payments service Response against banking portal and store Status code {string}")
    public void iWantToValidateScheduledPaymentsServiceResponseAgainstBankingPortalAndStoreStatusCode(String Status_Code) throws InterruptedException, StopTestException {
        JsonPathEvaluator= Obj.getResponse(response);
        if( Configuration.getConfiguration().getProperty("digibank").startsWith("enabled")) {
            if (Status_Code.contentEquals("200")) {
                System.out.println("Statuscode returned : " + Status_Code);
                Assert.assertEquals("Test Failed", Integer.parseInt(Status_Code), response.getStatusCode());
                OBReadScheduledPayment Obj1 = response.as(OBReadScheduledPayment.class);
                List<ScheduledPayment> SP = Obj1.getData().getScheduledPayment();

                general.getintoCAInternetBanking("", GlobalHooks.values.get(0).get("customer_no"), GlobalHooks.values.get(0).get("pac_no"), GlobalHooks.values.get(0).get("password"));
                Thread.sleep(20000);
                payment_details.clickOnPaymentServiceLink("Payment Services");
                Thread.sleep(2000);
                payment_details.clickOnPaymentServiceLink("Future Dated Payments");
//                payment_details.clickOnPaymentServiceLink("Payments History");
                Thread.sleep(3000);
                account_details.selctPersonalAccountType(GlobalHooks.values.get(0).get("ia_currency"), GlobalHooks.values.get(0).get("accountid"));

                List<HashMap<String, String>> fromweb = SP_portal.getSPData();
                System.out.println(fromweb);

                for (HashMap<String, String> map : fromweb) {
                    if (map.containsKey("KEY")) {
                        System.out.println("No Data returned from web for schedule payment");
                    } else {
                        System.out.println(response.prettyPrint());
                        long total = SP.stream()
                                .filter(sp -> sp.getScheduledPaymentDateTime().split("T")[0].contentEquals(cf.convertedformat3(map.get("SP_Date"))) &&
                                        sp.getScheduledPaymentId().contentEquals(map.get("SP_Reference")) &&
                                        sp.getCreditorAccount().getIdentification().contentEquals("165710" + map.get("SP_Account_Number")) &&
                                        //sp.getInstructedAmount().getAmount().contentEquals(map.get("SP_Amount").split(" ")[1]) &&
                                        sp.getInstructedAmount().getCurrency().contentEquals(cf.convertCurrencySymbolToCurrencyType(map.get("SP_Amount").split(" ")[0])))
                                //.map(sp -> sp.getCreditorAccount())
                                .count();
                        System.out.println("Total schedule payment entries with creditaccount " + total);
                        if (total != 1) {
                            Assert.fail("Journey failed for SP as more entries for same record found in API then exist in Portal");
                        } else {
                            List<ScheduledPayment> x = SP.stream()
                                    .filter(sp -> sp.getScheduledPaymentDateTime().split("T")[0].contentEquals(cf.convertedformat3(map.get("SP_Date"))) &&
                                            sp.getScheduledPaymentId().contentEquals(map.get("SP_Reference")) &&
                                            sp.getCreditorAccount().getIdentification().contentEquals("165710" + map.get("SP_Account_Number")) &&
                                            //sp.getInstructedAmount().getAmount().contentEquals(map.get("SP_Amount").split(" ")[1]) &&
                                            sp.getInstructedAmount().getCurrency().contentEquals(cf.convertCurrencySymbolToCurrencyType(map.get("SP_Amount").split(" ")[0])))
                                    .collect(Collectors.toList());

                            System.out.println(x);
                            App_genericFunction.putcommentinStep(x);
                            System.out.println("Filetered Date : " + cf.getDate_formatted(x.get(0).getScheduledPaymentDateTime().split("T")[0], "YYYY-MM-DD"));
                            System.out.println("CurrentDate : " + cf.getCurrentDate_formatted("YYYY-MM-DD"));

                            /*if (cf.getDate_formatted(x.get(0).getScheduledPaymentDateTime().split("T")[0], "YYYY-MM-DD").before(cf.getCurrentDate_formatted("YYYY-MM-DD"))) {
                                Assert.assertEquals("Test Failed", x.get(0).getScheduledType(), "Execution");
                            } else {
                                Assert.assertEquals("Test Failed", x.get(0).getScheduledType(), "Arrival");
                            }*/

                            System.out.println("Validation completed for Filetered schedule Payment");
                            App_genericFunction.putcommentinStep("Validation completed for Filetered schedule Payment");
                        }
                    }
                }

            } else if (Status_Code.contentEquals("401")) {
                Assert.assertEquals("Test Failed", Integer.parseInt(Status_Code), response.getStatusCode());
                App_genericFunction.putcommentinStep("StatusCode : " + response.getStatusCode());
                Assert.assertTrue("Error Message is not valid", response.asString().contains("Invalid Credentials. Make sure you have given the correct access token"));
                App_genericFunction.putcommentinStep((response.print()));

            } else if (Status_Code.contentEquals("400")) {
                App_genericFunction.putcommentinStep("StatusCode : " + response.getStatusCode());
                Assert.assertEquals("Test Failed", Integer.parseInt(Status_Code), response.getStatusCode());
                App_genericFunction.putcommentinStep("ErrorCode : " + JsonPathEvaluator.get("Errors[0].ErrorCode"));
                App_genericFunction.putcommentinStep("Message : " + JsonPathEvaluator.get("Errors[0].Message"));
                App_genericFunction.putcommentinStep((response.print()));
            }
        }else
        {
            Assert.assertEquals("Test Failed", Integer.parseInt(Status_Code), response.getStatusCode());
            response.prettyPrint();
        }
    }
}
