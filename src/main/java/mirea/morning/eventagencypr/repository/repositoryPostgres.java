package mirea.morning.eventagencypr.repository;

import mirea.morning.eventagencypr.model.Order1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface repositoryPostgres extends JpaRepository<Order1, Long> {

    @Query(
            value = "SELECT * FROM mirea.orders o " +
                    "WHERE o.creation_date = :cr",
            nativeQuery = true)
    List<Order1> findAllByCreationDate(String cr);
}
