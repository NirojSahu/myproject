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

public class JWT_Generic {

    public  PublicKey publicKey;
    public  PrivateKey privateKey;


    public JWT_Generic() throws Exception {
        //String filename =System.getProperty("user.dir")+"/src/test/resources/Certificates/5A";
        //String filename ="C:\\Users\\c0261484\\Desktop\\DCR Testing_22\\OBWAC";
        String filename ="C:\\Users\\c0261484\\Desktop\\DCR Testing_22\\OBSEAL";
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
        JWT_Generic obj = new JWT_Generic();

        Map<String,Object> headers = new LinkedHashMap<>();
        headers.put("alg","RS256");
        headers.put("kid","83:AB:74:0E:2D:B2:BF:83:09:F1:78:4B:07:90:AF:06:7C:5B:40:37:D9:B5:70:6E:E2:51:0C:1E:39:EA:28:50");
        headers.put("typ","jwt");
        //String token = obj.generateJwtToken_default(headers,"https://10.6.184.26:8243/token","XjqOowR0kXZe_iYqGWVfVlQC9esa","XjqOowR0kXZe_iYqGWVfVlQC9esa");
        String token = obj.generateJwtToken_default(headers,"https://10.6.184.26:8243/token","B4qlwRVhuvYyZAfFq0aiv5e8lIga","B4qlwRVhuvYyZAfFq0aiv5e8lIga");
        obj.printStructure(token);

        System.out.println("=================================================================================================================================================");

        // Using payload
        Map<String,Object> headers1 = new LinkedHashMap<>();
        headers.put("alg","RS256");

        JWT_Generic obj1= new JWT_Generic();
        LinkedList<String> values = new LinkedList<String>();
        values.add("urn:openbanking:psd2:sca");
        values.add("urn:openbanking:psd2:ca");

        LinkedHashMap<String,Object> openbanking_intent_id = new LinkedHashMap<String, Object>();
        openbanking_intent_id.put("value","601c1041-0195-4c2a-b900-920bb9469346");
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
        payload.put("aud","https://10.6.184.26:8243/token");
        payload.put("iss","B4qlwRVhuvYyZAfFq0aiv5e8lIga");
        payload.put("exp",1621242241);
        //payload.put("exp",gettimeperiod_millisec());
        payload.put("response_type","code id_token");
        payload.put("client_id","B4qlwRVhuvYyZAfFq0aiv5e8lIga");
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
        headers2.put("kid","HoLd4EvB7qmB0O3xV4CntNF-3pI");
        headers2.put("typ","JWT");

        JWT_Generic obj2= new JWT_Generic();
        LinkedList<String> values_1 = new LinkedList<String>();
        values_1.add("authorization_code");
        values_1.add("refresh_token");

        LinkedList<String> values_2 = new LinkedList<String>();
        values_2.add("code");
        values_2.add("code id_token");

        LinkedList<String> values_3 = new LinkedList<String>();
        values_3.add("https://caterallen.co.uk");

        JSONObject payload1 = new JSONObject();

        payload1.put("iss","lhet1nDTHjMkeTFyWGLQSb");
        payload1.put("iat",1654184766);
        payload1.put("exp",1743573565);
        payload1.put("jti","92713892-5514-11e9-8647-d663bd873d95");
        payload1.put("aud","https://sandbox.caterallen.co.uk");
        payload1.put("scope","accounts payments");
        payload1.put("token_endpoint_auth_method","private_key_jwt");
        payload1.put("grant_types",values_1);
        payload1.put("response_types",values_2);
        payload1.put("redirect_uris",values_3);
        payload1.put("id_token_signed_response_alg","PS256");
        payload1.put("request_object_signing_alg","PS256");
        payload1.put("software_id","lhet1nDTHjMkeTFyWGLQSb");
        payload1.put("application_type","web");
        payload1.put("software_statement","eyJhbGciOiJQUzI1NiIsImtpZCI6Ikh6YTl2NWJnREpjT25oY1VaN0JNd2JTTF80TlYwZ1NGdklqYVNYZEMtMWM9IiwidHlwIjoiSldUIn0.eyJpc3MiOiJPcGVuQmFua2luZyBMdGQiLCJpYXQiOjE2MDc0Mzk5NTgsImp0aSI6ImE4OWMzMjlhYTM5YzQ5NGIiLCJzb2Z0d2FyZV9lbnZpcm9ubWVudCI6InNhbmRib3giLCJzb2Z0d2FyZV9tb2RlIjoiVGVzdCIsInNvZnR3YXJlX2lkIjoibGhldDFuRFRIak1rZVRGeVdHTFFTYiIsInNvZnR3YXJlX2NsaWVudF9pZCI6ImxoZXQxbkRUSGpNa2VURnlXR0xRU2IiLCJzb2Z0d2FyZV9jbGllbnRfbmFtZSI6IkRDUiBUZXN0aW5nXzIyIiwic29mdHdhcmVfY2xpZW50X2Rlc2NyaXB0aW9uIjoiRENSIFRlc3Rpbmcgd2l0aCBPQldBQyBhbmQgT0JTRUFMIiwic29mdHdhcmVfdmVyc2lvbiI6My4xLCJzb2Z0d2FyZV9jbGllbnRfdXJpIjoiaHR0cHM6Ly9jYXRlcmFsbGVuLmNvLnVrLyIsInNvZnR3YXJlX3JlZGlyZWN0X3VyaXMiOlsiaHR0cHM6Ly9jYXRlcmFsbGVuLmNvLnVrLyJdLCJzb2Z0d2FyZV9yb2xlcyI6WyJBSVNQIiwiUElTUCIsIkNCUElJIl0sIm9yZ2FuaXNhdGlvbl9jb21wZXRlbnRfYXV0aG9yaXR5X2NsYWltcyI6eyJhdXRob3JpdHlfaWQiOiJGQ0FHQlIiLCJyZWdpc3RyYXRpb25faWQiOiIxNzg3MzciLCJzdGF0dXMiOiJBY3RpdmUiLCJhdXRob3Jpc2F0aW9ucyI6W3sibWVtYmVyX3N0YXRlIjoiR0IiLCJyb2xlcyI6WyJBSVNQIiwiUElTUCIsIkNCUElJIl19LHsibWVtYmVyX3N0YXRlIjoiSUUiLCJyb2xlcyI6WyJBSVNQIiwiUElTUCIsIkNCUElJIl19LHsibWVtYmVyX3N0YXRlIjoiTkwiLCJyb2xlcyI6WyJBSVNQIiwiUElTUCIsIkNCUElJIl19XX0sInNvZnR3YXJlX2xvZ29fdXJpIjoiaHR0cHM6Ly9jYXRlcmFsbGVuLmNvLnVrLyIsIm9yZ19zdGF0dXMiOiJBY3RpdmUiLCJvcmdfaWQiOiIwMDE1ODAwMDAxSFFRclVBQVgiLCJvcmdfbmFtZSI6IkNhdGVyIEFsbGVuIExpbWl0ZWQiLCJvcmdfY29udGFjdHMiOlt7Im5hbWUiOiJUZWNobmljYWwiLCJlbWFpbCI6ImFsZXNzYW5kcm8uZ3JlY29Ac2FudGFuZGVyLmNvLnVrIiwicGhvbmUiOiIwNzU0ODEyNzk2MiIsInR5cGUiOiJUZWNobmljYWwifSx7Im5hbWUiOiJCdXNpbmVzcyIsImVtYWlsIjoiam9uLmhvd2VAc2FudGFuZGVyLmNvLnVrIiwicGhvbmUiOiJKb24gSG93ZSIsInR5cGUiOiJCdXNpbmVzcyJ9XSwib3JnX2p3a3NfZW5kcG9pbnQiOiJodHRwczovL2tleXN0b3JlLm9wZW5iYW5raW5ndGVzdC5vcmcudWsvMDAxNTgwMDAwMUhRUXJVQUFYLzAwMTU4MDAwMDFIUVFyVUFBWC5qd2tzIiwib3JnX2p3a3NfcmV2b2tlZF9lbmRwb2ludCI6Imh0dHBzOi8va2V5c3RvcmUub3BlbmJhbmtpbmd0ZXN0Lm9yZy51ay8wMDE1ODAwMDAxSFFRclVBQVgvcmV2b2tlZC8wMDE1ODAwMDAxSFFRclVBQVguandrcyIsInNvZnR3YXJlX2p3a3NfZW5kcG9pbnQiOiJodHRwczovL2tleXN0b3JlLm9wZW5iYW5raW5ndGVzdC5vcmcudWsvMDAxNTgwMDAwMUhRUXJVQUFYL2xoZXQxbkRUSGpNa2VURnlXR0xRU2IuandrcyIsInNvZnR3YXJlX2p3a3NfcmV2b2tlZF9lbmRwb2ludCI6Imh0dHBzOi8va2V5c3RvcmUub3BlbmJhbmtpbmd0ZXN0Lm9yZy51ay8wMDE1ODAwMDAxSFFRclVBQVgvcmV2b2tlZC9saGV0MW5EVEhqTWtlVEZ5V0dMUVNiLmp3a3MiLCJzb2Z0d2FyZV9wb2xpY3lfdXJpIjoiaHR0cHM6Ly9jYXRlcmFsbGVuLmNvLnVrLyIsInNvZnR3YXJlX3Rvc191cmkiOiJodHRwczovL2NhdGVyYWxsZW4uY28udWsvIiwic29mdHdhcmVfb25fYmVoYWxmX29mX29yZyI6bnVsbH0.umSb5Ny3olpcMoOgUB8zT4wcL-52H5RGk96_7yJtlhxN72IorRDuyDU1gJiei2JOsY1Nw7uF5Inp8_eyp3rWpjPWv-nzquWbjsiTy_YzaEz1-qLSwMyJn3rELQ45GdfWEIRT4c_2rhSJvqVL_Pbg0LF2BBruGdICBGsy-xYbuy4THbPoSNrywXroo3Kp7KxvN3x39RtqAV56kHRVqxUSR1hwgmHFbLkMEg1BFzzJPGwEoQiQBzeFmfNv_i-0ZOSAywZoNYQb5IZDMrZWe7ECFRJz5PZnKTNt7ZaBnINjKcpYtzjEPIGVcy89Ep3MyM7g1cZoMemRn3MjGjIAPXmspw");
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
