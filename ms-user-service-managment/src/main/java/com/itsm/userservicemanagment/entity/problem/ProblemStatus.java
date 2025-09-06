package com.itsm.userservicemanagment.entity.problem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProblemStatus {

    private Long id;
    private int code;
    private String status;
    private Boolean active;

}
