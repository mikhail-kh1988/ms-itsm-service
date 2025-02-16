package com.itsm.userservicemanagment.entity.category;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CategoryModel {

    private Long id;
    private SubCategory subCategory1;
    private SubCategory subCategory2;
    private String categoryName;
    private String description;
    private Impact impact;
    private Priority priority;
    private Boolean active;
    private String owner;
    private Long ownerId;
    private LocalDateTime createDate;
    private LocalDateTime lastModifyDate;

}
