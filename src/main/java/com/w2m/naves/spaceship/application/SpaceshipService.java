package com.w2m.naves.spaceship.application;

import com.w2m.naves.spaceship.domain.EOrigin;
import com.w2m.naves.spaceship.domain.Spaceship;
import com.w2m.naves.spaceship.infrastructure.repository.SpaceshipRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SpaceshipService {

    private final SpaceshipRepository spaceshipRepository;
    private final ModelMapper modelMapper;

    public Page<SpaceshipDTO> getAllSpaceships(Pageable pageable) {
        return spaceshipRepository.findAll(pageable)
                .map(spaceship -> modelMapper.map(spaceship, SpaceshipDTO.class));
    }

    public Optional<SpaceshipDTO> getSpaceshipById(Long id) {
        return spaceshipRepository.findById(id)
                .map(spaceship -> modelMapper.map(spaceship, SpaceshipDTO.class));
    }


    public List<SpaceshipDTO> searchSpaceshipsByName(String name) {
        return spaceshipRepository.findByNameContaining(name).stream()
                .map(spaceship -> modelMapper.map(spaceship, SpaceshipDTO.class))
                .toList();
    }


    public List<SpaceshipDTO> getSpaceshipsByOrigin(EOrigin origin) {
        return spaceshipRepository.findByOrigin(origin).stream()
                .map(spaceship -> modelMapper.map(spaceship, SpaceshipDTO.class)).toList();
    }

    public SpaceshipDTO createSpaceship(SpaceshipDTO spaceship) {
        Spaceship spaceshipDb =  spaceshipRepository.save(modelMapper.map(spaceship,Spaceship.class));
        return modelMapper.map(spaceshipDb,SpaceshipDTO.class);
    }

    public SpaceshipDTO updateSpaceship(Long id, SpaceshipDTO spaceshipDetails) {
        return spaceshipRepository.findById(id).map(spaceship -> {
            if ( spaceshipDetails.getName()!= null)
                spaceship.setName(spaceshipDetails.getName());
            if ( spaceshipDetails.getDescription() !=  null )
                spaceship.setDescription(spaceshipDetails.getDescription());
            if (spaceshipDetails.getOrigin() != null)
                spaceship.setOrigin(spaceshipDetails.getOrigin());
            Spaceship updatedSpaceship = spaceshipRepository.saveAndFlush(spaceship);
            return modelMapper.map(updatedSpaceship, SpaceshipDTO.class);
        }).orElseThrow(() -> new RuntimeException("Nave no encontrada con ID: " + id));
    }


    public void deleteSpaceship(Long id) {
        spaceshipRepository.deleteById(id);
    }

}
