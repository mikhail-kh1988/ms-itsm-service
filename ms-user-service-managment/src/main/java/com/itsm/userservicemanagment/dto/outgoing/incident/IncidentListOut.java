package com.itsm.userservicemanagment.dto.outgoing.incident;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class IncidentListOut {

    private long count;
    private List<IncidentOut> list;

}
