package com.itsm.userservicemanagment.repository;

import com.itsm.userservicemanagment.entity.incident.IncidentStatusReason;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface IncidentStatusReasonRepository extends CrudRepository<IncidentStatusReason, Long> {

    IncidentStatusReason findByCode(Integer code);

}
