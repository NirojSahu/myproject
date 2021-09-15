package com.test.APIFunctions;

import Utilities.APIMethods;
import Utilities.JsonUtilities;
import api.Pojos.AccountAccessConsents.AccountAccessContent;
import api.Pojos.AccountAccessConsents.Data;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.test.CustomHooks.GlobalHooks;
import com.test.Utils.App_genericFunction;
import com.test.Utils.CommonVariables;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.avro.generic.GenericData;
import org.codehaus.jettison.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BalancesService {

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
        ResourceURI=ResourceURI.replace("{AccountId}",API_values.get(0).get("account_id"));
        Response response = APIMethods.method_get_withoutbody(GlobalHooks.BaseURI,headervalues,ResourceURI);
        App_genericFunction.putcommentinStep("GET : " + ResourceURI);
        return response;
    }

    public JsonPath getResponse(Response response)
    {
        return JsonUtilities.verifyResponse(response);
    }

    public void validateCreditlinetype(String type) {
        List<String> creditlinetypes = new ArrayList<String>();
        creditlinetypes.add("Available");
        creditlinetypes.add("Credit");
        creditlinetypes.add("Pre-Areed");
        creditlinetypes.add("Emergency");
        creditlinetypes.add("Temporary");
        if(creditlinetypes.contains(type))
        {
            App_genericFunction.putcommentinStep("Validation for creditlinetype completed");
        }else
        {
            App_genericFunction.putcommentinStep("Validation for creditlinetype failed : ");
            Assert.fail("Assertion failed for creditlinetype");
        }
    }
    public void validateBalancetype(String type) {
        List<String> balancetypes = new ArrayList<String>();
        balancetypes.add("ClosingAvailable");
        balancetypes.add("ClosingBooked");
        balancetypes.add("ClosingCleared");
        balancetypes.add("Expected");
        balancetypes.add("Information");
        balancetypes.add("InterimAvailable");
        balancetypes.add("ForwardAvailable");
        balancetypes.add("InterimBooked");
        balancetypes.add("InterimCleared");
        balancetypes.add("OpeningAvailable");
        balancetypes.add("OpeningBooked");
        balancetypes.add("OpeningCleared");
        balancetypes.add("PreviouslyClosedBooked");


        if(balancetypes.contains(type))
        {
            App_genericFunction.putcommentinStep("Validation for balancetypes completed");
        }else
        {
            App_genericFunction.putcommentinStep("Validation for balancetypes failed : ");
            Assert.fail("Assertion failed for balancetypes");
        }
    }
}
