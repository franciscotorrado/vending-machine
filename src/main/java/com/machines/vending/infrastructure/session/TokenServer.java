package com.machines.vending.infrastructure.session;

import com.machines.vending.domain.models.Role;
import com.machines.vending.domain.models.security.UserSessionDetails;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class TokenServer {
    private final static Map<String, UserSessionDetails> sessions = new ConcurrentHashMap<>();

    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

    public static Optional<String> getToken(Integer userId,
                                            Role role) {

        if (isAlreadyLogged(userId)) {
            return Optional.empty();
        }

        byte[] randomBytes = new byte[32];
        secureRandom.nextBytes(randomBytes);
        final String token = base64Encoder.encodeToString(randomBytes);
        final UserSessionDetails userSessionDetails = new UserSessionDetails(userId, role);
        sessions.put(token, userSessionDetails);
        return Optional.of(token);
    }

    public static Optional<UserSessionDetails> getUserSessionDetails(String token) {
        return sessions
                .entrySet()
                .stream()
                .filter(s -> s.getKey().equals(token))
                .findFirst()
                .map(Map.Entry::getValue);
    }

    public static void removeToken(Integer userId) {
        for (Map.Entry<String, UserSessionDetails> session : sessions.entrySet()) {
            if (session.getValue().getId().equals(userId)) {
                sessions.remove(session.getKey());
            }
        }
    }

    private static boolean isAlreadyLogged(final Integer userId) {
        return sessions.entrySet()
                .stream()
                .anyMatch(s -> s.getValue().getId().equals(userId));
    }
}
