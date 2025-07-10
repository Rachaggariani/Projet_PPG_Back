package com.example.demo.controllers;
  
  import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired; 
  import org.springframework.web.bind.annotation.CrossOrigin; 
  import org.springframework.web.bind.annotation.GetMapping; 
  import org.springframework.web.bind.annotation.PathVariable; 
  import org.springframework.web.bind.annotation.RequestMapping; 
  import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.UserDTO;
import com.example.demo.entities.ERole; 
  import com.example.demo.entities.User; 
  import com.example.demo.services.UserService;
  
  @RestController
  @RequestMapping("/api/users")
  @CrossOrigin(origins = "*") 
  public class UserController {
  
  @Autowired 
  private UserService service;
  
	@GetMapping("/by-role/{role}")
    public List<UserDTO> getUsersByRole(@PathVariable String role) {
		return service.getUsersByRoles(role)
                .stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
	}
  }
 