package com.itsm.userservicemanagment.entity.incident;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class IncidentServiceDateManagement {

    private Long id;
    private ServiceDateManagement serviceDateManagement;
    private Incident incident;
    private LocalDateTime createDate;

}
