package com.itsm.userservicemanagment.security.jwt;

import lombok.Data;

@Data
public class AuthRequest {

    private String login;
    private String password;

}
