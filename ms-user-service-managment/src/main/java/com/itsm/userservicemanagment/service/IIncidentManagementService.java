package com.itsm.userservicemanagment.service;

import com.itsm.userservicemanagment.dto.incoming.incident.*;
import com.itsm.userservicemanagment.dto.outgoing.Result;
import com.itsm.userservicemanagment.dto.outgoing.incident.IncidentListOut;
import com.itsm.userservicemanagment.dto.outgoing.incident.IncidentOut;
import com.itsm.userservicemanagment.entity.incident.Incident;

public interface IIncidentManagementService {

    Result createNewIncident(NewIncident newIncident);
    Result creteNewIncidentByTemplate(NewIncidentByTemplate template);
    Result modifyBodes(UpdateIncidentBodes body);
    Result modifyCategory(UpdateIncidentCategorisation categorisation);
    Result modifyStatuses(UpdateIncidentStatuses statuses);
    Result modifyAssignee(UpdateIncidentAssignee assignee);
    Result addProgressInWork(ProgressAdd progress);

    //не реализовывать.
    Result addServiceDate();
    //не реализовывать.
    Result appendFile();

    IncidentListOut findByGroupId(Long groupId);
    IncidentListOut findByAssigneeUserId(Long userId);
    IncidentOut findByExternalId(String id);
    IncidentOut findByInnerId(Long id);
    Incident findById(Long id);
    
}
