package com.bindschaedel.service;

import com.bindschaedel.annotations.IntegrationTest;
import com.bindschaedel.entity.Classification;
import com.google.common.collect.Iterables;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

@IntegrationTest
public class ClassificationServiceTest {

    @Autowired
    ClassificationService classificationService;

    @AfterEach
    public void cleanup() {
        classificationService.removeAll();
    }

    @Test
    public void canNotSaveNullAsClassification() {
        assertThat(classificationService.save(null)).isNull();
    }

    @Test
    public void canFindClassificationById() {
        Classification classification = new Classification();
        classification.setName("Test Classification");
        classification.setDescription("Test Description");
        Classification savedClassification = classificationService.save(classification);
        assertThat(classificationService.findById(savedClassification.getId())).isNotNull();
        assertThat(classificationService.findById(savedClassification.getId()).getName()).isEqualTo(classification.getName());
    }

    @Test
    public void canFindAll() {
        assertThat(Iterables.size(classificationService.getAll())).isZero();
        Classification classification = new Classification();
        classification.setName("Test Classification");
        classification.setDescription("Test Description");
        classificationService.save(classification);
        assertThat(Iterables.size(classificationService.getAll())).isOne();
    }
}
