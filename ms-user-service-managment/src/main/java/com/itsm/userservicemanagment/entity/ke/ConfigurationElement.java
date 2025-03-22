package com.itsm.userservicemanagment.entity.ke;

import com.itsm.userservicemanagment.entity.Group;
import com.itsm.userservicemanagment.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "configuration_element")
public class ConfigurationElement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String configurationName;
    private String description;
    private String version;
    private String prefix;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User owner;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Group ownerGroup;

    private String address;
    private String manufactured;
    private String licenseNumber;
    private String serialNumber;
    private String realiseNumber;
    private String kontur;
    private Boolean logical;
    private StatusKE status;
    private Integer  price;
    //гарантия
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Assurance assurance;

    //private List<ElementKE> subElements;
    //private List<LicenseWare> licenseWareList;
    //private List<HardWare> hardWareList;
    //private List<SoftWare> softWareList;

    private LocalDateTime createDate;
    private LocalDateTime lastModifyDate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User modifyBy;

    private Boolean softWare;
    private Boolean hardWare;
    private Boolean licenseWare;
    private Boolean informationSystem;

}
