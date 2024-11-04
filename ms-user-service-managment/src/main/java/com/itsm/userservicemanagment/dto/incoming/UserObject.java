package com.itsm.userservicemanagment.dto.incoming;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserObject {

    private String fullName;
    private String email;
    private String jobTitle;
    private List<Long> roles;

}
