package com.itsm.userservicemanagment.entity.incident;

import com.itsm.userservicemanagment.entity.category.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IncidentTemplate {


    private Long id;
    private String templateName;
    private String description;
    private Long KE;
    private Category category;
    private Long assigneeGroupId;
    private Long assigneeUserId;
    private IncidentStatus status;
    private IncidentStatusReason reason;

}
