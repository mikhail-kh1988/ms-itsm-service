package com.itsm.userservicemanagment.repository;

import com.itsm.userservicemanagment.entity.ke.LicenseWare;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicenseWareRepository extends CrudRepository<LicenseWare, Long> {
}
