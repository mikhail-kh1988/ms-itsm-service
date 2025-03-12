package com.itsm.userservicemanagment.dto.incoming.incident;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewIncidentByTemplate {

    private Long categoryId;
    private String body;
    private Long templateId;
    private Boolean isMass;
    private int priority;
    private int impact;

}
