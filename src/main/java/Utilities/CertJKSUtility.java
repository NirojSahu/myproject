package Utilities;

import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.specification.RequestSpecification;

//import javax.net.ssl.SSLSocketFactory;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.*;

import org.apache.http.conn.ssl.SSLSocketFactory;

public class CertJKSUtility {
    public static RequestSpecification clintCertSpecification(
            String keyStorePath, String keyStorePass) throws UnrecoverableKeyException, FileNotFoundException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        return clintCertSpecification(keyStorePath, keyStorePass,null,null);
    }


    public static RequestSpecification clintCertSpecification(
            String kyeStorePath, String keyStorePass, String trustStorePath, String trustStorePass   ) throws UnrecoverableKeyException, FileNotFoundException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        return clintCertSpecification(
                kyeStorePath,
                keyStorePass,
                KeyStore.getDefaultType(),
                trustStorePath,
                trustStorePass,
                KeyStore.getDefaultType()
                );
    }
    @Deprecated
    public static RequestSpecification clintCertSpecification(String keyStorePath,
                                                              String keyStorePass,
                                                              String keyStoreType,
                                                              String trustStorePath,
                                                              String trustStorePass,
                                                              String trustStoreType) throws FileNotFoundException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        KeyStore keyStore=loadKeyStore(keyStorePath,keyStorePass.toCharArray(),keyStoreType);
        SSLSocketFactory clintAuthFactory=new SSLSocketFactory(keyStore,keyStorePass);
        if(null !=trustStorePath){
            KeyStore trustStore=
                    loadKeyStore(keyStorePath,trustStorePass.toCharArray(),trustStoreType);
            clintAuthFactory =new SSLSocketFactory(keyStore,keyStorePass,trustStore);
        }
        SSLConfig sslConfig=
                RestAssuredConfig.config().getSSLConfig().with().sslSocketFactory(clintAuthFactory);
        RestAssuredConfig config= RestAssured.config().sslConfig(sslConfig);
        return RestAssured.given().config(config);
    }
    public static KeyStore loadKeyStore(String path, char[] password,String storeType) throws FileNotFoundException {
        KeyStore keyStore;
        try{
            keyStore=KeyStore.getInstance(storeType);
            keyStore.load(new FileInputStream(path),password);
        }catch (Exception ex){
            throw new RuntimeException("Error While Executing the KyeStore:", ex);
        }
        return keyStore;
    }



}
