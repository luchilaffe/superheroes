package com.mindata.superheroes.controller;

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
import com.mindata.superheroes.dto.SuperHeroesDto;
import com.mindata.superheroes.service.SuperHeroesService;

@ExtendWith(MockitoExtension.class)
class SuperHeroesControllerTest {

    @InjectMocks
    private SuperHeroesController superHeroesController;

    @Mock
    SuperHeroesService superHeroesService;

    private static SuperHeroesDto superOne, superTwo, superThree;
    private static List<SuperHeroesDto> superHeroesDtoList;

    @BeforeAll
    static void setup() {
        superOne = new SuperHeroesDto();
        superOne.setName("SuperMan");
        superTwo = new SuperHeroesDto();
        superTwo.setName("SpiderMan");
        superThree = new SuperHeroesDto();
        superThree.setName("Garfield");

        superHeroesDtoList = new ArrayList<SuperHeroesDto>();
        superHeroesDtoList.add(superOne);
        superHeroesDtoList.add(superTwo);
        superHeroesDtoList.add(superThree);
    }

    @Test
    void whenGetAllThenReturnOk() {

        /* Mock called methods */
        when(superHeroesService.getAll()).thenReturn(superHeroesDtoList);

        /* Call method */
        List<SuperHeroesDto> response = superHeroesController.getAll();

        /* Asserts */
        assertEquals(superHeroesDtoList, response);
        verify(superHeroesService, atLeastOnce()).getAll();
        verify(superHeroesService).getAll();
        verifyNoMoreInteractions(superHeroesService);
    }

}
