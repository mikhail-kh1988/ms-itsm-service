package com.itsm.userservicemanagment.dto.outgoing.KE;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChildConfigElement {

    private Long id;

    private String name;
    private String description;
    private String version;

    private String ownerLogin;
    private String ownerFullName;
    private Long ownerUserId;

    private String ownerGroupName;
    private Long ownerGroupId;

    private String address;
    private String manufactured;
    private String serialNumber;
    private String licenseNumber;
    private String realiseNumber;
    private String kontur;
    private Boolean logical;
    private String subStatus;
    private Integer  price;
    private LocalDateTime createDate;
    private LocalDateTime lastModifyDate;
    private String modifyByLogin;
    private Boolean softWare;
    private Boolean hardWare;
    private Boolean licenseWare;
    private Boolean informationSystem;
}
