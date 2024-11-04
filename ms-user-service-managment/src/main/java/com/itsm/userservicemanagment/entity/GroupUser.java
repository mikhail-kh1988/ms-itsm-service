package com.itsm.userservicemanagment.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class GroupUser {

    private Long id;
    private Group group;
    private User user;
    private LocalDateTime createDate;
    private User createBy;

}
