package com.itsm.userservicemanagment.dto.outgoing.KE;

import com.itsm.userservicemanagment.entity.Event;
import com.itsm.userservicemanagment.entity.ke.Assurance;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ConfigurationElement {

    private Long id;
    private String configurationName;
    private String description;
    private String version;
    private String prefix;

    private String ownerLogin;
    private String ownerFullName;
    private Long ownerUserId;

    private String ownerGroupName;
    private Long ownerGroupId;

    private String address;
    private String manufactured;
    private String licenseNumber;
    private String serialNumber;
    private String realiseNumber;
    private String kontur;
    private Boolean logical;
    private String status;
    private Integer  price;
    //гарантия
    private Assurance assurance;

    private LocalDateTime createDate;
    private LocalDateTime lastModifyDate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private String modifyByLogin;

    private Boolean softWare;
    private Boolean hardWare;
    private Boolean licenseWare;
    private Boolean informationSystem;

    private List<ChildConfigElement> childKEList;
    private List<HardWareKE> hardWareKEList;
    private List<SoftWareKE> softWareKEList;
    private List<LicenseWareKE> licenseWareKEList;
    private List<Event> eventList;

}
