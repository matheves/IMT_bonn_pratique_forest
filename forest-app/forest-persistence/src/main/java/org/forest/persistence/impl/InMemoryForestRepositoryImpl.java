package org.forest.persistence.impl;

import org.forest.model.ForestModel;
import org.forest.model.TreeModel;
import org.forest.persistence.ForestRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InMemoryForestRepositoryImpl implements ForestRepository {

    private List<ForestModel> mutableRepository = new ArrayList<>();

    @Override
    public List<ForestModel> findAll() {
        return mutableRepository;
    }

    @Override
    public ForestModel insert(ForestModel forest) {
        final ForestModel persisted = new ForestModel(forest.id(), forest.type(), forest.trees(), forest.surface());
        mutableRepository.add(persisted);

        return persisted;
    }

    @Override
    public ForestModel findById(UUID id) {
        for (ForestModel forest : mutableRepository) {
            if (forest.id().equals(id)) {
                return forest;
            }
        }
        return null;
    }

    @Override
    public void updateById(UUID id, ForestModel forest) {
        ForestModel forestToUpdate = findById(id);

        if (forestToUpdate != null) {
            mutableRepository.set(mutableRepository.indexOf(forestToUpdate), forest);
        } else {
            throw new IllegalArgumentException("Forest not found");
        }

    }

    @Override
    public void delete(ForestModel forest) {
        if (!mutableRepository.remove(forest)) {
            throw new IllegalArgumentException("Forest not found");
        }
    }

}
