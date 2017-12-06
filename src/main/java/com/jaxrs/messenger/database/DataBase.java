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
        messages.put(1L, new Message(1L, "Buenos dias", "Manolis"));
        messages.put(2L, new Message(2L, "Buenos noches", "Manolis"));

        profiles.put("develop", new Profile(1L, "develop", "Paquito", "Chocolatero"));
        profiles.put("manager", new Profile(2L, "manager", "Juanito", "Palotes"));

        messages.get(1L).getComments().add(new Comment(1L, "Este es mi comentario", "Manolis"));
        messages.get(1L).getComments().add(new Comment(2L, "Nuevo comentario", "Manolis"));
        messages.get(2L).getComments().add(new Comment(1L, "Este es mi comentario mensaje 2", "Manolis"));
        messages.get(2L).getComments().add(new Comment(2L, "Nuevo comentario mensaje 2", "Manolis"));

    }

    public static Map<Long, Message> getMessages() {
        return messages;
    }

    public static Map<String, Profile> getProfiles() {
        return profiles;
    }

}
