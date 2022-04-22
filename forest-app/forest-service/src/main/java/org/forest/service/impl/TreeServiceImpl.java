package org.forest.service.impl;

import org.forest.model.TreeModel;
import org.forest.persistence.TreeRepository;
import org.forest.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TreeServiceImpl implements TreeService {

    private TreeRepository treeRepository;

    @Autowired
    public TreeServiceImpl(TreeRepository treeRepository) {
        this.treeRepository = treeRepository;
    }

    @Override
    public Optional<TreeModel> get(UUID uuid) {
        return treeRepository.findAll().stream()
                .filter(tree -> uuid.equals(tree.id()))
                .findFirst();
    }


    @Override
    public List<TreeModel> list() {
        return treeRepository.findAll();
    }

    @Override
    public TreeModel save(TreeModel tree) {
        if (tree.birth() == null) {
            throw new IllegalArgumentException("Birth is required");
        }
        if (tree.species() == null) {
            throw new IllegalArgumentException("Species is required");
        }

        if (tree.id() == null) {
            throw new IllegalArgumentException("Id is required");
        }

        if (tree.exposure() == null) {
            throw new IllegalArgumentException("Exposure is required");
        }

        // Some other validation rules could be defined here

        return treeRepository.insert(tree);
    }
}
