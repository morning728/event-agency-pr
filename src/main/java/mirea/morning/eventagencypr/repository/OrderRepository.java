package mirea.morning.eventagencypr.repository;

import mirea.morning.eventagencypr.model.Order;
import mirea.morning.eventagencypr.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByName(String name);
}
