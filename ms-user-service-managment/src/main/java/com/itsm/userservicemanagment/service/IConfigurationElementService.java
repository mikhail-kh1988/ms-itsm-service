package com.itsm.userservicemanagment.service;


import com.itsm.userservicemanagment.dto.incoming.KE.AddNewElementToKE;
import com.itsm.userservicemanagment.dto.incoming.KE.ChangeOwners;
import com.itsm.userservicemanagment.dto.incoming.KE.NewConfigurationElement;
import com.itsm.userservicemanagment.dto.incoming.KE.UpdateConfigurationElement;
import com.itsm.userservicemanagment.dto.outgoing.KE.ConfigurationElement;
import com.itsm.userservicemanagment.dto.outgoing.KE.ListConfiguration;
import com.itsm.userservicemanagment.dto.outgoing.Result;

public interface IConfigurationElementService {
    Result createNewConfigurationElement(NewConfigurationElement newKE);
    Result modifyStatusConfigurationElement(Long code, Long id);
    Result modifyOwnersConfigurationElement(ChangeOwners changeOwners, Long id);
    Result modifyConfigurationElement(UpdateConfigurationElement updateKE, Long id);
    Result modifySubElementKE(UpdateConfigurationElement updateSubKe, Long id);
    Result addSubElementKE(NewConfigurationElement newKE, Long id);
    Result addHardWareElement(AddNewElementToKE elementToKE, Long id);
    Result addSoftWareElement(AddNewElementToKE elementToKE, Long id);
    Result addLicenseElement(AddNewElementToKE elementToKE, Long id);
    ListConfiguration findAllElementKE();
    ListConfiguration findByOwnerId(Long id);
    ListConfiguration findByOwnerGroupId(Long id);
    ListConfiguration findByManufactured(String manufactured);
    ListConfiguration findByStatus(Integer code);
    ListConfiguration findByPrice(int price);
    ListConfiguration findByPrefix(String prefix);
    ConfigurationElement findById(Long id);
}