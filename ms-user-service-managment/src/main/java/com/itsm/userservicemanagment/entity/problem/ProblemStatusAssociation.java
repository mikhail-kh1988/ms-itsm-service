package com.itsm.userservicemanagment.entity.problem;

import com.itsm.userservicemanagment.entity.category.Category;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProblemStatusAssociation {

    private Long id;
    private Problem problem;
    private ProblemStatus status;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

}
