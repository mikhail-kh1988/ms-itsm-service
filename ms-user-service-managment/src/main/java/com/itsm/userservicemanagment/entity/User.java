package com.itsm.userservicemanagment.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class User {

    private Long id;
    private boolean techAccount;
    private String fullName;
    private String family;
    private String name;
    private String fatherName;
    private String email;
    private String jobTitle;
    private boolean active;
    private long countAttempt;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private boolean deleted;
    private String language;
    private String timeZone;




}
