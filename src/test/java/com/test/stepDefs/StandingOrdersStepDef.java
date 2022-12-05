package com.test.stepDefs;

import Responses.OBReadProducts2.OBReadProducts;
//import Responses.OBReadScheduledPayments2.OBReadScheduledPayment;
//import Responses.OBReadStandingOrders4.OBReadStandingOrders4;
import Responses.OBReadStandingOrder4.OBReadStandingOrder;
import Utilities.JsonUtilities;
import Utilities.WebKit.configuration.Configuration;
import Utilities.WebKit.exceptions.StopTestException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.google.inject.Inject;
import com.test.APIFunctions.DirectDebitsService;
import com.test.APIFunctions.StandingSrdersService;
import com.test.CustomHooks.GlobalHooks;
import com.test.Utils.App_genericFunction;
import com.test.Utils.CommonFunctions;
import com.test.Utils.SeleniumHelper;
//import com.test.configuration.Configuration;
//import com.test.exceptions.StopTestException;
import com.test.pages.MyAccounts;
import com.test.pages.PaymentServices;
import com.test.pages.StandingOrder;
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

public class StandingOrdersStepDef {
    @Inject
    StandingSrdersService Obj;

    @Inject
    App_genericFunction general;

    @Inject
    PaymentServices payment_details;

    @Inject
    MyAccounts account_details;

    @Inject
    StandingOrder standing_order;

    @Inject
    CommonFunctions co;

    @Inject
    SeleniumHelper selenium_Helper;

    public String Verbose;
    Response response;
    JsonPath JsonPathEvaluator;
    public static String USER_DI= System.getProperty("user.dir");

    @Given("I have the data available to create {string} request for standing orders_service with Headers")
    public void i_have_the_data_available_to_create_request_for_standing_orders_service_with_Headers(String Method) throws JsonProcessingException, FileNotFoundException, JSONException {
        Verbose = Method;
        if(Verbose.contentEquals("POST"))
        {

        }else if(Verbose.contentEquals("GET"))
        {
            Obj.accountsServiceSetHeaders_get();
        }
    }
    @Then("I want to validate standing orders service Response for Valid Schema")
    public void iWantToValidateStandingOrdersServiceResponseForValidSchema() throws IOException, ProcessingException {
        System.out.println(response.getBody().asString());
        App_genericFunction.putcommentinStep(JsonUtilities.validateSchema(response,"/Schemas/StandingOrders_1.json").toString());
        //response.then().assertThat().body(matchesJsonSchemaInClasspath("Schemas/account-access-consents_1.json"));
        if(JsonUtilities.validateSchema(response,"/Schemas/StandingOrders_1.json").toString().contains("error"))
        {
            Assert.fail("Schemavalidation failed : issue with the response");
        }
    }
    @When("I want to make a call to standing orders_service to get consentID status")
    public void iWantToMakeACallToStandingOrders_serviceToGetConsentIDStatus() {
        response = Obj.hitAccountService_get();
    }


    @Then("I want to validate Standing Orders service Response and store Status code {string}")
    public void i_want_to_validate_Standing_Orders_service_Response_and_store_Status_code(String Status_Code) {
        JsonPathEvaluator= Obj.getResponse(response);
        if(Status_Code.contentEquals("200"))
        {
            OBReadStandingOrder Obj1 = response.as(OBReadStandingOrder.class);
            try
            {
            for(int i=0;i<Obj1.getData().getStandingOrder().size();i++)
            {
                general.validateReularExpression("^(NotKnown)$|^(EvryDay)$|^(EvryWorkgDay)$|^(IntrvlDay:((0[2-9])|([1-2][0-9])|3[0-1]))$|^(IntrvlWkDay:0[1-9]:0[1-7])$|^(WkInMnthDay:0[1-5]:0[1-7])$|^(IntrvlMnthDay:(0[1-6]|12|24):(-0[1-5]|0[1-9]|[12][0-9]|3[01]))$|^(QtrDay:(ENGLISH|SCOTTISH|RECEIVED))$",Obj1.getData().getStandingOrder().get(i).getFrequency());
            }
            Assert.assertEquals("Test Failed",Integer.parseInt(Status_Code),response.getStatusCode());
            for(int i=0;i<Obj1.getData().getStandingOrder().size();i++)
            {
                Assert.assertEquals("Test Failed",GlobalHooks.values.get(i).get("accountid"),Obj1.getData().getStandingOrder().get(i).getAccountId());
                App_genericFunction.OptionalField("so_id",GlobalHooks.values.get(i).get("so_id"),Obj1.getData().getStandingOrder().get(i).getStandingOrderId());
                Assert.assertEquals("Test Failed",GlobalHooks.values.get(i).get("frequency"),Obj1.getData().getStandingOrder().get(i).getFrequency());
                App_genericFunction.OptionalField("reference",GlobalHooks.values.get(i).get("reference"),Obj1.getData().getStandingOrder().get(i).getReference());

                App_genericFunction.OptionalField("firstpaymentdatetime",GlobalHooks.values.get(i).get("firstpaymentdatetime"),Obj1.getData().getStandingOrder().get(i).getFirstPaymentDateTime());
                if(Obj1.getData().getStandingOrder().get(i).getFirstPaymentAmount() != null && (!GlobalHooks.values.get(i).get("fpa_amount").isEmpty())) {
                    Assert.assertEquals("Test Failed", GlobalHooks.values.get(i).get("fpa_amount"), Obj1.getData().getStandingOrder().get(i).getFirstPaymentAmount().getAmount());
                    Assert.assertEquals("Test Failed", GlobalHooks.values.get(i).get("fpa_currency"), Obj1.getData().getStandingOrder().get(i).getFirstPaymentAmount().getCurrency());
                }else
                {
                    App_genericFunction.putcommentinStep("Error : Optional Field fpa_amount Skipped");
                    Assert.fail();
                }


                App_genericFunction.OptionalField("nextpaymentdatetime",GlobalHooks.values.get(i).get("nextpaymentdatetime"),Obj1.getData().getStandingOrder().get(i).getNextPaymentDateTime());
                if(Obj1.getData().getStandingOrder().get(i).getNextPaymentAmount() != null &&  (!GlobalHooks.values.get(i).get("npa_amount").isEmpty())) {
                    Assert.assertEquals("Test Failed", GlobalHooks.values.get(i).get("npa_amount"), Obj1.getData().getStandingOrder().get(i).getNextPaymentAmount().getAmount());
                    Assert.assertEquals("Test Failed", GlobalHooks.values.get(i).get("npa_currency"), Obj1.getData().getStandingOrder().get(i).getNextPaymentAmount().getCurrency());
                }else
                {
                    App_genericFunction.putcommentinStep("Error : Optional Field npa_amount Skipped");
                    Assert.fail();
                }

                App_genericFunction.OptionalField("finalpaymentdatetime",GlobalHooks.values.get(i).get("finalpaymentdatetime"),Obj1.getData().getStandingOrder().get(i).getFinalPaymentDateTime());
                if(Obj1.getData().getStandingOrder().get(i).getFinalPaymentAmount() != null && (!GlobalHooks.values.get(i).get("final_amount").isEmpty())) {
                    Assert.assertEquals("Test Failed", GlobalHooks.values.get(i).get("final_amount"), Obj1.getData().getStandingOrder().get(i).getFinalPaymentAmount().getAmount());
                    Assert.assertEquals("Test Failed", GlobalHooks.values.get(i).get("final_currency"), Obj1.getData().getStandingOrder().get(i).getFinalPaymentAmount().getCurrency());
                }else
                {
                    App_genericFunction.putcommentinStep("Error : Optional Field final_amount Skipped");
                    Assert.fail();
                }
                App_genericFunction.OptionalField("standingorderstatuscode",GlobalHooks.values.get(i).get("standingorderstatuscode"),Obj1.getData().getStandingOrder().get(i).getStandingOrderStatusCode());
                if(!GlobalHooks.values.get(i).get("standingorderstatuscode").isEmpty())
                {
                    Obj.validateStandingOrderStatusCode(Obj1.getData().getStandingOrder().get(i).getStandingOrderStatusCode());
                }
                if(Obj1.getData().getStandingOrder().get(i).getCreditorAccount()  != null && (!GlobalHooks.values.get(i).get("ca_schemename").isEmpty()))
                {
                    Assert.assertEquals("Test Failed",GlobalHooks.values.get(i).get("ca_schemename"),Obj1.getData().getStandingOrder().get(i).getCreditorAccount().getSchemeName());
                    Assert.assertEquals("Test Failed",GlobalHooks.values.get(i).get("ca_Identification"),Obj1.getData().getStandingOrder().get(i).getCreditorAccount().getIdentification());
                    App_genericFunction.OptionalField("ca_name",GlobalHooks.values.get(i).get("ca_name"),Obj1.getData().getStandingOrder().get(i).getCreditorAccount().getName());
                }else
                {
                    App_genericFunction.putcommentinStep("Error : Optional Field ca_schemename Skipped");
                    Assert.fail();
                }

            }
//            Assert.assertEquals("Test Failed",GlobalHooks.values.get(0).get("accountid"),response.jsonPath().get("Data.Beneficiary[0].AccountId").toString());
//            Assert.assertEquals("Test Failed",GlobalHooks.values.get(0).get("beneficiary_id"),response.jsonPath().get("Data.Beneficiary[0].BeneficiaryId").toString());
//            Assert.assertEquals("Test Failed",GlobalHooks.values.get(0).get("reference"),response.jsonPath().get("Data.Beneficiary[0].Reference").toString());
            App_genericFunction.putcommentinStep (response.print());
            }catch (NullPointerException exception){
                System.out.println("There is No SO Available");
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

    @Then("I verify the cases against Banking portal for StandingOrder with response status code {string}")
    public void iVerifyTheCasesAgainstBankingPortalForStandingOrderWithResponseStatusCode(String Status_Code) throws InterruptedException, StopTestException {
        if( Configuration.getConfiguration().getProperty("digibank").startsWith("enabled")) {
            if (Status_Code.contentEquals("200")) {
                Assert.assertEquals("Test Failed", Integer.parseInt(Status_Code), response.getStatusCode());
                OBReadStandingOrder Obj1 = response.as(OBReadStandingOrder.class);
                general.getintoCAInternetBanking("", GlobalHooks.values.get(0).get("customer_no"), GlobalHooks.values.get(0).get("pac_no"), GlobalHooks.values.get(0).get("password"));
                Thread.sleep(2000);
                payment_details.clickOnPaymentServiceLink("Payment Services");
                Thread.sleep(2000);
                payment_details.clickOnPaymentServiceLink("Manage Standing Orders");
                try {
                    for (int i = 0; i < Obj1.getData().getStandingOrder().size(); i++) {
                        general.validateReularExpression("^(NotKnown)$|^(EvryDay)$|^(EvryWorkgDay)$|^(IntrvlWkDay:0[1-9]:0[1-7])$|^(WkInMnthDay:0[1-5]:0[1-7])$|^(IntrvlMnthDay:(0[1-6]|12|24):(-0[1-5]|0[1-9]|[12][0-9]|3[01]))$|^(QtrDay:(ENGLISH|SCOTTISH|RECEIVED))$", Obj1.getData().getStandingOrder().get(i).getFrequency());
                    }
                } catch (Exception e) {
                    System.out.println("No Data Returned from Service");
                }

                Map<String, String> accountCompleteDetails = new LinkedHashMap<>();

                try {
                    Thread.sleep(3000);
                    account_details.selctPersonalAccountType(GlobalHooks.values.get(0).get("currency"), GlobalHooks.values.get(0).get("accountid"));
                    //selenium_Helper.waitForJSandJQueryToLoad();
                    Thread.sleep(2000);
                    App_genericFunction.putScreenshotInStep();
                    String freq_value = null;
                    for (int i = 0; i < Obj1.getData().getStandingOrder().size(); i++) {
                        if (Obj1.getData().getStandingOrder().get(i).getFrequency().contains("IntrvlMnthDay:03")) {
                            freq_value = "QUARTERLY";
                        } else if (Obj1.getData().getStandingOrder().get(i).getFrequency().contains("IntrvlMnthDay:06")) {
                            freq_value = "HALF YEARLY";
                        } else if (Obj1.getData().getStandingOrder().get(i).getFrequency().contains("IntrvlMnthDay:12")) {
                            freq_value = "YEARLY";
                        } else if (Obj1.getData().getStandingOrder().get(i).getFrequency().contains("IntrvlMnthDay:01")) {
                            freq_value = "MONTHLY";
                        } else if (Obj1.getData().getStandingOrder().get(i).getFrequency().contains("IntrvlWkDay:02")) {
                            freq_value = "FORTNIGHTLY";
                        } else if (Obj1.getData().getStandingOrder().get(i).getFrequency().contains("IntrvlWkDay:01")) {
                            freq_value = "WEEKLY";
                        } else if (Obj1.getData().getStandingOrder().get(i).getFrequency().contains("EvryWorkgDay") || Obj1.getData().getStandingOrder().get(i).getFrequency().contains("EvryDay")) {
                            freq_value = "DAILY";
                        }

                        //accountCompleteDetails = standing_order.getViewStandingOrderData(App_genericFunction.ListofValues(GlobalHooks.values.get(0).get("debtoraccount")).get(i), Obj1.getData().getStandingOrder().get(i).getCreditorAccount().getName(), freq_value, Obj1.getData().getStandingOrder().get(i).getStandingOrderStatusCode().toUpperCase(), Obj1.getData().getStandingOrder().get(i).getFirstPaymentAmount().getAmount());
                        accountCompleteDetails = standing_order.getViewStandingOrderData(Obj1.getData().getStandingOrder().get(i).getReference().trim(), Obj1.getData().getStandingOrder().get(i).getCreditorAccount().getName(), freq_value, Obj1.getData().getStandingOrder().get(i).getStandingOrderStatusCode().toUpperCase(), Obj1.getData().getStandingOrder().get(i).getFirstPaymentAmount().getAmount());
                        Thread.sleep(2000);
                        System.out.println("StandingOrder: " + i + " --> " + accountCompleteDetails);
                        if (accountCompleteDetails.get("KEY") == "EMPTY") {
                            Assert.fail("Standing Order Information not there for Account");
                            System.out.println("Standing Order Information not there for Account");
                        } else {
                            Assert.assertEquals("Test Failed", accountCompleteDetails.get("Debit Account Number"), Obj1.getData().getStandingOrder().get(i).getAccountId());
                            App_genericFunction.OptionalField("so_id", accountCompleteDetails.get("Transaction Reference"), Obj1.getData().getStandingOrder().get(i).getStandingOrderId());
                            Assert.assertEquals("Test Failed", accountCompleteDetails.get("Frequency"), freq_value);
                            //App_genericFunction.OptionalField("reference",GlobalHooks.values.get(i).get("reference"),Obj1.getData().getStandingOrder().get(i).getReference());

                            App_genericFunction.OptionalField("firstpaymentdatetime", co.convertedformat3(accountCompleteDetails.get("Start Date")) + "T00:00:00+00:00", Obj1.getData().getStandingOrder().get(i).getFirstPaymentDateTime());
                            //if(Obj1.getData().getStandingOrder().get(i).getFirstPaymentAmount() != null && (!GlobalHooks.values.get(i).get("fpa_amount").isEmpty())) {
                            Assert.assertEquals("Test Failed", accountCompleteDetails.get("Transfer Amount").split(" ")[1], Obj1.getData().getStandingOrder().get(i).getFirstPaymentAmount().getAmount());
                            Assert.assertEquals("Test Failed", accountCompleteDetails.get("Currency"), Obj1.getData().getStandingOrder().get(i).getFirstPaymentAmount().getCurrency());
               /* }else
                {
                    App_genericFunction.putcommentinStep("Error : Optional Field fpa_amount Skipped");
                    Assert.fail();
                }*/

                            // App_genericFunction.OptionalField("nextpaymentdatetime",co.convertedformat3(accountCompleteDetails.get("End Date")) + "T00:00:00+00:00",Obj1.getData().getStandingOrder().get(i).getNextPaymentDateTime());
                            // if(Obj1.getData().getStandingOrder().get(i).getNextPaymentAmount() != null &&  (!GlobalHooks.values.get(i).get("npa_amount").isEmpty())) {
                            Assert.assertEquals("Test Failed", accountCompleteDetails.get("Transfer Amount").split(" ")[1], Obj1.getData().getStandingOrder().get(i).getNextPaymentAmount().getAmount());
                            Assert.assertEquals("Test Failed", accountCompleteDetails.get("Currency"), Obj1.getData().getStandingOrder().get(i).getNextPaymentAmount().getCurrency());
             /*   }else
                {
                    App_genericFunction.putcommentinStep("Error : Optional Field npa_amount Skipped");
                    Assert.fail();
                }*/

                            App_genericFunction.OptionalField("finalpaymentdatetime", co.convertedformat3(accountCompleteDetails.get("End Date")) + "T00:00:00+00:00", Obj1.getData().getStandingOrder().get(i).getFinalPaymentDateTime());
                            // if(Obj1.getData().getStandingOrder().get(i).getFinalPaymentAmount() != null && (!GlobalHooks.values.get(i).get("final_amount").isEmpty())) {
                            Assert.assertEquals("Test Failed", accountCompleteDetails.get("Transfer Amount").split(" ")[1], Obj1.getData().getStandingOrder().get(i).getFinalPaymentAmount().getAmount());
                            Assert.assertEquals("Test Failed", accountCompleteDetails.get("Currency"), Obj1.getData().getStandingOrder().get(i).getFinalPaymentAmount().getCurrency());
                            // }else
               /* {
                    App_genericFunction.putcommentinStep("Error : Optional Field final_amount Skipped");
                    Assert.fail();
                }*/
                            String status;
                            if(accountCompleteDetails.get("Status").contentEquals("CANCELLED"))
                            {
                                status = "INACTIVE";
                            }
                            else
                            {
                                status ="ACTIVE";
                            }
                           // App_genericFunction.OptionalField("standingorderstatuscode", accountCompleteDetails.get("Status"), Obj1.getData().getStandingOrder().get(i).getStandingOrderStatusCode().toUpperCase());
                            App_genericFunction.OptionalField("standingorderstatuscode", status, Obj1.getData().getStandingOrder().get(i).getStandingOrderStatusCode().toUpperCase());
                            if (!Obj1.getData().getStandingOrder().get(i).getStandingOrderStatusCode().isEmpty()) {
                                Obj.validateStandingOrderStatusCode(Obj1.getData().getStandingOrder().get(i).getStandingOrderStatusCode());
                            }
                            //if(Obj1.getData().getStandingOrder().get(i).getCreditorAccount()  != null && (!GlobalHooks.values.get(i).get("ca_schemename").isEmpty()))
                            // {
                            //Assert.assertEquals("Test Failed",GlobalHooks.values.get(i).get("ca_schemename"),Obj1.getData().getStandingOrder().get(i).getCreditorAccount().getSchemeName());
                            Assert.assertEquals("Test Failed", "165710" + accountCompleteDetails.get("Payee Account"), Obj1.getData().getStandingOrder().get(i).getCreditorAccount().getIdentification());
                            App_genericFunction.OptionalField("ca_name", accountCompleteDetails.get("Payee Name"), Obj1.getData().getStandingOrder().get(i).getCreditorAccount().getName());
                /*}else
                {
                    App_genericFunction.putcommentinStep("Error : Optional Field ca_schemename Skipped");
                    Assert.fail();
                }
*/
                            System.out.println("Validation completed for Standing Order : " + i + 1);
                           App_genericFunction.putcommentinStep("Validation completed for Standing Order : " + i + 1);
                        }
                        accountCompleteDetails.clear();
                    }
                } catch (Exception e) {
                    accountCompleteDetails = standing_order.getViewStandingOrderData("", "", "", "", "");
                    System.out.println("---------------------------------------");
                    System.out.println(accountCompleteDetails);
                    System.out.println("---------------------------------------");
                    if (accountCompleteDetails.get("KEY") == "EMPTY") {
                        System.out.println("Standing Order Information not there for Account");
                        App_genericFunction.putcommentinStep("Standing Order Information not there for Account");
                    }
                }
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
