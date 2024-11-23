package com.itsm.userservicemanagment.service;


import com.itsm.userservicemanagment.dto.outgoing.Result;

public interface IPreferenceService {

    Result createGlobalPreference(String name);
    Result createPreference(String key, String value, Long globalID);
    Result updatePreference(String key, String value, Long globalID, Long prefId);

}
