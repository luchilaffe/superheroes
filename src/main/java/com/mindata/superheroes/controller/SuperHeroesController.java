package com.mindata.superheroes.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import com.mindata.superheroes.dto.SuperHeroesDto;
import com.mindata.superheroes.service.SuperHeroesService;

/**
 * Controller of Super Heroes Application
 * 
 * @author carlos.lafferriere
 */
@Controller
public class SuperHeroesController {

    private SuperHeroesService service;

    SuperHeroesController(SuperHeroesService superHeroesService) {
        this.service = superHeroesService;
    }

    /**
     * Method that get all Super Heroes.
     * 
     * @return a list with all super heroes
     */
    public List<SuperHeroesDto> getAll() {
        return service.getAll();
    }

    /**
     * Method that get the Super Heroe with the given Id.
     * 
     * @return the super heroe with the given Id.
     */
    public SuperHeroesDto get(Long id) {
        return service.get(id);
    }

}
