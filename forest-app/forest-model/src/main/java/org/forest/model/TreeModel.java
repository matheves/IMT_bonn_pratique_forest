package org.forest.model;

import java.time.LocalDate;
import java.util.UUID;

public record TreeModel(UUID id,
                        LocalDate birth,
                        SpeciesModel species,
                        ExposureModel exposure,
                        double carbonStorageCapacity // Between 10 and 50 kg/year
) {
}