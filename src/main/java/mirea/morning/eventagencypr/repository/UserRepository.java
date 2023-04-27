package mirea.morning.eventagencypr.repository;

import mirea.morning.eventagencypr.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String name);
}