package com.itsm.userservicemanagment.dto.incoming.incident;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProgressAdd {

    private String incidentId;
    private String comment;
    private Long createById;
    private String action;
}
