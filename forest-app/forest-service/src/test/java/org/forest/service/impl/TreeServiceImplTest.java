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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

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
}