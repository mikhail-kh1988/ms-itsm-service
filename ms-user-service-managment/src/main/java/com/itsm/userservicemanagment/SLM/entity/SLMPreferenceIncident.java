package com.itsm.userservicemanagment.SLM.entity;

import com.itsm.userservicemanagment.entity.category.Category;
import com.itsm.userservicemanagment.entity.category.Impact;
import com.itsm.userservicemanagment.entity.category.Priority;
import com.itsm.userservicemanagment.entity.incident.IncidentStatus;
import com.itsm.userservicemanagment.entity.incident.IncidentStatusReason;
import com.itsm.userservicemanagment.entity.ke.ConfigurationElement;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
/*@Entity
@Table*/
public class SLMPreferenceIncident {

    private Long id;

    private Boolean isResolve;
    private Boolean isRequest;
    private Boolean isCritical;
    private Boolean isMass;
    //Statuses
    private IncidentStatus status;
    private IncidentStatusReason reason;
    private Impact impact;
    private Priority priority;
    //Categorisation
    private Category category;
    private ConfigurationElement configurationElement;
}
