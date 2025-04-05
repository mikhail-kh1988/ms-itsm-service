package com.itsm.userservicemanagment.dto.incoming.KE;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewConfigurationElement {

    private Long userOwnerId;
    private Long groupOwnerId;
    private String configurationElementName;
    private String configurationType;
    private String prefix;
    private boolean logical;
    private Long createById;

}
