package com.itsm.userservicemanagment.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class RoleAccess {

    private Long id;
    private String path;
    private Role role;

}
