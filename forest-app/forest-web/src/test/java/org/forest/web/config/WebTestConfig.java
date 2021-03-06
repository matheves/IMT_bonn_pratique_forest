package org.forest.web.config;

import org.forest.persistence.ForestRepository;
import org.forest.persistence.TreeRepository;
import org.forest.service.ForestService;
import org.forest.service.TreeService;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebTestConfig {

    @MockBean
    private TreeService treeService;

    @MockBean
    private TreeRepository treeRepository;

    @MockBean
    private ForestRepository forestRepository;

    @MockBean
    private ForestService forestService;
}
