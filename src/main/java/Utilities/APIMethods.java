package Utilities;

import Utilities.WebKit.configuration.Configuration;
import Utilities.WebKit.exceptions.StopTestException;
import io.restassured.RestAssured;
import io.restassured.authentication.CertificateAuthSettings;
import io.restassured.config.EncoderConfig;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.security.*;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.specification.ProxySpecification.host;


public class APIMethods {


    public static Response method_post(String baseURI,LinkedHashMap header, String jsonString,String ResourceURI) throws UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, StopTestException
    {
        System.out.println("ResourceURI : " +ResourceURI);
        RestAssured.baseURI = baseURI;
        Response response= null;
        if (Configuration.getConfiguration().getProperty("environment").startsWith("Prod")) {
            RequestSpecification res = CertJKSUtility.clientCertSpecification(System.getProperty("user.dir")+"/src/test/resources/Certificates/6A/CAOB.p12","foobar","PKCS12",System.getProperty("user.dir")+"/src/test/resources/Certificates/6A/truststore.jks","foobar","jks");
              response = RestAssured.given(res).urlEncodingEnabled(false).log().all()
                .headers(header)
                    .body(jsonString).when()
                    .post(ResourceURI)
                    .then().assertThat().log().all().extract().response();
        }/*else if (Configuration.getConfiguration().getProperty("environment").startsWith("UAT")) {
            RestAssured.proxy = host("dia2.santanderuk.gs.corp").withPort(80);
            //RequestSpecification res = CertJKSUtility.clientCertSpecification(System.getProperty("user.dir")+"/src/test/resources/Certificates/6A/CAOB.p12","foobar","PKCS12",System.getProperty("user.dir")+"/src/test/resources/Certificates/6A/truststore.jks","foobar","jks");
            response = RestAssured.given().urlEncodingEnabled(false).log().all()
                    .headers(header)
                    .body(jsonString).when()
                    .post(ResourceURI)
                    .then().assertThat().log().all().extract().response();
        }*/else
        {
             response = RestAssured.given().relaxedHTTPSValidation().urlEncodingEnabled(false).log().all()
                    .headers(header)
                    .body(jsonString).when()
                    .post(ResourceURI)
                    .then().assertThat().log().all().extract().response();
        }
        return response;
    }

    @Deprecated
    public static Response method_get_withoutbody(String baseURI,LinkedHashMap header,String ResourceURI)
    {
        System.out.println("ResourceURI : " +ResourceURI);
        RestAssured.baseURI = baseURI;
        Response response= null;
        try {
            if (Configuration.getConfiguration().getProperty("environment").startsWith("Prod"))
            {
                RequestSpecification res = CertJKSUtility.clientCertSpecification(System.getProperty("user.dir")+"/src/test/resources/Certificates/6A/CAOB.p12","foobar","PKCS12",System.getProperty("user.dir")+"/src/test/resources/Certificates/6A/truststore.jks","foobar","jks");
                response = RestAssured.given(res).log().all()
                        .trustStore(System.getProperty("user.dir")+"/src/test/resources/Certificates/6A/truststore.jks","foobar")
                       /* .config(RestAssured.config()
                        .encoderConfig(EncoderConfig.encoderConfig()
                                .appendDefaultContentCharsetToContentTypeIfUndefined(false)))*/
                        .headers(header)
                        .when()
                        .get(ResourceURI)
                        .then().assertThat().log().all().extract().response();
            }
            else if (Configuration.getConfiguration().getProperty("environment").startsWith("UAT")) {
                RestAssured.proxy = host("dia2.santanderuk.gs.corp").withPort(80).withAuth("c0266294","LondonParis123123");
                //RequestSpecification res = CertJKSUtility.clientCertSpecification(System.getProperty("user.dir")+"/src/test/resources/Certificates/6A/CAOB.p12","foobar");
                response = RestAssured.given().urlEncodingEnabled(false).relaxedHTTPSValidation().log().all()
                      //  .trustStore(System.getProperty("user.dir")+"/src/test/resources/Certificates/4A/truststore.jks","foobar")
                       // .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                        .headers(header)
                        .when()
                        .get(ResourceURI)
                        .then().assertThat().log().all().extract().response();
            }
            else
            {
                // Response response = RestAssured.given().relaxedHTTPSValidation().urlEncodingEnabled(false).log().all()
                response = RestAssured.given().log().all().config(RestAssured.config()
                        .encoderConfig(EncoderConfig.encoderConfig()
                                .appendDefaultContentCharsetToContentTypeIfUndefined(false))).relaxedHTTPSValidation() /*RestAssured.given().log().all()
                    .config(RestAssured.config().httpClient(httpClientConfig().httpClientFactory(
                            new HttpClientConfig.HttpClientFactory() {

                                @Override
                                public HttpClient createHttpClient() {
                                    return new SystemDefaultHttpClient();
                                }
                            }))).relaxedHTTPSValidation()*/
                        .headers(header)
                        .when()
                        .get(ResourceURI)
                        .then().assertThat().log().all().extract().response();
            }
        } catch (StopTestException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        return response;
    }

    public static Response method_get_withoutbody_withqueryparam(String baseURI, LinkedHashMap header, String ResourceURI, Map<String,String> queryparam)
    {
        System.out.println("ResourceURI : " +ResourceURI);
        RestAssured.baseURI = baseURI;
        // Response response = RestAssured.given().relaxedHTTPSValidation().urlEncodingEnabled(false).log().all()
        RestAssured.proxy = host("dia2.santanderuk.gs.corp").withPort(80).withAuth("c0261484","LondonOrange23");
        Response response = RestAssured.given().log().all().config(RestAssured.config()
                .encoderConfig(EncoderConfig.encoderConfig()
                        .appendDefaultContentCharsetToContentTypeIfUndefined(false))).relaxedHTTPSValidation() /*RestAssured.given().log().all()
                .config(RestAssured.config().httpClient(httpClientConfig().httpClientFactory(
                        new HttpClientConfig.HttpClientFactory() {

                            @Override
                            public HttpClient createHttpClient() {
                                return new SystemDefaultHttpClient();
                            }
                        }))).relaxedHTTPSValidation()*/
                .headers(header)
                .queryParams(queryparam)
                .when()
                .get(ResourceURI)
                .then().assertThat().log().all().extract().response();
        return response;
    }

    public static Response method_get_withoutheader(String baseURI,String ResourceURI)
    {
        System.out.println("ResourceURI : " +ResourceURI);
        RestAssured.baseURI = baseURI;
        // Response response = RestAssured.given().relaxedHTTPSValidation().urlEncodingEnabled(false).log().all()
        Response response = RestAssured.given().log().all().config(RestAssured.config()
                .encoderConfig(EncoderConfig.encoderConfig()
                        .appendDefaultContentCharsetToContentTypeIfUndefined(false))).relaxedHTTPSValidation() /*RestAssured.given().log().all()
                .config(RestAssured.config().httpClient(httpClientConfig().httpClientFactory(
                        new HttpClientConfig.HttpClientFactory() {

                            @Override
                            public HttpClient createHttpClient() {
                                return new SystemDefaultHttpClient();
                            }
                        }))).relaxedHTTPSValidation()*/
                .when()
                .get(ResourceURI)
                .then().assertThat().log().all().extract().response();
        return response;
    }

    /*public static Response method_delete(String baseURI,LinkedHashMap header,String ResourceURI,String ConsentID)
    {
        System.out.println("ResourceURI : " +ResourceURI);
        RestAssured.baseURI = baseURI;
        Response response = RestAssured.given().relaxedHTTPSValidation().urlEncodingEnabled(false).log().all()
                .headers(header)
                .pathParam("ConsentId",ConsentID)
                .when()
                .delete(ResourceURI)
                .then().assertThat().log().all().extract().response();
        return response;
    }*/

    @Deprecated
    public static Response method_delete(String baseURI,LinkedHashMap header,String ResourceURI,String ConsentID) {
        Response response = null;
        System.out.println("ResourceURI : " +ResourceURI);
        RestAssured.baseURI = baseURI;
        try {
            if (Configuration.getConfiguration().getProperty("environment").startsWith("Prod")) {
                RestAssured.proxy = host("dia2.santanderuk.gs.corp").withPort(80);
                RequestSpecification res = CertJKSUtility.clientCertSpecification(System.getProperty("user.dir")+"/src/test/resources/Certificates/6A/CAOB.p12","foobar","PKCS12",System.getProperty("user.dir")+"/src/test/resources/Certificates/6A/truststore.jks","foobar","jks");
                response = RestAssured.given(res).log().all()
                        .trustStore(System.getProperty("user.dir")+"/src/test/resources/Certificates/6A/truststore.jks","foobar")
                        .headers(header)
                        .pathParam("ConsentId",ConsentID)
                        .when()
                        .delete(ResourceURI)
                        .then().assertThat().log().all().extract().response();
            }
            else {
                response = RestAssured.given().relaxedHTTPSValidation().urlEncodingEnabled(false).log().all()
                        .headers(header)
                        .pathParam("ConsentId",ConsentID)
                        .when()
                        .delete(ResourceURI)
                        .then().assertThat().log().all().extract().response();
            }
        } catch (StopTestException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        return response;
    }

    @Deprecated
    public static Response method_post_raw(String baseURI, String jsonString,String ResourceURI) throws UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, StopTestException {
        System.out.println("ResourceURI : " +ResourceURI);
        RestAssured.baseURI = baseURI;
        Response response = null;
       if (Configuration.getConfiguration().getProperty("environment").startsWith("Prod")) {
             RestAssured.proxy = host("dia2.santanderuk.gs.corp").withPort(80);
             RequestSpecification res = CertJKSUtility.clientCertSpecification(System.getProperty("user.dir")+"/src/test/resources/Certificates/6A/CAOB.p12","foobar","PKCS12",System.getProperty("user.dir")+"/src/test/resources/Certificates/6A/truststore.jks","foobar","jks");
             //RequestSpecification res = CertJKSUtility.clientCertSpecification(System.getProperty("user.dir")+"/src/test/resources/Certificates/6A/CAOB.p12","foobar");
              response = RestAssured.given(res).log().all()
                      .trustStore(System.getProperty("user.dir")+"/srcw/test/resources/Certificates/6A/truststore.jks","foobar")
                    /* .config(RestAssured.config()
                             .encoderConfig(EncoderConfig.encoderConfig()
                                     .encodeContentTypeAs("x-www-form-urlencoded",
                                             // ContentType.URLENC))).relaxedHTTPSValidation()
                                             ContentType.URLENC)))*/
                     .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                     .body(jsonString).when()
                     .post(ResourceURI)
                     .then().assertThat().log().all().extract().response();
         } else if (Configuration.getConfiguration().getProperty("environment").startsWith("UAT")) {
        RestAssured.proxy = host("dia2.santanderuk.gs.corp").withPort(80).withAuth("c0266294","LondonParis123123");
           //RestAssured.proxy = host("dia2.santanderuk.gs.corp").withPort(80);
          // RequestSpecification res = CertJKSUtility.clientCertSpecification(System.getProperty("user.dir")+"/src/test/resources/Certificates/4A/CAOB4A.p12","foobar","PKCS12",System.getProperty("user.dir")+"/src/test/resources/Certificates/4A/truststore.jks","foobar","jks");
           response = RestAssured.given().relaxedHTTPSValidation().log().all()
                  .trustStore(System.getProperty("user.dir")+"/src/test/resources/Certificates/4A/truststore.jks","foobar")
                   .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                   .body(jsonString).when()
                   .post(baseURI+ResourceURI)
                   .then().assertThat().log().all().extract().response();
       }
        else
        {
            response = RestAssured.given().log().all()
                    .config(RestAssured.config()
                            .encoderConfig(EncoderConfig.encoderConfig()
                                    .encodeContentTypeAs("x-www-form-urlencoded",
                                            ContentType.URLENC))).relaxedHTTPSValidation()
                                            //ContentType.URLENC)))
                    .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                    .body(jsonString).when()
                    .post(ResourceURI)
                    .then().assertThat().log().all().extract().response();
        }
        return response;
    }
}
