package com.itsm.userservicemanagment.dto.outgoing.categorization;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SubCat {

    private Long id;
    private String categoryName;
    private LocalDateTime creteDate;
    private LocalDateTime lastModifyDate;
    private String createByLogin;
    private String description;

}
