package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.entities.Message;
import com.example.demo.entities.Role;
import com.example.demo.entities.User;

public interface MessageService {
	Message sendMessage(Message message);
	List<Message> getMessagesForUser(Long userId);
	  public List<User> getUsersByRole(Role role);
	    Optional<User> getAssignedAdminForClient(Long clientId);
	    
	 /*   public Message sendMessage(User sender, User receiver, String content);
		  
		   public List<Message> getMessagesForUser(User user) ;
		   public List<Message> getConversation(User user1, User user2);*/

}
