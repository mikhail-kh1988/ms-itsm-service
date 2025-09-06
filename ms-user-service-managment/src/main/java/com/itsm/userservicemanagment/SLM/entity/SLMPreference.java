package com.itsm.userservicemanagment.SLM.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
/*@Entity
@Table*/
public class SLMPreference {

    private Long id;
    private String name;
    private SLMEntity entity;
    private String description;
    private SLMPreferenceIncident incident;
    //private SLMPreferenceProblem problem;
    private Long hours;
    private Long minutes;
    private Long days;
    private Boolean isCritical;
    private Boolean isMass;



}
