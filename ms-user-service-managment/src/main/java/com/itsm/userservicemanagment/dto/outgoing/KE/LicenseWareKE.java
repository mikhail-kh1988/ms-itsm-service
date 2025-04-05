package com.itsm.userservicemanagment.dto.outgoing.KE;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class LicenseWareKE {
    private Long id;
    private String name;
    private String key;
    private LocalDate startDate;
    private LocalDate endDate;
    private int countDay;
    private boolean unlimited;
    private boolean freeware;
}
