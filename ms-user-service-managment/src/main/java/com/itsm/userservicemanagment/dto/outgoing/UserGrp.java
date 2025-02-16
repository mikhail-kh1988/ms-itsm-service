package com.itsm.userservicemanagment.dto.outgoing;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserGrp {

    private Long id;
    private Boolean tech;
    private String fullName;
    private String jobTitle;
    private String login;
    private String email;
}
