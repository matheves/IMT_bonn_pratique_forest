package org.forest.persistence;

import org.forest.model.TreeModel;

import java.util.List;

/**
 * In a real application, the repositories are more complex : in this exercise,
 * no logic should be present, the repository is a simple in-memory one.
 */
public interface TreeRepository {

    List<TreeModel> findAll();

    TreeModel insert(TreeModel tree);
}
