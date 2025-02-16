package com.itsm.userservicemanagment.dto.incoming.category;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewCategory {

    private String name;
    private Long subCat;
    private String description;
    private String priorityLevel;
    private String impactLevel;
    private Long owner;
    private Long createById;

}
