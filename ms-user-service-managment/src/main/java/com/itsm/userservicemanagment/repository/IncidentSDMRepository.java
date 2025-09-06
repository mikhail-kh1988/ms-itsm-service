package com.itsm.userservicemanagment.repository;

import com.itsm.userservicemanagment.entity.incident.IncidentServiceDateManagement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidentSDMRepository extends CrudRepository<IncidentServiceDateManagement, Long> {

    IncidentServiceDateManagement findByIncidentId(Long id);

}
