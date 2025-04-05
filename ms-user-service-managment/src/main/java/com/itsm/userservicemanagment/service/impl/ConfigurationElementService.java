package com.itsm.userservicemanagment.service.impl;

import com.itsm.userservicemanagment.Exception.*;
import com.itsm.userservicemanagment.dto.incoming.KE.AddNewElementToKE;
import com.itsm.userservicemanagment.dto.incoming.KE.ChangeOwners;
import com.itsm.userservicemanagment.dto.incoming.KE.NewConfigurationElement;
import com.itsm.userservicemanagment.dto.incoming.KE.UpdateConfigurationElement;
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

        return result;
    }

    @Override
    public Result modifyStatusConfigurationElement(Long code, Long id) {

        Result result = new Result();

        if (KERepository.findById(id) ==  null)
            throw new NotFoundKeException("KE not found");
        else if(KERepository.findById(id).isEmpty())
            throw new NotFoundKeException("KE not found");

        if (statusRepository.findByCode(code) == null)
            throw new NotFoundStatusException("Status not found!");


        com.itsm.userservicemanagment.entity.ke.ConfigurationElement configurationElement = KERepository.findById(id).get();

        configurationElement.setStatus(statusRepository.findByCode(code).getName());

        KERepository.save(configurationElement);

        result.setDate(LocalDateTime.now());
        result.setMessage("Status ["+configurationElement.getStatus()+"] has been set.");
        return result;
    }

    @Override
    public Result modifyOwnersConfigurationElement(ChangeOwners changeOwners, Long id) {
        if (KERepository.findById(id) ==  null)
            throw new NotFoundKeException("KE not found");
        else if(KERepository.findById(id).isEmpty())
            throw new NotFoundKeException("KE not found");

        com.itsm.userservicemanagment.entity.ke.ConfigurationElement configurationElement = KERepository.findById(id).get();
        Result result = new Result();

        // Устанавливаем владельца КЕ
        if (changeOwners.getNewOwnerId() != null) {
            if (userRepository.findById(changeOwners.getNewOwnerId()).isPresent())
                configurationElement.setOwner(userRepository.findById(changeOwners.getNewOwnerId()).get());
            else
                throw new NotFoundUserExcption("User not found!");
        }

        // Устанавливаем группу владельца КЕ
        if (changeOwners.getNewOwnerGroupId() != null) {
            if (groupRepository.findById(changeOwners.getNewOwnerGroupId()).isPresent()
                    && groupRepository.findById(changeOwners.getNewOwnerGroupId()).get().isAssignee())
                configurationElement.setOwnerGroup(groupRepository.findById(changeOwners.getNewOwnerGroupId()).get());
            else
                throw new NotFoundGroupException("Group not found!");
        }

        KERepository.save(configurationElement);

        result.setDate(LocalDateTime.now());
        result.setMessage("New owner is set!");

        return result;
    }

    @Override
    public Result modifyConfigurationElement(UpdateConfigurationElement updateKE, Long id) {

        if (KERepository.findById(id) ==  null)
            throw new NotFoundKeException("KE not found");
        else if(KERepository.findById(id).isEmpty())
            throw new NotFoundKeException("KE not found");

        com.itsm.userservicemanagment.entity.ke.ConfigurationElement configurationElement = KERepository.findById(id).get();

        if (updateKE.getConfigurationName() != null)
            configurationElement.setConfigurationName(updateKE.getConfigurationName());
        if (updateKE.getDescription() != null)
            configurationElement.setDescription(updateKE.getDescription());
        if (updateKE.getVersion() != null)
            configurationElement.setVersion(updateKE.getVersion());
        if (updateKE.getPrefix() != null)
            configurationElement.setPrefix(updateKE.getPrefix());
        if (updateKE.getAddress() != null)
            configurationElement.setAddress(updateKE.getAddress());
        if (updateKE.getManufactured() != null)
            configurationElement.setManufactured(updateKE.getManufactured());
        if (updateKE.getLicenseNumber() != null)
            configurationElement.setLicenseNumber(updateKE.getLicenseNumber());
        if (updateKE.getSerialNumber() != null)
            configurationElement.setSerialNumber(updateKE.getSerialNumber());
        if (updateKE.getRealiseNumber() != null)
            configurationElement.setRealiseNumber(updateKE.getRealiseNumber());
        if (updateKE.getPrice() != null)
            configurationElement.setPrice(updateKE.getPrice());
        if (updateKE.getLogical() != null)
            configurationElement.setLogical(updateKE.getLogical());

        configurationElement.setModifyBy(userRepository.findById(updateKE.getModifyById()).get());
        KERepository.save(configurationElement);

        Result result = new Result();
        result.setDate(LocalDateTime.now());
        result.setMessage("KE ["+configurationElement.getConfigurationName()+"] is updated.");


        return result;
    }

    @Override
    public Result modifySubElementKE(UpdateConfigurationElement updateSubKe, Long id) {

        if (KERepository.findById(id) ==  null)
            throw new NotFoundKeException("KE not found");
        else if(KERepository.findById(id).isEmpty())
            throw new NotFoundKeException("KE not found");


        return null;
    }

    @Override
    public Result addSubElementKE(NewConfigurationElement newKE, Long id) {

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

        if (newKE.getConfigurationElementName().isEmpty())
            throw new NotFoundNameConfigurationElement("Name KE is empty!");


        // Устанавливаем кто последним изменил.
        if (newKE.getCreateById() != null) {
            if (userRepository.findById(newKE.getCreateById()).isPresent())
                elementKE.setModifyBy(userRepository.findById(newKE.getCreateById()).get());
            else
                throw new NotFoundUserExcption("User not found!");
        }

        // Устанавливаем владельца КЕ
        if (newKE.getUserOwnerId() != null) {
            if (userRepository.findById(newKE.getUserOwnerId()).isPresent())
                elementKE.setOwner(userRepository.findById(newKE.getUserOwnerId()).get());
            else
                throw new NotFoundUserExcption("User not found!");
        }

        // Устанавливаем группу владельца КЕ
        if (newKE.getGroupOwnerId() != null) {
            if (groupRepository.findById(newKE.getGroupOwnerId()).isPresent()
                    && groupRepository.findById(newKE.getGroupOwnerId()).get().isAssignee())
                elementKE.setOwnerGroup(groupRepository.findById(newKE.getGroupOwnerId()).get());
            else
                throw new NotFoundGroupException("Group not found!");
        }

        elementKE.setName(newKE.getConfigurationElementName());
        elementKE.setLogical(newKE.isLogical());
        elementKE.setSubStatus(statusRepository.findByCode(100L).getName());


        // Сохраняем дочерний эелемент
        childRepository.save(elementKE);


        // Делаем связку между эелементами.
        configurationElementElementKE.setKe(configurationElement);
        configurationElementElementKE.setElementKE(elementKE);
        configurationElementElementKE.setCreateDate(LocalDateTime.now());
        configurationElementElementKE.setLastChangeDate(LocalDateTime.now());
        configurationElementElementKE.setCreateBy(userRepository.findById(newKE.getCreateById()).get());


        KEEntityChildRepository.save(configurationElementElementKE);

        result.setDate(LocalDateTime.now());
        result.setMessage("Element ["+elementKE.getName()+"] has been connected with KE ["+configurationElement.getConfigurationName()+"]. ");

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

        return null;
    }

    @Override
    public ListConfiguration findByOwnerGroupId(Long id) {
        return null;
    }

    @Override
    public ListConfiguration findByManufactured(String manufactured) {
        return null;
    }

    @Override
    public ListConfiguration findByStatus(Integer code) {
        return null;
    }

    @Override
    public ListConfiguration findByPrice(int price) {
        return null;
    }

    @Override
    public ListConfiguration findByPrefix(String prefix) {
        return null;
    }

    @Override
    public ConfigurationElement findById(Long id) {

        ConfigurationElement configurationElement  = new ConfigurationElement();

        if (KERepository.findById(id) ==  null)
            throw new NotFoundKeException("KE not found");
        else if(KERepository.findById(id).isEmpty())
            throw new NotFoundKeException("KE not found");






        return null;
    }
}
