package com.itsm.userservicemanagment.entity.ke;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class LicenseWare {

    private Long id;
    private String name;
    private String key;
    private LocalDate start;
    private LocalDate end;
    private int countDay;
    private boolean unlimited;
    private boolean freeware;

}
