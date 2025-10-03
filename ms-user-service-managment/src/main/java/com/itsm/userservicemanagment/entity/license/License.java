package com.itsm.userservicemanagment.entity.license;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "license")
public class License {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private int countUser;
    private int fixedUser;
    private int floatUser;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String key;

}
