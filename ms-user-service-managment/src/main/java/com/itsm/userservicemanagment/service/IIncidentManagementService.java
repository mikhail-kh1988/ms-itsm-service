package com.itsm.userservicemanagment.service;

import com.itsm.userservicemanagment.dto.incoming.incident.*;
import com.itsm.userservicemanagment.dto.outgoing.Result;
import com.itsm.userservicemanagment.dto.outgoing.incident.IncidentListOut;
import com.itsm.userservicemanagment.dto.outgoing.incident.IncidentOut;
import com.itsm.userservicemanagment.entity.incident.Incident;

public interface IIncidentManagementService {

    Result createNewIncident(NewIncident newIncident);
    Result creteNewIncidentByTemplate(NewIncidentByTemplate template);
    Result modifyBodes(String incidentId, UpdateIncidentBodes body);
    Result modifyCategory(String incidentId, UpdateIncidentCategorisation categorisation);
    Result resolve(String incidentId, Resolution resolution);
    Result modifyStatuses(String incidentId, UpdateIncidentStatuses statuses);
    Result modifyAssignee(UpdateIncidentAssignee assignee);
    Result modifyAssigneeGroup(Long assigneeGroupId, String incidentId);
    Result addProgressInWork(ProgressAdd progress);

    Result addServiceDate();
    Result appendFile();

    IncidentListOut findByNotAssigneeGroup();
    IncidentListOut findByAssigneeGroupId(Long groupId);
    IncidentListOut findByAssigneeUserId(Long userId);
    IncidentOut findByExternalId(String id);
    IncidentOut findByInnerId(Long id);
    Incident findByIdInternal(Long id);
    
}
