package com.itsm.userservicemanagment.service.impl;

import com.itsm.userservicemanagment.Exception.*;
import com.itsm.userservicemanagment.dto.incoming.KE.*;
import com.itsm.userservicemanagment.dto.outgoing.KE.ChildConfigElement;
import com.itsm.userservicemanagment.dto.outgoing.KE.ConfigurationElement;
import com.itsm.userservicemanagment.dto.outgoing.KE.ListConfiguration;
import com.itsm.userservicemanagment.dto.outgoing.Result;
import com.itsm.userservicemanagment.entity.ke.ConfigurationElementElementKE;
import com.itsm.userservicemanagment.entity.ke.ElementKE;
import com.itsm.userservicemanagment.repository.*;
import com.itsm.userservicemanagment.service.IConfigurationElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConfigurationElementService implements IConfigurationElementService {

    @Autowired
    private StatusKERepository statusRepository;

    @Autowired
    private ConfigurationElementRepository KERepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private ConfigurationElementAndElementKERepository KEEntityChildRepository;

    @Autowired
    private ConfigurationElementChildRepository childRepository;

    @Autowired
    private EventLogService logService;



    @Override
    public Result createNewConfigurationElement(NewConfigurationElement newKE) {

        Result result = new Result();
        com.itsm.userservicemanagment.entity.ke.ConfigurationElement configurationElement = new com.itsm.userservicemanagment.entity.ke.ConfigurationElement();

        if (newKE.getConfigurationElementName().isEmpty())
            throw new NotFoundNameConfigurationElement("Name KE is empty!");

        configurationElement.setConfigurationName(newKE.getConfigurationElementName());

        // Устанавливаем кто последним изменил.
        if (newKE.getCreateById() != null) {
            if (userRepository.findById(newKE.getCreateById()).isPresent())
                configurationElement.setModifyBy(userRepository.findById(newKE.getCreateById()).get());
            else
                throw new NotFoundUserExcption("User not found!");
        }

        // Устанавливаем владельца КЕ
        if (newKE.getUserOwnerId() != null) {
            if (userRepository.findById(newKE.getUserOwnerId()).isPresent())
                configurationElement.setOwner(userRepository.findById(newKE.getUserOwnerId()).get());
            else
                throw new NotFoundUserExcption("User not found!");
        }

        // Устанавливаем группу владельца КЕ
        if (newKE.getGroupOwnerId() != null) {
            if (groupRepository.findById(newKE.getGroupOwnerId()).isPresent()
                    && groupRepository.findById(newKE.getGroupOwnerId()).get().isAssignee())
                configurationElement.setOwnerGroup(groupRepository.findById(newKE.getGroupOwnerId()).get());
            else
                throw new NotFoundGroupException("Group not found!");
        }

        configurationElement.setPrefix(newKE.getPrefix());
        configurationElement.setLogical(newKE.isLogical());
        configurationElement.setStatus(statusRepository.findByCode(100L).getName());

        switch (newKE.getConfigurationType()){

            case "hard":
                configurationElement.setHardWare(true);
                break;
            case "soft":
                configurationElement.setSoftWare(true);
                break;
            case "lice":
                configurationElement.setLicenseWare(true);
                break;
            case "is":
                configurationElement.setInformationSystem(true);
                break;
            default:
                break;
        }

        KERepository.save(configurationElement);

        result.setMessage("Configuration element ["+configurationElement.getConfigurationName()+"] by created!");
        result.setDate(LocalDateTime.now());

        //Добавление данных в систему логирования действий
        String tempExtId = configurationElement.getPrefix()+"-"+configurationElement.getId();
        logService.addEventLog(
                userRepository.findById(newKE.getCreateById()).get().getLogin(),
                "KE",
                "createNewConfigurationElement",
                "New element is created!",
                tempExtId);

        return result;
    }

    @Override
    public Result modifyStatusConfigurationElement(ChangeStatus status, Long id) {

        Result result = new Result();

        if (KERepository.findById(id) ==  null)
            throw new NotFoundKeException("KE not found");
        else if(KERepository.findById(id).isEmpty())
            throw new NotFoundKeException("KE not found");

        if (statusRepository.findByCode(status.getStatusId()) == null)
            throw new NotFoundStatusException("Status not found!");


        com.itsm.userservicemanagment.entity.ke.ConfigurationElement configurationElement = KERepository.findById(id).get();

        configurationElement.setStatus(statusRepository.findByCode(status.getStatusId()).getName());

        KERepository.save(configurationElement);

        result.setDate(LocalDateTime.now());
        result.setMessage("Status ["+configurationElement.getStatus()+"] has been set.");

        //Добавление данных в систему логирования действий
        String tempExtId = configurationElement.getPrefix()+"-"+configurationElement.getId();
        logService.addEventLog(
                userRepository.findById(status.getCreateBy()).get().getLogin(),
                "KE",
                "modifyStatusConfigurationElement",
                "Set status ["+statusRepository.findByCode(status.getStatusId()).getName()+"] .",
                tempExtId);
        return result;
    }

    @Override
    public Result modifyOwnersConfigurationElement(ChangeOwners changeOwners, Long id) {

        StringBuilder stringForUpdateLogSystem = new StringBuilder();

        if (KERepository.findById(id) ==  null)
            throw new NotFoundKeException("KE not found");
        else if(KERepository.findById(id).isEmpty())
            throw new NotFoundKeException("KE not found");

        com.itsm.userservicemanagment.entity.ke.ConfigurationElement configurationElement = KERepository.findById(id).get();
        Result result = new Result();

        // Устанавливаем владельца КЕ
        if (changeOwners.getNewOwnerId() != null) {
            if (userRepository.findById(changeOwners.getNewOwnerId()).isPresent()) {
                configurationElement.setOwner(userRepository.findById(changeOwners.getNewOwnerId()).get());
                stringForUpdateLogSystem.append("Set new owner '"+configurationElement.getOwner().getFullName()+"' by login ["+configurationElement.getOwner().getLogin()+"].");
            }
            else
                throw new NotFoundUserExcption("User not found!");
        }

        // Устанавливаем группу владельца КЕ
        if (changeOwners.getNewOwnerGroupId() != null) {
            if (groupRepository.findById(changeOwners.getNewOwnerGroupId()).isPresent()
                    && groupRepository.findById(changeOwners.getNewOwnerGroupId()).get().isAssignee()) {
                configurationElement.setOwnerGroup(groupRepository.findById(changeOwners.getNewOwnerGroupId()).get());
                stringForUpdateLogSystem.append("Set new group owner ["+configurationElement.getOwnerGroup().getName()+"]. ");
            }
            else
                throw new NotFoundGroupException("Group not found!");
        }

        KERepository.save(configurationElement);

        result.setDate(LocalDateTime.now());
        result.setMessage("New owner is set!");

        //Добавление данных в систему логирования действий
        String tempExtId = configurationElement.getPrefix()+"-"+configurationElement.getId();
        logService.addEventLog(
                userRepository.findById(changeOwners.getChangeById()).get().getLogin(),
                "KE",
                "modifyOwnersConfigurationElement",
                stringForUpdateLogSystem.toString(),
                tempExtId);

        return result;
    }

    @Override
    public Result modifyConfigurationElement(UpdateConfigurationElement updateKE, Long id) {

        StringBuilder stringForUpdateLogSystem = new StringBuilder();

        if (KERepository.findById(id) ==  null)
            throw new NotFoundKeException("KE not found");
        else if(KERepository.findById(id).isEmpty())
            throw new NotFoundKeException("KE not found");

        com.itsm.userservicemanagment.entity.ke.ConfigurationElement configurationElement = KERepository.findById(id).get();

        if (updateKE.getConfigurationName() != null) {
            configurationElement.setConfigurationName(updateKE.getConfigurationName());
            stringForUpdateLogSystem.append("Update name: "+updateKE.getConfigurationName()+" '\n ");
        }
        if (updateKE.getDescription() != null) {
            configurationElement.setDescription(updateKE.getDescription());
            stringForUpdateLogSystem.append("Update description: "+updateKE.getDescription()+" '\n ");
        }
        if (updateKE.getVersion() != null) {
            configurationElement.setVersion(updateKE.getVersion());
            stringForUpdateLogSystem.append("Update version: "+updateKE.getVersion()+" '\n ");
        }
        if (updateKE.getPrefix() != null) {
            configurationElement.setPrefix(updateKE.getPrefix());
            stringForUpdateLogSystem.append("Update prefix: "+updateKE.getPrefix()+" '\n ");
        }
        if (updateKE.getAddress() != null) {
            configurationElement.setAddress(updateKE.getAddress());
            stringForUpdateLogSystem.append("Update address:  "+updateKE.getAddress()+" '\n ");
        }
        if (updateKE.getManufactured() != null) {
            configurationElement.setManufactured(updateKE.getManufactured());
            stringForUpdateLogSystem.append("Update manufactured:  "+updateKE.getManufactured()+" '\n ");
        }
        if (updateKE.getLicenseNumber() != null) {
            configurationElement.setLicenseNumber(updateKE.getLicenseNumber());
            stringForUpdateLogSystem.append("Update license:  "+updateKE.getLicenseNumber()+" '\n ");
        }
        if (updateKE.getSerialNumber() != null) {
            configurationElement.setSerialNumber(updateKE.getSerialNumber());
            stringForUpdateLogSystem.append("Update serial number:  "+updateKE.getSerialNumber()+" '\n ");
        }
        if (updateKE.getRealiseNumber() != null) {
            configurationElement.setRealiseNumber(updateKE.getRealiseNumber());
            stringForUpdateLogSystem.append("Update realise number:  "+updateKE.getRealiseNumber()+" '\n ");
        }
        if (updateKE.getPrice() != null) {
            configurationElement.setPrice(updateKE.getPrice());
            stringForUpdateLogSystem.append("Update price:  "+updateKE.getPrice()+" '\n ");
        }
        if (updateKE.getLogical() != null) {
            configurationElement.setLogical(updateKE.getLogical());
            stringForUpdateLogSystem.append("Change logical status:  "+updateKE.getLogical()+" '\n ");
        }

        configurationElement.setModifyBy(userRepository.findById(updateKE.getModifyById()).get());
        KERepository.save(configurationElement);

        // Добавление события изменения конфигурционного элемента.
        String tempExtId = configurationElement.getPrefix()+"-"+configurationElement.getId();
        logService.addEventLog(configurationElement.getModifyBy().getLogin(),
                "KE",
                "modifyConfigurationElement",
                stringForUpdateLogSystem.toString(),
                tempExtId);

        Result result = new Result();
        result.setDate(LocalDateTime.now());
        result.setMessage("KE ["+configurationElement.getConfigurationName()+"] is updated.");


        return result;
    }

    @Override
    public Result modifySubElementKE(UpdateConfigurationElement updateSubKe, Long id) {
/*

        StringBuilder stringForUpdateLogSystem = new StringBuilder();

        if (childRepository.findByIdInternal(id) ==  null)
            throw new NotFoundKeException("KE not found");
        else if(childRepository.findByIdInternal(id).isEmpty())
            throw new NotFoundKeException("KE not found");

        ElementKE elementKE = childRepository.findByIdInternal(id).get();

        if (updateSubKe.getConfigurationName() != null) {
            elementKE.setName(updateSubKe.getConfigurationName());
            stringForUpdateLogSystem.append("Update name: "+updateSubKe.getConfigurationName()+" '\n ");
        }
        if (updateKE.getDescription() != null) {
            configurationElement.setDescription(updateKE.getDescription());
            stringForUpdateLogSystem.append("Update description: "+updateKE.getDescription()+" '\n ");
        }
        if (updateKE.getVersion() != null) {
            configurationElement.setVersion(updateKE.getVersion());
            stringForUpdateLogSystem.append("Update version: "+updateKE.getVersion()+" '\n ");
        }
        if (updateKE.getPrefix() != null) {
            configurationElement.setPrefix(updateKE.getPrefix());
            stringForUpdateLogSystem.append("Update prefix: "+updateKE.getPrefix()+" '\n ");
        }
        if (updateKE.getAddress() != null) {
            configurationElement.setAddress(updateKE.getAddress());
            stringForUpdateLogSystem.append("Update address:  "+updateKE.getAddress()+" '\n ");
        }
        if (updateKE.getManufactured() != null) {
            configurationElement.setManufactured(updateKE.getManufactured());
            stringForUpdateLogSystem.append("Update manufactured:  "+updateKE.getManufactured()+" '\n ");
        }
        if (updateKE.getLicenseNumber() != null) {
            configurationElement.setLicenseNumber(updateKE.getLicenseNumber());
            stringForUpdateLogSystem.append("Update license:  "+updateKE.getLicenseNumber()+" '\n ");
        }
        if (updateKE.getSerialNumber() != null) {
            configurationElement.setSerialNumber(updateKE.getSerialNumber());
            stringForUpdateLogSystem.append("Update serial number:  "+updateKE.getSerialNumber()+" '\n ");
        }
        if (updateKE.getRealiseNumber() != null) {
            configurationElement.setRealiseNumber(updateKE.getRealiseNumber());
            stringForUpdateLogSystem.append("Update realise number:  "+updateKE.getRealiseNumber()+" '\n ");
        }
        if (updateKE.getPrice() != null) {
            configurationElement.setPrice(updateKE.getPrice());
            stringForUpdateLogSystem.append("Update price:  "+updateKE.getPrice()+" '\n ");
        }
        if (updateKE.getLogical() != null) {
            configurationElement.setLogical(updateKE.getLogical());
            stringForUpdateLogSystem.append("Change logical status:  "+updateKE.getLogical()+" '\n ");
        }

        configurationElement.setModifyBy(userRepository.findByIdInternal(updateKE.getModifyById()).get());
        KERepository.save(configurationElement);

        // Добавление события изменения конфигурционного элемента.
        String tempExtId = configurationElement.getPrefix()+"-"+configurationElement.getId();
        logService.addEventLog(configurationElement.getModifyBy().getLogin(),
                "KE",
                "modifySubElementKE",
                stringForUpdateLogSystem.toString(),
                tempExtId);

        Result result = new Result();
        result.setDate(LocalDateTime.now());
        result.setMessage("Element KE ["+elementKE.getName()+"] is updated.");

*/

        return null;
    }

    @Override
    public Result addSubElementKE(NewConfigurationElement newKE, Long id) {

        StringBuilder stringForUpdateLogSystem = new StringBuilder();

        // Проверяем на наличие КЕ в БД
        if (KERepository.findById(id) ==  null)
            throw new NotFoundKeException("KE not found");
        else if(KERepository.findById(id).isEmpty())
            throw new NotFoundKeException("KE not found");

        // Создаем все элементы
        ConfigurationElementElementKE configurationElementElementKE = new ConfigurationElementElementKE();
        ElementKE elementKE = new ElementKE();
        Result result = new Result();
        com.itsm.userservicemanagment.entity.ke.ConfigurationElement configurationElement = KERepository.findById(id).get();

        // Устанавливаем родительский эелемент КЕ
        elementKE.setParentKE(configurationElement);
        stringForUpdateLogSystem.append("Set parent element "+configurationElement.getConfigurationName()+" '\n ");

        if (newKE.getConfigurationElementName().isEmpty())
            throw new NotFoundNameConfigurationElement("Name KE is empty!");


        // Устанавливаем кто последним изменил.
        if (newKE.getCreateById() != null) {
            if (userRepository.findById(newKE.getCreateById()).isPresent()) {
                elementKE.setModifyBy(userRepository.findById(newKE.getCreateById()).get());
                stringForUpdateLogSystem.append("Set last change user "+userRepository.findById(newKE.getCreateById()).get().getName()+" '\n ");
            }
            else
                throw new NotFoundUserExcption("User not found!");
        }

        // Устанавливаем владельца КЕ
        if (newKE.getUserOwnerId() != null) {
            if (userRepository.findById(newKE.getUserOwnerId()).isPresent()) {
                elementKE.setOwner(userRepository.findById(newKE.getUserOwnerId()).get());
                stringForUpdateLogSystem.append("Set user owner "+userRepository.findById(newKE.getUserOwnerId()).get().getFullName()+" '\n ");
            }
            else
                throw new NotFoundUserExcption("User not found!");
        }

        // Устанавливаем группу владельца КЕ
        if (newKE.getGroupOwnerId() != null) {
            if (groupRepository.findById(newKE.getGroupOwnerId()).isPresent()
                    && groupRepository.findById(newKE.getGroupOwnerId()).get().isAssignee()) {
                elementKE.setOwnerGroup(groupRepository.findById(newKE.getGroupOwnerId()).get());
                stringForUpdateLogSystem.append("Set group owner "+groupRepository.findById(newKE.getGroupOwnerId()).get().getName()+" '\n ");
            }
            else
                throw new NotFoundGroupException("Group not found!");
        }

        elementKE.setName(newKE.getConfigurationElementName());
        elementKE.setLogical(newKE.isLogical());
        elementKE.setSubStatus(statusRepository.findByCode(100L).getName());


        // Сохраняем дочерний эелемент
        childRepository.save(elementKE);


        // Делаем связку между эелементами.
        configurationElementElementKE.setKeke(configurationElement);
        configurationElementElementKE.setElementKE(elementKE);
        configurationElementElementKE.setCreateDate(LocalDateTime.now());
        configurationElementElementKE.setLastChangeDate(LocalDateTime.now());
        configurationElementElementKE.setCreateBy(userRepository.findById(newKE.getCreateById()).get());


        KEEntityChildRepository.save(configurationElementElementKE);

        result.setDate(LocalDateTime.now());
        result.setMessage("Element ["+elementKE.getName()+"] has been connected with KE ["+configurationElement.getConfigurationName()+"]. ");


        // Добавление события изменения конфигурционного элемента.
        String tempExtId = configurationElement.getPrefix()+"-"+configurationElement.getId();
        logService.addEventLog(userRepository.findById(newKE.getCreateById()).get().getLogin(),
                "KE",
                "addSubElementKE",
                stringForUpdateLogSystem.toString(),
                tempExtId);


        return result;
    }

    @Override
    public Result addHardWareElement(AddNewElementToKE elementToKE, Long id) {

        if (KERepository.findById(id) ==  null)
            throw new NotFoundKeException("KE not found");
        else if(KERepository.findById(id).isEmpty())
            throw new NotFoundKeException("KE not found");


        return null;
    }

    @Override
    public Result addSoftWareElement(AddNewElementToKE elementToKE, Long id) {

        if (KERepository.findById(id) ==  null)
            throw new NotFoundKeException("KE not found");
        else if(KERepository.findById(id).isEmpty())
            throw new NotFoundKeException("KE not found");

        return null;
    }

    @Override
    public Result addLicenseElement(AddNewElementToKE elementToKE, Long id) {

        if (KERepository.findById(id) ==  null)
            throw new NotFoundKeException("KE not found");
        else if(KERepository.findById(id).isEmpty())
            throw new NotFoundKeException("KE not found");

        return null;
    }

    @Override
    public ListConfiguration findAllElementKE() {




        return null;
    }

    @Override
    public ListConfiguration findByOwnerId(Long id) {

        ListConfiguration listConfiguration = new ListConfiguration();
        List<ConfigurationElement> listKE = new ArrayList<>();

        List<com.itsm.userservicemanagment.entity.ke.ConfigurationElement> KEByGroupOwer = KERepository.findByOwnerId(id);

        listConfiguration.setTotal(KEByGroupOwer.size());

        for (com.itsm.userservicemanagment.entity.ke.ConfigurationElement KE: KEByGroupOwer)
            listKE.add(getDTOOutgoing(KE));


        listConfiguration.setListKE(listKE);

        return listConfiguration;
    }

    @Override
    public ListConfiguration findByOwnerGroupId(Long id) {

        ListConfiguration listConfiguration = new ListConfiguration();
        List<ConfigurationElement> listKE = new ArrayList<>();

        List<com.itsm.userservicemanagment.entity.ke.ConfigurationElement> KEByGroupOwer = KERepository.findByOwnerGroupId(id);

        listConfiguration.setTotal(KEByGroupOwer.size());

        for (com.itsm.userservicemanagment.entity.ke.ConfigurationElement KE: KEByGroupOwer)
            listKE.add(getDTOOutgoing(KE));


        listConfiguration.setListKE(listKE);

        return listConfiguration;
    }

    @Override
    public ListConfiguration findByManufactured(String manufactured) {
        ListConfiguration listConfiguration = new ListConfiguration();
        List<ConfigurationElement> listKE = new ArrayList<>();

        List<com.itsm.userservicemanagment.entity.ke.ConfigurationElement> KEByGroupOwer = KERepository.findByManufactured(manufactured);

        listConfiguration.setTotal(KEByGroupOwer.size());

        for (com.itsm.userservicemanagment.entity.ke.ConfigurationElement KE: KEByGroupOwer)
            listKE.add(getDTOOutgoing(KE));


        listConfiguration.setListKE(listKE);

        return listConfiguration;
    }

    @Override
    public ListConfiguration findByStatus(Long code) {

        String status;

        if (statusRepository.findByCode(code) == null)
            throw new NotFoundStatusException("Status not found!");
        else
            status = statusRepository.findByCode(code).getName();



        ListConfiguration listConfiguration = new ListConfiguration();
        List<ConfigurationElement> listKE = new ArrayList<>();

        List<com.itsm.userservicemanagment.entity.ke.ConfigurationElement> KEByGroupOwer = KERepository.findByStatus(status);

        listConfiguration.setTotal(KEByGroupOwer.size());

        for (com.itsm.userservicemanagment.entity.ke.ConfigurationElement KE: KEByGroupOwer)
            listKE.add(getDTOOutgoing(KE));


        listConfiguration.setListKE(listKE);

        return listConfiguration;
    }

    @Override
    public ListConfiguration findByPrice(int price) {
        return null;
    }

    @Override
    public ListConfiguration findByPrefix(String prefix) {
        ListConfiguration listConfiguration = new ListConfiguration();
        List<ConfigurationElement> listKE = new ArrayList<>();

        List<com.itsm.userservicemanagment.entity.ke.ConfigurationElement> KEByGroupOwer = KERepository.findByPrefix(prefix);

        listConfiguration.setTotal(KEByGroupOwer.size());

        for (com.itsm.userservicemanagment.entity.ke.ConfigurationElement KE: KEByGroupOwer)
            listKE.add(getDTOOutgoing(KE));


        listConfiguration.setListKE(listKE);

        return listConfiguration;
    }

    @Override
    public ConfigurationElement findById(Long id) {

        if (KERepository.findById(id) ==  null) {
            throw new NotFoundKeException("KE not found");
        }
        else if(KERepository.findById(id).isEmpty()) {
            throw new NotFoundKeException("KE not found");
        }
        com.itsm.userservicemanagment.entity.ke.ConfigurationElement KE = KERepository.findById(id).get();

        return getDTOOutgoing(KE);
    }

    private ConfigurationElement getDTOOutgoing(com.itsm.userservicemanagment.entity.ke.ConfigurationElement KE){

        ConfigurationElement configurationElement  = new ConfigurationElement();

        configurationElement.setId(KE.getId());
        configurationElement.setConfigurationName(KE.getConfigurationName());
        configurationElement.setDescription(KE.getDescription());
        configurationElement.setVersion(KE.getVersion());
        configurationElement.setPrefix(KE.getPrefix());
        configurationElement.setOwnerLogin(KE.getOwner().getLogin());
        configurationElement.setOwnerFullName(KE.getOwner().getFullName());
        configurationElement.setOwnerUserId(KE.getOwner().getId());
        configurationElement.setOwnerGroupName(KE.getOwnerGroup().getName());
        configurationElement.setOwnerGroupId(KE.getOwnerGroup().getId());
        configurationElement.setAddress(KE.getAddress());
        configurationElement.setManufactured(KE.getManufactured());
        configurationElement.setLicenseNumber(KE.getLicenseNumber());
        configurationElement.setSerialNumber(KE.getSerialNumber());
        configurationElement.setKontur(KE.getKontur());
        configurationElement.setLogical(KE.getLogical());
        configurationElement.setStatus(KE.getStatus());
        configurationElement.setPrice(KE.getPrice());
        configurationElement.setAssurance(KE.getAssurance());
        configurationElement.setCreateDate(KE.getCreateDate());
        configurationElement.setLastModifyDate(KE.getLastModifyDate());
        configurationElement.setStartDate(KE.getStartDate());
        configurationElement.setEndDate(KE.getEndDate());
        configurationElement.setModifyByLogin(KE.getModifyBy().getLogin());
        configurationElement.setSoftWare(KE.getSoftWare());
        configurationElement.setHardWare(KE.getHardWare());
        configurationElement.setLicenseWare(KE.getLicenseWare());
        configurationElement.setInformationSystem(KE.getInformationSystem());

        List<ChildConfigElement> childConfigElementList = new ArrayList<>();

        // Приводим лонг к инт для того чтобы можно было передать в запрос SQL
        int tempId = (int) Long.parseLong(KE.getId().toString());
        List<ConfigurationElementElementKE> childElementKe = KEEntityChildRepository.findByKekeId(KE.getId());

        List<ElementKE> chdElementKE = new ArrayList<>();

        for (ConfigurationElementElementKE cee:  childElementKe){
            chdElementKE.add(cee.getElementKE());
        }

        for (ElementKE eke: chdElementKE){

            ChildConfigElement childConfigElement = new ChildConfigElement();
            childConfigElement.setId(eke.getId());
            childConfigElement.setName(eke.getName());
            childConfigElement.setDescription(eke.getDescription());
            childConfigElement.setVersion(eke.getVersion());
            childConfigElement.setOwnerLogin(eke.getOwner().getLogin());
            childConfigElement.setOwnerFullName(eke.getOwner().getFullName());
            childConfigElement.setOwnerUserId(eke.getOwner().getId());
            childConfigElement.setOwnerGroupName(eke.getOwnerGroup().getName());
            childConfigElement.setOwnerGroupId(eke.getOwnerGroup().getId());
            childConfigElement.setAddress(eke.getAddress());
            childConfigElement.setManufactured(eke.getManufactured());
            childConfigElement.setLicenseNumber(eke.getLicenseNumber());
            childConfigElement.setSerialNumber(eke.getSerialNumber());
            childConfigElement.setKontur(eke.getKontur());
            childConfigElement.setLogical(eke.getLogical());
            childConfigElement.setSubStatus(eke.getSubStatus());
            childConfigElement.setPrice(eke.getPrice());
            childConfigElement.setCreateDate(eke.getCreateDate());
            childConfigElement.setLastModifyDate(eke.getLastModifyDate());
            childConfigElement.setModifyByLogin(eke.getModifyBy().getLogin());
            childConfigElement.setSoftWare(eke.getSoftWare());
            childConfigElement.setHardWare(eke.getHardWare());
            childConfigElement.setLicenseWare(eke.getLicenseWare());
            childConfigElement.setInformationSystem(eke.getInformationSystem());

            childConfigElementList.add(childConfigElement);
        }

        configurationElement.setChildKEList(childConfigElementList);

        String tempExtId = configurationElement.getPrefix()+"-"+configurationElement.getId();
        configurationElement.setEventList(logService.getEventsByExternalIdEntity(tempExtId));


        return configurationElement;

    }
}
