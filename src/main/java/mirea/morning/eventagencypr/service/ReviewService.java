package mirea.morning.eventagencypr.service;

import mirea.morning.eventagencypr.model.Review;

import java.util.List;

public interface ReviewService {

    Review findById(Long id);

    Review addReview(Review review);

    List<Review> findAll();
}
