package com.itsm.userservicemanagment.repository;

import com.itsm.userservicemanagment.entity.ke.Assurance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssuranceRepository extends CrudRepository<Assurance, Long> {

}
