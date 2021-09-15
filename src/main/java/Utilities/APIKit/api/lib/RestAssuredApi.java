//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.santander.api.lib;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestAssuredApi {
    private static final Logger log = LoggerFactory.getLogger(RestAssuredApi.class);

    public RestAssuredApi() {
    }

    public RequestSpecification givenConfig(String resType, String baseURI) {
        RequestSpecification rs = null;
        RestAssured.useRelaxedHTTPSValidation();
        if ("json".equalsIgnoreCase(resType)) {
            rs = (RequestSpecification)RestAssured.given().baseUri(baseURI).urlEncodingEnabled(false).header("Content-Type", "application/json; charset=UTF-8", new Object[0]).accept(ContentType.JSON).log().all();
        }

        if ("urlencoded".equalsIgnoreCase(resType)) {
            rs = (RequestSpecification)RestAssured.given().baseUri(baseURI).urlEncodingEnabled(false).header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8", new Object[0]).accept(ContentType.URLENC).log().all();
        }

        if ("html".equalsIgnoreCase(resType)) {
            rs = (RequestSpecification)RestAssured.given().baseUri(baseURI).urlEncodingEnabled(false).header("Content-Type", "text/html", new Object[0]).accept(ContentType.HTML).log().all();
        }

        if ("soap".equalsIgnoreCase(resType)) {
            rs = RestAssured.given().baseUri(baseURI).urlEncodingEnabled(false).header("Content-Type", "application/soap+xml; charset=UTF-8", new Object[0]);
        }

        if (baseURI.equals((Object)null)) {
            if ("json".equalsIgnoreCase(resType)) {
                rs = (RequestSpecification)RestAssured.given().urlEncodingEnabled(false).header("Content-Type", "application/json; charset=UTF-8", new Object[0]).accept(ContentType.JSON).log().all();
            }

            if ("urlencoded".equalsIgnoreCase(resType)) {
                rs = (RequestSpecification)RestAssured.given().urlEncodingEnabled(false).header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8", new Object[0]).accept(ContentType.URLENC).log().all();
            }

            if ("html".equalsIgnoreCase(resType)) {
                rs = (RequestSpecification)RestAssured.given().urlEncodingEnabled(false).header("Content-Type", "text/html", new Object[0]).accept(ContentType.HTML).log().all();
            }

            if ("soap".equalsIgnoreCase(resType)) {
                rs = RestAssured.given().urlEncodingEnabled(false).header("Content-Type", "application/soap+xml; charset=UTF-8", new Object[0]);
            }
        }

        return rs;
    }

    public String extractPlainResponse(Response response, String jsonKey) {
        return response.body().jsonPath().get(jsonKey).toString();
    }

    public double roundDecimal(String i) {
        return (double)Math.round(Double.valueOf(i));
    }

    private RequestSpecification givenConfig(String baseURI) {
        RestAssured.useRelaxedHTTPSValidation();
        return (RequestSpecification)RestAssured.given().baseUri(baseURI).urlEncodingEnabled(false).log().all();
    }

    public Response Get(Headers headers, String url) {
        return (Response)((ValidatableResponse)((ValidatableResponse)((Response)((RequestSpecification)RestAssured.given().log().all()).relaxedHTTPSValidation().headers(headers).when().get(url, new Object[0])).then()).log().all()).extract().response();
    }

    public Response Get(String url) {
        return (Response)((ValidatableResponse)((ValidatableResponse)((Response)((RequestSpecification)RestAssured.given().log().all()).relaxedHTTPSValidation().when().get(url, new Object[0])).then()).log().all()).extract().response();
    }

    public Response PostWithHashBody(Headers headers, String url, Map<Object, Object> requestBodyHash) {
        return (Response)((ValidatableResponse)((ValidatableResponse)((Response)((RequestSpecification)RestAssured.given().log().all()).relaxedHTTPSValidation().headers(headers).body(requestBodyHash).when().post(url, new Object[0])).then()).log().all()).extract().response();
    }

    public Response PostWithStringBody(Headers headers, String url, String requestBodyString) {
        return (Response)((ValidatableResponse)((ValidatableResponse)((Response)((RequestSpecification)RestAssured.given().log().all()).relaxedHTTPSValidation().headers(headers).body(requestBodyString).when().post(url, new Object[0])).then()).log().all()).extract().response();
    }

    public Response PostWithJSON(Headers headers, String url, JSONObject jsonBody) {
        return (Response)((ValidatableResponse)((ValidatableResponse)((Response)((RequestSpecification)RestAssured.given().log().all()).relaxedHTTPSValidation().headers(headers).body(jsonBody).when().post(url, new Object[0])).then()).log().all()).extract().response();
    }

    public Response PutWithStringBody(Headers headers, String url, String requestBodyString) {
        return (Response)((ValidatableResponse)((ValidatableResponse)((Response)((RequestSpecification)RestAssured.given().log().all()).relaxedHTTPSValidation().headers(headers).body(requestBodyString).when().put(url, new Object[0])).then()).log().all()).extract().response();
    }

    public Response PutWithHashBody(Headers headers, String url, Map<Object, Object> requestBodyHash) {
        return (Response)((ValidatableResponse)((ValidatableResponse)((Response)((RequestSpecification)RestAssured.given().log().all()).relaxedHTTPSValidation().headers(headers).body(requestBodyHash).when().put(url, new Object[0])).then()).log().all()).extract().response();
    }

    public Response PutWithJsonBody(Headers headers, String url, JSONObject jsonBody) {
        return (Response)((ValidatableResponse)((ValidatableResponse)((Response)((RequestSpecification)RestAssured.given().log().all()).relaxedHTTPSValidation().headers(headers).body(jsonBody).when().put(url, new Object[0])).then()).log().all()).extract().response();
    }

    public Response Delete(Headers headers, String url) {
        return (Response)((ValidatableResponse)((ValidatableResponse)((Response)((RequestSpecification)RestAssured.given().log().all()).relaxedHTTPSValidation().headers(headers).when().delete(url, new Object[0])).then()).log().all()).extract().response();
    }

    public Headers setHTTPHeaders(HashMap<String, String> headerValueMap) {
        List<Header> headerList = new ArrayList();
        headerValueMap.forEach((param, value) -> {
            headerList.add(new Header(param, value));
        });
        return new Headers(headerList);
    }
}
