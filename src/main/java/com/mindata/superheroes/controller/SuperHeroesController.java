package com.mindata.superheroes.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import com.mindata.superheroes.dto.SuperHeroesDto;
import com.mindata.superheroes.service.SuperHeroesService;

@Controller
public class SuperHeroesController {

    private SuperHeroesService service;

    SuperHeroesController(SuperHeroesService superHeroesService) {
        this.service = superHeroesService;
    }

    public List<SuperHeroesDto> getAll() {

        return service.getAll();
    }
}
