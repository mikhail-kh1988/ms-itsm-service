package com.itsm.userservicemanagment.repository;

import com.itsm.userservicemanagment.entity.incident.ServiceDateManagement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceDateManagementRepository extends CrudRepository<ServiceDateManagement, Long> {

}
