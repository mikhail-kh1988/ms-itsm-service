package com.itsm.userservicemanagment.entity.ke;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "license_ware")
public class LicenseWare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String key;
    private LocalDate startDate;
    private LocalDate endDate;
    private int countDay;
    private boolean unlimited;
    private boolean freeware;

}
