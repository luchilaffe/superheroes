package com.mindata.superheroes.controller.rest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.mindata.superheroes.annotation.RunTimeCounter;
import com.mindata.superheroes.controller.SuperHeroesController;
import com.mindata.superheroes.dto.SuperHeroesDto;
import com.mindata.superheroes.mapper.SuperHeroesModelAssembler;
import com.mindata.superheroes.utils.RestEndpoints;

/**
 * Rest Controller that publish services of Super Heroes
 * 
 * @author carlos.lafferriere
 */
@RestController
public class SuperHeroesRestController {

	private final SuperHeroesModelAssembler assembler;
	private SuperHeroesController controller;

	private CacheControl cacheControl;

    SuperHeroesRestController(SuperHeroesController superHeroesController, 
    		SuperHeroesModelAssembler superHeroesModelAssembler) {
        this.assembler = superHeroesModelAssembler;
    	this.controller = superHeroesController;
        this.cacheControl = CacheControl.maxAge(1, TimeUnit.HOURS).noTransform().mustRevalidate();
    }

    /**
     * Get all Super Heroes.
     * 
     * @return a list with all the Super Heroes
     */
    @RunTimeCounter
    @GetMapping(RestEndpoints.GET_ALL)
    public ResponseEntity<CollectionModel<EntityModel<SuperHeroesDto>>> getAll() {
    	List<EntityModel<SuperHeroesDto>> response = controller.getAll().stream()
    			.map(assembler::toModel).collect(Collectors.toList());
        return ResponseEntity.ok()
        		.cacheControl(this.cacheControl)
        		.body(CollectionModel.of(response,
        				linkTo(methodOn(SuperHeroesRestController.class).getAll()).withSelfRel()));
    }

    /**
     * Get one Super Heroe, with the given id.
     * 
     * @return the Super Heroe with the given Id.
     */
    @RunTimeCounter
    @GetMapping(RestEndpoints.GET + "/{id}")
    public ResponseEntity<EntityModel<SuperHeroesDto>> get(@PathVariable Long id) {
        EntityModel<SuperHeroesDto> response = assembler.toModel(controller.get(id));
    	return ResponseEntity.ok().cacheControl(this.cacheControl).body(response);
    }

    /**
     * Method that get all the Super Heroe with the given string in its name.
     * 
     * @return a list with all super heores that satisfies the condition
     */
    @GetMapping(RestEndpoints.SEARCH)
    public ResponseEntity<CollectionModel<EntityModel<SuperHeroesDto>>> get(@RequestParam("name") String name) {
        
		List<EntityModel<SuperHeroesDto>> response = controller.searchByName(name).stream()
				.map(assembler::toModel).collect(Collectors.toList());
    	
    	return ResponseEntity.ok().cacheControl(this.cacheControl)
                .body(CollectionModel.of(response,
                		linkTo(methodOn(SuperHeroesRestController.class).getAll()).withSelfRel()
                ));
    }

    /**
     * Method that update the Super Heroe indicated by its Id, with the given data.
     * 
     * @return the updated Super Heroe with the given Id.
     */
    @PutMapping(RestEndpoints.UPDATE + "/{id}")
    public ResponseEntity<EntityModel<SuperHeroesDto>> update(@PathVariable Long id, @RequestBody SuperHeroesDto superHero) {
        EntityModel<SuperHeroesDto> response = assembler.toModel(controller.update(id, superHero));
    	return ResponseEntity.ok().cacheControl(this.cacheControl).body(response);
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
