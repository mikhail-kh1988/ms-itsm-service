package com.itsm.userservicemanagment.entity.incident;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "service_date_management")
public class ServiceDateManagement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    // OverTime
    private Boolean overDate;

}
