package com.itsm.userservicemanagment.entity.ke;

import com.itsm.userservicemanagment.entity.Group;
import com.itsm.userservicemanagment.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.engine.loading.internal.LoadContexts;

import java.time.LocalDateTime;

@Getter
@Setter
public class ElementKE {

    private Long id;
    private ConfigurationElement parentKE;
    private String name;
    private String description;
    private String version;
    private User owner;
    private Group ownerGroup;
    private String address;
    private String manufactured;
    private String serialNumber;
    private String licenseNumber;
    private Boolean logical;
    private StatusSubElementKE subStatus;
    private Integer  price;
    private LocalDateTime createDate;
    private LocalDateTime lastModifyDate;
    private User modifyBy;
    private Boolean softWare;
    private Boolean hardWare;
    private Boolean licenseWare;

}
