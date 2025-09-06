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

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@Table(name = "incident")
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "external_id")
    private String externalId;
    // Who create incident
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Contact contact;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User createByUser;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User modifyBy;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Group createByGroup;

    private String ticketNumber;
    //Body incident
    private String title;
    private String body;
    private String Resolution;
    private Boolean isWasResolved;
    private Integer countResolved;
    private Boolean isResolve;
    private Boolean isRequest;
    private Boolean isCritical;
    private Boolean isMass;
    //Statuses
    private IncidentStatus status;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private IncidentStatusReason reason;

    private Impact impact;
    private Priority priority;

    //Categorisation
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Category category;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ConfigurationElement configurationElement;
    //Assignee

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User owner;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Group ownerGroup;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User assignee;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Group assigneeGroup;
    //Date
    private LocalDateTime targetDate;
    private LocalDateTime createDate;
    private LocalDateTime lastChangeDate;


}
