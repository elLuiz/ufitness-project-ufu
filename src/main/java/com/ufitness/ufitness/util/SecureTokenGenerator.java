package com.ufitness.ufitness.util;

import java.security.SecureRandom;
import java.util.Base64;

public class SecureTokenGenerator {
    private SecureTokenGenerator() {}

    public static String generateSecureToken() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] bytes = new byte[32];
        secureRandom.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }
}
