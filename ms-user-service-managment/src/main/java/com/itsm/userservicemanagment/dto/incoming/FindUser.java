package com.itsm.userservicemanagment.dto.incoming;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindUser {
    private String login;
    private String name;
}
