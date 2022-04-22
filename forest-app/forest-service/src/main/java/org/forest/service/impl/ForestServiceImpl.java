package org.forest.service.impl;

import org.forest.model.ForestModel;
import org.forest.persistence.ForestRepository;
import org.forest.service.ForestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ForestServiceImpl implements ForestService {

    private ForestRepository forestRepository;

    @Autowired
    public ForestServiceImpl(ForestRepository forestRepository) {
        this.forestRepository = forestRepository;
    }

    @Override
    public Optional<ForestModel> get(UUID uuid) {
        return forestRepository.findAll().stream()
                .filter(forest -> uuid.equals(forest.id()))
                .findFirst();
    }


    @Override
    public List<ForestModel> list() {
        return forestRepository.findAll();
    }

    @Override
    public ForestModel save(ForestModel forest) {
        if (forest.type() == null) {
            throw new IllegalArgumentException("Type is required");
        }

        // Some other validation rules could be defined here

        return forestRepository.insert(forest);
    }

    @Override
    public void update(ForestModel forest) {
        Assert.notNull(forest.id(), "Id is required");
        forestRepository.updateById(forest.id(), forest);
    }

    @Override
    public void delete(ForestModel forest) {
        forestRepository.delete(forest);
    }
}
