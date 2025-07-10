package com.example.demo.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.PermissionDTO;
import com.example.demo.dtos.UserDTO;
import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.services.PermissionService;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

    @Autowired
    private PermissionService service;
    @Autowired
    private UserService userService;
    
    @PostMapping("/save")
    public ResponseEntity<Map<String, String>> savePermissions(@RequestBody List<PermissionDTO> permissionsDto) {
        service.savePermissions(permissionsDto);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Permissions enregistrées");
        return ResponseEntity.ok(response);
    }


    @GetMapping("/user/{id}")
    public ResponseEntity<List<PermissionDTO>> getPermissionsForUser(@PathVariable Long id) {
        return ResponseEntity.ok(service.getPermissionsForUser(id));
    }
    
}