package com.itsm.userservicemanagment.dto.incoming.incident;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateIncidentAssignee {

    private String incidentId;
    private Long assigneeUserId;
    private Long assigneeGroupId;
    private Long ownerGroupId;
    private Long ownerUserId;
    private Long changeByUserId;
}
