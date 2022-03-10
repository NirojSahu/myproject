package com.test.stepDefs;

import Responses.OBReadAccounts3.SortbyAccount;
import Responses.OBReadTransaction4.OBReadTransaction;
import Responses.OBReadTransaction4.SortbyBookingDatetime;
import Responses.OBReadTransaction4.Transaction;
import Utilities.JsonUtilities;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.google.inject.Inject;
import com.test.APIFunctions.TransactionsService;
import com.test.CustomHooks.GlobalHooks;
import com.test.Utils.App_genericFunction;
import com.test.Utils.CommonFunctions;
import com.test.configuration.Configuration;
import com.test.exceptions.StopTestException;
import com.test.pages.MyAccounts;
import com.test.pages.TableDataSet;
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
import java.util.Collections;
import java.util.Map;

public class TransactionsStepDef {

    @Inject
    TransactionsService Obj;
    @Inject
    App_genericFunction general;
    @Inject
    MyAccounts account_details;
    @Inject
    CommonFunctions com;


    public String Verbose;
    Response response;
    JsonPath JsonPathEvaluator;
    public static String USER_DI= System.getProperty("user.dir");

    @Then("I want to validate service Response for Valid Schema for transactions")
    public void iWantToValidateServiceResponseForValidSchemaForTransactions() throws IOException, ProcessingException {
        if(GlobalHooks.resource_API.contentEquals("transactions_1")) {
            App_genericFunction.putcommentinStep(response.getBody().asString());
            App_genericFunction.putcommentinStep(JsonUtilities.validateSchema(response,"/Schemas/transactions_1.json").toString());
            if(JsonUtilities.validateSchema(response,"/Schemas/transactions_1.json").toString().contains("error"))
            {
                Assert.fail("Schemavalidation failed : issue with the response");
            }
        }

    }

    @When("I want to make a call to transactions_service to get transactions information")
    public void iWantToMakeACallToTransactions_serviceToGetTransactionsInformation() {
        response = Obj.hitService_get();
    }

    @Given("I have the data available to create {string} request for transactions_service with Headers")
    public void iHaveTheDataAvailableToCreateRequestForTransactions_serviceWithHeaders(String arg0) throws FileNotFoundException, JSONException, JsonProcessingException {
        Obj.setHeaders_get();
    }

    @Then("I want to validate service Response and store the TransactionInfo with Status code {string}")
    public void iWantToValidateServiceResponseAndStoreTheTransactionInfoWithStatusCode(String Status_Code) {
        JsonPathEvaluator= Obj.getResponse(response);
        if(Status_Code.contentEquals("200")) {
            OBReadTransaction obj1 = response.as(OBReadTransaction.class);
            Assert.assertEquals("Test Failed",Integer.parseInt(Status_Code),response.getStatusCode());
            App_genericFunction.putcommentinStep("Total transactions count : "+JsonPathEvaluator.getList("Data.Transaction").size());
            App_genericFunction.putcommentinStep("Total transactions count from POJO : "+ obj1.getData().getTransaction().size());
            Assert.assertEquals("Transaction Count Failed",JsonPathEvaluator.getList("Data.Transaction").size(), Integer.parseInt(GlobalHooks.values.get(0).get("transaction_count")));
            for(int i =0;i < obj1.getData().getTransaction().size();i++)
            {
                Obj.validateCreditDebitIndicator(obj1.getData().getTransaction().get(i).getCreditDebitIndicator());
                Obj.validateStatus(obj1.getData().getTransaction().get(i).getStatus());
            }

            for(int i =0;i < obj1.getData().getTransaction().size();i++)
            {
                Assert.assertEquals("Test Failed ",GlobalHooks.values.get(i).get("accountid"),obj1.getData().getTransaction().get(i).getAccountId());
                App_genericFunction.OptionalField("transactionreference",GlobalHooks.values.get(i).get("transactionreference"),obj1.getData().getTransaction().get(i).getTransactionReference());
                if((obj1.getData().getTransaction().get(i).getAmount() != null)  && (!GlobalHooks.values.get(i).get("amount").isEmpty()))
                {
                    Assert.assertEquals("Test Failed ",GlobalHooks.values.get(i).get("amount"),obj1.getData().getTransaction().get(i).getAmount().getAmount());
                    Assert.assertEquals("Test Failed ",GlobalHooks.values.get(i).get("currency"),obj1.getData().getTransaction().get(i).getAmount().getCurrency());
                }else
                {
                    App_genericFunction.putcommentinStep("Error : Optional Field Amount Skipped");
                    Assert.fail();
                }

                Assert.assertEquals("Test Failed ",GlobalHooks.values.get(i).get("creditdebitindicator"),obj1.getData().getTransaction().get(i).getCreditDebitIndicator());
                Assert.assertEquals("Test Failed ",GlobalHooks.values.get(i).get("status"),obj1.getData().getTransaction().get(i).getStatus());
                Assert.assertTrue("Test Failed ",obj1.getData().getTransaction().get(i).getBookingDateTime().contains(GlobalHooks.values.get(i).get("bookingdatetime")));
                if(!GlobalHooks.values.get(i).get("valuedatetime").isEmpty())
                {
                    Assert.assertTrue("Test Failed ",obj1.getData().getTransaction().get(i).getValueDateTime().contains(GlobalHooks.values.get(i).get("valuedatetime")));
                }
                App_genericFunction.OptionalField("transactioninformation",GlobalHooks.values.get(i).get("transactioninformation"),obj1.getData().getTransaction().get(i).getTransactionInformation());

                if((obj1.getData().getTransaction().get(i).getBalance() != null) && (!GlobalHooks.values.get(i).get("balance_amount").isEmpty())) {
                    Assert.assertEquals("Test Failed ", GlobalHooks.values.get(i).get("balance_amount"), obj1.getData().getTransaction().get(i).getBalance().getAmount().getAmount());
                    Assert.assertEquals("Test Failed ",GlobalHooks.values.get(i).get("balance_currency"), obj1.getData().getTransaction().get(i).getBalance().getAmount().getCurrency() );
                    Assert.assertEquals("Test Failed ", GlobalHooks.values.get(i).get("balance_creditdebitindicator"), obj1.getData().getTransaction().get(i).getBalance().getCreditDebitIndicator());
                    Assert.assertEquals("Test Failed ", GlobalHooks.values.get(i).get("type"), obj1.getData().getTransaction().get(i).getBalance().getType());
                    Obj.validateBalancetype(GlobalHooks.values.get(i).get("type"));
                }else
                {
                    App_genericFunction.putcommentinStep("Error : Optional Field Balance Skipped");
                    Assert.fail();
                }

            }

        }else if(Status_Code.contentEquals("400")) {
            App_genericFunction.putcommentinStep("StatusCode : "+response.getStatusCode());
            Assert.assertEquals("Test Failed",Integer.parseInt(Status_Code),response.getStatusCode());
            App_genericFunction.putcommentinStep("ErrorCode : "+JsonPathEvaluator.get("Errors[0].ErrorCode"));
            App_genericFunction.putcommentinStep("Message : "+JsonPathEvaluator.get("Errors[0].Message"));
        }else if(Status_Code.contentEquals("401")) {
            Assert.assertEquals("Test Failed",Integer.parseInt(Status_Code),response.getStatusCode());
            App_genericFunction.putcommentinStep("StatusCode : "+response.getStatusCode());
            Assert.assertTrue("Error Message is not valid",response.asString().contains("Invalid Credentials. Make sure you have given the correct access token"));
            App_genericFunction.putcommentinStep((response.print()));
        }
    }

    @Then("I verify the cases against Banking portal for Transactions with response status code {string}")
    public void iVerifyTheCasesAgainstBankingPortalForTransactionsWithResponseStatusCode(String Status_Code) throws InterruptedException, ParseException, StopTestException {
        if( Configuration.getConfiguration().getProperty("digibank").startsWith("enabled")) {
            if(Status_Code.contentEquals("200")) {
                Assert.assertEquals("Test Failed", Integer.parseInt(Status_Code), response.getStatusCode());
                Map<Integer, TableDataSet> TransactionListData = null;
                String current_time = "";
                String previous_time = "";

                //Collections.sort(obj1.getData().getTransaction(), new SortbyBookingDatetime());
                general.getintoCAInternetBanking("", GlobalHooks.values.get(0).get("customer_no"), GlobalHooks.values.get(0).get("pac_no"), GlobalHooks.values.get(0).get("password"));
                account_details.clickOnMyAccountsServiceLink("Transaction List");
                account_details.selctPersonalAccountType(GlobalHooks.values.get(0).get("currency_ui"), GlobalHooks.values.get(0).get("account_id"));
                if ((GlobalHooks.values.get(0).get("from_date").isEmpty()) && (GlobalHooks.values.get(0).get("to_date").isEmpty())) {
                    TransactionListData = account_details.selectDateRangeAndSearch("01-01-2021", "20-02-2021");
                } else if ((!GlobalHooks.values.get(0).get("from_date").isEmpty()) && (!GlobalHooks.values.get(0).get("to_date").isEmpty())) {
                    TransactionListData = account_details.selectDateRangeAndSearch(com.convertedformat1(GlobalHooks.values.get(0).get("from_date").split("T")[0]), com.convertedformat1(GlobalHooks.values.get(0).get("to_date").split("T")[0]));
                } else if (!GlobalHooks.values.get(0).get("from_date").isEmpty()) {
                    current_time = com.convertedformat2(com.getCurrentDatewithouttime());
                    System.out.println("fromdate : " + com.convertedformat1(GlobalHooks.values.get(0).get("from_date").split("T")[0]));
                    System.out.println("todate : " + com.convertedformat1(com.next6months(GlobalHooks.values.get(0).get("from_date").split("T")[0]).toString()));
                    String todate =com.convertedformat1(com.next6months(GlobalHooks.values.get(0).get("from_date").split("T")[0]).toString());
                    TransactionListData = account_details.selectDateRangeAndSearch(com.convertedformat1(GlobalHooks.values.get(0).get("from_date").split("T")[0]), todate);
                } else if (!GlobalHooks.values.get(0).get("to_date").isEmpty()) {
                    //previous_time =com.takepreviousyearwithcurrentdate();
                    previous_time = com.convertedformat1(com.previous6months(GlobalHooks.values.get(0).get("to_date").split("T")[0]).toString());
                    System.out.println("fromdate : " + previous_time);
                    System.out.println("todate : " + com.convertedformat1(GlobalHooks.values.get(0).get("to_date").split("T")[0]));
                    TransactionListData = account_details.selectDateRangeAndSearch(previous_time, com.convertedformat1(GlobalHooks.values.get(0).get("to_date").split("T")[0]));
                }
                System.out.println("Data from Web : " + TransactionListData);
                if(TransactionListData.get(0) == null)
                {
                    try
                    {
                        JsonUtilities.validateSchema(response,"/Schemas/transactions_1.json");
                        OBReadTransaction obj1 = response.as(OBReadTransaction.class);
                        System.out.println("No Transaction exist");
                    }catch(Exception e)
                    {
                        Assert.fail("Issue with the schema");
                    }

                }
                else
                {
                    OBReadTransaction obj1 = response.as(OBReadTransaction.class);
                    Collections.reverse(obj1.getData().getTransaction());
                    App_genericFunction.putcommentinStep("Total transactions count from POJO : " + obj1.getData().getTransaction().size());
                    for (int i = 0; i < obj1.getData().getTransaction().size(); i++) {
                        Obj.validateCreditDebitIndicator(obj1.getData().getTransaction().get(i).getCreditDebitIndicator());
                        Obj.validateStatus(obj1.getData().getTransaction().get(i).getStatus());
                    }
                    for (int i = 0; i < obj1.getData().getTransaction().size(); i++) {
                        // Assert.assertEquals("Test Failed ",GlobalHooks.values.get(i).get("accountid"),obj1.getData().getTransaction().get(i).getAccountId());
                        App_genericFunction.OptionalField("transactionreference", TransactionListData.get(i).getTransactionDetails(), obj1.getData().getTransaction().get(i).getTransactionReference());
                        if (obj1.getData().getTransaction().get(i).getAmount() != null) {
                            if (TransactionListData.get(i).getCredit().contentEquals("-")) {
                                Assert.assertEquals("Test Failed ", "Debit", obj1.getData().getTransaction().get(i).getCreditDebitIndicator());
                                String actual="";
                                for(int k=0; k<obj1.getData().getTransaction().size();k++){
                                    if(TransactionListData.get(k).getDebits().split(" ")[1].equals(obj1.getData().getTransaction().get(i).getAmount().getAmount()))
                                    {
                                        actual=obj1.getData().getTransaction().get(i).getAmount().getAmount();
                                        Assert.assertEquals("Test Failed ", TransactionListData.get(k).getDebits().split(" ")[1], actual);
                                        Assert.assertEquals("Test Failed ", TransactionListData.get(k).getDebits().split(" ")[0], obj1.getData().getTransaction().get(i).getAmount().getCurrency());
                                        break;
                                    }
                                }
//                                Assert.assertEquals("Test Failed ", TransactionListData.get(i).getDebits().split(" ")[1], obj1.getData().getTransaction().get(i).getAmount().getAmount());

                            } else if (TransactionListData.get(i).getDebits().contentEquals("-")) {
                                Assert.assertEquals("Test Failed ", "Credit", obj1.getData().getTransaction().get(i).getCreditDebitIndicator());
                                String amount=TransactionListData.get(i).getCredit().split(" ")[1];
                                if(amount.contains(",")){
                                    amount=amount.replace(",","");
                                }
                                Assert.assertEquals("Test Failed ", amount, obj1.getData().getTransaction().get(i).getAmount().getAmount());
//                                Assert.assertEquals("Test Failed ", TransactionListData.get(i).getCredit().split(" ")[1], obj1.getData().getTransaction().get(i).getAmount().getAmount());
                                Assert.assertEquals("Test Failed ", TransactionListData.get(i).getCredit().split(" ")[0], obj1.getData().getTransaction().get(i).getAmount().getCurrency());
                            }

                        } else {
                            App_genericFunction.putcommentinStep("Error : Optional Field Amount Skipped");
                            //   Assert.fail();
                        }

                        //Assert.assertEquals("Test Failed ",GlobalHooks.values.get(i).get("status"),obj1.getData().getTransaction().get(i).getStatus());
                        Assert.assertTrue("Test Failed at : " + i , obj1.getData().getTransaction().get(i).getBookingDateTime().contains(com.convertedformat3(TransactionListData.get(i).getTransactionDate())));
                        Assert.assertTrue("Test Failed at : " + i, obj1.getData().getTransaction().get(i).getValueDateTime().contains(com.convertedformat3(TransactionListData.get(i).getValueDate())));

                        App_genericFunction.OptionalField("transactioninformation", TransactionListData.get(i).getTransactionDetails(), obj1.getData().getTransaction().get(i).getTransactionInformation());

                        if (obj1.getData().getTransaction().get(i).getBalance() != null) {
                            String amount="";
                            if(TransactionListData.get(i).getCredit().equals("-")){
                                 amount=TransactionListData.get(i).getDebits().split(" ")[1];
                            }
                            else {
                                 amount = TransactionListData.get(i).getCredit().split(" ")[1];
                            }

                            System.out.println("Amount-----"+amount);
                            if(amount.contains(",")){
                                amount=amount.replace(",","");
                                System.out.println("Amount-----"+amount);
                            }

                            Obj.validateBalancetype(obj1.getData().getTransaction().get(i).getBalance().getType());
                        } else {
                            App_genericFunction.putcommentinStep("Error : Optional Field Balance Skipped");
                            Assert.fail();
                        }

                    }
                }
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

    @When("I want to make a call to transactions_service to get transactions information with limits")
    public void iWantToMakeACallToTransactions_serviceToGetTransactionsInformationWithLimits() {
        response = Obj.hitService_get_withqueryParam();
    }

    @Then("I want to validate service Response and store the TransactionInfo with pagination with Status code {string}")
    public void iWantToValidateServiceResponseAndStoreTheTransactionInfoWithPaginationWithStatusCode(String Status_Code) {
        JsonPathEvaluator= Obj.getResponse(response);
        if(Status_Code.contentEquals("200")) {
            Assert.assertEquals("Test Failed",Integer.parseInt(Status_Code),response.getStatusCode());
            App_genericFunction.putcommentinStep("Total transactions count : "+JsonPathEvaluator.getList("Data.Transaction").size());
            Assert.assertEquals("Transaction Count Failed",JsonPathEvaluator.getList("Data.Transaction").size(), Integer.parseInt(GlobalHooks.values.get(0).get("transaction_count")));
        }else if (Status_Code.contentEquals("400")) {
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
    }
}
