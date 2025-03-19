package com.itsm.userservicemanagment.dto.incoming.incident;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateIncidentAssignee {

    private Long assigneeUserId;
    private Long ownerId;
    private Long assigneeGroupId;
}
