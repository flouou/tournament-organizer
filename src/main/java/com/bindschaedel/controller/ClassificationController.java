package com.bindschaedel.controller;

import com.bindschaedel.entity.Classification;
import com.bindschaedel.service.ClassificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClassificationController {

    private final ClassificationService classificationService;

    public ClassificationController(ClassificationService classificationService) {
        this.classificationService = classificationService;
    }

    @GetMapping("/classifications")
    public ResponseEntity<Iterable<Classification>> getAllClassifications() {
        return new ResponseEntity<>(classificationService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/classifications")
    public ResponseEntity<Classification> createClassification(@RequestBody Classification classification) {
        Classification savedClassification = classificationService.save(classification);
        return new ResponseEntity<>(savedClassification, HttpStatus.OK);
    }

}
