package com.itsm.userservicemanagment.repository;

import com.itsm.userservicemanagment.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByTechAccountTrue();
    List<User> findByActiveTrue();
    List<User> findByDeletedTrue();
    List<User> findByActiveFalse();


}
