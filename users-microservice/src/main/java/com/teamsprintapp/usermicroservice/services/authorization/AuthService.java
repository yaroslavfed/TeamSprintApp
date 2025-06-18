package com.teamsprintapp.usermicroservice.services.authorization;

import com.teamsprintapp.usermicroservice.models.UserEntity;
import com.teamsprintapp.usermicroservice.models.AuthResponse;
import com.teamsprintapp.usermicroservice.models.LoginRequest;
import com.teamsprintapp.usermicroservice.models.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest registerRequest);

    AuthResponse authenticate(LoginRequest loginRequest);

    UserEntity getUserByInfo(String login);
}
