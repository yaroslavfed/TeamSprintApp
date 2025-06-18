package com.teamsprintapp.usermicroservice.models;

public class AuthResponse {
    private final String accessToken;
    private final String refreshToken;

    public AuthResponse(String token, String refreshToken) {
        this.accessToken = token;
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
