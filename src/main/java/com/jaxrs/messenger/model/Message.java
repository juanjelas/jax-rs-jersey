package com.jaxrs.messenger.model;

import java.time.LocalDateTime;

public class Message {

    private long id;
    private String message;
    private LocalDateTime created;
    private String autor;

    public Message() {
        created = LocalDateTime.now();
    }

    public Message(long id) {
        this.id = id;
        created = LocalDateTime.now();
    }

    public Message(long id, String message, String autor) {
        this.id = id;
        this.message = message;
        created = LocalDateTime.now();
        this.autor = autor;
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

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Message message = (Message) o;

        return id == message.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
