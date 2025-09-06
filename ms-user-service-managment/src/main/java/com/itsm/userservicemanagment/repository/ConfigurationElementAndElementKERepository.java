package com.itsm.userservicemanagment.repository;

import com.itsm.userservicemanagment.entity.ke.ConfigurationElementElementKE;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConfigurationElementAndElementKERepository extends CrudRepository<ConfigurationElementElementKE, Long> {

    //@Query(value = "select ke from configuration_element_and_element_ke ke where ke_id = ?1")
    //List<ConfigurationElementElementKE> findKE(Long id);


    List<ConfigurationElementElementKE> findByKekeId(Long id);


}
