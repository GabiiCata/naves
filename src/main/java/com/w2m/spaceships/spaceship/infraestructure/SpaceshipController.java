package com.w2m.spaceships.spaceship.infraestructure;

import com.w2m.spaceships.spaceship.application.SpaceshipService;
import com.w2m.spaceships.spaceship.domain.EOrigin;
import com.w2m.spaceships.spaceship.domain.Spaceship;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/naves")
@AllArgsConstructor
public class SpaceshipController {

    private final SpaceshipService spaceshipService;

    @GetMapping
    public Page<Spaceship> getAllSpaceships(Pageable pageable) {
        return spaceshipService.getAllSpaceships(pageable);
    }

    @GetMapping("/{id}")
    public Spaceship getSpaceshipById(@PathVariable Long id) {
        return spaceshipService.getSpaceshipById(id)
                .orElseThrow(() -> new RuntimeException("Nave no encontrada con ID: " + id));
    }

    @GetMapping("/search")
    public List<Spaceship> searchSpaceshipsByName(@RequestParam String name) {
        return spaceshipService.searchSpaceshipsByName(name);
    }

    @GetMapping("/origin/{origin}")
    public List<Spaceship> getSpaceshipByOrigin(@PathVariable EOrigin origin) {
        return spaceshipService.getSpaceshipsByOrigin(origin);
    }

    @PostMapping
    public Spaceship createSpaceship(@RequestBody Spaceship nave) {
        return spaceshipService.createSpaceship(nave);
    }

    @PutMapping("/{id}")
    public Spaceship updateSpaceship(@PathVariable Long id, @RequestBody Spaceship naveDetails) {
        return spaceshipService.updateSpaceship(id, naveDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteSpaceship(@PathVariable Long id) {
        spaceshipService.deleteSpaceship(id);
    }
}
