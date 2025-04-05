package com.itsm.userservicemanagment.entity.ke;

import com.itsm.userservicemanagment.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "configuration_element_licence_ware")
public class ConfigurationElementLicenseWare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ConfigurationElement ke;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private LicenseWare licenseWare;

    private LocalDateTime createDate;
    private LocalDateTime lastChangeDate;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User createBy;
}
