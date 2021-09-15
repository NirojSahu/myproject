//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.santander.api.lib;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import javax.net.ssl.SSLContext;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class RestTemplateAPI {
    RestTemplate restTemplate;
    HttpHeaders headers = new HttpHeaders();

    public RestTemplateAPI() {
    }

    public ResponseEntity postEntity(String url, HttpEntity entity) throws RestClientException {
        ResponseEntity responseEntity = this.restTemplate.postForEntity(url, entity, String.class, new Object[0]);
        return responseEntity;
    }

    public HttpEntity buildHttpEntity(String mediaType, String token, MultiValueMap<String, String> jsonBody) {
        this.headers.set("Authorization", "Bearer " + token);
        if (mediaType == "application/json") {
            this.headers.setContentType(MediaType.APPLICATION_JSON);
        } else if (mediaType == "application/x-www-form-urlencoded") {
            this.headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        }

        return new HttpEntity(jsonBody, this.headers);
    }

    public HttpEntity buildHttpEntity(String mediaType, MultiValueMap<String, String> jsonBody) {
        if (mediaType == "application/json") {
            this.headers.setContentType(MediaType.APPLICATION_JSON);
        } else if (mediaType == "application/x-www-form-urlencoded") {
            this.headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        }

        return new HttpEntity(jsonBody, this.headers);
    }

    public void setHeaders(HashMap<String, String> headerValueMap) {
        headerValueMap.forEach((param, value) -> {
            this.headers.set(param, value);
        });
    }

    public String buildUri(String url, HashMap<String, String> queryParamvalueMap) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
        queryParamvalueMap.forEach((param, value) -> {
            builder.queryParam(param, new Object[]{value});
        });
        return builder.build().encode().toUriString();
    }

    public RestTemplate createTLSMAClient(String password, String proxyHost, String proxyPort, String fileAbsPath) throws IOException, UnrecoverableKeyException, CertificateException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        TrustStrategy acceptingTrustStrategy = (chain, authType) -> {
            return true;
        };
        int port = true;
        System.setProperty("http.proxyHost", proxyHost);
        System.setProperty("http.proxyPort", proxyPort);
        SSLContext sslContext = SSLContextBuilder.create().loadKeyMaterial(ResourceUtils.getFile(fileAbsPath), password.toCharArray(), password.toCharArray()).loadTrustMaterial((KeyStore)null, acceptingTrustStrategy).build();
        SSLConnectionSocketFactory f = new SSLConnectionSocketFactory(sslContext);
        HttpClientBuilder clientBuilder = HttpClientBuilder.create();
        clientBuilder.disableCookieManagement().setSSLSocketFactory(f);
        HttpClient httpClient = clientBuilder.build();
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(30000);
        factory.setConnectTimeout(30000);
        factory.setHttpClient(httpClient);
        RestTemplate tlsmaClientRestTemplate = new RestTemplate(factory);
        return tlsmaClientRestTemplate;
    }

    public RestTemplate createSSLDisabledClient() throws IOException, UnrecoverableKeyException, CertificateException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        TrustStrategy acceptingTrustStrategy = (chain, authType) -> {
            return true;
        };
        SSLContext sslContext = SSLContexts.custom().loadTrustMaterial((KeyStore)null, acceptingTrustStrategy).build();
        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        return restTemplate;
    }
}
