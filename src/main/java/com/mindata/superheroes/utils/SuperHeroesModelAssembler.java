package com.mindata.superheroes.utils;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.mindata.superheroes.controller.rest.SuperHeroesRestController;
import com.mindata.superheroes.dto.SuperHeroesDto;

@Component
public class SuperHeroesModelAssembler implements RepresentationModelAssembler<SuperHeroesDto, EntityModel<SuperHeroesDto>> {

	
	@Override
	public EntityModel<SuperHeroesDto> toModel(SuperHeroesDto superHeroe) {

	    return EntityModel.of(superHeroe,
	            linkTo(methodOn(SuperHeroesRestController.class)
	            		.get(superHeroe.getId())).withSelfRel(),
	            linkTo(methodOn(SuperHeroesRestController.class)
	            		.getAll()).withRel("superHeroes"));
	}
}