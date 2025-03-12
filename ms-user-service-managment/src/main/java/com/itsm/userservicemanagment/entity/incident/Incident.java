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
    private Contact contact;
    private String externalTicketNumber;
    private String title;
    private String body;
    private String Resolution;
    private Impact impact;
    private Priority priority;
    private Category category;
    private Boolean isMass;
    private ConfigurationElement configurationElement;
    private User assignee;
    private Group assigneeGroup;
    private LocalDateTime targetDate;
    private LocalDateTime createDate;



}
