package com.jaxrs.messenger.service;

import com.jaxrs.messenger.database.DataBase;
import com.jaxrs.messenger.model.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProfileService {

    private final Map<String, Profile> profilesMap = DataBase.getProfiles();

    public List<Profile> getAllProfiles() {
        return new ArrayList<>(profilesMap.values());
    }

    public Profile getProfile(String profile) {
        return profilesMap.get(profile);
    }

    public Profile addProfile(Profile profile) {
        profile.setId(profilesMap.size() + 1);
        profilesMap.put(profile.getProfileName(), profile);
        return profile;
    }

    public Profile updateProfile(Profile profile) {
        if (profile.getProfileName().isEmpty()) {
            return null;
        }
        profilesMap.put(profile.getProfileName(), profile);
        return profile;
    }

    public void deleteProfile(String profileName) {
        profilesMap.remove(profileName);
    }
}
