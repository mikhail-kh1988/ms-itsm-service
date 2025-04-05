package com.itsm.userservicemanagment.entity.problem;

import com.itsm.userservicemanagment.entity.Contact;
import com.itsm.userservicemanagment.entity.Group;
import com.itsm.userservicemanagment.entity.User;
import com.itsm.userservicemanagment.entity.category.Category;
import com.itsm.userservicemanagment.entity.ke.ConfigurationElement;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Problem {

    private Long id;
    // Create by
    private User createByUser;
    private Group createByGroup;
    private String externalTicketNumber;
    private String externalId;
    //Body
    private String title;
    private String description;
    private String rootInvestigation;
    private String workAround;
    private String Resolution;
    //Categorisation
    private Category category;
    private ConfigurationElement configurationElement;
    //Assignee
    private User assignee;
    private User owner;
    private Group assigneeGroup;
    //Statuses and lifecycle
    private ProblemLifecycle lifecycle;
    private ProblemStatus status;
    private ProblemType type;
    //Dates
    private LocalDateTime createDate;



}
