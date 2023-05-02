package mirea.morning.eventagencypr.repository;

import mirea.morning.eventagencypr.model.Review;
import mirea.morning.eventagencypr.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Review findByName(String name);
}
