package com.itsm.userservicemanagment.entity.ke;

import com.itsm.userservicemanagment.entity.Group;
import com.itsm.userservicemanagment.entity.User;
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
    private User owner;
    private Group ownerGroup;
    private String address;
    private String manufactured;
    private String licenseNumber;
    private String serialNumber;
    private Boolean logical;
    private StatusKE status;
    private Integer  price;
    //гарантия
    private Assurance assurance;

    private List<ElementKE> subElements;
    private List<LicenseWare> licenseWareList;
    private List<HardWare> hardWareList;
    private List<SoftWare> softWareList;

    private LocalDateTime createDate;
    private LocalDateTime lastModifyDate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private User modifyBy;
    private Boolean softWare;
    private Boolean hardWare;
    private Boolean licenseWare;


}
