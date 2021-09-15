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

public class ProductsService {

    String ResourceURI = GlobalHooks.ResourceURI;
    LinkedHashMap<String, String> headervalues = new LinkedHashMap<String, String>();
    ArrayList<Map<String, String>> API_values = GlobalHooks.values;
    String jsonString ;
    String YML_Path= CommonVariables.YML_Path;
    String valuepath="$.Resources."+GlobalHooks.resource_API+".headers";


    public void accountsServiceSetHeaders_get() throws FileNotFoundException, JSONException, JsonProcessingException {

        headervalues = JsonUtilities.getYamlData(YML_Path, valuepath);
        headervalues.put("Authorization",headervalues.get("Authorization").replace("token", API_values.get(0).get("application_access_token")));
    }
    public Response hitService_get()
    {
        Response response = APIMethods.method_get_withoutbody(GlobalHooks.BaseURI,headervalues,ResourceURI);
        App_genericFunction.putcommentinStep("GET : " + ResourceURI);
        return response;
    }

    public Response hitAccountService_get()
    {
        ResourceURI=ResourceURI.replace("{AccountId}",GlobalHooks.values.get(0).get("accountid").trim());
        Response response = APIMethods.method_get_withoutbody(GlobalHooks.BaseURI,headervalues,ResourceURI);
        App_genericFunction.putcommentinStep("GET : " + ResourceURI);
        return response;
    }


    public JsonPath getResponse(Response response)
    {
        return JsonUtilities.verifyResponse(response);
    }

    public void validateProductType(String type) {
        List<String> producttypes = new ArrayList<String>();
        producttypes.add("BusinessCurrentAccount");
        producttypes.add("CommercialCreditCard");
        producttypes.add("Other");
        producttypes.add("PersonalCurrentAccount");
        producttypes.add("SMELoan");
        if(producttypes.contains(type))
        {
            App_genericFunction.putcommentinStep("Validation for ProductType completed");
        }else
        {
            App_genericFunction.putcommentinStep("Validation for ProductType failed : ");
            Assert.fail("Assertion failed for ProductType");
        }
    }
}