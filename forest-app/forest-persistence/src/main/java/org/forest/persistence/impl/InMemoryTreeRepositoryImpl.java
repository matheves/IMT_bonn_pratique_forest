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
        final TreeModel persisted = new TreeModel(tree.id(), tree.birth(), tree.species(), tree.exposure(), tree.carbonStorageCapacity());
        mutableRepository.add(persisted);

        return persisted;
    }

    @Override
    public TreeModel findById(UUID id) {
        for (TreeModel tree : mutableRepository) {
            if (tree.id().equals(id)) {
                return tree;
            }
        }
        return null;
    }

    @Override
    public void updateById(UUID id, TreeModel tree) {
        TreeModel treeToUpdate = findById(id);

        if (treeToUpdate != null) {
            mutableRepository.set(mutableRepository.indexOf(treeToUpdate), tree);
        } else {
            throw new IllegalArgumentException("Tree not found");
        }

    }

    @Override
    public void delete(TreeModel tree) {
        if (!mutableRepository.remove(tree)) {
            throw new IllegalArgumentException("Tree not found");
        }
    }
}
