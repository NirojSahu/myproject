package com.test.APIFunctions;

import Utilities.APIMethods;
import Utilities.JsonUtilities;
import api.Pojos.AccountAccessConsents.AccountAccessContent;
import api.Pojos.AccountAccessConsents.Data;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.test.CustomHooks.GlobalHooks;
import com.test.Utils.App_genericFunction;
import com.test.Utils.jwt;
import com.test.exceptions.StopTestException;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONException;
import org.json.JSONObject;
import scala.App;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TokenService {
    String ResourceURI = GlobalHooks.ResourceURI;
    LinkedHashMap<String, String> headervalues = new LinkedHashMap<String, String>();
    ArrayList<Map<String, String>> API_values = GlobalHooks.values;
    String jsonString ;

    public void setHeaders_post(){

        /*headervalues.put("x-fapi-financial-id",API_values.get(0).get("x-fapi-financial-id"));
        headervalues.put("accept",API_values.get(0).get("accept"));
        headervalues.put("Authorization",API_values.get(0).get("Authorization"));
        headervalues.put("Content-Type",API_values.get(0).get("Content-Type"));*/

    }

    public void setRequestbody_post() throws JsonProcessingException {

        jsonString = "client_id="+API_values.get(0).get("client_id") +
                "&grant_type="+API_values.get(0).get("grant_type") +
                "&scope="+API_values.get(0).get("scope") +
                "&client_assertion_type="+API_values.get(0).get("client_assertion_type") +
                "&client_assertion=" +API_values.get(0).get("client_assertion")+
                "&redirect_uri="+API_values.get(0).get("redirect_uri");
        App_genericFunction.putcommentinStep(" Request as raw-text : " + jsonString);

    }
    public void setRequestbody_post_cct() throws JsonProcessingException {

        jsonString = "client_id="+API_values.get(0).get("client_id") +
                "&grant_type="+API_values.get(0).get("grant_type") +
                "&code="+API_values.get(0).get("code") +
                "&client_assertion_type="+API_values.get(0).get("client_assertion_type") +
                "&client_assertion=" +API_values.get(0).get("client_assertion")+
                "&redirect_uri="+API_values.get(0).get("redirect_uri");
        App_genericFunction.putcommentinStep(" Request as raw-text : " + jsonString);

    }
    public String setAuthURL() throws JsonProcessingException, JSONException, StopTestException {

        String ConsentID ;
        if(GlobalHooks.scenario_type.contains("UJ"))
        {
            ConsentID = App_genericFunction.ReadPropertiesFile_feature("UJ_Consent_ID");
        }else
        {
            ConsentID = App_genericFunction.ReadPropertiesFile_common("Consent_ID" +"_"+ App_genericFunction.ReadPropertiesFile_common("Scenario_type_accountconsent"));
        }
        jsonString = GlobalHooks.BaseURI +
                "authorize/?response_type=code id_token" +
                "&scope="+ API_values.get(0).get("scope") + " openid" +
                "&client_id="+API_values.get(0).get("client_id") +
                "&request="+jwt.generateJwtToken_forauthURL(GlobalHooks.privatekey,GlobalHooks.publickey,ConsentID,API_values.get(0).get("audience"),API_values.get(0).get("client_id"),API_values.get(0).get("redirect_uri"),API_values.get(0).get("scope").split(" ")[1]) +
                "&prompt=login" +
                "&redirect_uri="+API_values.get(0).get("redirect_uri");
        App_genericFunction.putcommentinStep(" AuthURL : " + jsonString);
         return jsonString;
    }
    public void setRequestbody_post_withdynamic_JWT() throws JsonProcessingException, JSONException, StopTestException {

        String jwt_token =jwt.generateJwtToken_AAT(GlobalHooks.privatekey,GlobalHooks.publickey,API_values.get(0).get("audience"),API_values.get(0).get("client_id"));
        App_genericFunction.putcommentinStep("JWT TOken : "+jwt_token);
        jsonString = "client_id="+API_values.get(0).get("client_id") +
                "&grant_type="+API_values.get(0).get("grant_type") +
                "&scope="+API_values.get(0).get("scope") +
                "&client_assertion_type="+API_values.get(0).get("client_assertion_type") +
                "&client_assertion=" + jwt_token +
                "&redirect_uri="+API_values.get(0).get("redirect_uri");
        App_genericFunction.putcommentinStep(" Request as raw-text : " + jsonString);
        jwt.printStructure_cucumber_report(jwt_token,GlobalHooks.publickey);
    }
    public void setRequestbody_post_withdynamic_cat_JWT() throws JsonProcessingException, JSONException, StopTestException {
        String auth_code ;
        if(GlobalHooks.scenario_type.contains("UJ"))
        {
         auth_code   = App_genericFunction.ReadPropertiesFile_feature("UJ_Auth_code");
        }else
        {
            auth_code   = App_genericFunction.ReadPropertiesFile_common("Auth_code");
        }
        String jwt_token =jwt.generateJwtToken_CAT(GlobalHooks.privatekey,GlobalHooks.publickey,API_values.get(0).get("audience"),API_values.get(0).get("client_id"));
        App_genericFunction.putcommentinStep("JWT TOken CAT : "+jwt_token);
        jsonString = "client_id="+API_values.get(0).get("client_id") +
                "&grant_type="+API_values.get(0).get("grant_type") +
                "&code="+auth_code +
                "&client_assertion_type="+API_values.get(0).get("client_assertion_type") +
                "&client_assertion=" +jwt_token+
                "&redirect_uri="+API_values.get(0).get("redirect_uri");
        App_genericFunction.putcommentinStep(" Request as raw-text for CAT : " + jsonString);
        jwt.printStructure_cucumber_report(jwt_token,GlobalHooks.publickey);
    }
    public void setRequestbody_post_withrefreshtoken() throws JsonProcessingException, JSONException, StopTestException {
        String refresh_token ;
        if(GlobalHooks.scenario_type.contains("UJ"))
        {
            refresh_token   = App_genericFunction.ReadPropertiesFile_feature("UJ_refresh_token");
        }else
        {
            refresh_token   = App_genericFunction.ReadPropertiesFile_common("refresh_token");
        }
        String jwt_token =jwt.generateJwtToken_CAT(GlobalHooks.privatekey,GlobalHooks.publickey,API_values.get(0).get("audience"),API_values.get(0).get("client_id"));
        App_genericFunction.putcommentinStep("JWT TOken CAT : "+jwt_token);
        jsonString = "client_id="+API_values.get(0).get("client_id") +
                "&grant_type="+API_values.get(0).get("grant_type") +
                "&refresh_token="+refresh_token +
                "&client_assertion_type="+API_values.get(0).get("client_assertion_type") +
                "&client_assertion=" +jwt_token+
                "&redirect_uri="+API_values.get(0).get("redirect_uri");
        App_genericFunction.putcommentinStep(" Request as raw-text for CAT : " + jsonString);
        jwt.printStructure_cucumber_report(jwt_token,GlobalHooks.publickey);
    }
    public Response hitService_post() throws UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, StopTestException {
        Response response = APIMethods.method_post_raw(GlobalHooks.BaseURI,jsonString,ResourceURI);
        App_genericFunction.putcommentinStep("POST AS RAW TEXT : " + ResourceURI);
        return response;
    }

    public JsonPath getResponse(Response response)
    {
        return JsonUtilities.verifyResponse(response);
    }
}
