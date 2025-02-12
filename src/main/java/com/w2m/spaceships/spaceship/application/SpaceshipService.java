package com.w2m.spaceships.spaceship.application;

import com.w2m.spaceships.spaceship.domain.EOrigin;
import com.w2m.spaceships.spaceship.domain.Spaceship;
import com.w2m.spaceships.spaceship.infraestructure.SpaceshipRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SpaceshipService {

    private final SpaceshipRepository spaceshipRepository;

    public Page<Spaceship> getAllSpaceships(Pageable pageable) {
        return spaceshipRepository.findAll(pageable);
    }

    public Optional<Spaceship> getSpaceshipById(Long id) {
        return spaceshipRepository.findById(id);
    }

    public List<Spaceship> searchSpaceshipsByName(String name) {
        return spaceshipRepository.findByNameContaining(name);
    }

    public List<Spaceship> getSpaceshipsByOrigin(EOrigin origin) {
        return spaceshipRepository.findByOrigin(origin);
    }

    public Spaceship createSpaceship(Spaceship spaceship) {
        return spaceshipRepository.save(spaceship);
    }

    public Spaceship updateSpaceship(Long id, Spaceship spaceshipDetails) {
        return spaceshipRepository.findById(id).map(spaceship -> {
            spaceship.setName(spaceshipDetails.getName());
            spaceship.setDescription(spaceshipDetails.getDescription());
            return spaceshipRepository.saveAndFlush(spaceship);
        }).orElseThrow(() -> new RuntimeException("Nave no encontrada con ID: " + id));
    }

    public void deleteSpaceship(Long id) {
        spaceshipRepository.deleteById(id);
    }

}
