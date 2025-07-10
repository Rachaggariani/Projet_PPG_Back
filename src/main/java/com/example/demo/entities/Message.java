/*
 * package com.example.demo.entities;
 * 
 * import java.util.Date;
 * 
 * import jakarta.persistence.Entity; import jakarta.persistence.GeneratedValue;
 * import jakarta.persistence.GenerationType; import jakarta.persistence.Id;
 * import jakarta.persistence.JoinColumn; import jakarta.persistence.ManyToOne;
 * import lombok.AllArgsConstructor; import lombok.NoArgsConstructor;
 * 
 * @AllArgsConstructor
 * 
 * @NoArgsConstructor
 * 
 * @Entity public class Message {
 * 
 * @Id
 * 
 * @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
 * 
 * @ManyToOne
 * 
 * @JoinColumn(name = "sender_id") private User sender;
 * 
 * @ManyToOne
 * 
 * @JoinColumn(name = "receiver_id") private User receiver;
 * 
 * private String content;
 * 
 * private Date timestamp;
 * 
 * 
 * 
 * // Getters et Setters
 * 
 * public Long getId() { return id; }
 * 
 * public void setId(Long id) { this.id = id; }
 * 
 * public User getSender() { return sender; }
 * 
 * public void setSender(User sender) { this.sender = sender; }
 * 
 * public User getReceiver() { return receiver; }
 * 
 * public void setReceiver(User receiver) { this.receiver = receiver; }
 * 
 * public String getContent() { return content; }
 * 
 * public void setContent(String content) { this.content = content; }
 * 
 * public Date getTimestamp() { return timestamp; }
 * 
 * public void setTimestamp(Date timestamp) { this.timestamp = timestamp; } }
 */