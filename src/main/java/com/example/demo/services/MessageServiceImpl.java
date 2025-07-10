/*
 * package com.example.demo.services;
 * 
 * import java.util.Date; import java.util.List; import java.util.Optional;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Service;
 * 
 * import com.example.demo.entities.Message; import
 * com.example.demo.entities.Role; import com.example.demo.entities.User; import
 * com.example.demo.repositories.MessageRepository; import
 * com.example.demo.repositories.UserRepository;
 * 
 * @Service public class MessageServiceImpl implements MessageService {
 * 
 * @Autowired private MessageRepository messageRepository;
 * 
 * @Autowired private UserRepository userRepository;
 * 
 * // Envoyer un message public Message sendMessage(Message message) { return
 * messageRepository.save(message); }
 * 
 * // Récupérer tous les messages d'un utilisateur (client ou admin) public
 * List<Message> getMessagesForUser(Long userId) { return
 * messageRepository.findMessagesBySender_IdOrReceiver_Id(userId, userId); }
 * 
 * // Récupérer les utilisateurs par rôle (Admin ou Client) public List<User>
 * getUsersByRole(Role role) { return userRepository.findByRole(role); }
 * 
 * @Override public Optional<User> getAssignedAdminForClient(Long clientId) {
 * List<Message> messages =
 * messageRepository.findMessagesBySender_IdOrReceiver_Id(clientId, clientId);
 * return messages.stream() .filter(m -> m.getSender().getRole() == Role.ADMIN
 * || m.getReceiver().getRole() == Role.ADMIN) .map(m -> m.getSender().getRole()
 * == Role.ADMIN ? m.getSender() : m.getReceiver()) .findFirst(); } }
 */