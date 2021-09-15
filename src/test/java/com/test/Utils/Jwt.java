package com.test.Utils;

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

public class Jwt {

    public static PublicKey get_publicKey(String fileName) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] kyeBytes= Files.readAllBytes(Paths.get(fileName));
        X509EncodedKeySpec spec= new X509EncodedKeySpec(kyeBytes);
        KeyFactory kf=KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }

    public static PrivateKey get_privateKey(String fileName) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] kyeBytes= Files.readAllBytes(Paths.get(fileName));
        PKCS8EncodedKeySpec spec= new PKCS8EncodedKeySpec(kyeBytes);
        KeyFactory kf=KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);
    }
}