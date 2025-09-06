package com.itsm.userservicemanagment.entity.incident;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "incident_servicedatemanagement")
public class IncidentServiceDateManagement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ServiceDateManagement serviceDateManagement;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Incident incident;

    private LocalDateTime createDate;

}
