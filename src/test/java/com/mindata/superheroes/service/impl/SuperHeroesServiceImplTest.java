package com.mindata.superheroes.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.mindata.superheroes.dao.SuperHeroesRepository;
import com.mindata.superheroes.dto.SuperHeroesDto;
import com.mindata.superheroes.model.SuperHeroes;
import com.mindata.superheroes.service.imple.SuperHeroesServiceImpl;

@ExtendWith(MockitoExtension.class)
class SuperHeroesServiceImplTest {

    @InjectMocks
    SuperHeroesServiceImpl superHeroesService;

    @Mock
    private SuperHeroesRepository superHeroesRepository;

    private static SuperHeroesDto superDtoOne, superDtoTwo, superDtoThree;
    private static SuperHeroes superOne, superTwo, superThree;
    private static List<SuperHeroesDto> superHeroesDtoList;
    private static List<SuperHeroes> superHeroesList;

    @BeforeAll
    static void setup() {
        superOne = new SuperHeroes();
        superTwo = new SuperHeroes();
        superThree = new SuperHeroes();

        superHeroesList = new ArrayList<SuperHeroes>();
        superHeroesList.add(superOne);
        superHeroesList.add(superTwo);
        superHeroesList.add(superThree);

        superDtoOne = new SuperHeroesDto();
        superDtoOne.setId(1L);
        superDtoOne.setName("SuperMan");
        superDtoTwo = new SuperHeroesDto();
        superDtoTwo.setName("SpiderMan");
        superDtoThree = new SuperHeroesDto();
        superDtoThree.setName("Garfield");

        superHeroesDtoList = new ArrayList<SuperHeroesDto>();
        superHeroesDtoList.add(superDtoOne);
        superHeroesDtoList.add(superDtoTwo);
        superHeroesDtoList.add(superDtoThree);
    }

    @BeforeEach
    void setupValues() {
        superOne.setId(1L);
        superOne.setName("SuperMan");
        superTwo.setName("SpiderMan");
        superThree.setName("Garfield");

    }

    @Test
    void whenGetAllThenReturnOk() {

        /* Mock called methods */
        when(superHeroesRepository.findAll()).thenReturn(superHeroesList);

        /* Call Method */
        List<SuperHeroesDto> response = superHeroesService.getAll();

        /* Asserts */
        assertEquals(superHeroesDtoList.get(0).getName(), response.get(0).getName());
        assertEquals(superHeroesDtoList.get(1).getName(), response.get(1).getName());
        assertEquals(superHeroesDtoList.get(2).getName(), response.get(2).getName());
        assertEquals(superHeroesDtoList.size(), response.size());
        verify(superHeroesRepository, atLeastOnce()).findAll();
        verify(superHeroesRepository).findAll();
        verifyNoMoreInteractions(superHeroesRepository);
    }

    @Test
    void whenGetOneThenReturnOk() {

        /* Mock called methods */
        when(superHeroesRepository.findById(superOne.getId())).thenReturn(Optional.of(superOne));

        /* Call Method */
        SuperHeroesDto response = superHeroesService.get(superOne.getId());

        /* Asserts */
        assertEquals(superDtoOne.getId(), response.getId());
        assertEquals(superDtoOne.getName(), response.getName());
        verify(superHeroesRepository, atLeastOnce()).findById(superOne.getId());
        verify(superHeroesRepository).findById(superOne.getId());
        verifyNoMoreInteractions(superHeroesRepository);
    }

    @Test
    void whenSearchByNameThenReturnOk() {

        String stringToSearch = "a";

        /* Mock called methods */
        when(superHeroesRepository.findByNameIsContainingIgnoreCase(stringToSearch))
                .thenReturn(superHeroesList);

        /* Call Method */
        List<SuperHeroesDto> response = superHeroesService.searchByName(stringToSearch);

        /* Asserts */
        assertEquals(superHeroesDtoList.get(0).getName(), response.get(0).getName());
        assertEquals(superHeroesDtoList.get(1).getName(), response.get(1).getName());
        assertEquals(superHeroesDtoList.get(2).getName(), response.get(2).getName());
        assertEquals(superHeroesDtoList.size(), response.size());
        verify(superHeroesRepository, atLeastOnce())
                .findByNameIsContainingIgnoreCase(stringToSearch);
        verify(superHeroesRepository).findByNameIsContainingIgnoreCase(stringToSearch);
        verifyNoMoreInteractions(superHeroesRepository);
    }

    @Test
    void whenUpdateOneThenReturnOk() {

        /* Mock called methods */
        when(superHeroesRepository.findById(superOne.getId())).thenReturn(Optional.of(superOne));

        /* Call Method */
        SuperHeroesDto response = superHeroesService.update(superOne.getId(), superDtoTwo);

        /* Asserts */
        assertEquals(superDtoOne.getId(), response.getId());
        assertEquals(superDtoTwo.getName(), response.getName());
        verify(superHeroesRepository, atLeastOnce()).findById(superOne.getId());
        verify(superHeroesRepository).findById(superOne.getId());
    }

    @Test
    void whenDeleteOneThenReturnTrue() {

        /* Mock called methods */
        when(superHeroesRepository.findById(superOne.getId())).thenReturn(Optional.of(superOne));

        /* Call Method */
        Boolean response = superHeroesService.delete(superOne.getId());

        /* Asserts */
        assertEquals(Boolean.TRUE, response);
        verify(superHeroesRepository, atLeastOnce()).findById(superOne.getId());
        verify(superHeroesRepository).findById(superOne.getId());
        verify(superHeroesRepository, atLeastOnce()).deleteById(superOne.getId());
        verify(superHeroesRepository).deleteById(superOne.getId());
        verifyNoMoreInteractions(superHeroesRepository);
    }

}
