package com.offlinepay.link.crypto;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

import static com.google.common.hash.Hashing.sha256;


public class HashUtil {

    public static byte[] salt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    public static String hash(String clearText) {
        byte[] salt = salt();
        byte[] clear = clearText.getBytes(StandardCharsets.UTF_8);
        byte[] destination = new byte[salt().length + clear.length];
        System.arraycopy(salt, 0, destination, 0, salt.length);
        System.arraycopy(clear, 0, destination, salt.length, clear.length);
        return sha256().hashBytes(destination).toString();
    }
}
