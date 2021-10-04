package com.mindata.superheroes.service.imple;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.mindata.superheroes.dao.SuperHeroesRepository;
import com.mindata.superheroes.dto.SuperHeroesDto;
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
        return repository.findAll().stream().map(sH -> SuperHeroesMapper.toSuperHeroesDto(sH))
                .collect(Collectors.toList());
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
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SuperHeroesDto> searchByName(String name) {
        return repository.findByNameIsContainingIgnoreCase(name).stream()
                .map(sH -> SuperHeroesMapper.toSuperHeroesDto(sH)).collect(Collectors.toList());
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
            return Boolean.FALSE;
        }
    }

}
