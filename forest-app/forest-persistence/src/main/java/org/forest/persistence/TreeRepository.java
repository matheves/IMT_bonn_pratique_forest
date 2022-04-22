package org.forest.persistence;

import org.forest.model.TreeModel;

import java.util.List;
import java.util.UUID;

/**
 * In a real application, the repositories are more complex : in this exercise,
 * no logic should be present, the repository is a simple in-memory one.
 */
public interface TreeRepository {

    List<TreeModel> findAll();

    TreeModel insert(TreeModel tree);

    TreeModel findById(UUID id);

    void updateById(UUID id, TreeModel tree);

    void delete(TreeModel tree);
}

