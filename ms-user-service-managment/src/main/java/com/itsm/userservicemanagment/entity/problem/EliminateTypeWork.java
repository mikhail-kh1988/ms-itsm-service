package com.itsm.userservicemanagment.entity.problem;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class EliminateTypeWork {

    private Long id;
    private String name;
    private String describe;
    private LocalDateTime createDate;
}
