package com.itsm.userservicemanagment.SLM.entity;

import com.itsm.userservicemanagment.dto.outgoing.KE.ConfigurationElement;
import com.itsm.userservicemanagment.entity.Group;
import com.itsm.userservicemanagment.entity.category.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
/*@Entity
@Table*/
public class SMLTask {

    private Long id;
    private LocalDateTime createDate;
    private SLMEntity entityName;
    private String entityStatus;
    private Group entityAssigneeGroup;
    private String entityExternalId;
    private Category category;
    private ConfigurationElement ke;
    private SLMStatusTask status;

}
