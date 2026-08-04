package com.biblioteca.ejercicios_practica.service;

import com.biblioteca.ejercicios_practica.dto.LoginRequest;
import com.biblioteca.ejercicios_practica.dto.LoginResponse;
import com.biblioteca.ejercicios_practica.dto.RegisterRequest;
import com.biblioteca.ejercicios_practica.dto.RegisterResponse;

public interface AuthService {
    RegisterResponse registerRequest (RegisterRequest registerRequest);
    LoginResponse loginRequest (LoginRequest loginRequest);
}
