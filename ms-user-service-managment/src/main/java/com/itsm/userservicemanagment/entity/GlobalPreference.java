package com.itsm.userservicemanagment.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class GlobalPreference {

    private Long id;
    private String name;
    private LocalDateTime createDate;

}
