package com.test.APIFunctions;

import Utilities.APIMethods;
import Utilities.JsonUtilities;
import api.Pojos.AccountAccessConsents.AccountAccessContent;
import api.Pojos.AccountAccessConsents.Data;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.test.CustomHooks.GlobalHooks;
import com.test.Utils.App_genericFunction;
import com.test.Utils.CommonVariables;
import com.test.Utils.jwt;
import com.test.exceptions.StopTestException;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import kafka.utils.Json;
import org.codehaus.jettison.json.JSONException;
import org.json.JSONObject;
import scala.App;
import scala.xml.PrettyPrinter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AccountAccessConsentService {

    String ResourceURI = GlobalHooks.ResourceURI;
    LinkedHashMap<String, String> headervalues = new LinkedHashMap<>();
    ArrayList<Map<String, String>> API_values = GlobalHooks.values;
    String jsonString ;
    String YML_Path= CommonVariables.YML_Path;
    String valuepath="$.Resources."+GlobalHooks.resource_API+".headers";


    public void setHeaders_post() throws FileNotFoundException, JSONException, JsonProcessingException {

        headervalues = JsonUtilities.getYamlData(YML_Path, valuepath);
        if(GlobalHooks.scenario_type.contains("UJ"))
        {
            headervalues.put("Authorization",headervalues.get("Authorization").replace("token", App_genericFunction.ReadPropertiesFile_feature("UJ_account_access_token")));
            App_genericFunction.putcommentinStep(headervalues);
        }else if(GlobalHooks.scenario_screenshot.getName().contains("Invalid token"))
        {
            headervalues.put("Authorization",headervalues.get("Authorization").replace("token", API_values.get(0).get("application_access_token")));
            App_genericFunction.putcommentinStep(headervalues);
        }else
        {
            headervalues.put("Authorization",headervalues.get("Authorization").replace("token", App_genericFunction.ReadPropertiesFile_common("account_access_token")));
            App_genericFunction.putcommentinStep(headervalues);
        }
        /*headervalues.put("x-fapi-financial-id",API_values.get(0).get("x-fapi-financial-id"));
        headervalues.put("accept",API_values.get(0).get("accept"));
        headervalues.put("Authorization",API_values.get(0).get("Authorization"));
        headervalues.put("Content-Type",API_values.get(0).get("Content-Type"));*/
    }
    public void setHeaders_delete() throws FileNotFoundException, JSONException, JsonProcessingException {

        headervalues = JsonUtilities.getYamlData(YML_Path, valuepath);
       // headervalues.put("Authorization",headervalues.get("Authorization").replace("token", API_values.get(0).get("application_access_token")));
        if(GlobalHooks.scenario_screenshot.getName().contains("Invalid token"))
        {
            headervalues.put("Authorization",headervalues.get("Authorization").replace("token", API_values.get(0).get("application_access_token")));
            App_genericFunction.putcommentinStep(headervalues);
        }else
        {
            headervalues.put("Authorization",headervalues.get("Authorization").replace("token", App_genericFunction.ReadPropertiesFile_common("account_access_token")));
            App_genericFunction.putcommentinStep(headervalues);
        }
    }
    public void setHeaders_get() throws FileNotFoundException, JSONException, JsonProcessingException {

        headervalues = JsonUtilities.getYamlData(YML_Path, valuepath);
       // headervalues.put("Authorization",headervalues.get("Authorization").replace("token", API_values.get(0).get("application_access_token")));
        if(GlobalHooks.scenario_screenshot.getName().contains("Invalid token"))
        {
            headervalues.put("Authorization",headervalues.get("Authorization").replace("token", API_values.get(0).get("application_access_token")));
            App_genericFunction.putcommentinStep(headervalues);
        }else
        {
            headervalues.put("Authorization",headervalues.get("Authorization").replace("token", App_genericFunction.ReadPropertiesFile_common("account_access_token")));
            App_genericFunction.putcommentinStep(headervalues);
        }
    }

    public void setRequestbody_post() throws JsonProcessingException {
        Data d = new Data();
        List<String> Parameters =  JsonUtilities.ListofValues(API_values.get(0).get("permissions"));
        d.setPermissions(Parameters);
        d.setExpirationDateTime(API_values.get(0).get("expirationdatetime"));
        d.setTransactionFromDateTime(API_values.get(0).get("transactionfromdatetime"));
        d.setTransactionToDateTime(API_values.get(0).get("transactiontodatetime"));
        AccountAccessContent a = new AccountAccessContent();
        a.setData(d);
        a.setRisk(new JSONObject());
        jsonString = JsonUtilities.getJsonStringfromPojo(a);
        App_genericFunction.putcommentinStep("Mapper Request : " + jsonString);

    }

    public Response hitService_post() throws UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, StopTestException {
        Response response = APIMethods.method_post(GlobalHooks.BaseURI,headervalues,jsonString,ResourceURI);
        App_genericFunction.putcommentinStep("POST : " + ResourceURI);
        return response;
    }

    public Response hitService_delete(String ConsentID)
    {

        System.out.println("ConsentID Received :" + ConsentID);
        System.out.println("ResourceURI : " + ResourceURI);
       // ResourceURI=ResourceURI.replace("{ConsentId}",ConsentID);
        Response response = APIMethods.method_delete(GlobalHooks.BaseURI,headervalues,ResourceURI,ConsentID);
        App_genericFunction.putcommentinStep("POST : " + ResourceURI);
        return response;
    }

    public Response hitService_get()
    {

        ResourceURI=ResourceURI.replace("{ConsentId}",API_values.get(0).get("ConsentID"));
        Response response = APIMethods.method_get_withoutbody(GlobalHooks.BaseURI,headervalues,ResourceURI);
        App_genericFunction.putcommentinStep("GET : " + ResourceURI);
        return response;
    }

    public JsonPath getResponse(Response response)
    {
        return JsonUtilities.verifyResponse(response);
    }

}
