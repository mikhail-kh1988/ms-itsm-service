package com.itsm.userservicemanagment.dto.incoming;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GroupObject {

    private String name;
    private Long ownerId;
    private String description;
    private boolean approval;
    private boolean assignee;
    private boolean system;
    private List<Long> users;

}
