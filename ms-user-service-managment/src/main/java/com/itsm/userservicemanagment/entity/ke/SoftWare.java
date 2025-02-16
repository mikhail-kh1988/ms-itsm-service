package com.itsm.userservicemanagment.entity.ke;

import com.itsm.userservicemanagment.entity.Group;
import com.itsm.userservicemanagment.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SoftWare {

    private Long id;
    private String name;
    private String license;
    private String licenseNumber;
    private String version;
    private User owner;
    private Group ownerGroup;
    private int price;
    private boolean limit;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String manufactured;
    private String host;
    private String ipAddress;
    private String cloudAddress;
    private String dnsName;
    private Boolean openSource;
    private Boolean freeWare;
    private Boolean cloud;
    private Boolean storage;
    private Boolean paas;
    private Boolean saas;
}
