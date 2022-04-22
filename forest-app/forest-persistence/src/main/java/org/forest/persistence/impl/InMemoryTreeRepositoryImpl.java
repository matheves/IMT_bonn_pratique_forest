package org.forest.persistence.impl;

import org.forest.persistence.TreeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.forest.model.TreeModel;
import org.springframework.stereotype.Service;

/**
 * /!\ This repository is a simple in-memory one : DO NOT WRITE ANY BUSINESS CODE HERE /!\
 *
 */
@Service
public class InMemoryTreeRepositoryImpl implements TreeRepository {

    private List<TreeModel> mutableRepository = new ArrayList<>();

    @Override
    public List<TreeModel> findAll() {
        return mutableRepository;
    }

    @Override
    public TreeModel insert(TreeModel tree) {
        final TreeModel persisted = new TreeModel(UUID.randomUUID(), tree.birth(), tree.species(), tree.exposure(), tree.carbonStorageCapacity());
        mutableRepository.add(persisted);

        return persisted;
    }
}
