package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Role;
import com.example.demo.entities.Roles;
import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;

@Service
public class UserServiceImpl  implements UserService{
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<User> getAllUsers() {
	    return userRepository.findAll();
	}
	@Override
	public Optional<User> getUserById(Long id) {
	    return userRepository.findById(id);
	}
	@Override
	public User saveUser(User user) {
	    return userRepository.save(user);
	}
	@Override
	public List<User> getUsersByRole(Role role) {
        return userRepository.findByRole(role); // ✅ méthode du repository

	}

@Override
public List<User> getUsersByRoles(String role) {
    try {
        Role enumRole = Role.valueOf(role.toUpperCase()); // assuming all enums are uppercase
        return userRepository.findByRole(enumRole);
    } catch (IllegalArgumentException e) {
        throw new RuntimeException("Invalid role: " + role);
    }
}

}
