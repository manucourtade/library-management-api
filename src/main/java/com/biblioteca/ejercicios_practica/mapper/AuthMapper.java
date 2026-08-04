package com.biblioteca.ejercicios_practica.mapper;

import com.biblioteca.ejercicios_practica.dto.LoginRequest;
import com.biblioteca.ejercicios_practica.dto.LoginResponse;
import com.biblioteca.ejercicios_practica.dto.RegisterRequest;
import com.biblioteca.ejercicios_practica.dto.RegisterResponse;
import com.biblioteca.ejercicios_practica.model.User;
import lombok.extern.java.Log;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthMapper {
    User toUser(RegisterRequest registerRequest);
    RegisterResponse toRegisterResponse(User user);
    LoginResponse toLoginResponse(User user);
}
