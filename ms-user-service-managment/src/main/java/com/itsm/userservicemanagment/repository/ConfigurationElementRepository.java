package com.itsm.userservicemanagment.repository;

import com.itsm.userservicemanagment.entity.ke.ConfigurationElement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigurationElementRepository extends CrudRepository<ConfigurationElement, Long> {
}
