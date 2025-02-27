package com.w2m.naves.spaceship.infrastructure.repository;

import com.w2m.naves.spaceship.domain.EOrigin;
import com.w2m.naves.spaceship.domain.Spaceship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface SpaceshipRepository extends JpaRepository<Spaceship,Long> {

    Optional<List<Spaceship> > findByNameContainingIgnoreCase (String name);

    Optional<List<Spaceship> > findByOrigin(EOrigin origin);

}

