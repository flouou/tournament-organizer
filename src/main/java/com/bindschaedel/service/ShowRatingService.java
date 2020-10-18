package com.bindschaedel.service;

import com.bindschaedel.entity.ShowRating;
import com.bindschaedel.repository.ShowRatingRepository;
import org.springframework.stereotype.Service;

@Service
public class ShowRatingService {

    final ShowRatingRepository showRatingRepository;

    public ShowRatingService(ShowRatingRepository showRatingRepository) {
        this.showRatingRepository = showRatingRepository;
    }

    public void delete(Long id) {
        showRatingRepository.deleteById(id);
    }

    public ShowRating save(ShowRating showRating) {
        return showRatingRepository.save(showRating);
    }

    public void deleteByGroupId(Long id) {
        showRatingRepository.deleteByGroupId(id);
    }
}
