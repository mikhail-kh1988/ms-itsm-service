package com.itsm.userservicemanagment.entity.preference;

import com.itsm.userservicemanagment.entity.User;
import lombok.Getter;
import lombok.Setter;

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
