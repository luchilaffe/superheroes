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

    /**
     * Method that get all the Super Heroe with the given string in its name.
     * 
     * @return a list with all super heores that satisfies the condition
     */
    public List<SuperHeroesDto> searchByName(String name) {
        return service.searchByName(name);
    }

}
