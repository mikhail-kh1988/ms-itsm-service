package com.itsm.userservicemanagment.entity.problem;

import com.itsm.userservicemanagment.entity.User;
import com.itsm.userservicemanagment.entity.incident.Incident;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProblemIncidentAssociation {

    private Long id;
    private Problem problem;
    private Incident incident;
    private LocalDateTime createDate;
    private User createBy;

}
