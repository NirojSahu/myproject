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

public class JWT_Generic_Sandbox {

    public  PublicKey publicKey;
    public  PrivateKey privateKey;


    public JWT_Generic_Sandbox() throws Exception {
        //String filename =System.getProperty("user.dir")+"/src/test/resources/Certificates/5A";
        //String filename ="C:\\Users\\c0261484\\Desktop\\DCR Testing_22\\OBWAC";
        String filename ="C:\\Users\\c0266294\\OneDrive - Santander Office 365\\Documents\\Open Banking Docs\\Sandbox Certificates\\Newcert_30062021_SET2_DCR\\OBSEAL";
        //String filename ="C:\\Users\\c0261484\\Desktop\\Prod\\OBSEAL";
       // this.publicKey = get_publicKey(filename + "/Public.der");
       // this.privateKey = get_privateKey(filename + "/Private.der");

        /*this.publicKey = get_publicKey(filename + "\\OBWACPUBLIC.der");
        this.privateKey = get_privateKey(filename + "\\OBWACPRIVATE.der");*/

         this.publicKey = get_publicKey(filename + "\\OBSEALPUBLIC.der");
        this.privateKey = get_privateKey(filename + "\\OBSEALPRIVATE.der");

             /* this.publicKey = get_publicKey(filename + "\\OBSEAL_public.der");
        this.privateKey = get_privateKey(filename + "\\OBSEAL_private.der");*/
    }

    public static void main(String[] args) throws Exception {

        // Using default
        JWT_Generic_Sandbox obj = new JWT_Generic_Sandbox();

        Map<String,Object> headers = new LinkedHashMap<>();
        headers.put("alg","PS256");
        headers.put("kid","NcXTzDUx_x_Xh2QszHpkAfPoWR0");
        //headers.put("typ","jwt");
        //String token = obj.generateJwtToken_default(headers,"https://ppuatdeveloper.intellectonlinebanking.com/token","XjqOowR0kXZe_iYqGWVfVlQC9esa","XjqOowR0kXZe_iYqGWVfVlQC9esa");
        String token = obj.generateJwtToken_default_PS256(headers,"https://sandbox.caterallen.co.uk/token","6Qrdfg9KtmUDR4ZiAHECxa ","6Qrdfg9KtmUDR4ZiAHECxa ");
        obj.printStructure(token);

        System.out.println("=================================================================================================================================================");

        // Using payload
        Map<String,Object> headers1 = new LinkedHashMap<>();
        headers.put("alg","RS256");

        JWT_Generic_Sandbox obj1= new JWT_Generic_Sandbox();
        LinkedList<String> values = new LinkedList<String>();
        values.add("urn:openbanking:psd2:sca");
        values.add("urn:openbanking:psd2:ca");

        LinkedHashMap<String,Object> openbanking_intent_id = new LinkedHashMap<String, Object>();
        openbanking_intent_id.put("value","653e7091-feb2-485e-bf50-6dca60a726db");
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
        payload.put("aud","https://ppuatdeveloper.intellectonlinebanking.com/");
        payload.put("iss","6Qrdfg9KtmUDR4ZiAHECxa");
        payload.put("exp",1666546950);
        //payload.put("exp",gettimeperiod_millisec());
        payload.put("response_type","code id_token");
        payload.put("client_id","6Qrdfg9KtmUDR4ZiAHECxa");
        payload.put("redirect_uri","https://www.google.com/");
        payload.put("scope",  "accounts openid");
        payload.put("nonce","n-0S6_WzA2M");
        payload.put("max_age",86400);
        payload.put("claims",claims);
        String token_1 = obj1.generateJwtToken_payload(headers1,payload.toString());
        obj.printStructure(token_1);

        System.out.println("===================================================================================");

        Map<String,Object> headers2 = new LinkedHashMap<>();
        headers2.put("alg","PS256");
        headers2.put("kid","NcXTzDUx_x_Xh2QszHpkAfPoWR0");
        headers2.put("typ","JWT");

        JWT_Generic_Sandbox obj2= new JWT_Generic_Sandbox();
        LinkedList<String> values_1 = new LinkedList<String>();
        values_1.add("authorization_code");
        values_1.add("refresh_token");

        LinkedList<String> values_2 = new LinkedList<String>();
        values_2.add("code");
        values_2.add("code id_token");

        LinkedList<String> values_3 = new LinkedList<String>();
        values_3.add("https://caterallen.co.uk/");

        JSONObject payload1 = new JSONObject();

        payload1.put("iss","6Qrdfg9KtmUDR4ZiAHECxa");
        payload1.put("iat",1631444440);
        payload1.put("exp",1666546950);
        payload1.put("jti","rwrer3424242342fdsdfsd");
        payload1.put("aud","https://sandbox.caterallen.co.uk");
        payload1.put("scope","accounts payments fundsconfirmations");
        payload1.put("token_endpoint_auth_method","private_key_jwt");
        payload1.put("grant_types",values_1);
        payload1.put("response_types",values_2);
        payload1.put("redirect_uris",values_3);
        payload1.put("id_token_signed_response_alg","PS256");
        payload1.put("request_object_signing_alg","PS256");
        payload1.put("software_id","6Qrdfg9KtmUDR4ZiAHECxa");
        payload1.put("application_type","web");
        payload1.put("software_statement","eyJhbGciOiJQUzI1NiIsImtpZCI6Imo4SFdZMDBhSUJtS0ExT1c3WW50dnRLVU0ycnVueDdvQWdiS2hJRE1IM0k9IiwidHlwIjoiSldUIn0.eyJpc3MiOiJPcGVuQmFua2luZyBMdGQiLCJpYXQiOjE2MjUwNjQ2NTksImp0aSI6IjRiZjU5YWM4ZTcxMTQ3NGQiLCJzb2Z0d2FyZV9lbnZpcm9ubWVudCI6InNhbmRib3giLCJzb2Z0d2FyZV9tb2RlIjoiVGVzdCIsInNvZnR3YXJlX2lkIjoiNlFyZGZnOUt0bVVEUjRaaUFIRUN4YSIsInNvZnR3YXJlX2NsaWVudF9pZCI6IjZRcmRmZzlLdG1VRFI0WmlBSEVDeGEiLCJzb2Z0d2FyZV9jbGllbnRfbmFtZSI6Ik9CV2FjIGFuZCBPQlNlYWwgVGVzdGluZyBEQ1IgNCIsInNvZnR3YXJlX2NsaWVudF9kZXNjcmlwdGlvbiI6Ik9CV2FjIGFuZCBPQlNlYWwgVGVzdGluZyBEQ1IgNCIsInNvZnR3YXJlX3ZlcnNpb24iOjMuMSwic29mdHdhcmVfY2xpZW50X3VyaSI6Imh0dHBzOi8vY2F0ZXJhbGxlbi5jby51ay8iLCJzb2Z0d2FyZV9yZWRpcmVjdF91cmlzIjpbImh0dHBzOi8vY2F0ZXJhbGxlbi5jby51ay8iXSwic29mdHdhcmVfcm9sZXMiOlsiUElTUCIsIkNCUElJIiwiQUlTUCJdLCJvcmdhbmlzYXRpb25fY29tcGV0ZW50X2F1dGhvcml0eV9jbGFpbXMiOnsiYXV0aG9yaXR5X2lkIjoiRkNBR0JSIiwicmVnaXN0cmF0aW9uX2lkIjoiMTc4NzM3Iiwic3RhdHVzIjoiQWN0aXZlIiwiYXV0aG9yaXNhdGlvbnMiOlt7Im1lbWJlcl9zdGF0ZSI6IkdCIiwicm9sZXMiOlsiUElTUCIsIkNCUElJIiwiQUlTUCIsIkFTUFNQIl19LHsibWVtYmVyX3N0YXRlIjoiSUUiLCJyb2xlcyI6WyJDQlBJSSIsIkFJU1AiLCJBU1BTUCIsIlBJU1AiXX0seyJtZW1iZXJfc3RhdGUiOiJOTCIsInJvbGVzIjpbIlBJU1AiLCJDQlBJSSIsIkFJU1AiLCJBU1BTUCJdfV19LCJzb2Z0d2FyZV9sb2dvX3VyaSI6Imh0dHBzOi8vY2F0ZXJhbGxlbi5jby51ay8iLCJvcmdfc3RhdHVzIjoiQWN0aXZlIiwib3JnX2lkIjoiMDAxNTgwMDAwMUhRUXJVQUFYIiwib3JnX25hbWUiOiJDYXRlciBBbGxlbiBMaW1pdGVkIiwib3JnX2NvbnRhY3RzIjpbeyJuYW1lIjoiVGVjaG5pY2FsIiwiZW1haWwiOiJjYXRlcmFsbGVub3BlbmJhbmtpbmdAc2FudGFuZGVyLmNvLnVrIiwicGhvbmUiOiIwNzU0ODEyNzk2MiIsInR5cGUiOiJUZWNobmljYWwifSx7Im5hbWUiOiJCdXNpbmVzcyIsImVtYWlsIjoiY2F0ZXJhbGxlbm9wZW5iYW5raW5nQHNhbnRhbmRlci5jby51ayIsInBob25lIjoiSm9uIEhvd2UiLCJ0eXBlIjoiQnVzaW5lc3MifV0sIm9yZ19qd2tzX2VuZHBvaW50IjoiaHR0cHM6Ly9rZXlzdG9yZS5vcGVuYmFua2luZ3Rlc3Qub3JnLnVrLzAwMTU4MDAwMDFIUVFyVUFBWC8wMDE1ODAwMDAxSFFRclVBQVguandrcyIsIm9yZ19qd2tzX3Jldm9rZWRfZW5kcG9pbnQiOiJodHRwczovL2tleXN0b3JlLm9wZW5iYW5raW5ndGVzdC5vcmcudWsvMDAxNTgwMDAwMUhRUXJVQUFYL3Jldm9rZWQvMDAxNTgwMDAwMUhRUXJVQUFYLmp3a3MiLCJzb2Z0d2FyZV9qd2tzX2VuZHBvaW50IjoiaHR0cHM6Ly9rZXlzdG9yZS5vcGVuYmFua2luZ3Rlc3Qub3JnLnVrLzAwMTU4MDAwMDFIUVFyVUFBWC82UXJkZmc5S3RtVURSNFppQUhFQ3hhLmp3a3MiLCJzb2Z0d2FyZV9qd2tzX3Jldm9rZWRfZW5kcG9pbnQiOiJodHRwczovL2tleXN0b3JlLm9wZW5iYW5raW5ndGVzdC5vcmcudWsvMDAxNTgwMDAwMUhRUXJVQUFYL3Jldm9rZWQvNlFyZGZnOUt0bVVEUjRaaUFIRUN4YS5qd2tzIiwic29mdHdhcmVfcG9saWN5X3VyaSI6Imh0dHBzOi8vY2F0ZXJhbGxlbi5jby51ay8iLCJzb2Z0d2FyZV90b3NfdXJpIjoiaHR0cHM6Ly9jYXRlcmFsbGVuLmNvLnVrLyIsInNvZnR3YXJlX29uX2JlaGFsZl9vZl9vcmciOiIifQ.QhWlNfBvmeH5yJWoSOmsiykl31y5Rm6vELvfJsOCU6zrseTDj2zntBxI5-kgNZ0EhsAS_Q37a8kawgbMzkZC4yDqEpjtjp39Mco02jOggfUw89hKBeQkafz_QADKKpkD9cdQA68oIDiK-u7DE3FD2kV5p6KoVBp9OveZ0g5IakOJuML8Av_BDXeuiIHFBdGV1tlCtxrr6EGxmdGmoiTb4kS_33D-P2w7cKIwkKPI4AnZzKpuqWZT3tZRnqds6adUWwoVpPnN-M_vVYBigxB_ga56NOqIWP5LD11Mxoty7QpGdZxVh1_5aJPcu-ajrZWcmi43FRfZa-MQ6je3UgnUqw");
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
