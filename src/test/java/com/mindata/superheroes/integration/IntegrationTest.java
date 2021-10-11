package com.mindata.superheroes.integration;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import com.mindata.superheroes.dao.SuperHeroesRepository;
import com.mindata.superheroes.utils.RestEndpoints;


@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
class IntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    SuperHeroesRepository repository;


    @Test
    void whenTestSomeMethodsThenReturnOk() throws Exception {

        /* Get All */
        this.mockMvc
                .perform(get(RestEndpoints.GET_ALL).with(csrf())
                        .with(SecurityMockMvcRequestPostProcessors.user("admin").password("admin")))
                .andExpect(status().isOk()).andExpect(jsonPath("$").isNotEmpty()).andReturn();

        /* Get One */
        this.mockMvc
                .perform(get(RestEndpoints.GET + "/1").with(csrf())
                        .with(SecurityMockMvcRequestPostProcessors.user("admin").password("admin")))
                .andExpect(status().isOk()).andExpect(jsonPath("$").isNotEmpty()).andReturn();

        /* Search by Name */
        this.mockMvc
                .perform(get(RestEndpoints.SEARCH + "?name=Man").with(csrf())
                        .with(SecurityMockMvcRequestPostProcessors.user("admin").password("admin")))
                .andExpect(status().isOk()).andExpect(jsonPath("$").isNotEmpty()).andReturn();
    }

}
