package com.teamsprintapp.usermicroservice.controllers;

import com.teamsprintapp.usermicroservice.models.UserEntity;
import com.teamsprintapp.usermicroservice.models.AuthResponse;
import com.teamsprintapp.usermicroservice.models.LoginRequest;
import com.teamsprintapp.usermicroservice.models.RegisterRequest;
import com.teamsprintapp.usermicroservice.services.authorization.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@Tag(name = "Authorization controller", description = "API для работы с авторизацией пользователей")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authService.register(registerRequest));
    }

    @Operation(summary = "Авторизация пользователя")
    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.authenticate(loginRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refreshToken(@RequestBody String refreshToken) {
        // тут должна быть логика поиска пользователя по refreshToken
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        // обнулить refresh токен, отметить как loggedOut, и т.д.
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<UserEntity> me(Authentication authentication) {
        String login = authentication.name();
        UserEntity user = authService.getUserByInfo(login); // реализуй этот метод
        return ResponseEntity.ok(user);
    }
}
