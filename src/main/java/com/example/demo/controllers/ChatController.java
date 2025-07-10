package com.example.demo.controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Message;
import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.services.MessageService;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ChatController {
	@Autowired
	private UserService userService;

	@Autowired
	private MessageService messageService;
	@PostMapping("/message")
    public ResponseEntity<Message> sendMessage(@RequestBody Message message) {
        User sender = message.getSender();
        User receiver = message.getReceiver();

        // Vérification que le client ne contacte que l'admin
        if (sender.getRole() == Role.CLIENT) {
            if (receiver.getRole() != Role.ADMIN) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
            }

            // Vérifier si le client a déjà discuté avec un admin
            Optional<User> existingAdmin = messageService.getAssignedAdminForClient(sender.getId());

            if (existingAdmin.isPresent() && !existingAdmin.get().getId().equals(receiver.getId())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
            }
        }

        // (Optionnel) auto-affectation d’un admin si aucun encore attribué
        if (sender.getRole() == Role.CLIENT && message.getReceiver() == null) {
            Optional<User> admin = userService.getUsersByRole(Role.ADMIN).stream().findFirst();
            if (admin.isPresent()) {
                message.setReceiver(admin.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        }

        // Sauvegarde
        Message sentMessage = messageService.sendMessage(message);
        return ResponseEntity.ok(sentMessage);
    }

    // Récupérer les messages d’un utilisateur
    @GetMapping("/messages/{userId}")
    public List<Message> getMessages(@PathVariable Long userId) {
        return messageService.getMessagesForUser(userId);
    }

    // Récupérer les utilisateurs par rôle
    @GetMapping("/users/{role}")
    public List<User> getUsersByRole(@PathVariable String role) {
        return messageService.getUsersByRole(Role.valueOf(role));
    }

    // Ajouter un utilisateur
    @PostMapping("/users/")
    public User addUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    // Récupérer tous les utilisateurs
    @GetMapping("/Allusers")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }
}
