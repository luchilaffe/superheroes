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
        superOne.setId(1L);
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

    @Test
    void whenGetOneThenReturnOk() {

        /* Mock called methods */
        when(superHeroesService.get(superOne.getId())).thenReturn(superOne);

        /* Call method */
        SuperHeroesDto response = superHeroesController.get(superOne.getId());

        /* Asserts */
        assertEquals(superOne, response);
        verify(superHeroesService, atLeastOnce()).get(superOne.getId());
        verify(superHeroesService).get(superOne.getId());
        verifyNoMoreInteractions(superHeroesService);
    }

    @Test
    void whenSearchByNameThenReturnOk() {

        String stringToSearch = "Man";

        /* Mock called methods */
        when(superHeroesService.searchByName(stringToSearch)).thenReturn(superHeroesDtoList);

        /* Call method */
        List<SuperHeroesDto> response = superHeroesController.searchByName(stringToSearch);

        /* Asserts */
        assertEquals(superHeroesDtoList, response);
        verify(superHeroesService, atLeastOnce()).searchByName(stringToSearch);
        verify(superHeroesService).searchByName(stringToSearch);
        verifyNoMoreInteractions(superHeroesService);
    }

    @Test
    void whenUpdateOneThenReturnOk() {

        /* Mock called methods */
        when(superHeroesService.update(superOne.getId(), superOne)).thenReturn(superTwo);

        /* Call method */
        SuperHeroesDto response = superHeroesController.update(superOne.getId(), superOne);

        /* Asserts */
        assertEquals(superTwo, response);
        verify(superHeroesService, atLeastOnce()).update(superOne.getId(), superOne);
        verify(superHeroesService).update(superOne.getId(), superOne);
        verifyNoMoreInteractions(superHeroesService);
    }

    @Test
    void whenDeleteOneThenReturnTrue() {

        /* Mock called methods */
        when(superHeroesService.delete(superOne.getId())).thenReturn(Boolean.TRUE);

        /* Call method */
        Boolean response = superHeroesController.delete(superOne.getId());

        /* Asserts */
        assertEquals(Boolean.TRUE, response);
        verify(superHeroesService, atLeastOnce()).delete(superOne.getId());
        verify(superHeroesService).delete(superOne.getId());
        verifyNoMoreInteractions(superHeroesService);
    }

    @Test
    void whenDeleteOneThenReturnFalse() {

        /* Mock called methods */
        when(superHeroesService.delete(superOne.getId())).thenReturn(Boolean.FALSE);

        /* Call method */
        Boolean response = superHeroesController.delete(superOne.getId());

        /* Asserts */
        assertEquals(Boolean.FALSE, response);
        verify(superHeroesService, atLeastOnce()).delete(superOne.getId());
        verify(superHeroesService).delete(superOne.getId());
        verifyNoMoreInteractions(superHeroesService);
    }

}
