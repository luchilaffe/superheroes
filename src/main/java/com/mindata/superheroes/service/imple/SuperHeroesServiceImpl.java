package com.mindata.superheroes.service.imple;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.mindata.superheroes.dao.SuperHeroesRepository;
import com.mindata.superheroes.dto.SuperHeroesDto;
import com.mindata.superheroes.exceptions.NotFoundException;
import com.mindata.superheroes.model.SuperHeroes;
import com.mindata.superheroes.service.SuperHeroesService;
import com.mindata.superheroes.utils.SuperHeroesMapper;

/**
 * {@inheritDoc}
 */
@Service
public class SuperHeroesServiceImpl implements SuperHeroesService {

    private SuperHeroesRepository repository;

    SuperHeroesServiceImpl(SuperHeroesRepository superHeroesRepository) {
        this.repository = superHeroesRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SuperHeroesDto> getAll() {
        List<SuperHeroesDto> response = repository.findAll().stream()
                .map(sH -> SuperHeroesMapper.toSuperHeroesDto(sH)).collect(Collectors.toList());
        if (!response.isEmpty()) {
            return response;
        } else {
            throw new NotFoundException("There are no Superheroes.");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SuperHeroesDto get(Long id) {
        SuperHeroes superHero = repository.findById(id).orElse(null);
        if (null != superHero) {
            return SuperHeroesMapper.toSuperHeroesDto(superHero);
        }
        throw new NotFoundException("Not found ID: " + id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SuperHeroesDto> searchByName(String name) {
        List<SuperHeroesDto> response = repository.findByNameIsContainingIgnoreCase(name).stream()
                .map(sH -> SuperHeroesMapper.toSuperHeroesDto(sH)).collect(Collectors.toList());
        if (!response.isEmpty()) {
            return response;
        } else {
            throw new NotFoundException("Not found name with: " + name);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SuperHeroesDto update(Long id, SuperHeroesDto superHero) {
        SuperHeroes superHeroToUpdate = repository.findById(id).orElseGet(() -> new SuperHeroes());
        superHeroToUpdate.setName(superHero.getName());
        repository.save(superHeroToUpdate);
        return SuperHeroesMapper.toSuperHeroesDto(superHeroToUpdate);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean delete(Long id) {
        Optional<SuperHeroes> sH = repository.findById(id);
        if (sH.isPresent()) {
            repository.deleteById(id);
            return Boolean.TRUE;
        } else {
            throw new NotFoundException("Not found ID: " + id);
        }
    }

}
