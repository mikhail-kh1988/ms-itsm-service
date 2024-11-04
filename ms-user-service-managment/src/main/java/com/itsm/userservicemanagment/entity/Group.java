package com.itsm.userservicemanagment.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDateTime;


@Getter
@Setter
@Entity
public class Group {

    private Long id;
    private String name;
    private boolean active;
    private User owner;
    private String description;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private boolean approval;
    private boolean assignee;
    private boolean system;

}
