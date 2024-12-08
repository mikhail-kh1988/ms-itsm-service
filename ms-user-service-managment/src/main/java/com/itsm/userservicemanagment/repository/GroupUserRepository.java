package com.itsm.userservicemanagment.repository;

import com.itsm.userservicemanagment.entity.Group;
import com.itsm.userservicemanagment.entity.GroupUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupUserRepository extends CrudRepository<GroupUser, Long> {

    List<Group> findByUserId(Long id);
}
