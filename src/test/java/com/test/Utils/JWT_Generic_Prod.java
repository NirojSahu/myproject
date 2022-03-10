package com.test.Utils;

import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang.RandomStringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;

public class JWT_Generic_Prod {

    public  PublicKey publicKey;
    public  PrivateKey privateKey;


    public JWT_Generic_Prod() throws Exception {
        //String filename =System.getProperty("user.dir")+"/src/test/resources/Certificates/5A";
        //String filename ="C:\\Users\\c0261484\\Desktop\\DCR Testing_22\\OBWAC";
        //String filename ="C:\\Users\\c0261484\\Desktop\\DCR Testing_22\\OBSEAL";
        String filename ="C:\\Users\\c0266294\\OneDrive - Santander Office 365\\Documents\\Open Banking Docs\\Sandbox Certificates\\Newcert_30062021_NSET3_PROD\\OBSEAL";

           // this.publicKey = get_publicKey(filename + "/Public.der");
       // this.privateKey = get_privateKey(filename + "/Private.der");

        /*this.publicKey = get_publicKey(filename + "\\OBWACPUBLIC.der");
        this.privateKey = get_privateKey(filename + "\\OBWACPRIVATE.der");*/

       /* this.publicKey = get_publicKey(filename + "\\OBSEALPUBLIC.der");
        this.privateKey = get_privateKey(filename + "\\OBSEALPRIVATE.der");*/

        this.publicKey = get_publicKey(filename + "\\OBSEALPUBLIC.der");
        this.privateKey = get_privateKey(filename + "\\OBSEALPRIVATE.der");
    }

    public static void main(String[] args) throws Exception {

        // Using default
        JWT_Generic_Prod obj = new JWT_Generic_Prod();

        Map<String,Object> headers = new LinkedHashMap<>();
        headers.put("alg","PS256");
        headers.put("kid","Qxz1Jxd6UFVcremYuhxaOG8N1gE");
        headers.put("typ","jwt");
        //String token = obj.generateJwtToken_default(headers,"https://10.6.184.26:8243/token","XjqOowR0kXZe_iYqGWVfVlQC9esa","XjqOowR0kXZe_iYqGWVfVlQC9esa");
        String token = obj.generateJwtToken_default_PS256(headers,"https://developer.caterallen.co.uk/token","ScHkGA7OHYX1uJ7tMDYF_1IM8Voa","ScHkGA7OHYX1uJ7tMDYF_1IM8Voa");
        obj.printStructure(token);

        System.out.println("=================================================================================================================================================");

        // Using payload
        Map<String,Object> headers1 = new LinkedHashMap<>();
        headers1.put("alg","PS256");
        headers1.put("kid","Qxz1Jxd6UFVcremYuhxaOG8N1gE");
        headers1.put("typ","JWT");

        JWT_Generic_Prod obj1= new JWT_Generic_Prod();
        LinkedList<String> values = new LinkedList<String>();
        values.add("urn:openbanking:psd2:sca");
        values.add("urn:openbanking:psd2:ca");

        LinkedHashMap<String,Object> openbanking_intent_id = new LinkedHashMap<String, Object>();
        openbanking_intent_id.put("value","06d286d1-806b-446e-b730-9a64aed66869");
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
        payload.put("aud","https://developer.caterallen.co.uk/");
        payload.put("iss","ScHkGA7OHYX1uJ7tMDYF_1IM8Voa");
        payload.put("exp",1666546950);
        //payload.put("exp",gettimeperiod_millisec());
        payload.put("response_type","code id_token");
        payload.put("client_id","ScHkGA7OHYX1uJ7tMDYF_1IM8Voa");
        payload.put("redirect_uri","https://caterallen.co.uk/");
        payload.put("scope",  "accounts payments fundsconfirmations openid");
        payload.put("nonce","n-0S6_WzA2M");
        payload.put("max_age",86400);
        payload.put("claims",claims);
        String token_1 = obj1.generateJwtToken_payload_PS256(headers1,payload.toString());
        obj.printStructure(token_1);

        System.out.println("===================================================================================");

        Map<String,Object> headers2 = new LinkedHashMap<>();
        headers2.put("alg","PS256");
        headers2.put("kid","Qxz1Jxd6UFVcremYuhxaOG8N1gE");
        headers2.put("typ","JWT");

        JWT_Generic_Prod obj2= new JWT_Generic_Prod();
        LinkedList<String> values_1 = new LinkedList<String>();
        values_1.add("authorization_code");
        values_1.add("refresh_token");

        LinkedList<String> values_2 = new LinkedList<String>();
        values_2.add("code");
        values_2.add("code id_token");

        LinkedList<String> values_3 = new LinkedList<String>();
        values_3.add("https://caterallen.co.uk/");

        JSONObject payload1 = new JSONObject();

        payload1.put("iss","T3Hk7o8UaIkyYeZ5nxTBv4");
        payload1.put("iat",1631444440);
        payload1.put("exp",1666546950);
        payload1.put("jti","dgdfgg34242");
        payload1.put("aud","https://developer.caterallen.co.uk/");
        payload1.put("scope","accounts payments fundsconfirmations");
        payload1.put("token_endpoint_auth_method","private_key_jwt");
        payload1.put("grant_types",values_1);
        payload1.put("response_types",values_2);
        payload1.put("redirect_uris",values_3);
        payload1.put("id_token_signed_response_alg","PS256");
        payload1.put("request_object_signing_alg","PS256");
        payload1.put("software_id","T3Hk7o8UaIkyYeZ5nxTBv4");
        payload1.put("application_type","web");
        payload1.put("software_statement","eyJhbGciOiJQUzI1NiIsImtpZCI6ImJDbFIwX1Y1cmNhNTdZdlVPaWNmam00V0VVOEFkNjhiNlRsaXZWcS1fMlU9IiwidHlwIjoiSldUIn0.eyJpc3MiOiJPcGVuQmFua2luZyBMdGQiLCJpYXQiOjE2MjUxMDAwODksImp0aSI6ImVkZDE3MTA3YzFlODQ2ZjciLCJzb2Z0d2FyZV9lbnZpcm9ubWVudCI6InByb2R1Y3Rpb24iLCJzb2Z0d2FyZV9tb2RlIjoiTGl2ZSIsInNvZnR3YXJlX2lkIjoiVDNIazdvOFVhSWt5WWVaNW54VEJ2NCIsInNvZnR3YXJlX2NsaWVudF9pZCI6IlQzSGs3bzhVYUlreVllWjVueFRCdjQiLCJzb2Z0d2FyZV9jbGllbnRfbmFtZSI6Ikp1bmUgRENSIE9CV2FjIE9CIFNlYWwgVGVzdGluZyAxIiwic29mdHdhcmVfY2xpZW50X2Rlc2NyaXB0aW9uIjoiSnVuZSBEQ1IgT0JXYWMgT0IgU2VhbCBUZXN0aW5nIDEiLCJzb2Z0d2FyZV92ZXJzaW9uIjozLjEsInNvZnR3YXJlX2NsaWVudF91cmkiOiJodHRwczovL2NhdGVyYWxsZW4uY28udWsvIiwic29mdHdhcmVfcmVkaXJlY3RfdXJpcyI6WyJodHRwczovL2NhdGVyYWxsZW4uY28udWsvIl0sInNvZnR3YXJlX3JvbGVzIjpbIlBJU1AiLCJDQlBJSSIsIkFJU1AiXSwib3JnYW5pc2F0aW9uX2NvbXBldGVudF9hdXRob3JpdHlfY2xhaW1zIjp7ImF1dGhvcml0eV9pZCI6IkZDQUdCUiIsInJlZ2lzdHJhdGlvbl9pZCI6IjE3ODczNyIsInN0YXR1cyI6IkFjdGl2ZSIsImF1dGhvcmlzYXRpb25zIjpbeyJtZW1iZXJfc3RhdGUiOiJHQiIsInJvbGVzIjpbIlBJU1AiLCJDQlBJSSIsIkFJU1AiLCJBU1BTUCJdfV19LCJzb2Z0d2FyZV9sb2dvX3VyaSI6Imh0dHBzOi8vY2F0ZXJhbGxlbi5jby51ay8iLCJvcmdfc3RhdHVzIjoiQWN0aXZlIiwib3JnX2lkIjoiMDAxNTgwMDAwMUhRUXJVQUFYIiwib3JnX25hbWUiOiJDYXRlciBBbGxlbiBMaW1pdGVkIiwib3JnX2NvbnRhY3RzIjpbeyJuYW1lIjoiVGVjaG5pY2FsIiwiZW1haWwiOiJjYXRlcmFsbGVub3BlbmJhbmtpbmdAc2FudGFuZGVyLmNvLnVrIiwicGhvbmUiOiIwNzU0ODEyNzk2MiIsInR5cGUiOiJUZWNobmljYWwifSx7Im5hbWUiOiJCdXNpbmVzcyIsImVtYWlsIjoiY2F0ZXJhbGxlbm9wZW5iYW5raW5nQHNhbnRhbmRlci5jby51ayIsInBob25lIjoiSm9uYXRoYW4gSG93ZSIsInR5cGUiOiJCdXNpbmVzcyJ9XSwib3JnX2p3a3NfZW5kcG9pbnQiOiJodHRwczovL2tleXN0b3JlLm9wZW5iYW5raW5nLm9yZy51ay8wMDE1ODAwMDAxSFFRclVBQVgvMDAxNTgwMDAwMUhRUXJVQUFYLmp3a3MiLCJvcmdfandrc19yZXZva2VkX2VuZHBvaW50IjoiaHR0cHM6Ly9rZXlzdG9yZS5vcGVuYmFua2luZy5vcmcudWsvMDAxNTgwMDAwMUhRUXJVQUFYL3Jldm9rZWQvMDAxNTgwMDAwMUhRUXJVQUFYLmp3a3MiLCJzb2Z0d2FyZV9qd2tzX2VuZHBvaW50IjoiaHR0cHM6Ly9rZXlzdG9yZS5vcGVuYmFua2luZy5vcmcudWsvMDAxNTgwMDAwMUhRUXJVQUFYL1QzSGs3bzhVYUlreVllWjVueFRCdjQuandrcyIsInNvZnR3YXJlX2p3a3NfcmV2b2tlZF9lbmRwb2ludCI6Imh0dHBzOi8va2V5c3RvcmUub3BlbmJhbmtpbmcub3JnLnVrLzAwMTU4MDAwMDFIUVFyVUFBWC9yZXZva2VkL1QzSGs3bzhVYUlreVllWjVueFRCdjQuandrcyIsInNvZnR3YXJlX3BvbGljeV91cmkiOiJodHRwczovL2NhdGVyYWxsZW4uY28udWsvIiwic29mdHdhcmVfdG9zX3VyaSI6Imh0dHBzOi8vY2F0ZXJhbGxlbi5jby51ay8iLCJzb2Z0d2FyZV9vbl9iZWhhbGZfb2Zfb3JnIjoiIn0.Lz4KrWJjjPzsa4jC-xwgjqS9E3cinYx4c_ACjrr45xIsKYbI-eEmZtExiqVzwweUA0OpvqcSunf9xrJtOtct2djr5N2Fr4pz-AEFpE37csk0Q2KILdwXxODesZyK0WksZRdt01c4b8CpZpN0m74AOZumJXbkWop_vbqXYWOAkCGurxxF4hJWZ7PqS1soq6ImK8aMSWPonSgpgbqtD9HSoiPnL9s3BF2CII0Yc6t7NB0GcFVP7Ofg71_SS8pqQ2NspYOmDzRogEHJRv6I4jt7B7dFtS1A38TPZ-BgakD7CYJmM6GNIhHKGkpIBBujog6jGOB3KeUyBPa52aU8rDfL1A");
        String token_2 = obj1.generateJwtToken_payload_PS256(headers2,payload1.toString());
        obj.printStructure(token_2);
    }

    @SuppressWarnings("deprecation")
    public  String generateJwtToken_default(Map<String,Object> headers,String audience,String issuer,String Subject) throws JSONException
    {
        String token = Jwts.builder().setHeaderParam("alg","RS256")
                .setHeader(headers)
                //.setHeaderParam("kid","83:AB:74:0E:2D:B2:BF:83:09:F1:78:4B:07:90:AF:06:7C:5B:40:37:D9:B5:70:6E:E2:51:0C:1E:39:EA:28:50")
                //.setHeaderParam("typ","jwt")
                .setIssuer(issuer)
                .setSubject(Subject)
                .setExpiration(gettimeperiod())
                .setIssuedAt(getcurrenttime())
                .setAudience(audience)
                .setId(getRandomString(5) +getRandomNumber(5))

                // RS256 with privateKey
                .signWith(SignatureAlgorithm.RS256, this.privateKey).compact();
        System.out.println("Private Key : " + token);
        return token;
    }

    @SuppressWarnings("deprecation")
    public  String generateJwtToken_default_PS256(Map<String,Object> headers,String audience,String issuer,String Subject) throws JSONException
    {
        String token = Jwts.builder().setHeaderParam("alg","PS256")
                .setHeader(headers)
                //.setHeaderParam("kid","83:AB:74:0E:2D:B2:BF:83:09:F1:78:4B:07:90:AF:06:7C:5B:40:37:D9:B5:70:6E:E2:51:0C:1E:39:EA:28:50")
                //.setHeaderParam("typ","jwt")
                .setIssuer(issuer)
                .setSubject(Subject)
                .setExpiration(gettimeperiod())
                .setIssuedAt(getcurrenttime())
                .setAudience(audience)
                .setId(getRandomString(5) +getRandomNumber(5))

                // RS256 with privateKey
                .signWith(SignatureAlgorithm.PS256, this.privateKey).compact();
        System.out.println("Private Key : " + token);
        return token;
    }
    @SuppressWarnings("deprecation")
    public String generateJwtToken_payload(Map<String,Object> headers,String payload) throws JSONException {
        String token = Jwts.builder().setHeader(headers)
                .setPayload(payload)
                .signWith(SignatureAlgorithm.RS256, this.privateKey).compact();
        System.out.println("Private Key token : " + token);
        return token;
    }
    @SuppressWarnings("deprecation")
    public String generateJwtToken_payload_PS256(Map<String,Object> headers,String payload) throws JSONException {
        String token = Jwts.builder().setHeader(headers)
                .setPayload(payload)
                .signWith(SignatureAlgorithm.PS256, this.privateKey).compact();
        System.out.println("Private Key token : " + token);
        return token;
    }
    //Print structure of JWT
    public void printStructure(String token) throws JSONException {
        Jws parseClaimsJws = Jwts.parser().setSigningKey(this.publicKey)
                .parseClaimsJws(token);
        System.out.println("Header     : " + parseClaimsJws.getHeader());
        System.out.println("Body       : " + parseClaimsJws.getBody());
        System.out.println("Signature  : " + parseClaimsJws.getSignature());
        System.out.println(convertToPublicKey(token));
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
    public static String getRandomString(int length) {
        return RandomStringUtils.randomAlphanumeric(length).toUpperCase();

    }

    public static String getRandomNumber(int length) {
        return RandomStringUtils.randomNumeric(length);

    }
}
