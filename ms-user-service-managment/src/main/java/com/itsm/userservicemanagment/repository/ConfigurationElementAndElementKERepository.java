package com.itsm.userservicemanagment.repository;

import com.itsm.userservicemanagment.entity.ke.ConfigurationElementElementKE;
import com.itsm.userservicemanagment.entity.ke.ElementKE;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConfigurationElementAndElementKERepository extends CrudRepository<ConfigurationElementElementKE, Long> {



}
