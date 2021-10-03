package com.mindata.superheroes.controller.rest;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.mindata.superheroes.controller.SuperHeroesController;
import com.mindata.superheroes.dto.SuperHeroesDto;
import com.mindata.superheroes.utils.RestEndpoints;

/**
 * Rest Controller that publish services of Super Heroes
 * 
 * @author carlos.lafferriere
 */
@RestController
public class SuperHeroesRestController {

    private SuperHeroesController controller;

    SuperHeroesRestController(SuperHeroesController superHeroesController) {
        this.controller = superHeroesController;
    }

    /**
     * Get all Super Heroes.
     * 
     * @return a list with all the Super Heroes
     */
    @GetMapping(RestEndpoints.GET_ALL)
    public List<SuperHeroesDto> getAll() {
        return controller.getAll();
    }

    /**
     * Get one Super Heroe, with the given id.
     * 
     * @return the Super Heroe with the given Id.
     */
    @GetMapping(RestEndpoints.GET + "/{id}")
    public SuperHeroesDto get(@PathVariable Long id) {
        return controller.get(id);
    }

}
