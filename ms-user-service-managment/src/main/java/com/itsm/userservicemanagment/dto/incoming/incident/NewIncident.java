package com.itsm.userservicemanagment.dto.incoming.incident;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class NewIncident {


    private long contactId;
    private String externalTicketNumber;
    private String title;
    private String body;
    private long impact;
    private long priority;
    private long categoryId;
    private Boolean isMass;
    private long configurationElementId;
    private long assigneeUserId;
    private long assigneeGroupId;


}
