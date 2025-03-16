package com.itsm.userservicemanagment.entity.incident;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ServiceDateManagement {

    private Long id;
    private LocalDateTime createDate;
    private LocalDateTime targetDate;
    private LocalDateTime changeDate;

    //Target waiting
    private Boolean statusWaiting;
    private Long pendingTime;
    private String typePendingTime;

    //Target working
    private Long targetTimeWorking;
    private String describeTargetTimeWorking;

    // Просрочка
    private Boolean overDate;

}
