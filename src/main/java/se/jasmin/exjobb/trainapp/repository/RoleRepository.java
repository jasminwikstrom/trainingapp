package se.jasmin.exjobb.trainapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.jasmin.exjobb.trainapp.repository.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
