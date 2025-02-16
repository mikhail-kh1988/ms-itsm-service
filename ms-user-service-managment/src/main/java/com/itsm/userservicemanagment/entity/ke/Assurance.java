package com.itsm.userservicemanagment.entity.ke;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Assurance {

    private Long id;
    private Boolean active;
    private String name;
    private String companyNameOfAssure;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int countDay;
    private int countMonth;
    private int countYear;
    private String description;
}
