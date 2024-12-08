package com.itsm.userservicemanagment.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean active;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User owner;

    private String description;
    private String timeZone;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private LocalDateTime workPeriodTo;
    private LocalDateTime workPeriodFrom;
    private boolean approval;
    private boolean assignee;
    private boolean system;
    private boolean notification;

}
