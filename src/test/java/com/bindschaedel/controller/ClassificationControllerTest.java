package com.bindschaedel.controller;

import com.bindschaedel.entity.Classification;
import com.bindschaedel.service.ClassificationService;
import com.google.common.collect.Iterables;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClassificationControllerTest {

    @InjectMocks
    ClassificationController classificationController;

    @Mock
    ClassificationService classificationService;

    @Test
    public void testGetAllClubs() {
        Classification class1 = new Classification();
        class1.setName("Class1");
        class1.setDescription("description");
        Classification class2 = new Classification();
        class2.setName("Class2");
        class2.setDescription("description");
        Iterable<Classification> classifications = Arrays.asList(class1, class2);
        when(classificationService.getAll()).thenReturn(classifications);
        ResponseEntity<Iterable<Classification>> responseEntity = classificationController.getAllClassifications();

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(Iterables.size(responseEntity.getBody())).isEqualTo(2);
    }
}
