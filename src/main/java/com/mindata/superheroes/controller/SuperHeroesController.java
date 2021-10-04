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
     * @param id of the super hero that is searched
     * @return the super heroe with the given Id.
     */
    public SuperHeroesDto get(Long id) {
        return service.get(id);
    }

    /**
     * Method that get all the Super Heroe with the given string in its name.
     * 
     * @param name string to be search in the name of the super heroes
     * @return a list with all super heores that satisfies the condition
     */
    public List<SuperHeroesDto> searchByName(String name) {
        return service.searchByName(name);
    }

    /**
     * Method that update the Super Heroe indicated by its Id, with the given data.
     * 
     * @param id of the super hero that will be updated.
     * @param superHero cointains the data to be set in the super Hero.
     * @return the updated Super Heroe with the given Id.
     */
    public SuperHeroesDto update(Long id, SuperHeroesDto superHero) {
        return service.update(id, superHero);
    }

    /**
     * Method that delete the Super Heroe with the given Id.
     * 
     * @param id of the super hero that would be deleted
     * @return boolean that confirm the operation.
     */
    public Boolean delete(Long id) {
        return service.delete(id);
    }

}
