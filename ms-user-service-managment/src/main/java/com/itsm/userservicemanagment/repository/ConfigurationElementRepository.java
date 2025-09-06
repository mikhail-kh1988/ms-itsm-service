package com.itsm.userservicemanagment.repository;

import com.itsm.userservicemanagment.entity.ke.ConfigurationElement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConfigurationElementRepository extends CrudRepository<ConfigurationElement, Long> {

    List<ConfigurationElement> findByOwnerGroupId(Long id);
    List<ConfigurationElement> findByOwnerId(Long id);
    List<ConfigurationElement> findByManufactured(String name);
    List<ConfigurationElement> findByPrefix(String prefix);
    List<ConfigurationElement> findByStatus(String status);

}
