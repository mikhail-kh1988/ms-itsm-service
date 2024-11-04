package com.itsm.userservicemanagment.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Role {

    private Long id;
    private String name;
    private String description;

}
