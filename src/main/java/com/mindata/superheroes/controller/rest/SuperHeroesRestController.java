package com.mindata.superheroes.controller.rest;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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

    /**
     * Method that get all the Super Heroe with the given string in its name.
     * 
     * @return a list with all super heores that satisfies the condition
     */
    @GetMapping(RestEndpoints.SEARCH)
    public List<SuperHeroesDto> get(@RequestParam("name") String name) {
        return controller.searchByName(name);
    }

    /**
     * Method that update the Super Heroe indicated by its Id, with the given data.
     * 
     * @return the updated Super Heroe with the given Id.
     */
    @PutMapping(RestEndpoints.UPDATE + "/{id}")
    public SuperHeroesDto update(@PathVariable Long id, @RequestBody SuperHeroesDto superHero) {
        return controller.update(id, superHero);
    }

}
