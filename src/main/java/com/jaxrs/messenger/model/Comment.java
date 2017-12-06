package com.jaxrs.messenger.model;

import java.time.LocalDateTime;

public class Comment {

    private long id;
    private String message;
    private LocalDateTime created;
    private String author;

    public Comment() {
    }

    public Comment(long id, String message, String author) {
        this.id = id;
        this.message = message;
        created = LocalDateTime.now();
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
