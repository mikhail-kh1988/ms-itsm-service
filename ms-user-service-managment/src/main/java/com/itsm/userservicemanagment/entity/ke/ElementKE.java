package com.itsm.userservicemanagment.entity.ke;

import com.itsm.userservicemanagment.entity.Group;
import com.itsm.userservicemanagment.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.engine.loading.internal.LoadContexts;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "configuration_element_element_ke")
public class ElementKE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ConfigurationElement parentKE;

    private String name;
    private String description;
    private String version;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User owner;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Group ownerGroup;

    private String address;
    private String manufactured;
    private String serialNumber;
    private String licenseNumber;
    private String realiseNumber;
    private String kontur;
    private Boolean logical;
    private StatusSubElementKE subStatus;
    private Integer  price;
    private LocalDateTime createDate;
    private LocalDateTime lastModifyDate;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User modifyBy;

    private Boolean softWare;
    private Boolean hardWare;
    private Boolean licenseWare;
    private Boolean informationSystem;

}
