package com.w2m.naves.spaceship.infrastructure.controller;

import com.w2m.naves.spaceship.application.SpaceshipDTO;
import com.w2m.naves.spaceship.domain.EOrigin;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Tag(name = "Naves Espaciales", description = "Endpoints para el mantenimiento CRUD de naves espaciales de series y películas")
public interface ISpaceshipController {


    @Operation(
            summary = "Obtener todas las naves espaciales",
            description = "Retorna una lista paginada de todas las naves espaciales disponibles.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Operación exitosa",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = SpaceshipDTO.class))),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            }
    )
    public Page<SpaceshipDTO> getAllSpaceships(Pageable pageable) ;


    @Operation(
            summary = "Obtener una nave espacial por ID",
            description = "Retorna los detalles de una nave espacial específica según su ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Operación exitosa",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = SpaceshipDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Nave no encontrada con ID: {id}"),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            }
    )
    public SpaceshipDTO getSpaceshipById( @Parameter(description = "ID de la nave espacial", required = true)  Long id);


    @Operation(
            summary = "Buscar naves espaciales por nombre",
            description = "Retorna una lista de naves espaciales cuyo nombre contiene el valor proporcionado.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Operación exitosa",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = SpaceshipDTO.class))),
                    @ApiResponse(responseCode = "404", description = "No se encontraron naves"),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            }
    )
    public List<SpaceshipDTO> searchSpaceshipsByName( @Parameter(description = "Nombre o parte del nombre de la nave espacial", required = true)  String name );


    @Operation(
            summary = "Filtrar naves espaciales por origen",
            description = "Retorna una lista de naves espaciales filtradas por su origen (PELICULA o SERIE).",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Operación exitosa",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = SpaceshipDTO.class))),
                    @ApiResponse(responseCode = "404", description = "No se encontraron naves"),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            }
    )
    public List<SpaceshipDTO> getSpaceshipByOrigin ( @Parameter(description = "Origen de la nave espacial (PELICULA o SERIE)", required = true) EOrigin origin);

    @Operation(
            summary = "Crear una nueva nave espacial",
            description = "Crea una nueva nave espacial y la guarda en la base de datos.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Nave creada exitosamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = SpaceshipDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            }
    )
    public SpaceshipDTO createSpaceship( @Parameter(description = "Datos de la nueva nave espacial", required = true) SpaceshipDTO spaceship) ;


    @Operation(
            summary = "Actualizar una nave espacial existente",
            description = "Actualiza los datos de una nave espacial existente según su ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Nave actualizada exitosamente",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = SpaceshipDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
                    @ApiResponse(responseCode = "404", description = "Nave no encontrada"),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            }
    )
    public SpaceshipDTO updateSpaceship(  @Parameter(description = "ID de la nave espacial a actualizar", required = true)  Long id,
                                          @Parameter(description = "Datos actualizados de la nave espacial", required = true)  SpaceshipDTO spaceship) ;

    @Operation(
            summary = "Eliminar una nave espacial",
            description = "Elimina una nave espacial existente según su ID.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Nave eliminada exitosamente"),
                    @ApiResponse(responseCode = "404", description = "Nave no encontrada"),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
            }
    )
    public void deleteSpaceship( @Parameter(description = "ID de la nave espacial a eliminar", required = true) Long id) ;
}
