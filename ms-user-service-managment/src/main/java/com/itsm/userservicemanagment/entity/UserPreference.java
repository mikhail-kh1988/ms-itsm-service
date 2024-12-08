package com.itsm.userservicemanagment.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.List;

@Getter
@Setter
//@Entity
public class UserPreference {

    private Long id;
    private User user;
    private GlobalPreference globalPreference;
    private List<Preference> preferences;

}
