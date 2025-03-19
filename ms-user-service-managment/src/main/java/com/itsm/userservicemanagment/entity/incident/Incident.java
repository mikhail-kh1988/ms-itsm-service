package com.itsm.userservicemanagment.entity.incident;

import com.itsm.userservicemanagment.entity.Contact;
import com.itsm.userservicemanagment.entity.Group;
import com.itsm.userservicemanagment.entity.User;
import com.itsm.userservicemanagment.entity.category.Category;
import com.itsm.userservicemanagment.entity.category.Impact;
import com.itsm.userservicemanagment.entity.category.Priority;
import com.itsm.userservicemanagment.entity.ke.ConfigurationElement;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class Incident {

    private Long id;
    private String externalId;
    // Who create incident
    private Contact contact;
    private User createByUser;
    private Group createByGroup;
    private String externalTicketNumber;
    //Body incident
    private String title;
    private String body;
    private String Resolution;
    private Boolean isResolve;
    private Boolean isRequest;
    private Boolean isCritical;
    private Boolean isMass;
    //Statuses
    private IncidentStatus status;
    private IncidentStatusReason reason;
    private Impact impact;
    private Priority priority;
    //Categorisation
    private Category category;
    private ConfigurationElement configurationElement;
    //Assignee
    private User assignee;
    private User owner;
    private Group assigneeGroup;
    //Date
    private LocalDateTime targetDate;
    private LocalDateTime createDate;
    private LocalDateTime lastChangeDate;


}
