package com.teamsprintapp.usermicroservice.services.authorization;

import com.teamsprintapp.usermicroservice.models.UserEntity;
import com.teamsprintapp.usermicroservice.enums.Role;
import com.teamsprintapp.usermicroservice.models.AuthResponse;
import com.teamsprintapp.usermicroservice.models.LoginRequest;
import com.teamsprintapp.usermicroservice.models.RegisterRequest;
import com.teamsprintapp.usermicroservice.repositories.UserRepository;
import com.teamsprintapp.usermicroservice.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthResponse register(RegisterRequest registerRequest) {
        UserEntity user = new UserEntity();
        user.setLogin(registerRequest.getLogin());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setFirstname(registerRequest.getFirstName());
        user.setLastname(registerRequest.getLastName());
        user.setRole(registerRequest.getRole());
        userRepository.save(user);

        String jwt = jwtTokenProvider.generateToken(user);
        String refreshToken = UUID.randomUUID().toString();

        return new AuthResponse(jwt, refreshToken);
    }

    @Override
    public AuthResponse authenticate(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getLogin(),
                        loginRequest.getPassword()
                )
        );

        UserEntity user = userRepository
                .findByLogin(loginRequest.getLogin())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String jwt = jwtTokenProvider.generateToken(user);
        String refreshToken = UUID.randomUUID().toString();

        return new AuthResponse(jwt, refreshToken);
    }

    @Override
    public UserEntity getUserByInfo(String login) {
        return userRepository
                .findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
