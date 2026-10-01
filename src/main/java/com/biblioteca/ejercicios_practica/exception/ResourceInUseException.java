package com.biblioteca.ejercicios_practica.exception;

public class ResourceInUseException extends RuntimeException {
    public ResourceInUseException(String resource, Object id, String reason) {
        super(String.format("%s with id '%s' cannot be deleted: %s", resource, id, reason));
    }
}
