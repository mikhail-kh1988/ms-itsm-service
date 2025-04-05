package com.itsm.userservicemanagment.repository;

import com.itsm.userservicemanagment.entity.ke.HardWare;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HardWareRepository extends CrudRepository<HardWare, Long> {
}
