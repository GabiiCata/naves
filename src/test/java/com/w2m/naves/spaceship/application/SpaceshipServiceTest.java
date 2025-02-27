package com.w2m.naves.spaceship.application;

import com.w2m.naves.mq.SpaceShipProducer;
import com.w2m.naves.spaceship.domain.EOrigin;
import com.w2m.naves.spaceship.domain.Spaceship;
import com.w2m.naves.spaceship.infrastructure.exception.SpaceshipNotFoundException;
import com.w2m.naves.spaceship.infrastructure.repository.SpaceshipRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SpaceshipServiceTest {

    @Mock
    private SpaceshipRepository spaceshipRepository;

    @Mock
    private SpaceShipProducer spaceShipProducer;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private SpaceshipService spaceshipService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Tag("unit")
    @DisplayName("Test: Obtener todas las naves espaciales")
    void testGetAllSpaceships() {
        // Precondiciones
        Pageable pageable = mock(Pageable.class);
        Spaceship spaceship = new Spaceship();
        spaceship.setSpaceshipId(1L);
        spaceship.setName("Millennium Falcon");

        Page<Spaceship> page = new PageImpl<>(List.of(spaceship));
        when(spaceshipRepository.findAll(pageable)).thenReturn(page);

        SpaceshipDTO spaceshipDTO = new SpaceshipDTO();
        spaceshipDTO.setName("Millennium Falcon");
        when(modelMapper.map(spaceship, SpaceshipDTO.class)).thenReturn(spaceshipDTO);

        // Accion de test
        Page<SpaceshipDTO> result = spaceshipService.getAllSpaceships(pageable);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals("Millennium Falcon", result.getContent().getFirst().getName());
        verify(spaceshipRepository, times(1)).findAll(pageable);
    }

    @Test
    @Tag("unit")
    @DisplayName("Test: Obtener nave espacial por ID (éxito)")
    void testGetSpaceshipById_Success() {
        // Precondiciones
        Long id = 1L;
        Spaceship spaceship = new Spaceship();
        spaceship.setSpaceshipId(id);
        spaceship.setName("X-Wing");

        when(spaceshipRepository.findById(id)).thenReturn(Optional.of(spaceship));

        SpaceshipDTO spaceshipDTO = new SpaceshipDTO();
        spaceshipDTO.setName("X-Wing");
        when(modelMapper.map(spaceship, SpaceshipDTO.class)).thenReturn(spaceshipDTO);

        // Accion de test
        SpaceshipDTO result = spaceshipService.getSpaceshipById(id);

        // Assert
        assertNotNull(result);
        assertEquals("X-Wing", result.getName());
        verify(spaceshipRepository, times(1)).findById(id);
    }

    @Test
    @Tag("unit")
    @DisplayName("Test: Obtener nave espacial por ID (no encontrada)")
    void testGetSpaceshipById_NotFound() {
        // Precondiciones
        Long id = 1L;
        when(spaceshipRepository.findById(id)).thenReturn(Optional.empty());

        // Accion de test & Assert
        SpaceshipNotFoundException exception = assertThrows(SpaceshipNotFoundException.class, () -> {
            spaceshipService.getSpaceshipById(id);
        });
        assertEquals( "Spaceship with ID 1 not found", exception.getReason());
        verify(spaceshipRepository, times(1)).findById(id);
    }

    @Test
    @Tag("unit")
    @DisplayName("Test: Buscar naves espaciales por nombre")
    void testSearchSpaceshipsByName() {
        // Precondiciones
        String name = "Falcon";
        Spaceship spaceship = new Spaceship();
        spaceship.setSpaceshipId(1L);
        spaceship.setName("Millennium Falcon");

        when(spaceshipRepository.findByNameContainingIgnoreCase(name)).thenReturn(Optional.of(List.of(spaceship)));

        SpaceshipDTO spaceshipDTO = new SpaceshipDTO();
        spaceshipDTO.setName("Millennium Falcon");
        when(modelMapper.map(spaceship, SpaceshipDTO.class)).thenReturn(spaceshipDTO);

        // Accion de test
        List<SpaceshipDTO> result = spaceshipService.searchSpaceshipsByName(name);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        System.out.println("pruebaimpresionante-> " + result);
        System.out.println("pruebaimpresionante-> " + result.size());
        assertEquals("Millennium Falcon", result.getFirst().getName());
        verify(spaceshipRepository, times(1)).findByNameContainingIgnoreCase(name);
    }

    @Test
    @Tag("unit")
    @DisplayName("Test: Buscar naves espaciales por origen")
    void testGetSpaceshipsByOrigin() {
        // Precondiciones
        EOrigin origin = EOrigin.PELICULA;
        Spaceship spaceship = new Spaceship();
        spaceship.setSpaceshipId(1L);
        spaceship.setOrigin(origin);

        when(spaceshipRepository.findByOrigin(origin)).thenReturn(Optional.of(List.of(spaceship)));

        SpaceshipDTO spaceshipDTO = new SpaceshipDTO();
        spaceshipDTO.setOrigin(origin);
        when(modelMapper.map(spaceship, SpaceshipDTO.class)).thenReturn(spaceshipDTO);

        // Accion de test
        List<SpaceshipDTO> result = spaceshipService.getSpaceshipsByOrigin(origin);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(EOrigin.PELICULA, result.getFirst().getOrigin());
        verify(spaceshipRepository, times(1)).findByOrigin(origin);
    }

    @Test
    @Tag("unit")
    @DisplayName("Test: Crean nueva nave")
    void testCreateSpaceship() {
        // Precondiciones
        SpaceshipDTO spaceshipDTO = new SpaceshipDTO();
        spaceshipDTO.setName("TIE Fighter");

        Spaceship spaceship = new Spaceship();
        spaceship.setSpaceshipId(1L);
        spaceship.setName("TIE Fighter");

        when(modelMapper.map(spaceshipDTO, Spaceship.class)).thenReturn(spaceship);
        when(spaceshipRepository.save(spaceship)).thenReturn(spaceship);
        when(modelMapper.map(spaceship, SpaceshipDTO.class)).thenReturn(spaceshipDTO);

        // Accion de test
        SpaceshipDTO result = spaceshipService.createSpaceship(spaceshipDTO);

        // Assert
        assertNotNull(result);
        assertEquals("TIE Fighter", result.getName());
        verify(spaceShipProducer, times(1)).sendSpaceShipMessage("nave 1 creada");
    }

    @Test
    @Tag("unit")
    @DisplayName("Test: Actualizar una nave")
    void testUpdateSpaceship_Success() {
        // Precondiciones
        Long id = 1L;
        SpaceshipDTO spaceshipDetails = new SpaceshipDTO();
        spaceshipDetails.setName("Updated Falcon");

        Spaceship existingSpaceship = new Spaceship();
        existingSpaceship.setSpaceshipId(id);
        existingSpaceship.setName("Millennium Falcon");

        when(spaceshipRepository.findById(id)).thenReturn(Optional.of(existingSpaceship));
        when(spaceshipRepository.save(existingSpaceship)).thenReturn(existingSpaceship);

        SpaceshipDTO updatedSpaceshipDTO = new SpaceshipDTO();
        updatedSpaceshipDTO.setName("Updated Falcon");
        when(modelMapper.map(existingSpaceship, SpaceshipDTO.class)).thenReturn(updatedSpaceshipDTO);

        // Accion de test
        SpaceshipDTO result = spaceshipService.updateSpaceship(id, spaceshipDetails);

        // Assert
        assertNotNull(result);
        assertEquals("Updated Falcon", result.getName());
        verify(spaceShipProducer, times(1)).sendSpaceShipMessage("nave 1 actualizada");
    }

    @Test
    @Tag("unit")
    @DisplayName("Test: Actualizar una nave (no encontrada)")
    void testUpdateSpaceship_NotFound() {
        // Precondiciones
        Long id = 1L;
        SpaceshipDTO spaceshipDetails = new SpaceshipDTO();
        spaceshipDetails.setName("Updated Falcon");

        when(spaceshipRepository.findById(id)).thenReturn(Optional.empty());

        // Accion de test & Assert
        SpaceshipNotFoundException exception = assertThrows(SpaceshipNotFoundException.class, () -> {
            spaceshipService.updateSpaceship(id, spaceshipDetails);
        });
        assertEquals("Spaceship with ID 1 not found", exception.getReason());
        verify(spaceshipRepository, times(1)).findById(id);
    }

    @Test
    @Tag("unit")
    @DisplayName("Test: Eliminar una nave")
    void testDeleteSpaceship() {
        // Precondiciones
        Long id = 1L;

        // Accion de test
        spaceshipService.deleteSpaceship(id);

        // Assert
        verify(spaceshipRepository, times(1)).deleteById(id);
        verify(spaceShipProducer, times(1)).sendSpaceShipMessage("nave 1 eliminada");
    }
}