package org.forest.web.controller;

import org.forest.model.ExposureModel;
import org.forest.model.SpeciesModel;
import org.forest.model.TreeModel;
import org.forest.service.TreeService;
import org.forest.web.config.WebTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;

@WebMvcTest(TreeController.class)
@Import(WebTestConfig.class)
class TreeControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private TreeService treeService;

    @Autowired
    public TreeControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void shouldGetATreeById() throws Exception {
        // Given
        UUID uuid = UUID.randomUUID();

        // When
        when(treeService.get(uuid)).thenReturn(Optional.of(
                new TreeModel(uuid, LocalDate.now(), SpeciesModel.OAK, ExposureModel.SHADOW, 40)
        ));

        // Then
        mockMvc.perform(MockMvcRequestBuilders.get("/tree/%s".formatted(uuid.toString()))
                .accept(MediaType.ALL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.species").value("OAK"));

    }

    @Test
    void shouldGetAllTrees() throws Exception {
        // Given
        UUID uuid = UUID.randomUUID();

        // When
        when(treeService.list()).thenReturn(List.of(
                new TreeModel(uuid, LocalDate.now(), SpeciesModel.OAK, ExposureModel.SHADOW, 40)
        ));

        // Then
        mockMvc.perform(MockMvcRequestBuilders.get("/tree")
                .accept(MediaType.ALL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].species").value("OAK"));

    }

    // Need to fix it, no time to do it
    @Test
    void shouldCreateATree() throws Exception {
        // Given
        UUID uuid = UUID.randomUUID();

        // When
        when(treeService.save(
                new TreeModel(uuid, LocalDate.now(), SpeciesModel.OAK, ExposureModel.SHADOW, 40)
        )).thenReturn(new TreeModel(uuid, LocalDate.now(), SpeciesModel.OAK, ExposureModel.SHADOW, 40));

        // Then
        mockMvc.perform(MockMvcRequestBuilders.post("/tree")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"species\":\"OAK\",\"exposure\":\"SHADOW\",\"height\":40}")
                .accept(MediaType.ALL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.species").value("OAK"));

    }


}