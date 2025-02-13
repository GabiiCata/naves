package com.w2m.naves.spaceship.application;

import com.w2m.naves.spaceship.domain.EOrigin;
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
