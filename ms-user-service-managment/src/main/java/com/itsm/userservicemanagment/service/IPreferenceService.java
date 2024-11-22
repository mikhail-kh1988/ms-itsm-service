package com.itsm.userservicemanagment.service;


public interface IPreferenceService {
    void createGlobalPreference(String name);
    void createPreference(String key, String value, Long globalID);
    boolean updatePreference(String key, String value, Long globalID);
}
