package com.jaxrs.messenger.database;

import com.jaxrs.messenger.model.Comment;
import com.jaxrs.messenger.model.Message;
import com.jaxrs.messenger.model.Profile;

import java.util.HashMap;
import java.util.Map;

public class DataBase {

    private static final Map<Long, Message> messages = new HashMap<>();
    private static final Map<String, Profile> profiles = new HashMap<>();

    static {
        messages.put(1L, new Message(1L, "Buenos dias", "paquito"));
        messages.put(2L, new Message(2L, "Buenos noches", "juanito"));

        profiles.put("paquito", new Profile(1L, "paquito", "Fran", "Chocolatero"));
        profiles.put("juanito", new Profile(2L, "juanito", "Juan", "Palotes"));

        messages.get(1L).getComments().put(1L, new Comment(1L, "Este es mi comentario", "Manolis"));
        messages.get(1L).getComments().put(2L, new Comment(2L, "Nuevo comentario", "Manolis"));
        messages.get(2L).getComments().put(1L, new Comment(1L, "Este es mi comentario mensaje 2", "Manolis"));
        messages.get(2L).getComments().put(2L, new Comment(2L, "Nuevo comentario mensaje 2", "Manolis"));

    }

    public static Map<Long, Message> getMessages() {
        return messages;
    }

    public static Map<String, Profile> getProfiles() {
        return profiles;
    }

}
