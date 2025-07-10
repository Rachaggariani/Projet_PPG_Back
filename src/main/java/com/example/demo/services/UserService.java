package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.entities.Role;
import com.example.demo.entities.User;

public interface UserService {
	List<User> getAllUsers();
	Optional<User> getUserById(Long id);
	User saveUser(User user);
    List<User> getUsersByRole(Role role);

}
