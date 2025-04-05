package com.itsm.userservicemanagment.repository;

import com.itsm.userservicemanagment.entity.ke.ElementKE;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigurationElementChildRepository extends CrudRepository<ElementKE, Long> {

}
