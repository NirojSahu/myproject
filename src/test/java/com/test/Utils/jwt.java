package com.test.Utils;

import Utilities.WebKit.configuration.Configuration;
import Utilities.WebKit.exceptions.StopTestException;
import com.google.gson.JsonObject;
import com.google.inject.Inject;
import com.google.inject.internal.asm.$ByteVector;
//import com.test.configuration.Configuration;
//import com.test.exceptions.StopTestException;
import cucumber.api.java.eo.Do;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;

import io.restassured.path.json.JsonPath;
import org.json.JSONException;
import org.json.JSONObject;

import javax.sound.sampled.Line;


public class jwt {

    public static void main(String[] args) throws Exception {
        PublicKey publicKey = get_publicKey(  System.getProperty("user.dir")+"/src/test/resources/Certificates/6A/Public.der");
        PrivateKey privateKey = get_privateKey(System.getProperty("user.dir")+"/src/test/resources/Certificates/6A/Private.der");
         /*PublicKey publicKey = get_publicKey("C:\\Users\\c0261484\\Desktop\\Key_5A\\Public.der");
         PrivateKey privateKey = get_privateKey("C:\\Users\\c0261484\\Desktop\\Key_5A\\Private.der");*/

        //5A
        //generateJwtToken_AAT(privateKey,publicKey,"https://10.6.184.26:8243/token","XjqOowR0kXZe_iYqGWVfVlQC9esa");
        //generateJwtToken_forauthURL(privateKey,publicKey,"6af5ad08-5d80-42d9-beb2-058d4e2d4684","https://10.6.184.26:8243/token","XjqOowR0kXZe_iYqGWVfVlQC9esa","https://www.google.com/","account");
        //generateJwtToken_CAT(privateKey,publicKey,"https://10.6.184.26:8243/token","XjqOowR0kXZe_iYqGWVfVlQC9esa");

        //3A
        //generateJwtToken_AAT(privateKey,publicKey,"https://10.6.184.149:8243/token/","fY64Xz7bYBDbMtQRpjCXEDEXDusa");
        //generateJwtToken_forauthURL(privateKey,publicKey,"9cd4b78b-7d65-4efc-a565-f3205d04b6b1","https://10.6.184.149:8243/token/","fY64Xz7bYBDbMtQRpjCXEDEXDusa","https://www.google.com/","account");
        //generateJwtToken_CAT(privateKey,publicKey,"https://10.6.184.149:8243/token/","fY64Xz7bYBDbMtQRpjCXEDEXDusa");

        //6A
      //  generateJwtToken_AAT(privateKey,publicKey,"https://developer.caterallen.co.uk/token","eubXX5Wm9TGJNMHf48fS_vQ88foa");
        //generateJwtToken_forauthURL(privateKey,publicKey,"2108c3c4-102f-4924-b820-df700333242b","https://developer.caterallen.co.uk/","eubXX5Wm9TGJNMHf48fS_vQ88foa","https://caterallen.co.uk","account");
        //generateJwtToken_CAT(privateKey,publicKey,"https://developer.caterallen.co.uk/token","eubXX5Wm9TGJNMHf48fS_vQ88foa");
    }

    public static void display(PublicKey public_key,String token,String token_type) throws JSONException {
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------");
        if(token_type.contentEquals("aat"))
        {
            System.out.println("Application Access TOKEN: - JWT:");
            System.out.println(token);
            printStructure(token, public_key);
        }else if(token_type.contentEquals("cat"))
        {
            System.out.println("Customer Access TOKEN - JWT:");
            System.out.println(token);
            printStructure(token, public_key);
        }else if(token_type.contentEquals("authurl"))
        {
            System.out.println("AuthURL TOKEN - JWT:");
            System.out.println(token);
            printStructure(token, public_key);
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------");
    }
    //@test
    public void JWTWithRsa() throws Exception {

      /*  KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA");
        keyGenerator.initialize(2048);

       KeyPair kp = keyGenerator.genKeyPair();
       PublicKey publicKey = (PublicKey) kp.getPublic();
       PrivateKey privateKey = (PrivateKey) kp.getPrivate();*/
        PublicKey publicKey = get_publicKey("C:\\Users\\c0261484\\Desktop\\Key_5A\\Public.der");
        PrivateKey privateKey = get_privateKey("C:\\Users\\c0261484\\Desktop\\Key_5A\\Private.der");

        String encodedPublicKey = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        System.out.println("Public Key:");
        System.out.println(convertToPublicKey(encodedPublicKey));
        System.out.println("--------------------------------------------------------------------------------------");
        //String token_aat = generateJwtToken_AAT(privateKey,"https://10.6.184.26:8243/token","XjqOowR0kXZe_iYqGWVfVlQC9esa",new Date(120, 06, 10),new Date(120, 10, 03));
        //String token_aat = generateJwtToken_AAT(privateKey,"https://10.6.184.26:8243/token","XjqOowR0kXZe_iYqGWVfVlQC9esa");
        String token_aat = generateJwtToken_AAT(privateKey,publicKey,"https://10.6.184.149:8243/token/","fY64Xz7bYBDbMtQRpjCXEDEXDusa");
        System.out.println("Application Access TOKEN - JWT:");
        System.err.println(token_aat);
        printStructure(token_aat, publicKey);
        System.out.println("--------------------------------------------------------------------------------------");
        //String token_authURL = generateJwtToken_forauthURL(privateKey,"3fa20a3c-7eb7-44ae-a3f1-43723f3d7f56","https://10.6.184.26:8243/token","XjqOowR0kXZe_iYqGWVfVlQC9esa","https://www.google.com/","account");
        String token_authURL = generateJwtToken_forauthURL(privateKey,publicKey,"9cd4b78b-7d65-4efc-a565-f3205d04b6b1","https://10.6.184.149:8243/token/","fY64Xz7bYBDbMtQRpjCXEDEXDusa","https://www.google.com/","account");
        System.out.println("AuthURL TOKEN - JWT:");
        System.err.println(token_authURL);
        printStructure(token_authURL, publicKey);
        System.out.println("--------------------------------------------------------------------------------------");
        //String token_cat = generateJwtToken_CAT(privateKey,"https://10.6.184.26:8243/token","XjqOowR0kXZe_iYqGWVfVlQC9esa",new Date(120, 06, 10),new Date(120, 10, 03));
        //String token_cat = generateJwtToken_CAT(privateKey,"https://10.6.184.26:8243/token","XjqOowR0kXZe_iYqGWVfVlQC9esa");
        String token_cat = generateJwtToken_CAT(privateKey,publicKey,"https://10.6.184.149:8243/token/","fY64Xz7bYBDbMtQRpjCXEDEXDusa");
        System.out.println("Customer Access TOKEN - JWT:");
        System.err.println(token_cat);
        printStructure(token_cat, publicKey);
        System.out.println("--------------------------------------------------------------------------------------");

       // gettimeperiod();
    }


    @SuppressWarnings("deprecation")
    public static String generateJwtToken_AAT(PrivateKey privateKey,PublicKey publicKey,String audience,String customer_key) throws JSONException, StopTestException {
        System.out.println("=====>> Application Access token ======>>");
        //String oldvalue = App_genericFunction.ReadPropertiesFile_common("id_aat");
        //System.out.println("JWTID_aat : pen" + oldvalue);
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        String Kid ="83:AB:74:0E:2D:B2:BF:83:09:F1:78:4B:07:90:AF:06:7C:5B:40:37:D9:B5:70:6E:E2:51:0C:1E:39:EA:28:50";
        String alg ="RS256";
        String token;
        if(Configuration.getConfiguration().getProperty("environment").startsWith("Prod"))
        {
            Kid = "p3VWA5Yis1h656IIyKthkcT5pfE";
            alg = "PS256";
             token = Jwts.builder().setHeaderParam("alg",alg)
                    .setHeaderParam("kid",Kid)
                    .setHeaderParam("typ","jwt")
                    .setIssuer(customer_key)
                    .setSubject(customer_key)
                    // .setExpiration(expiration)
                    .setExpiration(gettimeperiod())
                    // .setIssuedAt(Issuer)
                    .setIssuedAt(getcurrenttime())
                    .setAudience(audience)
                    //.setId("pen"+oldvalue)
                    .setId(CommonFunctions.getRandomString(5) +CommonFunctions.getRandomNumber(5))
                    //.claim("groups", new String[] { "user", "admin" })
                    // RS256 with privateKey
                    .signWith(SignatureAlgorithm.PS256, privateKey).compact();
            System.out.println("Algorithm_Private: " + privateKey.getAlgorithm());
            //int newvalue = Integer.parseInt(oldvalue) +1;
            //App_genericFunction.WritePropertiesFile_common("id_aat",Integer.toString(newvalue));
            display(publicKey,token,"aat");
        }else if(Configuration.getConfiguration().getProperty("environment").startsWith("UAT"))
        {
            alg ="PS256";
            token = Jwts.builder().setHeaderParam("alg",alg)
                    .setHeaderParam("kid",Kid)
                    .setHeaderParam("typ","jwt")
                    .setIssuer(customer_key)
                    .setSubject(customer_key)
                    // .setExpiration(expiration)
                    .setExpiration(gettimeperiod())
                    // .setIssuedAt(Issuer)
                    .setIssuedAt(getcurrenttime())
                    .setAudience(audience)
                    //.setId("pen"+oldvalue)
                    .setId(CommonFunctions.getRandomString(5) +CommonFunctions.getRandomNumber(5))
                    //.claim("groups", new String[] { "user", "admin" })
                    // RS256 with privateKey
                    .signWith(SignatureAlgorithm.PS256, privateKey).compact();
            System.out.println("Algorithm_Private: " + privateKey.getAlgorithm());
            //int newvalue = Integer.parseInt(oldvalue) +1;
            //App_genericFunction.WritePropertiesFile_common("id_aat",Integer.toString(newvalue));
            display(publicKey,token,"aat");
        }else
        {
             token = Jwts.builder().setHeaderParam("alg",alg)
                    .setHeaderParam("kid",Kid)
                    .setHeaderParam("typ","jwt")
                    .setIssuer(customer_key)
                    .setSubject(customer_key)
                    // .setExpiration(expiration)
                    .setExpiration(gettimeperiod())
                    // .setIssuedAt(Issuer)
                    .setIssuedAt(getcurrenttime())
                    .setAudience(audience)
                    //.setId("pen"+oldvalue)
                    .setId(CommonFunctions.getRandomString(5) +CommonFunctions.getRandomNumber(5))
                    //.claim("groups", new String[] { "user", "admin" })
                    // RS256 with privateKey
                    .signWith(SignatureAlgorithm.RS256, privateKey).compact();
            System.out.println("Algorithm_Private: " + privateKey.getAlgorithm());
            //int newvalue = Integer.parseInt(oldvalue) +1;
            //App_genericFunction.WritePropertiesFile_common("id_aat",Integer.toString(newvalue));
            display(publicKey,token,"aat");
        }

       // System.out.println("--------------------------------------------------------------------------------------");
        return token;
    }
    @SuppressWarnings("deprecation")
    public static String generateJwtToken_CAT(PrivateKey privateKey,PublicKey publicKey,String audience,String customer_key) throws JSONException, StopTestException {
        System.out.println("=====>> Client Access token ======>>");
        /*String oldvalue = App_genericFunction.ReadPropertiesFile_common("id_cat");
        System.out.println("JWTID_cat : pento" + oldvalue);*/
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        String Kid ="83:AB:74:0E:2D:B2:BF:83:09:F1:78:4B:07:90:AF:06:7C:5B:40:37:D9:B5:70:6E:E2:51:0C:1E:39:EA:28:50";
        String alg ="RS256";
        String token;
        if(Configuration.getConfiguration().getProperty("environment").startsWith("Prod"))
        {
            token = Jwts.builder().setHeaderParam("alg", "PS256")
                    .setHeaderParam("kid", "p3VWA5Yis1h656IIyKthkcT5pfE")
                    .setHeaderParam("typ", "jwt")
                    .setIssuer(customer_key)
                    .setSubject(customer_key)
                    //.setExpiration(expiration)
                    .setExpiration(gettimeperiod())
                    //.setIssuedAt(Issuer)
                    .setIssuedAt(getcurrenttime())
                    .setAudience(audience)
                    //.setId("pento"+ oldvalue)
                    .setId(CommonFunctions.getRandomString(5) + CommonFunctions.getRandomNumber(5))
                    //.claim("groups", new String[] { "user", "admin" })
                    // RS256 with privateKey
                    .signWith(SignatureAlgorithm.PS256, privateKey).compact();
        /*int newvalue = Integer.parseInt(oldvalue) +1;
        App_genericFunction.WritePropertiesFile_common("id_cat",Integer.toString(newvalue));*/
            display(publicKey, token, "cat");
        }else  if(Configuration.getConfiguration().getProperty("environment").startsWith("UAT"))
        {
            token = Jwts.builder().setHeaderParam("alg", "PS256")
                    .setHeaderParam("kid", Kid)
                    .setHeaderParam("typ", "jwt")
                    .setIssuer(customer_key)
                    .setSubject(customer_key)
                    //.setExpiration(expiration)
                    .setExpiration(gettimeperiod())
                    //.setIssuedAt(Issuer)
                    .setIssuedAt(getcurrenttime())
                    .setAudience(audience)
                    //.setId("pento"+ oldvalue)
                    .setId(CommonFunctions.getRandomString(5) + CommonFunctions.getRandomNumber(5))
                    //.claim("groups", new String[] { "user", "admin" })
                    // RS256 with privateKey
                    .signWith(SignatureAlgorithm.PS256, privateKey).compact();
        /*int newvalue = Integer.parseInt(oldvalue) +1;
        App_genericFunction.WritePropertiesFile_common("id_cat",Integer.toString(newvalue));*/
            display(publicKey, token, "cat");
        }else
            {
             token = Jwts.builder().setHeaderParam("alg", "RS256")
                    .setHeaderParam("kid", Kid)
                    .setHeaderParam("typ", "jwt")
                    .setIssuer(customer_key)
                    .setSubject(customer_key)
                    //.setExpiration(expiration)
                    .setExpiration(gettimeperiod())
                    //.setIssuedAt(Issuer)
                    .setIssuedAt(getcurrenttime())
                    .setAudience(audience)
                    //.setId("pento"+ oldvalue)
                    .setId(CommonFunctions.getRandomString(5) + CommonFunctions.getRandomNumber(5))
                    //.claim("groups", new String[] { "user", "admin" })
                    // RS256 with privateKey
                    .signWith(SignatureAlgorithm.RS256, privateKey).compact();
        /*int newvalue = Integer.parseInt(oldvalue) +1;
        App_genericFunction.WritePropertiesFile_common("id_cat",Integer.toString(newvalue));*/
            display(publicKey, token, "cat");
        }
       // System.out.println("--------------------------------------------------------------------------------------");
        return token;
    }
    @SuppressWarnings("deprecation")
    public static String generateJwtToken_forauthURL(PrivateKey privateKey,PublicKey publicKey,String ConsentID,String audience,String customer_key,String redirect_url,String Scope) throws JSONException, StopTestException {
        System.out.println("=====>> Auth URL token ======>>");
        LinkedList <String>values = new LinkedList<String>();
        values.add("urn:openbanking:psd2:sca");
        values.add("urn:openbanking:psd2:ca");

        LinkedHashMap <String,Object> openbanking_intent_id = new LinkedHashMap<String, Object>();
        openbanking_intent_id.put("value",ConsentID);
        openbanking_intent_id.put("essential",true);

        LinkedHashMap <String,Object> acr = new LinkedHashMap<String, Object>();
        acr.put("essential",true);
        acr.put("values",values);

        LinkedHashMap <String,Object> id_token = new LinkedHashMap<String, Object>();
        id_token.put("openbanking_intent_id",openbanking_intent_id);
        id_token.put("acr",acr);

        JSONObject body = new JSONObject();
        body.put("openbanking_intent_id",openbanking_intent_id);

        LinkedHashMap claims = new LinkedHashMap();
        claims.put("userinfo",body);
        claims.put("id_token",id_token);

        JSONObject payload = new JSONObject();
        //LinkedHashMap<String,Object> payload = new LinkedHashMap<>();
        payload.put("aud",audience);
        payload.put("iss",customer_key);
        payload.put("exp",1631242241);
        //payload.put("exp",gettimeperiod_millisec());
        payload.put("response_type","code id_token");
        payload.put("client_id",customer_key);
        payload.put("redirect_uri",redirect_url);
        payload.put("scope",Scope + " openid");
        payload.put("nonce","n-0S6_WzA2M");
        payload.put("max_age",86400);
        payload.put("claims",claims);

        System.out.println("Payload : " + payload.toString());

        String token;
        if(Configuration.getConfiguration().getProperty("environment").startsWith("Prod"))
        {
             token = Jwts.builder().setHeaderParam("alg","PS256")
                    .setPayload(payload.toString())
                    //.setClaims(payload)
                    //.setExpiration(gettimeperiod())
                    .signWith(SignatureAlgorithm.PS256, privateKey).compact();
            display(publicKey,token,"authurl");
        }else if(Configuration.getConfiguration().getProperty("environment").startsWith("UAT"))
        {
            token = Jwts.builder().setHeaderParam("alg","PS256")
                    .setPayload(payload.toString())
                    //.setClaims(payload)
                    //.setExpiration(gettimeperiod())
                    .signWith(SignatureAlgorithm.PS256, privateKey).compact();
            display(publicKey,token,"authurl");
        }
        else
        {
             token = Jwts.builder().setHeaderParam("alg","RS256")
                .setPayload(payload.toString())
                //.setClaims(payload)
                //.setExpiration(gettimeperiod())
                .signWith(SignatureAlgorithm.RS256, privateKey).compact();
            display(publicKey,token,"authurl");
        }


        return token;
    }
    //Print structure of JWT
    public static void printStructure(String token, PublicKey publicKey) throws JSONException {
        Jws parseClaimsJws = Jwts.parser().setSigningKey(publicKey)
                .parseClaimsJws(token);

        System.out.println("Header     : " + parseClaimsJws.getHeader());
        System.out.println("Body       : " + parseClaimsJws.getBody());
        System.out.println("Signature  : " + parseClaimsJws.getSignature());
        System.out.println("Algorithm_Public: " + publicKey.getAlgorithm());
        //Claims claims = (Claims) parseClaimsJws.getBody();
        //System.out.println("Expiration : "+ claims.getExpiration());
        /*JSONObject evaluator =new JSONObject(parseClaimsJws.getBody().toString());
        System.out.println( evaluator.getJSONObject("exp").toString());*/
    }

    public static void printStructure_cucumber_report(String token, PublicKey publicKey) throws JSONException {
        Jws parseClaimsJws = Jwts.parser().setSigningKey(publicKey)
                .parseClaimsJws(token);

       App_genericFunction.putcommentinStep("Header     : " + parseClaimsJws.getHeader());
        App_genericFunction.putcommentinStep("Body       : " + parseClaimsJws.getBody());
        App_genericFunction.putcommentinStep("Signature  : " + parseClaimsJws.getSignature());
        //Claims claims = (Claims) parseClaimsJws.getBody();
        //System.out.println("Expiration : "+ claims.getExpiration());
        /*JSONObject evaluator =new JSONObject(parseClaimsJws.getBody().toString());
        System.out.println( evaluator.getJSONObject("exp").toString());*/
    }
    // Add BEGIN and END comments
    private static String convertToPublicKey(String key){
        StringBuilder result = new StringBuilder();
        result.append("-----BEGIN PUBLIC KEY-----\n");
        result.append(key);
        result.append("\n-----END PUBLIC KEY-----");
        return result.toString();
    }



        public static PrivateKey get_privateKey(String filename) throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {

            byte[] keyBytes = Files.readAllBytes(Paths.get(filename));

            PKCS8EncodedKeySpec spec =
                    new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            return kf.generatePrivate(spec);
        }

    public static PublicKey get_publicKey(String filename)
            throws Exception {

        byte[] keyBytes = Files.readAllBytes(Paths.get(filename));

        X509EncodedKeySpec spec =
                new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }

    public static Date gettimeperiod()
    {
        Date exp=null;

        Calendar today = Calendar.getInstance();
        long nowMillis = today.getTimeInMillis();
        Date now = new Date(nowMillis);
        Calendar MonthsAhead = Calendar.getInstance();
        MonthsAhead.add(Calendar.MONTH, 6);
        long ttlMillis = MonthsAhead.getTimeInMillis() - today.getTimeInMillis();
        if (ttlMillis > 0) {
            long expMillis = nowMillis + ttlMillis;
            exp = new Date(expMillis);
            System.out.println("Time validity : " + exp);
        }
        return exp;
    }
    public static long gettimeperiod_millisec()
    {
        long expMillis = 0;
        Calendar today = Calendar.getInstance();
        long nowMillis = today.getTimeInMillis();
        Date now = new Date(nowMillis);
        Calendar MonthsAhead = Calendar.getInstance();
        MonthsAhead.add(Calendar.MONTH, 6);
        long ttlMillis = MonthsAhead.getTimeInMillis() - today.getTimeInMillis();
        if (ttlMillis > 0) {
            expMillis = nowMillis + ttlMillis;
           //exp = new Date(expMillis);
           // System.out.println("Time validity : " + exp);
        }
        return expMillis;
    }
    public static Date getcurrenttime()
    {
        Date now=null;

        Calendar today = Calendar.getInstance();
        long nowMillis = today.getTimeInMillis();
        now = new Date(nowMillis);
        return now;
    }

}

