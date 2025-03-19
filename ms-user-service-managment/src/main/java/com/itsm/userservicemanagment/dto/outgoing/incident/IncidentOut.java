package com.itsm.userservicemanagment.dto.outgoing.incident;


import com.itsm.userservicemanagment.entity.Contact;
import com.itsm.userservicemanagment.entity.Group;
import com.itsm.userservicemanagment.entity.User;
import com.itsm.userservicemanagment.entity.category.Category;
import com.itsm.userservicemanagment.entity.category.Impact;
import com.itsm.userservicemanagment.entity.category.Priority;
import com.itsm.userservicemanagment.entity.incident.IncidentStatus;
import com.itsm.userservicemanagment.entity.incident.IncidentStatusReason;
import com.itsm.userservicemanagment.entity.ke.ConfigurationElement;
import lombok.Getter;
import lombok.Lombok;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class IncidentOut {

    private String externalId;
    // Who create incident
    private Long contactId;
    private String contactFullName;
    private String createByUserFullName;
    private Long createByUserId;
    private String createByGroupName;
    private Long createByGroupId;
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
    private String status;
    private String reason;
    private String impact;
    private String priority;
    //Categorisation
    private Long categoryId;
    private String categoryName;
    private Long subCategoryId;
    private String subCategoryName;
    private Long configurationElementId;
    private String configurationElementName;
    //Assignee
    private String assigneeUserFullName;
    private Long assigneeUserId;
    private String ownerUserFullName;
    private Long ownerUserId;
    private Long assigneeGroupId;
    private String assigneeGroupName;
    //Date
    private LocalDateTime targetDate;
    private LocalDateTime createDate;
    private LocalDateTime lastChangeDate;

    private SLA sla;

    private List<Progress> progresses;
    private List<FilesAdded> filesAdded;


}
