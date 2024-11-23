package com.itsm.userservicemanagment.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class License {

    private String id;
    private String type;
    private int countUser;
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;
    private String key;
    private String listModules;

}
