package org.forest.service;

import org.forest.model.TreeModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * TODO : Here, you will have to create the service methods and implement them in the impl package.
 */
public interface TreeService {

    Optional<TreeModel> get(UUID uuid);

    List<TreeModel> list();

    TreeModel save(TreeModel tree);
}
