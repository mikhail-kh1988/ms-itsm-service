package com.itsm.userservicemanagment.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.spi.LocaleNameProvider;

@Getter
@Setter
@Entity
@Table(name = "users_roles")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User user;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Role role;

    private LocalDateTime createDate;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User createBy;

}
