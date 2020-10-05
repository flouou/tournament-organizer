package com.bindschaedel.service;

import com.bindschaedel.entity.Classification;
import com.bindschaedel.repository.ClassificationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClassificationService {

    final ClassificationRepository classificationRepository;

    public ClassificationService(
            ClassificationRepository classificationRepository) {
        this.classificationRepository = classificationRepository;
    }

    public Classification findById(Long id) {
        Optional<Classification> classification = classificationRepository.findById(id);
        return classification.orElse(null);
    }

    public Classification save(Classification classification) {
        if (classification != null) {
            return classificationRepository.save(classification);
        }
        else {
            return null;
        }
    }

    public void removeAll() {
        classificationRepository.deleteAll();
    }
}
