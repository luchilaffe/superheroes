package com.mindata.superheroes.controller.rest;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mindata.superheroes.controller.SuperHeroesController;
import com.mindata.superheroes.dto.SuperHeroesDto;
import com.mindata.superheroes.utils.RestEndpoints;

@WebMvcTest({SuperHeroesRestController.class})
class SuperHeroesRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SuperHeroesController superHeroesController;

    private static List<SuperHeroesDto> superHeroesDtoList;
    private static SuperHeroesDto superOne, superTwo, superThree;

    @BeforeAll
    static void setup() {
        superOne = new SuperHeroesDto();
        superOne.setId(1L);
        superOne.setName("SuperMan");
        superTwo = new SuperHeroesDto();
        superTwo.setId(2L);
        superTwo.setName("SpiderMan");
        superThree = new SuperHeroesDto();
        superThree.setName("Garfield");

        superHeroesDtoList = new ArrayList<SuperHeroesDto>();
        superHeroesDtoList.add(superOne);
        superHeroesDtoList.add(superTwo);
        superHeroesDtoList.add(superThree);
    }

    @Test
    void whenGetAllThenReturnOk() throws Exception {

        /* Mock called method */
        when(superHeroesController.getAll()).thenReturn(superHeroesDtoList);

        /* Call method */
        this.mockMvc.perform(get(RestEndpoints.GET_ALL)).andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty()).andReturn();

        /* Asserts */
        verify(superHeroesController).getAll();
        verifyNoMoreInteractions(superHeroesController);
    }

    @Test
    void whenGetOneThenReturnOk() throws Exception {

        /* Mock called method */
        when(superHeroesController.get(superOne.getId())).thenReturn(superOne);

        /* Call method */
        this.mockMvc.perform(get(RestEndpoints.GET + "/" + superOne.getId().toString()))
                .andExpect(status().isOk()).andExpect(jsonPath("$").isNotEmpty()).andReturn();

        /* Asserts */
        verify(superHeroesController).get(superOne.getId());
        verifyNoMoreInteractions(superHeroesController);
    }

    @Test
    void whenFindByNameThenReturnOk() throws Exception {

        String stringToSearch = "man";

        /* Mock called method */
        when(superHeroesController.searchByName(stringToSearch)).thenReturn(superHeroesDtoList);

        /* Call method */
        this.mockMvc.perform(get(RestEndpoints.SEARCH + "?name=" + stringToSearch))
                .andExpect(status().isOk()).andExpect(jsonPath("$").isNotEmpty()).andReturn();

        /* Asserts */
        verify(superHeroesController).searchByName(stringToSearch);
        verifyNoMoreInteractions(superHeroesController);
    }

    @Test
    void whenUpdateThenReturnOk() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
        String operationJson = objectWriter.writeValueAsString(superOne);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put(RestEndpoints.UPDATE + "/" + superOne.getId()).content(operationJson)
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_VALUE);

        /* Mock called method */
        when(superHeroesController.update(Mockito.anyLong(), Mockito.any(SuperHeroesDto.class)))
                .thenReturn(superTwo);

        /* Call Method */
        this.mockMvc.perform(request).andExpect(status().isOk()).andReturn();
    }
}
