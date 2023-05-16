package lms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import lms.entities.Role;

public interface RoleRepository extends JpaRepository<Role, String>{

}
