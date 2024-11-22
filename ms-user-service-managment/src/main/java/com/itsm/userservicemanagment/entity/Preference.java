package com.itsm.userservicemanagment.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Preference {
    private Long id;
    private GlobalPreference globalPreferenceName;
    private String paramName;
    private LocalDateTime changeDate;
    private String paramValue;
    private boolean active;
}
