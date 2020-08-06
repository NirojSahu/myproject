package Utilities;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.LinkedHashMap;
import java.util.Map;

public class APIMethods {

    //Method for POST
    public static Response method_post(String baseURI, LinkedHashMap header, String jsonString,String resourceURI){
        System.out.println("ResourceUri :"+resourceURI);
        RestAssured.baseURI=baseURI;
        Response response= (Response) RestAssured.given().relaxedHTTPSValidation().urlEncodingEnabled(false).log().all()
                .headers(header)
                .body(jsonString).when()
                .post(resourceURI)
                .then().assertThat().log().all().extract().response();
        return response;
    }

    public static Response method_get_withoutbody(String baseURI, LinkedHashMap header, String resourceURI){
        System.out.println("ResourceUri :"+resourceURI);
        RestAssured.baseURI=baseURI;
        Response response= RestAssured.given().log().all().config(RestAssured.config()
        .encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)))
                .relaxedHTTPSValidation()
                .headers(header)
                .when()
                .get(resourceURI)
                .then().assertThat().log().all().extract().response();
        return response;
    }

    public static Response method_get_withoutbody_withqueryparam(String baseURI, LinkedHashMap header, String resourceURI, Map<String,String> queryparam){
        System.out.println("ResourceUri :"+resourceURI);
        RestAssured.baseURI=baseURI;
        Response response= RestAssured.given().log().all().config(RestAssured.config()
                .encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)))
                .relaxedHTTPSValidation()
                .headers(header)
                .queryParams(queryparam)
                .when()
                .get(resourceURI)
                .then().assertThat().log().all().extract().response();
        return response;
    }
    public static Response method_get_withoutheader(String baseURI,  String resourceURI){
        System.out.println("ResourceUri :"+resourceURI);
        RestAssured.baseURI=baseURI;
        Response response= RestAssured.given().log().all().config(RestAssured.config()
                .encoderConfig(EncoderConfig.encoderConfig()
                .appendDefaultContentCharsetToContentTypeIfUndefined(false))).relaxedHTTPSValidation()
                .when()
                .get(resourceURI)
                .then().assertThat().log().all().extract().response();
        return response;
    }
    public static Response method_delete(String baseURI, LinkedHashMap header, String resourceURI, String ConsentID){
        System.out.println("ResourceUri :"+resourceURI);
        RestAssured.baseURI=baseURI;
        Response response= RestAssured.given().relaxedHTTPSValidation().urlEncodingEnabled(false).log().all()
                .headers(header)
                .pathParam("ConsentID", ConsentID)
                .when()
                .delete(resourceURI)
                .then().assertThat().log().all().extract().response();
        return response;
    }

    public static Response method_post_raw(String baseURI, String jsonString,String resourceURI){
        System.out.println("ResourceUri :"+resourceURI);
        RestAssured.baseURI=baseURI;
        Response response= (Response) RestAssured.given().relaxedHTTPSValidation().log().all()
                .config(RestAssured.config()
                .encoderConfig(EncoderConfig.encoderConfig().encodeContentTypeAs("x-www-form-urlencoded", ContentType.URLENC))).relaxedHTTPSValidation()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .body(jsonString).when()
                .post(resourceURI)
                .then().assertThat().log().all().extract().response();
        return response;
    }
}
