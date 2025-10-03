package com.itsm.userservicemanagment.tools;

import com.itsm.userservicemanagment.dto.outgoing.incident.IncidentOut;
import com.itsm.userservicemanagment.entity.incident.Incident;

public class TransferIncidentToFromDtoObject {

    public static IncidentOut getIncOutFromIncident(Incident incident){

        IncidentOut incidentOut = new IncidentOut();

        incidentOut.setIsRequest(incident.getIsRequest());
        incidentOut.setIsCritical(incident.getIsCritical());
        incidentOut.setIsMass(incident.getIsMass());
        incidentOut.setIsResolve(incident.getIsResolve());
        incidentOut.setExternalTicketNumber(incident.getTicketNumber());
        incidentOut.setExternalId(incident.getExternalId());
        incidentOut.setTitle(incident.getTitle());
        incidentOut.setBody(incident.getBody());
        incidentOut.setImpact(incident.getImpact().toString());
        incidentOut.setPriority(incident.getPriority().toString());
        //if ()
        incidentOut.setStatus(incident.getStatus().name());
        incidentOut.setResolution(incident.getResolution());

        if (incident.getReason() != null)
            incidentOut.setReason(incident.getReason().getName());
        else
            incidentOut.setReason("No reason");

        //Вывод группы назначения
        if (incident.getAssigneeGroup() != null) {
            incidentOut.setAssigneeGroupId(incident.getAssigneeGroup().getId());
            incidentOut.setAssigneeGroupName(incident.getAssigneeGroup().getName());
        }else {
            incidentOut.setAssigneeGroupName("No group assignee");
        }

        //Вывод пользователя назначения
        if (incident.getAssignee() != null) {
            incidentOut.setAssigneeUserId(incident.getAssignee().getId());
            incidentOut.setAssigneeUserFullName(incident.getAssignee().getFullName());
        }else {
            incidentOut.setAssigneeUserFullName("No user assignee");
        }

        //Вывод владельца
        if (incident.getOwner() != null) {
            incidentOut.setOwnerUserId(incident.getOwner().getId());
            incidentOut.setOwnerUserFullName(incident.getOwner().getFullName());
        }else {
            incidentOut.setOwnerUserFullName("No owner user");
        }
        if (incident.getOwnerGroup() != null) {
            incidentOut.setAssigneeGroupId(incident.getOwnerGroup().getId());
            incidentOut.setAssigneeGroupName(incident.getOwnerGroup().getName());
        }else {
            incidentOut.setAssigneeGroupName("No owner group");
        }

        //Вывод категории
        if(incident.getCategory() != null) {
            incidentOut.setCategoryName(incident.getCategory().getCategoryName());
            incidentOut.setCategoryId(incident.getCategory().getId());
        }else {
            incidentOut.setCategoryName("Without category");
        }

        // Вывод подкатегории
        if (incident.getCategory().getRootCategory() != null) {
            incidentOut.setSubCategoryName(incident.getCategory().getRootCategory().getCategoryName());
            incidentOut.setSubCategoryId(incident.getCategory().getRootCategory().getId());
        } else {
            incidentOut.setSubCategoryName("Without sub category");
        }

        //Вывод конфигурационного элемента
        if (incident.getConfigurationElement() != null) {
            incidentOut.setConfigurationElementId(incident.getConfigurationElement().getId());
            incidentOut.setConfigurationElementName(incident.getConfigurationElement().getConfigurationName());
        } else {
            incidentOut.setConfigurationElementName("No configuration element set");
        }

        incidentOut.setIsWasResolve(incident.getIsWasResolved());
        incidentOut.setCountResolve(incident.getCountResolved());

        // Вывод работы с датой
        incidentOut.setCreateDate(incident.getCreateDate());
        incidentOut.setTargetDate(incident.getTargetDate());
        incidentOut.setLastChangeDate(incident.getLastChangeDate());


        return incidentOut;
    }
}
