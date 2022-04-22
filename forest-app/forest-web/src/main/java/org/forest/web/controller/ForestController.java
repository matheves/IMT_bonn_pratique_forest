package org.forest.web.controller;

import org.forest.api.controller.ForestApi;
import org.forest.api.model.Forest;
import org.forest.api.model.ForestType;
import org.forest.api.model.Tree;
import org.forest.model.*;
import org.forest.service.ForestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping
public class ForestController implements ForestApi {

    private final Logger logger = LoggerFactory.getLogger(TreeController.class);

    private ForestService forestService;

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return ForestApi.super.getRequest();
    }

    @Override
    public ResponseEntity<Forest> createForest(Forest forest) {
        ForestModel saved = forestService.save(this.map(forest));

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(
                "/{id}").buildAndExpand(saved.id()).toUri();

        return ResponseEntity.created(uri).body(map(saved));
    }

    @Override
    public ResponseEntity<Forest> deleteForest(String id) {
        Optional<ForestModel> saved = forestService.get(UUID.fromString(id));
        if(saved.isEmpty()) return ResponseEntity.badRequest().build();
        forestService.delete(saved.get());

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Forest> getForest(String id) {
        return getOptionalUUID(id)
                .flatMap(forestService::get)
                .map(this::map)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<List<Forest>> listForests() {
        return ResponseEntity.ok(forestService.list().stream().map(this::map).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<Forest> updateForest(Forest forest) {
        ForestModel forestModel = this.map(forest);

        if (forestService.get(forest.getId()).isPresent()) {
            return ResponseEntity.ok(this.map(forestService.save(forestModel)));
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    private Optional<UUID> getOptionalUUID(String uuid) {
        try {
            return Optional.of(UUID.fromString(uuid));
        } catch (IllegalArgumentException e) {
            logger.error("Error while parsing UUID", e);
            return Optional.empty();
        }
    }

    private ForestModel map(org.forest.api.model.Forest apiForest) {
        List<TreeModel> trees = new ArrayList<>();
        for(Tree t : apiForest.getTrees())
        {
            trees.add(map(t));
        }
        return new ForestModel(
                apiForest.getId(),
                ForestTypeModel.valueOf(apiForest.getType().getValue()),
                trees,
                apiForest.getSurface()
        );
    }

    private org.forest.api.model.Forest map(ForestModel modelForest) {
        List<Tree> trees = new ArrayList<>();
        for(TreeModel t : modelForest.trees())
        {
            trees.add(map(t));
        }
        return new org.forest.api.model.Forest()
                .id(modelForest.id())
                .type(ForestType.valueOf(modelForest.type().toString()))
                .trees(trees)
                .surface(modelForest.surface());
    }

    private TreeModel map(org.forest.api.model.Tree apiTree) {
        return new TreeModel(apiTree.getId(),
                apiTree.getBirth(),
                SpeciesModel.valueOf(apiTree.getSpecies().getValue()),
                ExposureModel.valueOf(apiTree.getExposure().getValue()),
                apiTree.getCarbonStorageCapacity()
        );
    }

    private org.forest.api.model.Tree map(TreeModel modelTree) {
        return new org.forest.api.model.Tree()
                .id(modelTree.id())
                .birth(modelTree.birth())
                .species(org.forest.api.model.Species.valueOf(modelTree.species().name()))
                .exposure(org.forest.api.model.Exposure.valueOf(modelTree.exposure().name()))
                .carbonStorageCapacity(modelTree.carbonStorageCapacity());
    }
}
