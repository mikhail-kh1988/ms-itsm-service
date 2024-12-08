package com.itsm.userservicemanagment.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean techAccount;
    private String fullName;
    private String family;
    private String name;
    private String fatherName;
    private String email;
    private String login;
    private String password;
    private String jobTitle;
    private String ldapId;
    private boolean active;
    private long countAttempt;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private boolean deleted;
    private String language;
    private String timeZone;

    @OneToMany(mappedBy = "user")
    private List<GroupUser> groupUsers;

}
