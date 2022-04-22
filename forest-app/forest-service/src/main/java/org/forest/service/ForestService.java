package org.forest.service;

import org.forest.model.ForestModel;
import org.forest.model.TreeModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ForestService {

    Optional<ForestModel> get(UUID uuid);

    List<ForestModel> list();

    ForestModel save(ForestModel forest);

    void update(ForestModel forest);

    void delete(ForestModel forest);
}
