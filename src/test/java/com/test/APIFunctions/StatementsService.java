package com.test.APIFunctions;

import Utilities.APIMethods;
import Utilities.JsonUtilities;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.test.CustomHooks.GlobalHooks;
import com.test.Utils.App_genericFunction;
import com.test.Utils.CommonVariables;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.codehaus.jettison.json.JSONException;
import org.junit.Assert;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class StatementsService {

    String ResourceURI = GlobalHooks.ResourceURI;
    LinkedHashMap<String, String> headervalues = new LinkedHashMap<String, String>();
    ArrayList<Map<String, String>> API_values = GlobalHooks.values;
    String jsonString ;
    String YML_Path= CommonVariables.YML_Path;
    String valuepath="$.Resources."+GlobalHooks.resource_API+".headers";

    public void setHeaders_get() throws FileNotFoundException, JSONException, JsonProcessingException {

        headervalues = JsonUtilities.getYamlData(YML_Path, valuepath);
        if(GlobalHooks.scenario_type.contains("UJ"))
        {
            headervalues.put("Authorization",headervalues.get("Authorization").replace("token", App_genericFunction.ReadPropertiesFile_feature("UJ_Client_access_token")));
            App_genericFunction.putcommentinStep(headervalues);
        }else
        {
            headervalues.put("Authorization",headervalues.get("Authorization").replace("token", API_values.get(0).get("client_access_token")));
            App_genericFunction.putcommentinStep(headervalues);
        }
    }


    public Response hitService_get()
    {
        Response response = null;
        if(GlobalHooks.resource_API.contentEquals("statements_1")) {
            ResourceURI = ResourceURI.replace("{AccountId}", API_values.get(0).get("account_id"));
             response = APIMethods.method_get_withoutbody(GlobalHooks.BaseURI, headervalues, ResourceURI);
            App_genericFunction.putcommentinStep("GET : " + ResourceURI);

        }else if(GlobalHooks.resource_API.contentEquals("statements_2")) {
            ResourceURI = ResourceURI.replace("{AccountId}", API_values.get(0).get("account_id")).replace("{StatementId}", API_values.get(0).get("statement_id"));
             response = APIMethods.method_get_withoutbody(GlobalHooks.BaseURI, headervalues, ResourceURI);
            App_genericFunction.putcommentinStep("GET : " + ResourceURI);

        }
        return response;
    }

    public JsonPath getResponse(Response response)
    {
        return JsonUtilities.verifyResponse(response);
    }

    public void validateStatementtype(String type) {
        List<String> Statementtype = new ArrayList<String>();
        Statementtype.add("AccountClosure");
        Statementtype.add("AccountOpening");
        Statementtype.add("Annual");
        Statementtype.add("Interim");
        Statementtype.add("RegularPeriodic");
        if(Statementtype.contains(type))
        {
            App_genericFunction.putcommentinStep("Validation for Statementtype completed");
        }else
        {
            App_genericFunction.putcommentinStep("Validation for Statementtype failed : ");
            Assert.fail("Assertion failed for Statementtype");
        }
    }
    public void validateCreditDebitIndicator(String type) {
        List<String> CreditDebitIndicator = new ArrayList<String>();
        CreditDebitIndicator.add("Credit");
        CreditDebitIndicator.add("Debit");

        if(CreditDebitIndicator.contains(type))
        {
            App_genericFunction.putcommentinStep("Validation for CreditDebitIndicator completed");
        }else
        {
            App_genericFunction.putcommentinStep("Validation for CreditDebitIndicator failed : ");
            Assert.fail("Assertion failed for CreditDebitIndicator");
        }
    }

}
