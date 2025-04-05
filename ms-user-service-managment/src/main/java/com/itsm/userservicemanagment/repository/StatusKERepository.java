package com.itsm.userservicemanagment.repository;

import com.itsm.userservicemanagment.entity.ke.StatusKE;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusKERepository extends CrudRepository<StatusKE, Long> {

    StatusKE findByCode(Long code);
}
