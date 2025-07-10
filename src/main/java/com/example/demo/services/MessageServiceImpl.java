package com.example.demo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Message;
import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.repositories.MessageRepository;
import com.example.demo.repositories.UserRepository;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;
    
    @Autowired
    private UserRepository userRepository;

    // Envoyer un message
    public Message sendMessage(Message message) {
        return messageRepository.save(message);
    }

    // Récupérer tous les messages d'un utilisateur (client ou admin)
    public List<Message> getMessagesForUser(Long userId) {
        return messageRepository.findMessagesBySender_IdOrReceiver_Id(userId, userId);
    }

    // Récupérer les utilisateurs par rôle (Admin ou Client)
    public List<User> getUsersByRole(Role role) {
        return userRepository.findByRole(role);
    }

	@Override
	public Optional<User> getAssignedAdminForClient(Long clientId) {
		   List<Message> messages = messageRepository.findMessagesBySender_IdOrReceiver_Id(clientId, clientId);
	        return messages.stream()
	            .filter(m -> m.getSender().getRole() == Role.ADMIN || m.getReceiver().getRole() == Role.ADMIN)
	            .map(m -> m.getSender().getRole() == Role.ADMIN ? m.getSender() : m.getReceiver())
	            .findFirst();
	}
}
/*	  @Autowired
private MessageRepository messageRepository;

@Autowired
private UserRepository userRepository;

public Message sendMessage(User sender, User receiver, String content) {
    // Vérification des rôles
    boolean isAdmin = sender.getRoles().stream().anyMatch(role -> role.getName() == ERole.ADMIN);
    boolean isClientSender = sender.getRoles().stream().anyMatch(role -> role.getName() == ERole.CLIENT);
    boolean isClientReceiver = receiver.getRoles().stream().anyMatch(role -> role.getName() == ERole.CLIENT);

    if ((isAdmin && isClientReceiver) || 
        (isClientSender && receiver.getRoles().stream().anyMatch(role -> role.getName() == ERole.ADMIN))) {
        
        Message message = new Message();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setContent(content);
        message.setTimestamp(new Date());
        
        return messageRepository.save(message);
    } else {
        throw new RuntimeException("Les règles d'envoi de message ne sont pas respectées");
    }
}

public List<Message> getMessagesForUser(User user) {
    return messageRepository.findBySenderOrReceiverOrderByTimestampDesc(user, user);
}

public List<Message> getConversation(User user1, User user2) {
    // Vérification des règles de conversation
    boolean isAdmin1 = user1.getRoles().stream().anyMatch(role -> role.getName() == ERole.ADMIN);
    boolean isAdmin2 = user2.getRoles().stream().anyMatch(role -> role.getName() == ERole.ADMIN);
    boolean isClient1 = user1.getRoles().stream().anyMatch(role -> role.getName() == ERole.CLIENT);
    boolean isClient2 = user2.getRoles().stream().anyMatch(role -> role.getName() == ERole.CLIENT);

    // Un admin peut parler avec un client et vice versa
    // Deux clients ne peuvent pas parler entre eux
    if ((isAdmin1 && isClient2) || (isAdmin2 && isClient1) || 
        (isAdmin1 && isAdmin2) || (isClient1 && isAdmin2) || (isClient2 && isAdmin1)) {
        return messageRepository.findConversation(user1, user2);
    } else {
        throw new RuntimeException("Vous n'êtes pas autorisé à voir cette conversation");
    }
}*/