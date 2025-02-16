package com.itsm.userservicemanagment.dto.outgoing.categorization;

import com.itsm.userservicemanagment.entity.category.Impact;
import com.itsm.userservicemanagment.entity.category.Priority;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Category {

    private Long id;
    private String name;
    private SubCat subCat;
    private String description;
    private Impact impact;
    private Priority priority;
    private Boolean active;
    private String owner;
    private Long ownerId;
    private LocalDateTime createDate;
    private LocalDateTime lastModifyDate;

}
