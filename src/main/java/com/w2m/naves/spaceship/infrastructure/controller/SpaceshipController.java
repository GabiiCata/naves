package com.w2m.naves.spaceship.infrastructure.controller;

import com.w2m.naves.spaceship.application.SpaceshipDTO;
import com.w2m.naves.spaceship.application.SpaceshipService;
import com.w2m.naves.spaceship.domain.EOrigin;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/naves")
@AllArgsConstructor
public class SpaceshipController implements ISpaceshipController {

    private final SpaceshipService spaceshipService;

    @GetMapping
    public Page<SpaceshipDTO> getAllSpaceships(Pageable pageable) {
        return spaceshipService.getAllSpaceships(pageable);
    }

    @GetMapping("/{id}")
    public SpaceshipDTO getSpaceshipById( @PathVariable Long id) {
        return spaceshipService.getSpaceshipById(id);
    }

    @GetMapping("/search")
    public List<SpaceshipDTO> searchSpaceshipsByName(  @RequestParam String name) {
        return spaceshipService.searchSpaceshipsByName(name);
    }

    @GetMapping("/origin/{origin}")
    public List<SpaceshipDTO> getSpaceshipByOrigin( @PathVariable EOrigin origin) {
        return spaceshipService.getSpaceshipsByOrigin(origin);
    }

    @PostMapping
    public SpaceshipDTO createSpaceship(@RequestBody SpaceshipDTO spaceship) {
        return spaceshipService.createSpaceship(spaceship);
    }

    @PutMapping("/{id}")
    public SpaceshipDTO updateSpaceship( @PathVariable Long id, @RequestBody SpaceshipDTO spaceship) {
        return spaceshipService.updateSpaceship(id, spaceship);
    }

    @DeleteMapping("/{id}")
    public void deleteSpaceship( @PathVariable Long id) {
        spaceshipService.deleteSpaceship(id);
    }
}