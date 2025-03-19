package com.itsm.userservicemanagment.entity.ke;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HardWare {

    private Long id;
    private String name;
    private LicenseWare license;
    private String serialNumber;
    private String ipAddress;
    private String dnsName;
    private String manufactured;
    private String address;
    private String building;
    private int price;

}
