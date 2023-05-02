package mirea.morning.eventagencypr.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mirea.morning.eventagencypr.model.Exception.OrderNotFoundException;
import mirea.morning.eventagencypr.model.Order;
import mirea.morning.eventagencypr.model.Review;
import mirea.morning.eventagencypr.repository.ReviewRepository;
import mirea.morning.eventagencypr.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    @Override
    public Review findById(Long id) {
        Optional<Review> result = reviewRepository.findById(id);
        log.info(
                result.isPresent() ?
                        "ReviewService.findById() - review with id " + id + " was found" :
                        "ReviewService.findById() - review with id " + id + " was not found"
        );
        if (result.isEmpty()) throw new OrderNotFoundException("ReviewService.findById() - " +
                "review with id " + id + " was not found");
        return result.get();
    }

    @Override
    public Review addReview(Review review) {
        Review result = reviewRepository.save(review);
        log.info("ReviewService.addOrder() - Review with id {} was added", result.getId());
        return result;
    }

    @Override
    public List<Review> findAll() {
        List<Review> result = reviewRepository.findAll();
        log.info("ReviewService.findAll() - {} reviews found", result.size());
        return result;
    }
}
