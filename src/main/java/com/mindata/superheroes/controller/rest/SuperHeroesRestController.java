package com.mindata.superheroes.controller.rest;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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

    private CacheControl cacheControl;

    SuperHeroesRestController(SuperHeroesController superHeroesController) {
        this.controller = superHeroesController;
        this.cacheControl = CacheControl.maxAge(1, TimeUnit.HOURS).noTransform().mustRevalidate();
    }

    /**
     * Get all Super Heroes.
     * 
     * @return a list with all the Super Heroes
     */
    @GetMapping(RestEndpoints.GET_ALL)
    public ResponseEntity<List<SuperHeroesDto>> getAll() {
        return ResponseEntity.ok().cacheControl(this.cacheControl).body(controller.getAll());
    }

    /**
     * Get one Super Heroe, with the given id.
     * 
     * @return the Super Heroe with the given Id.
     */
    @GetMapping(RestEndpoints.GET + "/{id}")
    public ResponseEntity<SuperHeroesDto> get(@PathVariable Long id) {
        return ResponseEntity.ok().cacheControl(this.cacheControl).body(controller.get(id));
    }

    /**
     * Method that get all the Super Heroe with the given string in its name.
     * 
     * @return a list with all super heores that satisfies the condition
     */
    @GetMapping(RestEndpoints.SEARCH)
    public ResponseEntity<List<SuperHeroesDto>> get(@RequestParam("name") String name) {
        return ResponseEntity.ok().cacheControl(this.cacheControl)
                .body(controller.searchByName(name));
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

    /**
     * Method that delete the Super Heroe indicated by its Id.
     * 
     * @return boolean that confirm the operation.
     */
    @DeleteMapping(RestEndpoints.DELETE + "/{id}")
    public Boolean delete(@PathVariable Long id) {
        return controller.delete(id);
    }

}
