package com.w2m.spaceships.spaceship.infraestructure;

import com.w2m.spaceships.spaceship.domain.EOrigin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpaceshipDTO {

    private String name;
    private String description;
    private EOrigin origin;

}
