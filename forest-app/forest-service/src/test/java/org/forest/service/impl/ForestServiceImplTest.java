package org.forest.service.impl;

import org.forest.model.*;
import org.forest.persistence.ForestRepository;
import org.forest.persistence.TreeRepository;
import org.forest.service.ForestService;
import org.forest.service.TreeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ForestServiceImplTest {

    @Mock
    private ForestRepository forestRepository;

    @InjectMocks
    private ForestService forestService = new ForestServiceImpl(forestRepository);

    @Test
    void shouldGetAForest() {
        // GIVEN
        final UUID uuid = UUID.randomUUID();
        final ForestModel repositoryForest = new ForestModel(uuid, ForestTypeModel.BOREAL, new ArrayList<TreeModel>(), 50);
        when(forestRepository.findAll()).thenReturn(List.of(repositoryForest));

        // WHEN
        Optional<ForestModel> forest = forestService.get(uuid);

        // THEN
        assertTrue(forest.isPresent());
        assertEquals(uuid, forest.map(ForestModel::id).get());
    }

    @Test
    void shouldSaveAValidForest() {
        // GIVEN
        final UUID id = UUID.randomUUID();
        final ForestModel repositoryForest = new ForestModel(id, ForestTypeModel.BOREAL, new ArrayList<TreeModel>(), 50);

        // WHEN
        when(forestRepository.insert(any(ForestModel.class))).thenReturn(repositoryForest);

        // THEN
        assertEquals(id, forestService.save(repositoryForest).id());

    }

    @Test
    void shouldNotSaveAnInvalidForest() {
        // GIVEN
        final UUID id = UUID.randomUUID();
        final ForestModel repositoryForest = new ForestModel(id, null, new ArrayList<TreeModel>(), 50);

        // WHEN


        // THEN
        assertThrows(IllegalArgumentException.class, () -> forestService.save(repositoryForest));
    }

    @Test
    void shouldUpdateAForest() {
        // GIVEN
        final UUID id = UUID.randomUUID();
        final ForestModel repositoryForest = new ForestModel(id, ForestTypeModel.BOREAL, new ArrayList<TreeModel>(), 50);

        // WHEN
        forestService.update(repositoryForest);

        // THEN
        verify(forestRepository, times(1)).updateById(id, repositoryForest);
    }

    @Test
    void shouldDeleteATree() {
        // GIVEN
        final UUID id = UUID.randomUUID();
        final ForestModel repositoryForest = new ForestModel(id, ForestTypeModel.BOREAL, new ArrayList<TreeModel>(), 50);

        // WHEN
        forestService.delete(repositoryForest);

        // THEN
        verify(forestRepository, times(1)).delete(repositoryForest);
    }
}
