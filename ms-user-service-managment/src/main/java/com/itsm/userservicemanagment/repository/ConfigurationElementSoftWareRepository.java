package com.itsm.userservicemanagment.repository;

import com.itsm.userservicemanagment.entity.ke.ConfigurationElementSoftWare;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigurationElementSoftWareRepository extends CrudRepository<ConfigurationElementSoftWare, Long> {

}
