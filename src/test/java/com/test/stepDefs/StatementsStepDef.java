package com.test.stepDefs;

import Responses.OBReadStatement1.OBReadStatement;
import Responses.OBReadStatement2.OBReadStatment3;
import Utilities.JsonUtilities;
import Utilities.WebKit.configuration.Configuration;
import Utilities.WebKit.exceptions.StopTestException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.google.inject.Inject;
import com.test.APIFunctions.StatementsService;
import com.test.CustomHooks.GlobalHooks;
import com.test.Utils.App_genericFunction;
//import com.test.configuration.Configuration;
//import com.test.exceptions.StopTestException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.codehaus.jettison.json.JSONException;
import org.junit.Assert;
//import scala.App;

import java.io.FileNotFoundException;
import java.io.IOException;

public class StatementsStepDef {

    @Inject
    StatementsService Obj;
    @Inject
    App_genericFunction general;

    public String Verbose;
    Response response;
    JsonPath JsonPathEvaluator;
    public static String USER_DI= System.getProperty("user.dir");

    @Given("I have the data available to create {string} request for statements_service with Headers")
    public void i_have_the_data_available_to_create_request_for_statements_service_with_Headers(String string) throws FileNotFoundException, JSONException, JsonProcessingException {
        Obj.setHeaders_get();
    }

    @When("I want to make a call to statements_service to get statement information")
    public void i_want_to_make_a_call_to_statements_service_to_get_statement_information() {
        response = Obj.hitService_get();
    }
    @When("I want to make a call to statements_service to get statement information with AccountID and StatementID")
    public void iWantToMakeACallToStatements_serviceToGetStatementInformationWithAccountIDAndStatementID() {
        response = Obj.hitService_get();
    }

    @Then("I want to validate service Response for Valid Schema for statements with Accountid")
    public void i_want_to_validate_service_Response_for_Valid_Schema_for_statements_with_Accountid() throws IOException, ProcessingException {
        if(GlobalHooks.resource_API.contentEquals("statements_1")) {
            App_genericFunction.putcommentinStep(response.getBody().asString());
            App_genericFunction.putcommentinStep(JsonUtilities.validateSchema(response,"/Schemas/Statements_1.json").toString());
            if(JsonUtilities.validateSchema(response,"/Schemas/Statements_1.json").toString().contains("error"))
            {
                Assert.fail("Schemavalidation failed : issue with the response");
            }
        }else if(GlobalHooks.resource_API.contentEquals("statements_2"))
        {
            App_genericFunction.putcommentinStep(response.getBody().asString());
            App_genericFunction.putcommentinStep(JsonUtilities.validateSchema(response,"/Schemas/Statements_2.json").toString());
            if(JsonUtilities.validateSchema(response,"/Schemas/Statements_2.json").toString().contains("error"))
            {
                Assert.fail("Schemavalidation failed : issue with the response");
            }

        }

    }

    @Then("I want to validate service Response and store the Statements for accounts  with Status code {string}")
    public void i_want_to_validate_service_Response_and_store_the_Statements_for_accounts_with_Status_code(String Status_Code) {
        JsonPathEvaluator= Obj.getResponse(response);

        if(Status_Code.contentEquals("200")) {
            Assert.assertEquals("Test Failed",Integer.parseInt(Status_Code),response.getStatusCode());
            OBReadStatement Obj1= response.as(OBReadStatement.class);
            System.out.println("Total Statements : " + JsonPathEvaluator.getList("Data.Statement").size());
            System.out.println("Total Statements_fromPojo : " + Obj1.getData().getStatement().size());
            App_genericFunction.getRecordsCount("statements",GlobalHooks.scenario_type);
//            Assert.assertEquals("Test Failed Total Statements",App_genericFunction.getRecordsCount("statements",GlobalHooks.scenario_type).toString(),Integer.toString(Obj1.getData().getStatement().size()));
            App_genericFunction.putcommentinStep("Total Statements : " +Obj1.getData().getStatement().size());
            for(int i =0;i < Obj1.getData().getStatement().size();i++)
            {
                Obj.validateStatementtype(Obj1.getData().getStatement().get(i).getType());
            }

            for(int i =0;i < Obj1.getData().getStatement().size();i++)
            {
                Assert.assertEquals("Test Failed ",GlobalHooks.values.get(i).get("accountId"),Obj1.getData().getStatement().get(i).getAccountId());
                App_genericFunction.OptionalField("statementId",GlobalHooks.values.get(i).get("statementId"),Obj1.getData().getStatement().get(i).getStatementId());
//                App_genericFunction.OptionalField("statementreference",GlobalHooks.values.get(i).get("statementreference"),Obj1.getData().getStatement().get(i).getStatementReference());
                Assert.assertEquals("Test Failed ",GlobalHooks.values.get(i).get("type"),Obj1.getData().getStatement().get(i).getType());
                if(!GlobalHooks.values.get(i).get("starttime").isEmpty())
                {
                    Assert.assertTrue(Obj1.getData().getStatement().get(i).getStartDateTime().contains(GlobalHooks.values.get(i).get("starttime")));
                }
                else if(!GlobalHooks.values.get(i).get("endtime").isEmpty()) {
                    Assert.assertTrue(Obj1.getData().getStatement().get(i).getEndDateTime().contains(GlobalHooks.values.get(i).get("endtime")));
                }
                else if(!GlobalHooks.values.get(i).get("creationtime").isEmpty())
                {
                    Assert.assertTrue(Obj1.getData().getStatement().get(i).getCreationDateTime().contains(GlobalHooks.values.get(i).get("creationtime")));
                }

                for(int j =0;j < Obj1.getData().getStatement().get(i).getStatementAmount().size();j++)
                {

                    Assert.assertEquals("Test Failed ",App_genericFunction.ListofValues(GlobalHooks.values.get(i).get("sa_amount")).get(j),Obj1.getData().getStatement().get(i).getStatementAmount().get(j).getAmount().getAmount());
                    Assert.assertEquals("Test Failed ",App_genericFunction.ListofValues(GlobalHooks.values.get(i).get("sa_currency")).get(j),Obj1.getData().getStatement().get(i).getStatementAmount().get(j).getAmount().getCurrency());
                    Obj.validateCreditDebitIndicator(Obj1.getData().getStatement().get(i).getStatementAmount().get(j).getCreditDebitIndicator());
                    Assert.assertEquals("Test Failed ",App_genericFunction.ListofValues(GlobalHooks.values.get(i).get("sa_creditdebitindicator")).get(j),Obj1.getData().getStatement().get(i).getStatementAmount().get(j).getCreditDebitIndicator());
                    Assert.assertEquals("Test Failed ",App_genericFunction.ListofValues(GlobalHooks.values.get(i).get("statementamount_type")).get(j),Obj1.getData().getStatement().get(i).getStatementAmount().get(j).getType());
                }

            }
           App_genericFunction.putcommentinStep(response.print());
         }else if(Status_Code.contentEquals("400"))
        {
            App_genericFunction.putcommentinStep("StatusCode : "+response.getStatusCode());
            Assert.assertEquals("Test Failed",Integer.parseInt(Status_Code),response.getStatusCode());
            App_genericFunction.putcommentinStep("ErrorCode : "+JsonPathEvaluator.get("Errors[0].ErrorCode"));
            App_genericFunction.putcommentinStep("Message : "+JsonPathEvaluator.get("Errors[0].Message"));

        }else if(Status_Code.contentEquals("401"))
        {
            Assert.assertEquals("Test Failed",Integer.parseInt(Status_Code),response.getStatusCode());
            App_genericFunction.putcommentinStep("StatusCode : "+response.getStatusCode());
            Assert.assertTrue("Error Message is not valid",response.asString().contains("Invalid Credentials. Make sure you have given the correct access token"));
            App_genericFunction.putcommentinStep((response.print()));
        }

    }

//    @Then("I want to validate service Response and store the Statements with StatementID for account with Status code {string}")
//    public void iWantToValidateServiceResponseAndStoreTheStatementsWithStatementIDForAccountWithStatusCode(String Status_Code) {
//        JsonPathEvaluator= Obj.getResponse(response);
//        if(Status_Code.contentEquals("200")) {
//            OBReadStatement Obj1= response.as(OBReadStatement.class);
//            App_genericFunction.putcommentinStep("Total Statements : " + JsonPathEvaluator.getList("Data.Statement").size());
//            App_genericFunction.putcommentinStep("Total Statements_fromPojo : " + Obj1.getData().getStatement().size());
//            Assert.assertEquals("Test Failed for total statements",Integer.parseInt(GlobalHooks.values.get(0).get("Statementcount")),Obj1.getData().getStatement().size());
//            for(int i =0;i < Obj1.getData().getStatement().size();i++)
//            {
//                Obj.validateStatementtype(Obj1.getData().getStatement().get(i).getType());
//            }
//            for(int i =0;i < Obj1.getData().getStatement().size();i++)
//            {
//                Assert.assertEquals("Test Failed ",GlobalHooks.values.get(i).get("accountId"),Obj1.getData().getStatement().get(i).getAccountId());
//                App_genericFunction.OptionalField("statementId",GlobalHooks.values.get(i).get("statementId"),Obj1.getData().getStatement().get(i).getStatementId());
////                App_genericFunction.OptionalField("statementreference",GlobalHooks.values.get(i).get("statementreference"),Obj1.getData().getStatement().get(i).getStatementReference());
//                Assert.assertEquals("Test Failed ",GlobalHooks.values.get(i).get("type"),Obj1.getData().getStatement().get(i).getType());
//                if(!GlobalHooks.values.get(i).get("starttime").isEmpty())
//                {
//                    Assert.assertTrue(Obj1.getData().getStatement().get(i).getStartDateTime().contains(GlobalHooks.values.get(i).get("starttime")));
//                }
//                if(!GlobalHooks.values.get(i).get("endtime").isEmpty()) {
//                    Assert.assertTrue(Obj1.getData().getStatement().get(i).getEndDateTime().contains(GlobalHooks.values.get(i).get("endtime")));
//                }
//               if(!GlobalHooks.values.get(i).get("creationtime").isEmpty())
//                {
//                    Assert.assertTrue(Obj1.getData().getStatement().get(i).getCreationDateTime().contains(GlobalHooks.values.get(i).get("creationtime")));
//                }
//               // {Assert.assertFalse(Obj1.getData().getStatement().get(i).getCreationDateTime().contains(GlobalHooks.values.get(i).get("creationtime")));}
//                for(int j =0;j < Obj1.getData().getStatement().get(i).getStatementAmount().size();j++)
//                {
//                    Assert.assertEquals("Test Failed ",App_genericFunction.ListofValues(GlobalHooks.values.get(i).get("sa_amount")).get(j),Obj1.getData().getStatement().get(i).getStatementAmount().get(j).getAmount().getAmount());
//                    Assert.assertEquals("Test Failed ",App_genericFunction.ListofValues(GlobalHooks.values.get(i).get("sa_currency")).get(j),Obj1.getData().getStatement().get(i).getStatementAmount().get(j).getAmount().getCurrency());
//                    Obj.validateCreditDebitIndicator(Obj1.getData().getStatement().get(i).getStatementAmount().get(j).getCreditDebitIndicator());
//                    Assert.assertEquals("Test Failed ",App_genericFunction.ListofValues(GlobalHooks.values.get(i).get("sa_creditdebitindicator")).get(j),Obj1.getData().getStatement().get(i).getStatementAmount().get(j).getCreditDebitIndicator());
//                    Assert.assertEquals("Test Failed ",App_genericFunction.ListofValues(GlobalHooks.values.get(i).get("statementamount_type")).get(j),Obj1.getData().getStatement().get(i).getStatementAmount().get(j).getType());
//                }
//
//            }
//
//        }else if(Status_Code.contentEquals("400"))
//        {
//            App_genericFunction.putcommentinStep("StatusCode : "+response.getStatusCode());
//            Assert.assertEquals("Test Failed",Integer.parseInt(Status_Code),response.getStatusCode());
//            App_genericFunction.putcommentinStep("ErrorCode : "+JsonPathEvaluator.get("Errors[0].ErrorCode"));
//            App_genericFunction.putcommentinStep("Message : "+JsonPathEvaluator.get("Errors[0].Message"));
//
//        }else if(Status_Code.contentEquals("401"))
//        {
//            Assert.assertEquals("Test Failed",Integer.parseInt(Status_Code),response.getStatusCode());
//            App_genericFunction.putcommentinStep("StatusCode : "+response.getStatusCode());
//            Assert.assertTrue("Error Message is not valid",response.asString().contains("Invalid Credentials. Make sure you have given the correct access token"));
//            App_genericFunction.putcommentinStep((response.print()));
//        }
//    }

    @Then("I want to validate service Response and store the Statements with StatementID for account with Status code {string}")
    public void iWantToValidateServiceResponseAndStoreTheStatementsWithStatementIDForAccountWithStatusCode(String Status_Code) {
        JsonPathEvaluator= Obj.getResponse(response);
        if(Status_Code.contentEquals("200")) {
            OBReadStatment3 Obj1= response.as(OBReadStatment3.class);
//            App_genericFunction.putcommentinStep("Total Statements : " + JsonPathEvaluator.getList("Data.Statement").size());


                Obj.validateStatementtype(Obj1.getData().getStatement().getType());
                Assert.assertEquals("Test Failed ",GlobalHooks.values.get(0).get("accountId"),Obj1.getData().getStatement().getAccountId());
                App_genericFunction.OptionalField("statementId",GlobalHooks.values.get(0).get("statementId"),Obj1.getData().getStatement().getStatementId());

                Assert.assertEquals("Test Failed ",GlobalHooks.values.get(0).get("type"),Obj1.getData().getStatement().getType());
                if(!GlobalHooks.values.get(0).get("starttime").isEmpty())
                {
                    Assert.assertTrue(Obj1.getData().getStatement().getStartDateTime().contains(GlobalHooks.values.get(0).get("starttime")));
                }
                if(!GlobalHooks.values.get(0).get("endtime").isEmpty()) {
                    Assert.assertTrue(Obj1.getData().getStatement().getEndDateTime().contains(GlobalHooks.values.get(0).get("endtime")));
                }
                if(!GlobalHooks.values.get(0).get("creationtime").isEmpty())
                {
                    Assert.assertTrue(Obj1.getData().getStatement().getCreationDateTime().contains(GlobalHooks.values.get(0).get("creationtime")));
                }
                // {Assert.assertFalse(Obj1.getData().getStatement().get(i).getCreationDateTime().contains(GlobalHooks.values.get(i).get("creationtime")));}
                for(int j =0;j < Obj1.getData().getStatement().getStatementAmount().size();j++)
                {
                    Assert.assertEquals("Test Failed ",App_genericFunction.ListofValues(GlobalHooks.values.get(0).get("sa_amount")).get(j),Obj1.getData().getStatement().getStatementAmount().get(j).getAmount().getAmount());
                    Assert.assertEquals("Test Failed ",App_genericFunction.ListofValues(GlobalHooks.values.get(0).get("sa_currency")).get(j),Obj1.getData().getStatement().getStatementAmount().get(j).getAmount().getCurrency());
                    Obj.validateCreditDebitIndicator(Obj1.getData().getStatement().getStatementAmount().get(j).getCreditDebitIndicator());
                    Assert.assertEquals("Test Failed ",App_genericFunction.ListofValues(GlobalHooks.values.get(0).get("sa_creditdebitindicator")).get(j),Obj1.getData().getStatement().getStatementAmount().get(j).getCreditDebitIndicator());
                    Assert.assertEquals("Test Failed ",App_genericFunction.ListofValues(GlobalHooks.values.get(0).get("statementamount_type")).get(j),Obj1.getData().getStatement().getStatementAmount().get(j).getType());
                }

        }else if(Status_Code.contentEquals("400"))
        {
            App_genericFunction.putcommentinStep("StatusCode : "+response.getStatusCode());
            Assert.assertEquals("Test Failed",Integer.parseInt(Status_Code),response.getStatusCode());
            App_genericFunction.putcommentinStep("ErrorCode : "+JsonPathEvaluator.get("Errors[0].ErrorCode"));
            App_genericFunction.putcommentinStep("Message : "+JsonPathEvaluator.get("Errors[0].Message"));

        }else if(Status_Code.contentEquals("401"))
        {
            Assert.assertEquals("Test Failed",Integer.parseInt(Status_Code),response.getStatusCode());
            App_genericFunction.putcommentinStep("StatusCode : "+response.getStatusCode());
            Assert.assertTrue("Error Message is not valid",response.asString().contains("Invalid Credentials. Make sure you have given the correct access token"));
            App_genericFunction.putcommentinStep((response.print()));
        }
    }

    @Then("I verify the cases against Banking portal for Ststements with response status code {string}")
    public void iVerifyTheCasesAgainstBankingPortalForStstementsWithResponseStatusCode(String Status_Code) throws StopTestException {
        //JsonPathEvaluator= Obj.getResponse(response);
        if( Configuration.getConfiguration().getProperty("digibank").startsWith("enabled")) {

            if(Status_Code.contentEquals("200")) {
                Assert.assertEquals("Test Failed",Integer.parseInt(Status_Code),response.getStatusCode());
                OBReadStatement Obj1= response.as(OBReadStatement.class);
                //  System.out.println("Total Statements : " + JsonPathEvaluator.getList("Data.Statement").size());
                //  System.out.println("Total Statements_fromPojo : " + Obj1.getData().getStatement().size());
                //App_genericFunction.getRecordsCount("statements",GlobalHooks.scenario_type);
                // Assert.assertEquals("Test Failed Total Statements",App_genericFunction.getRecordsCount("statements",GlobalHooks.scenario_type).toString(),Integer.toString(Obj1.getData().getStatement().size()));
                try {
                    App_genericFunction.putcommentinStep("Total Statements : " + Obj1.getData().getStatement().size());
                    for (int i = 0; i < Obj1.getData().getStatement().size(); i++) {
                        Obj.validateStatementtype(Obj1.getData().getStatement().get(i).getType());
                    }
                }catch(Exception e)
                {
                    System.out.println("No Data Returned from Service");
                }

            /*for(int i =0;i < Obj1.getData().getStatement().size();i++)
            {
                Assert.assertEquals("Test Failed ",GlobalHooks.values.get(i).get("accountId"),Obj1.getData().getStatement().get(i).getAccountId());
                App_genericFunction.OptionalField("statementId",GlobalHooks.values.get(i).get("statementId"),Obj1.getData().getStatement().get(i).getStatementId());
                App_genericFunction.OptionalField("statementreference",GlobalHooks.values.get(i).get("statementreference"),Obj1.getData().getStatement().get(i).getStatementReference());
                Assert.assertEquals("Test Failed ",GlobalHooks.values.get(i).get("type"),Obj1.getData().getStatement().get(i).getType());
                if(!GlobalHooks.values.get(i).get("starttime").isEmpty())
                {
                    Assert.assertTrue(Obj1.getData().getStatement().get(i).getStartDateTime().contains(GlobalHooks.values.get(i).get("starttime")));
                }
                else if(!GlobalHooks.values.get(i).get("endtime").isEmpty()) {
                    Assert.assertTrue(Obj1.getData().getStatement().get(i).getEndDateTime().contains(GlobalHooks.values.get(i).get("endtime")));
                }
                else if(!GlobalHooks.values.get(i).get("creationtime").isEmpty())
                {
                    Assert.assertTrue(Obj1.getData().getStatement().get(i).getCreationDateTime().contains(GlobalHooks.values.get(i).get("creationtime")));
                }

                for(int j =0;j < Obj1.getData().getStatement().get(i).getStatementAmount().size();j++)
                {

                    Assert.assertEquals("Test Failed ",App_genericFunction.ListofValues(GlobalHooks.values.get(i).get("sa_amount")).get(j),Obj1.getData().getStatement().get(i).getStatementAmount().get(j).getAmount().getAmount());
                    Assert.assertEquals("Test Failed ",App_genericFunction.ListofValues(GlobalHooks.values.get(i).get("sa_currency")).get(j),Obj1.getData().getStatement().get(i).getStatementAmount().get(j).getAmount().getCurrency());
                    Obj.validateCreditDebitIndicator(Obj1.getData().getStatement().get(i).getStatementAmount().get(j).getCreditDebitIndicator());
                    Assert.assertEquals("Test Failed ",App_genericFunction.ListofValues(GlobalHooks.values.get(i).get("sa_creditdebitindicator")).get(j),Obj1.getData().getStatement().get(i).getStatementAmount().get(j).getCreditDebitIndicator());
                    Assert.assertEquals("Test Failed ",App_genericFunction.ListofValues(GlobalHooks.values.get(i).get("statementamount_type")).get(j),Obj1.getData().getStatement().get(i).getStatementAmount().get(j).getType());
                }

            }*/
                App_genericFunction.putcommentinStep(response.print());
            }
            else if(Status_Code.contentEquals("400")) {
                App_genericFunction.putcommentinStep("StatusCode : "+response.getStatusCode());
                Assert.assertEquals("Test Failed",Integer.parseInt(Status_Code),response.getStatusCode());
                App_genericFunction.putcommentinStep("ErrorCode : "+JsonPathEvaluator.get("Errors[0].ErrorCode"));
                App_genericFunction.putcommentinStep("Message : "+JsonPathEvaluator.get("Errors[0].Message"));

            }
            else if(Status_Code.contentEquals("401")) {
                Assert.assertEquals("Test Failed",Integer.parseInt(Status_Code),response.getStatusCode());
                App_genericFunction.putcommentinStep("StatusCode : "+response.getStatusCode());
                Assert.assertTrue("Error Message is not valid",response.asString().contains("Invalid Credentials. Make sure you have given the correct access token"));
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
            Assert.assertEquals("Test Failed",Integer.parseInt(Status_Code),response.getStatusCode());
            response.prettyPrint();
        }

    }
}
