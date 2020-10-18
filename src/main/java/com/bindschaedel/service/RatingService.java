package com.bindschaedel.service;

import com.bindschaedel.entity.Rating;
import com.bindschaedel.repository.RatingRepository;
import org.springframework.stereotype.Service;

@Service
public class RatingService {

    final RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public void save(Rating rating) {
        ratingRepository.save(rating);
    }
}
