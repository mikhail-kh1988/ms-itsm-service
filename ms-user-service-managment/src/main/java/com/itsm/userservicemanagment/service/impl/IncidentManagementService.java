package com.itsm.userservicemanagment.service.impl;

import com.itsm.userservicemanagment.Exception.*;
import com.itsm.userservicemanagment.dto.incoming.incident.*;
import com.itsm.userservicemanagment.dto.outgoing.Result;
import com.itsm.userservicemanagment.dto.outgoing.incident.IncidentListOut;
import com.itsm.userservicemanagment.dto.outgoing.incident.IncidentOut;
import com.itsm.userservicemanagment.entity.category.Impact;
import com.itsm.userservicemanagment.entity.category.Priority;
import com.itsm.userservicemanagment.entity.incident.Incident;
import com.itsm.userservicemanagment.entity.incident.IncidentStatus;
import com.itsm.userservicemanagment.entity.incident.IncidentStatusReason;
import com.itsm.userservicemanagment.repository.*;
import com.itsm.userservicemanagment.service.IIncidentManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Slf4j
@Service
public class IncidentManagementService implements IIncidentManagementService {

    @Autowired
    private IncidentRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ConfigurationElementRepository configurationElementRepository;

    @Autowired
    private EventLogService eventLogService;

    @Autowired
    private IncidentStatusReasonRepository statusReasonRepository;


    @Override
    public Result createNewIncident(NewIncident newIncident) {

        Result result = new Result();
        Incident incident = new Incident();

        // установка пользователя который создал инцидент
        if (newIncident.getCreateById() != null)
            incident.setCreateByUser(userRepository.findById(newIncident.getCreateById()).get());
        else
            incident.setCreateByUser(null);

        // установка назначенной группы
        if (newIncident.getAssigneeGroupId() != null) {

            incident.setAssigneeGroup(groupRepository.findById(newIncident.getAssigneeGroupId()).get());

            setOwnerIncident(incident, newIncident);
        }
        else
            incident.setAssigneeGroup(null);

        // установка назначенного лица
        if (newIncident.getAssigneeUserId() != null)
            incident.setAssignee(userRepository.findById(newIncident.getAssigneeUserId()).get());
        else
            incident.setAssignee(null);


        // Установка категории
        if (newIncident.getCategoryId() != null
                && categoryRepository.findById(newIncident.getCategoryId()).isPresent())
            incident.setCategory(categoryRepository.findById(newIncident.getCategoryId()).get());
        else
            throw new NotFoundCategoryException("Category not found!");

        // Установка конфигурационного элемента
        if (newIncident.getConfigurationElementId() != null
                && configurationElementRepository.findById(newIncident.getConfigurationElementId()).isPresent())
            incident.setConfigurationElement(configurationElementRepository.findById(newIncident.getConfigurationElementId()).get());
        else
            throw new NotFoundConfigurationElement("Configuration element not found!");

        incident.setTicketNumber(newIncident.getExternalTicketNumber());
        incident.setTitle(newIncident.getTitle());
        incident.setBody(newIncident.getBody());
        incident.setCountResolved(0);
        incident.setIsResolve(false);

        // Проверка влияния инцидента
        if (newIncident.getImpact() > 4)
            throw new NotFoundImpactException("Impact not found!");

        switch (newIncident.getImpact()){
            case 1:
                incident.setImpact(Impact.Overall);
                break;
            case 2:
                incident.setImpact(Impact.High);
                break;
            case 3:
                incident.setImpact(Impact.Medium);
                break;
            case 4:
                incident.setImpact(Impact.Low);
                break;
            default:
                incident.setImpact(Impact.Draft);
        }

        // Проверка приоритета инцидента
        if (newIncident.getPriority() > 3)
            throw new NotFoundPriorityException("Not found priority");

        switch (newIncident.getPriority()){
            case 1:
                incident.setPriority(Priority.High);
                break;
            case 2:
                incident.setPriority(Priority.Medium);
                break;
            case 3:
                incident.setPriority(Priority.Low);
                break;
        }

        incident.setIsMass(newIncident.getIsMass());
        incident.setIsRequest(newIncident.getIsRequest());
        incident.setIsCritical(newIncident.getIsCritical());
        incident.setStatus(IncidentStatus.NEW);

        // устанавливаем причину инцидента
        if (statusReasonRepository.findByCode(newIncident.getReason()) != null )
            incident.setReason(statusReasonRepository.findByCode(newIncident.getReason()));
        else
            incident.setReason(null);


        // Устанавливаем кто последним изменил и создал.
        if (newIncident.getCreateById() != null) {
            if (userRepository.findById(newIncident.getCreateById()).isPresent()) {
                incident.setModifyBy(userRepository.findById(newIncident.getCreateById()).get());
                incident.setCreateByUser(userRepository.findById(newIncident.getCreateById()).get());
            }
            else
                throw new NotFoundUserExcption("User not found!");
        }

        repository.save(incident);

        incident.setExternalId("INC-"+incident.getId());

        repository.save(incident);

        eventLogService.addEventLog(
                userRepository.findById(newIncident.getCreateById()).get().getLogin(),
                "INCIDENT",
                "createNewIncident",
                "New incident is created!",
                incident.getExternalId());


        result.setDate(LocalDateTime.now());
        result.setMessage("New incident by ["+incident.getExternalId()+"]");

        return result;
    }

    private void setOwnerIncident(Incident incident, NewIncident newIncident){
        // Устанавливаем группу владельца инцидента
        if (newIncident.getAssigneeGroupId() != null) {
            if (groupRepository.findById(newIncident.getAssigneeGroupId()).isPresent()
                    && groupRepository.findById(newIncident.getAssigneeGroupId()).get().isAssignee())
                incident.setOwnerGroup(groupRepository.findById(newIncident.getAssigneeGroupId()).get());
            else
                throw new NotFoundGroupException("Group not found!");
        }

        // Устанавливаем владельца инцидента
        if (newIncident.getAssigneeGroupId() != null) {
            if (groupRepository.findById(newIncident.getAssigneeGroupId()).isPresent()
                    && groupRepository.findById(newIncident.getAssigneeGroupId()).get().getOwner() != null)
                incident.setOwner(groupRepository.findById(newIncident.getAssigneeGroupId()).get().getOwner());
            else
                throw new NotFoundUserExcption("User not found!");
        }

    }

    @Override
    public Result creteNewIncidentByTemplate(NewIncidentByTemplate template) {
        return null;
    }

    @Override
    public Result modifyBodes(UpdateIncidentBodes body) {
        return null;
    }

    @Override
    public Result modifyCategory(UpdateIncidentCategorisation categorisation) {
        return null;
    }

    @Override
    public Result modifyStatuses(String incidentId, UpdateIncidentStatuses statuses) {

        // Логирование статусов и приоритетов которые были до изменений.
        StringBuilder modify = new StringBuilder();
        modify.append("Wases: ");

        if (incidentId.isEmpty())
            throw new NotFoundIncidentException("Incident not found!");

        if (repository.findByExternalId(incidentId).isEmpty())
            throw new NotFoundIncidentException("Incident not found!");

        Incident incident = repository.findByExternalId(incidentId).get();

        modify.append("priority:"+incident.getPriority().toString()+"\n ");

        // Установка приоритета
        switch (statuses.getPriority()) {
            case 1:
                incident.setPriority(Priority.High);
                break;
            case 2:
                incident.setPriority(Priority.Medium);
                break;
            case 3:
                incident.setPriority(Priority.Low);
                break;
            default:
                //incident.setPriority(Priority.Draft);
                break;
        }

        modify.append("impact:"+incident.getImpact().toString()+"\n ");

        // Установка влияния
        switch (statuses.getImpact()) {
            case 1:
                incident.setImpact(Impact.Overall);
                break;
            case 2:
                incident.setImpact(Impact.High);
                break;
            case 3:
                incident.setImpact(Impact.Medium);
                break;
            case 4:
                incident.setImpact(Impact.Low);
                break;
            default:
                //incident.setImpact(Impact.Draft);
                break;
        }

        modify.append("status:"+incident.getStatus().toString()+"\n ");

        // Установка статуса


        switch (statuses.getIncidentStatus()) {
            case 1:
                incident.setStatus(IncidentStatus.NEW);
                if(incident.getIsResolve()){
                    incident.setIsResolve(false);
                    incident.setIsWasResolved(true);
                    if (incident.getCountResolved() == 0)
                        incident.setCountResolved(1);
                    else
                        incident.setCountResolved(incident.getCountResolved()+1);
                }else {}
                break;
            case 2:
                incident.setStatus(IncidentStatus.ASSIGNEE);
                break;
            case 3:
                incident.setStatus(IncidentStatus.IN_PROGRESS);
                break;
            case 4:
                incident.setStatus(IncidentStatus.PENDING);
                break;
            case 5:
                incident.setStatus(IncidentStatus.RESOLVED);
                incident.setIsResolve(true);
                break;
            case 6:
                incident.setStatus(IncidentStatus.CANCELING);
                break;
            case 7:
                incident.setStatus(IncidentStatus.CLOSED);
                break;
            case 0:
                //incident.setStatus(IncidentStatus.DRAFT);
                break;
            default:
                break;
        }

        // устанавливаем причину инцидента
        if (statusReasonRepository.findByCode(statuses.getIncidentStatusReason()) != null )
            incident.setReason(statusReasonRepository.findByCode(statuses.getIncidentStatusReason()));
        else
            incident.setReason(null);

        incident.setLastChangeDate(LocalDateTime.now());

        repository.save(incident);

        modify.append("\n --- \n");
        modify.append("\n new: \n ");
        modify.append(" priority:"+incident.getPriority().toString()+"\n ");
        modify.append(" impact:"+incident.getImpact().toString()+"\n ");
        modify.append(" status:"+incident.getStatus().toString()+"\n ");


        eventLogService.addEventLog(
                userRepository.findById(statuses.getChangeById()).get().getLogin(),
                "INCIDENT",
                "modifyStatuses",
                "Modify by "+modify,
                incident.getExternalId()
        );


        Result result = new Result();
        result.setDate(LocalDateTime.now());
        result.setMessage("Modify status by "+incident.getStatus().toString());

        return result;
    }

    @Override
    public Result modifyAssignee(UpdateIncidentAssignee assignee) {
        return null;
    }

    @Override
    public Result modifyAssigneeGroup(Long assigneeGroupId) {
        return null;
    }

    @Override
    public Result addProgressInWork(ProgressAdd progress) {
        return null;
    }

    @Override
    public Result addServiceDate() {
        return null;
    }

    @Override
    public Result appendFile() {
        return null;
    }

    @Override
    public IncidentListOut findByNotAssigneeGroup() {
        return null;
    }

    @Override
    public IncidentListOut findByGroupId(Long groupId) {
        return null;
    }

    @Override
    public IncidentListOut findByAssigneeUserId(Long userId) {
        return null;
    }



    @Override
    public IncidentOut findByExternalId(String id) {

        IncidentOut incidentOut = new IncidentOut();

        if (id.isEmpty())
            throw new NotFoundIncidentException("Incident not found!");

        if (repository.findByExternalId(id).isEmpty())
            throw new NotFoundIncidentException("Incident not found!");

        Incident incident = repository.findByExternalId(id).get();

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
        if (incident.getCategory().getSubCategory() != null) {
            incidentOut.setSubCategoryName(incident.getCategory().getSubCategory().getCategoryName());
            incidentOut.setSubCategoryId(incident.getCategory().getSubCategory().getId());
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



        incidentOut.setEvents(eventLogService.getEventsByExternalIdEntity(incident.getExternalId()));


        return incidentOut;
    }

    @Override
    public IncidentOut findByInnerId(Long id) {


        return null;
    }

    @Override
    public Incident findByIdInternal(Long id) {
        return repository.findById(id).get();
    }
}
