package com.itsm.userservicemanagment.entity.license;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class FieldLicenceFile {

    private String companyName;
    private String licType;
    private int countUsers;
    private int fixedUser;
    private int floatUser;
    private LocalDate endDate;

}
