package com.itsm.userservicemanagment.dto.incoming.incident;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateIncidentStatuses {

    private int incidentStatus;
    private int incidentStatusReason;
    private int impact;
    private int priority;
    private Long changeById;

}
