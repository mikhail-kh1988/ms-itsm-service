package com.itsm.userservicemanagment.dto.incoming.incident;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateIncidentBodes {

    private String body;
    private String title;
    private String resolution;

}
