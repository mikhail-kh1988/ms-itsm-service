package com.itsm.userservicemanagment.dto.outgoing.KE;

import com.itsm.userservicemanagment.entity.ke.LicenseWare;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class HardWareKE {

    private Long id;
    private String name;

    private String licenseName;

    private String serialNumber;
    private String ipAddress;
    private String dnsName;
    private String manufactured;
    private String address;
    private String building;
    private int price;
}
