package com.itsm.userservicemanagment.repository;

import com.itsm.userservicemanagment.entity.ke.SoftWare;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoftWareRepository extends CrudRepository<SoftWare, Long> {
}
