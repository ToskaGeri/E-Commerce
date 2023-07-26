package com.ecommerce.Utils;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class KeyGenerator {

    public static KeyPair generateRsaKey(){

        KeyPair keyPair;

        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            keyPair = keyPairGenerator.generateKeyPair();

        }catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return keyPair;
    }
}
