package com.w2m.naves.spaceship.infrastructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class SpaceshipNotFoundException extends ResponseStatusException {
    public SpaceshipNotFoundException(Long id) {
        super(HttpStatus.NOT_FOUND, "Nave no encontrada con ID: " + id);
    }
}
