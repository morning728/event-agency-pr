package mirea.morning.eventagencypr.repository;

import mirea.morning.eventagencypr.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
