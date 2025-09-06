package com.itsm.userservicemanagment.entity.change;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChangeDateManagement {

    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime planingDateStart;
    private LocalDateTime planingDateEnd;
    private LocalDateTime actualDateStart;
    private LocalDateTime actualDateEnd;
    private LocalDateTime lastChangeDate;
    private LocalDateTime createDate;
}
