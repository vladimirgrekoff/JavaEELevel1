package com.grekoff.lesson12.repositories;

import com.grekoff.lesson12.entities.Role;
import com.grekoff.lesson12.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);
}
