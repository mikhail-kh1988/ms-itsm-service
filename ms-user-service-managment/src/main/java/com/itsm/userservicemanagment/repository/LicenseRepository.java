package com.itsm.userservicemanagment.repository;

import com.itsm.userservicemanagment.entity.license.License;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicenseRepository extends CrudRepository<Long, License> {
}
