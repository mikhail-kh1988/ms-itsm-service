package com.itsm.userservicemanagment.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.spi.LocaleNameProvider;

@Getter
@Setter
@Entity
public class UserRole {

    private Long id;
    private User user;
    private Role role;

}
