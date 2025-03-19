package com.itsm.userservicemanagment.dto.incoming.incident;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateIncidentStatuses {

    private Long incidentStatus;
    private Long incidentStatusReason;
    private int impact;
    private int priority;

}
