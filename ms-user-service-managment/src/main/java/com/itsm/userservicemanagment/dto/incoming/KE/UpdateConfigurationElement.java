package com.itsm.userservicemanagment.dto.incoming.KE;

import com.itsm.userservicemanagment.entity.ke.StatusKE;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateConfigurationElement {
    private String configurationName;
    private String description;
    private String version;
    private String prefix;
    private String address;
    private String manufactured;
    private String licenseNumber;
    private String serialNumber;
    private String realiseNumber;
    private Integer  price;
    private Boolean logical;
    private Long modifyById;
    private String logicalType;
}
