package com.mindata.superheroes.mapper;

import com.mindata.superheroes.dto.SuperHeroesDto;
import com.mindata.superheroes.model.SuperHeroes;

public class SuperHeroesMapper {

    /**
     * Constructor
     */
    private SuperHeroesMapper() {}

    public static SuperHeroesDto toSuperHeroesDto(SuperHeroes superHeroe) {
        SuperHeroesDto response = new SuperHeroesDto();
        response.setId(superHeroe.getId());
        response.setName(superHeroe.getName());
        return response;
    }

}
