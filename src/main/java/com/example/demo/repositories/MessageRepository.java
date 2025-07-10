package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    // Récupérer les messages envoyés ou reçus par un utilisateur donné
    List<Message> findMessagesBySender_IdOrReceiver_Id(Long senderId, Long receiverId);

    // Récupérer les messages envoyés par un admin à un client spécifique (si nécessaire)
    List<Message> findMessagesBySender_RoleAndReceiver_Id(String senderRole, Long receiverId);
    
    /*  List<Message> findBySenderOrReceiverOrderByTimestampDesc(User sender, User receiver);
    List<Message> findBySender(User sender);
    List<Message> findByReceiver(User receiver);
    
    // Nouvelle méthode pour récupérer une conversation entre deux utilisateurs
    @Query("SELECT m FROM Message m WHERE " +
           "(m.sender = :user1 AND m.receiver = :user2) OR " +
           "(m.sender = :user2 AND m.receiver = :user1) " +
           "ORDER BY m.timestamp ASC")
    List<Message> findConversation(@Param("user1") User user1, @Param("user2") User user2);*/
}