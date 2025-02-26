package com.w2m.naves.spaceship.application;

import com.w2m.naves.mq.SpaceShipProducer;
import com.w2m.naves.spaceship.domain.EOrigin;
import com.w2m.naves.spaceship.domain.Spaceship;
import com.w2m.naves.spaceship.infrastructure.exception.SpaceshipNotFoundException;
import com.w2m.naves.spaceship.infrastructure.repository.SpaceshipRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SpaceshipService {

    private final SpaceshipRepository spaceshipRepository;
    private final SpaceShipProducer spaceShipProducer;

    private final ModelMapper modelMapper;

    @Cacheable(value = "spaceships")
    public Page<SpaceshipDTO> getAllSpaceships(Pageable pageable) {
        return spaceshipRepository.findAll(pageable)
                .map(spaceship -> modelMapper.map(spaceship, SpaceshipDTO.class));
    }

    @Cacheable(value = "spaceships", key = "#id")
    public SpaceshipDTO getSpaceshipById(Long id) {
        return spaceshipRepository.findById(id)
                .map(spaceship -> modelMapper.map(spaceship, SpaceshipDTO.class))
                .orElseThrow(() -> new SpaceshipNotFoundException(id));
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
        spaceShipProducer.sendSpaceShipMessage( "nave "+spaceshipDb.getSpaceshipId()+" creada" );
        return modelMapper.map(spaceshipDb,SpaceshipDTO.class);
    }

    @CachePut(value = "spaceships", key = "#id")
    @CacheEvict(value = "spaceships", allEntries = true)
    public SpaceshipDTO updateSpaceship(Long id, SpaceshipDTO spaceshipDetails) {
        Optional<Spaceship> spaceshipOptional = spaceshipRepository.findById(id);
        if (spaceshipOptional.isEmpty())
            throw new SpaceshipNotFoundException(id);

        Spaceship spaceship = spaceshipOptional.get();

        if (spaceshipDetails.getName() != null)
            spaceship.setName(spaceshipDetails.getName());

        if (spaceshipDetails.getDescription() != null)
            spaceship.setDescription(spaceshipDetails.getDescription());

        if (spaceshipDetails.getOrigin() != null)
            spaceship.setOrigin(spaceshipDetails.getOrigin());

        Spaceship updatedSpaceship = spaceshipRepository.save(spaceship);

        spaceShipProducer.sendSpaceShipMessage("nave " + updatedSpaceship.getSpaceshipId() + " actualizada");

        return modelMapper.map(updatedSpaceship, SpaceshipDTO.class);
    }


    @CacheEvict(value = "spaceships", key = "#id")
    public void deleteSpaceship(Long id) {
        spaceshipRepository.deleteById(id);
        spaceShipProducer.sendSpaceShipMessage( "nave "+id+" eliminada" );
    }

}
