/*
 * package com.example.demo.repositories;
 * 
 * import java.util.List;
 * 
 * import org.springframework.data.jpa.repository.JpaRepository; import
 * org.springframework.stereotype.Repository;
 * 
 * import com.example.demo.entities.Message;
 * 
 * @Repository public interface MessageRepository extends JpaRepository<Message,
 * Long> {
 * 
 * // Récupérer les messages envoyés ou reçus par un utilisateur donné
 * List<Message> findMessagesBySender_IdOrReceiver_Id(Long senderId, Long
 * receiverId);
 * 
 * // Récupérer les messages envoyés par un admin à un client spécifique (si
 * nécessaire) List<Message> findMessagesBySender_RoleAndReceiver_Id(String
 * senderRole, Long receiverId); }
 */