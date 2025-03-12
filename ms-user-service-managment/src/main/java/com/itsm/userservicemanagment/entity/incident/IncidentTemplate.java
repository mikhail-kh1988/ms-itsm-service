package com.itsm.userservicemanagment.entity.incident;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IncidentTemplate {


    private Long id;
    private String description;
    private Long KE;
    private Long assigneeGroupId;

}
