package com.itsm.userservicemanagment.dto.incoming.category;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewSubCategory {
    private String name;
    private String description;
    private Long createById;
}
