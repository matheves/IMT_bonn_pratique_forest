package org.forest.service.impl;

import org.forest.model.ExposureModel;
import org.forest.model.SpeciesModel;
import org.forest.model.TreeModel;
import org.forest.persistence.TreeRepository;
import org.forest.service.TreeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TreeServiceImplTest {

    @Mock
    private TreeRepository treeRepository;

    @InjectMocks
    private TreeService treeService = new TreeServiceImpl(treeRepository);

    @Test
    void shouldGetATree() {
        // GIVEN
        final UUID uuid = UUID.randomUUID();
        final TreeModel repositoryTree = new TreeModel(uuid, LocalDate.now(), SpeciesModel.OAK, ExposureModel.SHADOW, 40);
        when(treeRepository.findAll()).thenReturn(List.of(repositoryTree));

        // WHEN
        Optional<TreeModel> tree = treeService.get(uuid);

        // THEN
        assertTrue(tree.isPresent());
        assertEquals(uuid, tree.map(TreeModel::id).get());
    }

    @Test
    void shouldSaveAValidTree() {
        // GIVEN
        final UUID id = UUID.randomUUID();
        final TreeModel tree = new TreeModel(id, LocalDate.now(), SpeciesModel.OAK, ExposureModel.SHADOW, 40);

        // WHEN
        when(treeRepository.insert(any(TreeModel.class))).thenReturn(tree);

        // THEN
        assertEquals(id, treeService.save(tree).id());

    }

    @Test
    void shouldNotSaveAnInvalidTree() {
        // GIVEN
        final TreeModel tree = new TreeModel(UUID.randomUUID(),LocalDate.now(), null, ExposureModel.SHADOW, 40);

        // WHEN


        // THEN
        assertThrows(IllegalArgumentException.class, () -> treeService.save(tree));
    }

    @Test
    void shouldUpdateATree() {
        // GIVEN
        final UUID id = UUID.randomUUID();
        final TreeModel tree = new TreeModel(id, LocalDate.now(), SpeciesModel.OAK, ExposureModel.SHADOW, 40);

        // WHEN
        treeService.update(tree);

        // THEN
        verify(treeRepository, times(1)).updateById(id, tree);
    }

    @Test
    void shouldDeleteATree() {
        // GIVEN
        final UUID id = UUID.randomUUID();
        final TreeModel tree = new TreeModel(id, LocalDate.now(), SpeciesModel.OAK, ExposureModel.SHADOW, 40);

        // WHEN
        treeService.delete(tree);

        // THEN
        verify(treeRepository, times(1)).delete(tree);
    }
}