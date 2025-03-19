package com.itsm.userservicemanagment.dto.incoming.incident;

import com.itsm.userservicemanagment.entity.category.Category;
import com.itsm.userservicemanagment.entity.ke.ConfigurationElement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateIncidentCategorisation {

    private Long categoryId;
    private Long configurationElementId;

}
