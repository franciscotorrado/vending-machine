package com.machines.vending.infrastructure.persistence.session;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TokenServer {
    private final static Map<Integer, String> sessions = Collections.synchronizedMap(new HashMap<>());

    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

    public static Optional<String> getToken(Integer userId) {
        if (sessions.containsKey(userId)) {
            return Optional.empty();
        }

        byte[] randomBytes = new byte[32];
        secureRandom.nextBytes(randomBytes);
        final String newToken = base64Encoder.encodeToString(randomBytes);
        sessions.put(userId, newToken);
        return Optional.of(newToken);
    }

    public static Optional<Integer> getUserId(String token) {
        return sessions.entrySet()
                .stream()
                .filter(s -> s.getValue().equals(token))
                .findFirst()
                .map(Map.Entry::getKey);
    }

    public static void removeToken(int userId) {
        sessions.remove(userId);
    }
}
