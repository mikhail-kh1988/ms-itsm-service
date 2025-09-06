package com.itsm.userservicemanagment.entity.problem;

import com.itsm.userservicemanagment.entity.User;
import com.itsm.userservicemanagment.entity.file.File;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProblemWorkToEliminate {

    private Long id;
    private String title;
    private File file;
    private LocalDateTime createDate;
    private User createBy;
    private EliminateTypeWork typeWork;

}
