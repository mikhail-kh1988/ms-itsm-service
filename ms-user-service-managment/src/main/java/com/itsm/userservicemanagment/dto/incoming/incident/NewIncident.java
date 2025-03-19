package com.itsm.userservicemanagment.dto.incoming.incident;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class NewIncident {


    private Long contactId;
    private String externalTicketNumber;
    private String title;
    private String body;
    private Long impact;
    private int priority;
    private int categoryId;
    private Boolean isMass;
    private Boolean isRequest;
    private Boolean isCritical;
    private Long configurationElementId;
    private Long createById;
    private Long assigneeUserId;
    private Long assigneeGroupId;


}
