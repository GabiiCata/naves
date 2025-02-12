package com.w2m.spaceships.spaceship.infraestructure;

import com.w2m.spaceships.spaceship.domain.EOrigin;
import com.w2m.spaceships.spaceship.domain.Spaceship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpaceshipRepository extends JpaRepository<Spaceship,Long> {

    List<Spaceship>  findByNameContaining (String name);

    List<Spaceship> findByOrigin(EOrigin origin);
}

