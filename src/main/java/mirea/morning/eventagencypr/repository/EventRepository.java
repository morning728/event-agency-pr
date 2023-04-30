package mirea.morning.eventagencypr.repository;

import mirea.morning.eventagencypr.model.Event;
import mirea.morning.eventagencypr.model.enums.EventType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {
    Event findByName(String name);

    List<Event> findByType(EventType type);

    @Query(
            value = "SELECT * FROM EVENTS e WHERE e.price_for_person >= :minPrice" +
                    " AND e.price_for_person <= :maxPrice",
            nativeQuery = true)
    List<Event> findByPriceForPerson(Long minPrice, Long maxPrice);

    @Query(
            value = "SELECT * FROM EVENTS e WHERE e.minimum_price >= :minPrice" +
                    " AND e.minimum_price <= :maxPrice",
            nativeQuery = true)
    List<Event> findByMinPrice(Long minPrice, Long maxPrice);
}
