package com.w2m.naves.auth.infrastructure.controller;

import com.w2m.naves.spaceship.application.SpaceshipDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Login", description = "Endpoint para iniciar sesion")
public interface IAuthController {

    @Operation(
            summary = "Obtener el token JWT",
            description = "Retorna un token para utilizar en los endpoints de naves",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Operación exitosa",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = SpaceshipDTO.class))),
                    @ApiResponse(responseCode = "500", description = "Bad credentials")
            }
    )
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest loginRequest) throws Exception ;
}
