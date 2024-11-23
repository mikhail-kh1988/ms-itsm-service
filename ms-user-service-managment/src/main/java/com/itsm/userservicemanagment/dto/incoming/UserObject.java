package com.itsm.userservicemanagment.dto.incoming;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserObject {

    private String family;
    private String name;
    private String fatherName;
    private String email;
    private String jobTitle;
    private boolean tech;
    private boolean active;
    private String language;
    private String timeZone;

}
