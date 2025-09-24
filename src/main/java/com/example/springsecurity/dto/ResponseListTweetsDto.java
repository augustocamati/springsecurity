package com.example.springsecurity.dto;

import java.time.Instant;

public record ResponseListTweetsDto(
        Long id,
        String content,
        Instant createdAt,
        String username
        ) {
    public ResponseListTweetsDto(Long id, String content, Instant createdAt, String username) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.username = username;
    }
}
