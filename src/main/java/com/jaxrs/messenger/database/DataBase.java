package com.jaxrs.messenger.database;

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
    }

    public static Map<Long, Message> getMessages() {
        return messages;
    }

    public static Map<String, Profile> getProfiles() {
        return profiles;
    }

}
