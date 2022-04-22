package org.forest.model;

import java.util.List;
import java.util.UUID;

public record ForestModel(UUID id,
                          ForestTypeModel type,
                          List<TreeModel> trees,
                          double surface) {
}
