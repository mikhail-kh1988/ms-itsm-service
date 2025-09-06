package com.itsm.userservicemanagment.entity.problem;

import com.itsm.userservicemanagment.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProblemListWorkEliminate {

    private Long id;
    private Problem problem;
    private ProblemWorkToEliminate workEliminate;
    private LocalDateTime createDate;
    private User createBy;

}
