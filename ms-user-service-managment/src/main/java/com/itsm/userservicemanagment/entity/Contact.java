package com.itsm.userservicemanagment.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.function.BinaryOperator;

@Getter
@Setter
public class Contact {

    private Long id;
    private String name;
    private String description;
    private Boolean isCompany;
    private Boolean customer;
    private Boolean supplier;
    private Long inn;
    private Long ogrn;
    private String fullName;

}
