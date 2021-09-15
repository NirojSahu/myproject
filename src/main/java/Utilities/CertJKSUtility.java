package Utilities;

import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.specification.RequestSpecification;
import org.apache.http.conn.ssl.SSLSocketFactory;

import java.io.FileInputStream;
import java.security.*;

public class CertJKSUtility {

    public static RequestSpecification clientCertSpecification(
            String keyStorePath, String keyStorePass)
            throws UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException,
            KeyManagementException {
        return clientCertSpecification(keyStorePath, keyStorePass, null, null);
    }

    public static RequestSpecification clientCertSpecification(
            String keyStorePath, String keyStorePass, String trustStorePath, String trustStorePass)
            throws UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException,
            KeyManagementException {
        return clientCertSpecification(
                keyStorePath,
                keyStorePass,
                KeyStore.getDefaultType(),
                trustStorePath,
                trustStorePass,
                KeyStore.getDefaultType());
    }

    @Deprecated
    public static RequestSpecification clientCertSpecification(
            String keyStorePath,
            String keyStorePass,
            String keyStoreType,
            String trustStorePath,
            String trustStorePass,
            String trustStoreType)
            throws UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException,
            KeyManagementException {

        KeyStore keyStore = loadKeyStore(keyStorePath, keyStorePass.toCharArray(), keyStoreType);
        SSLSocketFactory clientAuthFactory = new SSLSocketFactory(keyStore, keyStorePass);
        if (null != trustStorePath) {
            KeyStore trustStore =
                    loadKeyStore(trustStorePath, trustStorePass.toCharArray(), trustStoreType);
            clientAuthFactory = new SSLSocketFactory(keyStore, keyStorePass, trustStore);
        }

        SSLConfig sslConfig =
                RestAssuredConfig.config().getSSLConfig().with().sslSocketFactory(clientAuthFactory);
        RestAssuredConfig config = RestAssured.config().sslConfig(sslConfig);

        return RestAssured.given().config(config);
    }

    public static KeyStore loadKeyStore(String path, char[] password, String storeType) {
        KeyStore keyStore;
        try {
            keyStore = KeyStore.getInstance(storeType);
            keyStore.load(new FileInputStream(path), password);
        } catch (Exception ex) {
            throw new RuntimeException("Error while extracting the keystore", ex);
        }
        return keyStore;
    }

    public static KeyStore loadKeyStore(String path, char[] password) {
        return loadKeyStore(path, password, KeyStore.getDefaultType());
    }
}
