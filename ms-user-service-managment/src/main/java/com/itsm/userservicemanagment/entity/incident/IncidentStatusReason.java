package com.itsm.userservicemanagment.entity.incident;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "incident_status_reason")
public class IncidentStatusReason {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int code;
    private String name;
    private Boolean resolved;
    private LocalDateTime createDate;
    private Boolean active;

}
