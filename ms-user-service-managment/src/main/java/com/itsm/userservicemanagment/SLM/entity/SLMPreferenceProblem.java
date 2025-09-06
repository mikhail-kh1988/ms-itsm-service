package com.itsm.userservicemanagment.SLM.entity;

import com.itsm.userservicemanagment.entity.Group;
import com.itsm.userservicemanagment.entity.problem.ProblemReason;
import com.itsm.userservicemanagment.entity.problem.ProblemStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
/*@Entity
@Table*/
public class SLMPreferenceProblem {

    private Long id;

    private Group assigneeGroup;
    //Statuses
    private ProblemStatus status;
    private ProblemReason reason;

}
