package com.itsm.userservicemanagment.dto.outgoing.incident;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SLA {

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

    // OverTime
    private Boolean overDate;


}
