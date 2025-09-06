package com.itsm.userservicemanagment.entity.incident;

import com.itsm.userservicemanagment.entity.Group;
import com.itsm.userservicemanagment.entity.User;
import com.itsm.userservicemanagment.entity.category.Category;
import com.itsm.userservicemanagment.entity.category.Impact;
import com.itsm.userservicemanagment.entity.category.Priority;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IncidentTemplate {

    private Long id;
    private String templateName;
    private String description;
    //This is configuration Element;
    private Long KE;
    private Category category;
    private Group assigneeGroup;
    private User assigneeUser;
    private IncidentStatus status;
    private IncidentStatusReason reason;
    private Impact impact;
    private Priority priority;
    private Boolean isRequest;
    private Boolean isCritical;
    private Boolean isMass;
    private User owner;


}
