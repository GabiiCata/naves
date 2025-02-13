package com.w2m.naves.spaceship.infraestructure.repository;

import com.w2m.naves.spaceship.domain.EOrigin;
import com.w2m.naves.spaceship.domain.Spaceship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpaceshipRepository extends JpaRepository<Spaceship,Long> {

    Optional<List<Spaceship> > findByNameContaining (String name);

    Optional<List<Spaceship> >findByOrigin(EOrigin origin);
}

