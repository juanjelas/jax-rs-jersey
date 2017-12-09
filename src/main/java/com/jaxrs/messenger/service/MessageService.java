package com.jaxrs.messenger.service;

import com.jaxrs.messenger.database.DataBase;
import com.jaxrs.messenger.exception.DataNotFoundException;
import com.jaxrs.messenger.model.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class MessageService {

    private final Map<Long, Message> messageMap = DataBase.getMessages();

    public List<Message> getAllMessages() {
        return new ArrayList<>(messageMap.values());
    }

    public List<Message> getAllMessageForYear(int year) {
        return messageMap.values().stream().filter(d -> d.getCreated().getYear() == year).collect(Collectors.toList());
    }

    public List<Message> getAllMessagePaginated(int start, int size) {
        List<Message> listMessage = new ArrayList<>(messageMap.values());
        if (start + size > messageMap.size()) {
            return listMessage;
        } else {
            return listMessage.subList(start, start + size);
        }
    }

    public Message getMessage(long id) {
        if (messageMap.containsKey(id)) {
            return messageMap.get(id);
        } else {
            throw new DataNotFoundException("Elemento no encontrado");
        }
    }

    public Message addMessage(Message message) {
        message.setId(messageMap.size() + 1);
        messageMap.put(message.getId(), message);
        return message;
    }

    public Optional<Message> updateMessage(Message message) {
        if (message.getId() <= 0) {
            return Optional.empty();
        }
        messageMap.put(message.getId(), message);
        return Optional.of(message);
    }

    public void deleteMessage(long id) {
        messageMap.remove(id);
    }
}
