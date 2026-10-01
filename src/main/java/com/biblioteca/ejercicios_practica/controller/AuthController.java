package com.biblioteca.ejercicios_practica.controller;

import com.biblioteca.ejercicios_practica.dto.LoginRequest;
import com.biblioteca.ejercicios_practica.dto.LoginResponse;
import com.biblioteca.ejercicios_practica.dto.RegisterRequest;
import com.biblioteca.ejercicios_practica.dto.RegisterResponse;
import com.biblioteca.ejercicios_practica.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterResponse userRegister (@Valid @RequestBody RegisterRequest registerRequest) {
        return authService.registerRequest(registerRequest);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse userLogin (@Valid @RequestBody LoginRequest loginRequest) {
        return authService.loginRequest(loginRequest);
    }
}
