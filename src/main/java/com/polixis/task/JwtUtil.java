package com.polixis.task;

import io.smallrye.jwt.build.Jwt;

public class JwtUtil {
    public static String generateToken(String username) {
        return Jwt.issuer("quarkus-chat-app")
                .subject(username)
                .expiresAt(System.currentTimeMillis() / 1000 + 3600) // 1-hour expiry
                .sign();
    }
}
