package com.itsm.userservicemanagment.repository;

import com.itsm.userservicemanagment.entity.Role;
import com.itsm.userservicemanagment.entity.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, Long> {

    List<Role> findByUser(Long userId);
}
