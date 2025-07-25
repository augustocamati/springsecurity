
package com.example.springsecurity.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "tweets")
public class Tweet {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String content;
  private User author;

  private Instant createdAt;

  

  public Tweet() {
}

  public Tweet(Long id, String content, User author, Instant createdAt) {
    this.id = id;
    this.content = content;
    this.author = author;
    this.createdAt = createdAt;
}

  // Getters and Setters
  public Long getId() {
      return id;
  }

  public void setId(Long id) {
      this.id = id;
  }
  public Instant getCreatedAt() {
    return createdAt;
}

  public void setCreatedAt(Instant createdAt) {
    this.createdAt = createdAt;
  }


  public String getContent() {
      return content;
  }

  public void setContent(String content) {
      this.content = content;
  }

  public User getAuthor() {
      return author;
  }

  public void setAuthor(User author) {
      this.author = author;
  }
}
