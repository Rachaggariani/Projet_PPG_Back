package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Permission;
import com.example.demo.entities.User;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    List<Permission> findByUser(User user);
    Optional<Permission> findByUserAndInterfaceName(User user, String interfaceName);
}