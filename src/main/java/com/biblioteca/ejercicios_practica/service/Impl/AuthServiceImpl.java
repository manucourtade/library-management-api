package com.biblioteca.ejercicios_practica.service.Impl;

import com.biblioteca.ejercicios_practica.RolesEnum.Role;
import com.biblioteca.ejercicios_practica.dto.LoginRequest;
import com.biblioteca.ejercicios_practica.dto.LoginResponse;
import com.biblioteca.ejercicios_practica.dto.RegisterRequest;
import com.biblioteca.ejercicios_practica.dto.RegisterResponse;
import com.biblioteca.ejercicios_practica.exception.InvalidCredentialsException;
import com.biblioteca.ejercicios_practica.mapper.AuthMapper;
import com.biblioteca.ejercicios_practica.model.User;
import com.biblioteca.ejercicios_practica.repository.UserRepository;
import com.biblioteca.ejercicios_practica.securityConfig.JwtService;
import com.biblioteca.ejercicios_practica.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthMapper authMapper;
    private final JwtService jwtService;

    @Override
    public RegisterResponse registerRequest (RegisterRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.username())) {
            throw new RuntimeException("This username is already exists! " + registerRequest.username());
        }
        String hashEncoder = passwordEncoder.encode(registerRequest.password());
        User user = authMapper.toUser(registerRequest);
        user.setRole(Role.MEMBER);
        user.setPassword(hashEncoder);
        User userRegister = userRepository.save(user);
        log.info("User created with successful! {}", userRegister.getId());


        return authMapper.toRegisterResponse(userRegister);
    }

    @Override
    public LoginResponse loginRequest(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.username())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid credentials"));

        if (!passwordEncoder.matches(loginRequest.password(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid credentials");
        }
        String token = jwtService.generateToken(user);
        return new LoginResponse(user.getId(), user.getRole(), user.getUsername(), token);
    }


}
