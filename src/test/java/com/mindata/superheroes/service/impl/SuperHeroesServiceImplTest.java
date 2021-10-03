package com.mindata.superheroes.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
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
public class SuperHeroesServiceImplTest {

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
        superOne.setName("SuperMan");
        superTwo = new SuperHeroes();
        superTwo.setName("SpiderMan");
        superThree = new SuperHeroes();
        superThree.setName("Garfield");

        superHeroesList = new ArrayList<SuperHeroes>();
        superHeroesList.add(superOne);
        superHeroesList.add(superTwo);
        superHeroesList.add(superThree);

        superDtoOne = new SuperHeroesDto();
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

}
