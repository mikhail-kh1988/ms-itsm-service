package com.itsm.userservicemanagment.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.function.BinaryOperator;

@Getter
@Setter
@Entity
@Table(name = "contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Boolean iscompany;
    private Boolean customer;
    private Boolean supplier;
    private Long inn;
    private Long ogrn;
    private String fullName;

}
