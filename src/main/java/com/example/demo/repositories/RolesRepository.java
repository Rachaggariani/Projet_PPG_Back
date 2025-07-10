package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Roles;
import com.example.demo.entities.ERole;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {
  Optional<Roles> findByName(ERole name);
}
