package com.mindata.superheroes.service;

import java.util.List;
import com.mindata.superheroes.dto.SuperHeroesDto;

/**
 * Service layer for Super Heroes
 * 
 * @author carlos.lafferriere
 *
 */
public interface SuperHeroesService {

    /**
     * Service that returns all Super Heroes
     * 
     * @return a list of Super Heroes Dto with all of them
     */
    List<SuperHeroesDto> getAll();

}
