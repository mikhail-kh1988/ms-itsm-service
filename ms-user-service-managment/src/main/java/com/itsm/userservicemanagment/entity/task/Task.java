package com.itsm.userservicemanagment.entity.task;

import com.itsm.userservicemanagment.entity.Group;
import com.itsm.userservicemanagment.entity.User;
import com.itsm.userservicemanagment.entity.category.Priority;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String externalId;
    private String title;
    private String body;
    private Priority priority;
    private StatusTask status;

    @Column(name = "type_task")
    private TypeTask type;
    private String assigneeUserName;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User assigneeUser;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Group assigneeGroup;

    private String assigneeGroupName;
    private String loginCreated;
    private LocalDateTime createDate;
    private LocalDateTime lastModifyDate;
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;
    private String lastModifyLogin;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User owner;

}
