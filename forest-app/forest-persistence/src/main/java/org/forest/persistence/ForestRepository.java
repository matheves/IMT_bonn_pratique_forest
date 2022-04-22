package org.forest.persistence;

import org.forest.model.ForestModel;
import org.forest.model.TreeModel;

import java.util.List;
import java.util.UUID;

public interface ForestRepository {

    List<ForestModel> findAll();

    ForestModel insert(ForestModel forest);

    ForestModel findById(UUID id);

    void updateById(UUID id, ForestModel forest);

    void delete(ForestModel forest);
}
